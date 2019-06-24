/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;


import Controleur.Utils.EtatTuile;
import Controleur.Utils.tresor;
import Modele.*;
import Vue.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tardyj
 */
public class Controleur implements Observateur {
    private final VueAccueil accueil;
    private VueJeu jeu;
    private ArrayList<Aventurier> mesAventuriers;
    private Grille grille;
    private DeckInondation deckInondation;
    private DeckTresor deckTresor;
    private Aventurier AvTrActuel;
    private int index;
    private boolean aPiocher;
    //Création du controleur
    Controleur() throws IOException{
        accueil = new VueAccueil();
        accueil.addObservateur(this);
        accueil.afficher(true);
        index = 0;
        
    }
    //Liste des trésors récupéré
    public HashSet<Utils.tresor> getTresorsRecuperers() {
        return Aventurier.getTresorsRecuperer();
    }
    //Renvoie les Aventuriers de la partie en cours
    public ArrayList<Aventurier> getMesAventuriers() {
        return mesAventuriers;
    }
    //Actualise le tour de l'aventurier en fonction de l'index
    private void setTrAv() {
        this.AvTrActuel = getMesAventuriers().get(index);
        jeu.setTrActuel(getAvTrActuel());
    }
    //Ajoute l'index
    private void addIndex(){
        index = (index+1)%getMesAventuriers().size();
    }
    //Pioche 2 fois (avec l'aide du boolean) 
    public boolean piocher () throws IOException{
        if(!aPiocher){
            aPiocher = true;
            for (int i = 0; i < 2; i++) {
                CarteJoueur carte = getDeckTresor().Piocher();
                if(carte instanceof CarteMonteeEau){
                    MonteeDesEaux(carte);
                    System.out.println("Montée des eaux actuel :" + grille.getEchelonMonteEau());
                }else{
                    getAvTrActuel().AddCarte(carte);
                    jeu.ajoutCarte(getAvTrActuel(), carte);
                }
            }
            
            //Pioche des cartes inondation
            ArrayList<Tuile> tuiles = getDeckInondation().Inondation(grille.getEchelonMonteEau());
            for(Tuile tui : tuiles){
                jeu.changeEtat(tui.getEtat(), tui);
            }
        }
        //Force le déplacement d'un aventurier si la tuile où il est positionner devient coulée
        boolean interruption = false;
        Iterator it = getMesAventuriers().iterator();
        int i=0;
        while(it.hasNext() && !interruption){
            i++;
            Aventurier av = (Aventurier) it.next();
                if(!av.getMaPos().estDisponible()){
                    if(av.getDeplacement(grille).size() > 0){
                        interruption = true;
                        System.out.println("deplacement forcé");
                        AvTrActuel = av;
                        if(AvTrActuel instanceof Pilote){
                            ((Pilote) AvTrActuel).setaVoler(false);
                        }
                        getAvTrActuel().setActionsRestantes(0);
                        jeu.deplacementforce(AvTrActuel.getDeplacement(grille));
                        System.out.println(i);
                    }else{
                        jeu.PartiePerdu();
                    }
                }
            }
        //Si il y a déjà une interruption, il n'y en a pas une deuxième
        if(!interruption){
            CheckNbCarte(getAvTrActuel());
        }
        return !interruption;
    }
   //Renvoie true si la partie est Gagné
    public boolean partieGagne(){
        boolean gagne = true;
        gagne = getTresorsRecuperers().contains(Utils.tresor.values());
        if(gagne){
            boolean helicoptere = false;
            for(Aventurier av : getMesAventuriers()){
                gagne = gagne && av.getMaPos().getNom().equals(Utils.TuilesUtils.heliport);
                if(!helicoptere){
                    for(CarteJoueur c : av.getCartesSpecial()){
                        helicoptere = helicoptere && c instanceof Helicoptere;
                    }
                }
            }
            gagne = gagne && helicoptere;
        }
        return gagne;
    }
    //Revnoie true si la partie est perdu
    public boolean partiePerdu(){
        boolean perdu = !grille.getTuile(Utils.TuilesUtils.heliport).estDisponible();
        perdu = perdu || grille.getEchelonMonteEau() >= 10;
        if(!perdu){
            HashSet<Utils.tresor> tresors = new HashSet<>();
            for(Tuile tui : grille.getTuilesDisponibles()){
                tresors.add(tui.getTresor());
            }
            perdu = tresors.contains(Utils.tresor.values());
        }
        if(!perdu){
            for(Aventurier av : getMesAventuriers()){
                if(!av.getMaPos().estDisponible()){
                    perdu = av.getDeplacement(grille).size() == 0;
                }
            }
        }
        return perdu;
    }
    
