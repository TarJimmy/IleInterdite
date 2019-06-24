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
    private ArrayList<VuePion> mesPions;
    private boolean aPiocher;
    //Cree et affiche l'accueil
    Controleur() throws IOException{
        accueil = new VueAccueil();
        accueil.addObservateur(this);
        accueil.afficher(true);
        index = 0;
        
    }
    
    public HashSet<Utils.tresor> getTresorsRecuperers() {
        return Aventurier.getTresorsRecuperer();
    }
    
    public ArrayList<Aventurier> getMesAventuriers() {
        return mesAventuriers;
    }
    //Actualise le tour de l'aventurier en fonction de l'index
    private void setTrAv() {
        this.AvTrActuel = getMesAventuriers().get(index);
        jeu.setTrActuel(getAvTrActuel());
    }
    private void addIndex(){
        index = (index+1)%getMesAventuriers().size();
    }
    //Permet de piocher 2 cartes
    public boolean piocher () throws IOException{
        if(!aPiocher){
            aPiocher = true;
            for (int i = 0; i < 2; i++) {
                CarteJoueur carte = getDeckTresor().Piocher();
                if(carte instanceof CarteMonteeEau){
                    MonteeDesEaux(carte);
                    System.out.println("Niveau d'eau : "+grille.getEchelonMonteEau());
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
        //Fais un déplacmeent forcé si un aventurier est sur une tuile qui coule
        boolean interruption = false;
        Iterator it = getMesAventuriers().iterator();
        
        while(it.hasNext() && !interruption){
            Aventurier av = (Aventurier) it.next();
                if(!av.getMaPos().estDisponible()){
                    if(av.getDeplacement(grille).size() > 0){
                        interruption = true;
                        AvTrActuel = av;
                        if(AvTrActuel instanceof Pilote){
                            ((Pilote) AvTrActuel).setaVoler(false);
                        }
                        getAvTrActuel().setActionsRestantes(0);
                        jeu.deplacementforce(AvTrActuel.getDeplacement(grille));
                    }else{
                        jeu.PartiePerdu();
                    }
                }
            }
        //Permet d'éviter 2 interruption d'affiler
        if(!interruption){
            CheckNbCarte(getAvTrActuel());
        }
        return !interruption;
    }
    //Verfifie que la partie est gagné
    public boolean partieGagne(){
        boolean gagne = true;
        gagne = getTresorsRecuperers().size() == Utils.tresor.values().length;
        if(gagne){
            boolean helicoptere = false;
            for(Aventurier av : getMesAventuriers()){
                gagne = gagne && av.getMaPos().getNom().equals(Utils.TuilesUtils.heliport);
                if(!helicoptere){
                    for(CarteJoueur c : av.getCartesSpecial()){
                        helicoptere = helicoptere || c instanceof Helicoptere;
                    }
                }
            }
            gagne = gagne && helicoptere;
        }
        return gagne;
    }
    //Verifie que la partie est perdu
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
    
    //Change d'aventurier actuel et fais un début de tour pour le modele et la vue
    private void debutTour(){
        aPiocher = false;
        setTrAv();
        getAvTrActuel().DebutTour();
        jeu.debutTour(AvTrActuel);
    }

    public Aventurier getAvTrActuel(){
        return AvTrActuel;
    }
    
    //Fais une montée des eaux pur la vue et pour le modèle
    public void MonteeDesEaux(CarteJoueur c){
        grille.MonterNiveauDeau();
        deckInondation.ResetPioche();
        getDeckTresor().Defausser(c);
        jeu.getMonteeDesEau().addNiveau();
    }
    public void faireDefausser(Aventurier av,int nbCartes){
            //defausser des cartes via l'ihm
            //Defausser(int nbCartes) avec nbCartes = getAvTrActuel().nbCarte()
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //Verifie que l'aventurier actuel peut encore faire des actions
    public void checkFinTour() throws IOException{
        if(getAvTrActuel().getActionsRestantes() <= 0 ){
            finTour();
        }
    }

    //Fais la fin d'un tour
    public void finTour() throws IOException{
        if(partieGagne()){
            jeu.PartieGagner();
        }else{
            if(piocher() && !partiePerdu()){
                addIndex();
                debutTour();
            }
        }
    }
    public void CheckNbCarte(Aventurier av){
        if( av.nbCarte() > 5){
            //faireDefausser(av, av.nbCarte()-5);
        }
    }
    //Crée les aventuriers
    protected void creationAventurier(int nbAventuriers){
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
            //Initialise le jeu en fonction du message de l'accueil
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
    //Methode réservé pour la première pioche
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
                //Action déclencher si le joueur clique sur le bouton déplacer
                case DEPLACER:
                    if(getAvTrActuel().getDeplacement(grille).size()>0){
                        jeu.faireChoixTuile(jeu.getCHOIX_DEP(), getAvTrActuel().getDeplacement(grille));
                    }
                    else{
                        jeu.erreur_deplacer();
                    }
                    break;
                    //Action déclencher si le joueur clique sur le bouton assecher
                case ASSECHER:
                    if (getAvTrActuel().getAssechement(grille).size()>0){
                        jeu.faireChoixTuile(jeu.getCHOIX_AS(),getAvTrActuel().getAssechement(grille));
                    }
                    else {
                        jeu.erreur_assecher();
                    }
                    break;
                    //Action déclencher si le joeuur clique sur le bouton terminerTour
                case TERMINER_TOUR:
                {
                    finTour();
                }
                break;
                //Action déclencher si le joueur clique sur le bouton GagnerTresor
                case GAGNERTRESOR:
                    if (getAvTrActuel().checkGagnerTresor()!=null){
                        Utils.tresor tres = getAvTrActuel().checkGagnerTresor();
                        ArrayList<CarteJoueur> cartes = getAvTrActuel().GagnerTresor(tres);
                        jeu.gainTresor(tres);
                        for(CarteJoueur ca : cartes){
                            deckTresor.Defausser(ca);
                            jeu.supprimeCarte(getAvTrActuel(), ca);
                        }
                        jeu.indic_Passif(getAvTrActuel());
                    }
                    else{
                        jeu.erreurTresor();
                    }
                    checkFinTour();
                    break;
                    //Action déclencher si le joueur clique sur le bouton Donner Carte
                case DONNERCARTE:
                    if(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()).size()>0){
                        jeu.faireChoixAventurier(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()), VueJeu.DON_CARTE);
                    }
                    else{
                        jeu.erreur_choixAventurier();
                    }
                    break;
                    //Action déclencher si le joueur clique sur son choix d carte
                case CHOIX_INTER_DONCARTE:
                    if(getAvTrActuel().getCartesDonnables().size()>0){
                        jeu.faireChoixCarte(VueJeu.DON_CARTE, getAvTrActuel().getCartesDonnables());
                        
                    }
                    else{
                        jeu.erreur_DonCarte();
                    }
                    break;
                    //Action déclencher si le joueur clique sur le a tuile où il veut se déplacer
                case CHOIX_TUILE_DEP:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().deplacer(t);
                    jeu.finDeplacement(getAvTrActuel(), t);
                    jeu.actualise();
                    jeu.indic_Passif(this.getAvTrActuel());
                    checkFinTour();
                    break;
                    //Action déclencher si le joueur clique sur la tuile qu'il veut assecher
                case CHOIX_TUILE_AS:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().assecher(t);
                    jeu.changeEtat(t.getEtat(),t);
                    jeu.actualise();
                    jeu.indic_Passif(getAvTrActuel());
                    checkFinTour();
                    break;
                    //Action déclencher si le joueur clique sur la carte qu'il veut donner
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

    public VueJeu getJeu() {
        return jeu;
    }


    
}