    //Initialise le debut du tour
    private void debutTour(){
        aPiocher = false;
        setTrAv();
        getAvTrActuel().DebutTour();
        jeu.debutTour(AvTrActuel);
    }

    public Aventurier getAvTrActuel(){
        return AvTrActuel;
    }
    
    //Fait une montée des eaux
    public void MonteeDesEaux(CarteJoueur c){
        grille.MonterNiveauDeau();
        deckInondation.ResetPioche();
        getDeckTresor().Defausser(c);
        jeu.getMonteeDesEau().addNiveau();
    }
    public void faireDefausser(Aventurier av,int nbCartes){
            //defausser des cartes via l'ihm
            //Defausser(int nbCartes) avec nbCartes = getAvTrActuel().nbCarte()
        
    }
    //Vérifie que 'aventurier actuel possède encore 1 action possible
    public void checkFinTour() throws IOException{
        if(getAvTrActuel().getActionsRestantes() <= 0 ){
            finTour();
        }
    }

    //Fait les action nécessaire pour la fin d'un tour
    public void finTour() throws IOException{
        if(!partieGagne()){
            if(piocher() && !partiePerdu()){
                addIndex();
                debutTour();
            }
        }else{
            jeu.PartieGagner();
        }
    }
    //Force le defaussage si l'aventurier possède plus de 5 carte
    public void CheckNbCarte(Aventurier av){
        if( av.nbCarte() > 5){
            //faireDefausser(av, av.nbCarte()-5);
        }
    }
    //Initialise les aventurier de manière aléatoire
    private void creationAventurier(int nbAventuriers){
        mesAventuriers = new ArrayList<>();
        mesAventuriers.add(new Ingenieur(grille));
        mesAventuriers.add(new Messager(grille));
        mesAventuriers.add(new Navigateur(grille));
        mesAventuriers.add(new Explorateur(grille));
        mesAventuriers.add(new Plongeur(grille));
        mesAventuriers.add(new Pilote(grille));
        Utils.melangerAventuriers(mesAventuriers);
        for (int i=0;i<6-nbAventuriers;i++){
            mesAventuriers.remove(mesAventuriers.get(mesAventuriers.size()-1));
        }
    }

    public Grille getGrille() {
        return grille;
    }
    
    /**
     *
     * @param msg
     */
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            //Action pour le debut du jeu
            case DEBUTJEU:
                accueil.afficher(false);
                
                grille= new Grille(msg.difficulte);
                creationAventurier(msg.nbJoueur);
                
                deckInondation= new DeckInondation(getGrille());
                deckTresor = new DeckTresor();
            try {
                jeu = new VueJeu(grille,mesAventuriers,msg.noms,msg.difficulte);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                jeu.addObservateur(this);
                piochesInitial();
                debutTour();
        }
    }
    //Fais la piche du début du jeu
    public void piochesInitial(){
        //Pioche des cartes pour les joueurs
        for(Aventurier av : getMesAventuriers()){
            for (int i = 0; i < 2; i++) {
                CarteJoueur carte = getDeckTresor().Piocher();
            while(carte instanceof CarteMonteeEau){
                getDeckTresor().Defausser(carte);
                carte = getDeckTresor().Piocher();
            }
            try {
                av.AddCarte(carte);
                jeu.ajoutCarte(av, carte);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        getDeckTresor().ResetPioche();
        //Pioche des cartes inondation
        ArrayList<Tuile> tuiles = getDeckInondation().InondationInitial();
        for(Tuile tui : tuiles){
            jeu.changeEtat(tui.getEtat(), tui);
        }
    }

    public DeckInondation getDeckInondation() {
        return deckInondation;
    }

    public DeckTresor getDeckTresor() {
        return deckTresor;
    }
    

    @Override
    public void traiterMessageAction(MessageAction msg) {
        
        try {
            
            Tuile t;
            switch(msg.typeact){
                //Action déclencher si l'utilisateur clique sur le bouton deplacer de la vueJeu
                case DEPLACER:
                    if(getAvTrActuel().getDeplacement(grille).size()>0){
                        jeu.faireChoixTuile(jeu.getCHOIX_DEP(), getAvTrActuel().getDeplacement(grille));
                    }
                    else{
                        jeu.erreur_deplacer();
                    }
                    break;
                    //Action déclencher si l'utilisateur clique sur le bouton assecher de la vueJeu
                case ASSECHER:
                    if (getAvTrActuel().getAssechement(grille).size()>0){
                        jeu.faireChoixTuile(jeu.getCHOIX_AS(),getAvTrActuel().getAssechement(grille));
                    }
                    else {
                        jeu.erreur_assecher();
                    }
                    break;
                    //Action déclencher si l'utilisateur clique sur le bouton TermnerTour de la vueJeu
                case TERMINER_TOUR:
                {
                    try {
                        finTour();
                    } catch (IOException ex) {
                        Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                //Action déclencher si l'utilisateur clique sur le bouton GagnerTresor de la vueJeu
                case GAGNERTRESOR:
                    if (getAvTrActuel().checkGagnerTresor()!=null){
                        Utils.tresor tres = getAvTrActuel().checkGagnerTresor();
                        getAvTrActuel().GagnerTresor(tres);
                        jeu.gainTresor(tres);
                    }
                    else{
                        jeu.erreurTresor();
                    }
                    break;
                case DONNERCARTE:
                    //Action déclencher si l'utilisateur clique sur le bouton Donner carte de la vueJeu
                    //Il active le choix d'un aventurier
                    if(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()).size()>0){
                        jeu.faireChoixAventurier(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()), VueJeu.DON_CARTE);
                    }
                    else{
                        jeu.erreur_choixAventurier();
                    }
                    break;
                    //Action déclencher si l'utilisateur clique sur l'aventurier a qui il veut donner une carte de la vueJeu
                case CHOIX_INTER_DONCARTE:
                    if(getAvTrActuel().getCartesDonnables().size()>0){
                        jeu.faireChoixCarte(VueJeu.DON_CARTE, getAvTrActuel().getCartesDonnables());
                    }
                    else{
                        jeu.erreur_DonCarte();
                    }
                    break;
                    //Action déclencher lorsque l'utilisateur choisie la tuile où il veut se déplacer
                case CHOIX_TUILE_DEP:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().deplacer(t);
                    jeu.finDeplacement(getAvTrActuel(), t);
                    jeu.actualise();
                    jeu.indic_Passif(this.getAvTrActuel());
                    break;
                    //Action déclencher lorsque l'utilisateur choisie la tuile qu'il veut assecher
                case CHOIX_TUILE_AS:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().assecher(t);
                    jeu.changeEtat(t.getEtat(),t);
                    jeu.actualise();
                    jeu.indic_Passif(getAvTrActuel());
                    break;
                    //Action declencher lorsque l'utilisateur a choisie la carte a qui il veut donner
                case CHOIX_DONCARTE:
                    Aventurier receveur = jeu.translate_VueAv_Av(msg.vueAv.get(0), getMesAventuriers());
                    CarteJoueur carte = jeu.translate_VueCa_Ca(msg.vueCarte, getAvTrActuel().getCartesDonnables());
                    
                    getAvTrActuel().DonnerCarte(carte, receveur);
                    jeu.ajoutCarte(receveur, carte);
                    jeu.supprimeCarte(getAvTrActuel(), carte);
                    
                    
                    CheckNbCarte(receveur);
                    checkFinTour();
                    break;
            }
            try {
                checkFinTour();
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    

    
    public static void main(String[]args ) throws IOException{
        new Controleur();
    }


    
}



