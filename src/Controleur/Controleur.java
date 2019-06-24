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
    public boolean piocher() throws IOException{
        if(!aPiocher){
            aPiocher = true;
            for (int i = 0; i < 2; i++) {
                CarteJoueur carte = getDeckTresor().Piocher();
                if(carte instanceof CarteMonteeEau){
                    MonteeDesEaux(carte);
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
        if(!interruption){
            CheckNbCarte(getAvTrActuel());
        }
        return !interruption;
    }
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
    
    
    private void debutTour(){
        aPiocher = false;
        setTrAv();
        getAvTrActuel().DebutTour();
        jeu.debutTour(AvTrActuel);
    }

    public Aventurier getAvTrActuel(){
        return AvTrActuel;
    }
    
    
    public void MonteeDesEaux(CarteJoueur c){
        grille.MonterNiveauDeau();
        deckInondation.ResetPioche();
        getDeckTresor().Defausser(c);
        jeu.getMonteeDesEau().addNiveau();
    }
    public void checkFinTour() throws IOException{
        if(getAvTrActuel().getActionsRestantes() <= 0 ){
            finTour();
        }
    }

    
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
            jeu.faireChoixCarte(VueJeu.DEFAUSSER, av.getCartes(),av);
        }
    }
    
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
        CarteJoueur carte;
        try {
            
            Tuile t;
            switch(msg.typeact){
                case DEPLACER:
                    if(getAvTrActuel().getDeplacement(grille).size()>0){
                        jeu.faireChoixTuile(jeu.getCHOIX_DEP(), getAvTrActuel().getDeplacement(grille));
                    }
                    else{
                        jeu.erreur_deplacer();
                    }
                    break;
                case ASSECHER:
                    if (getAvTrActuel().getAssechement(grille).size()>0){
                        jeu.faireChoixTuile(jeu.getCHOIX_AS(),getAvTrActuel().getAssechement(grille));
                    }
                    else {
                        jeu.erreur_assecher();
                    }
                    break;
                case TERMINER_TOUR:
                {
                    finTour();
                }
                break;
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
                case DONNERCARTE:
                    if(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()).size()>0){
                        jeu.faireChoixAventurier(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()), VueJeu.DON_CARTE);
                    }
                    else{
                        jeu.erreur_choixAventurier();
                    }
                    break;
                case CHOIX_INTER_DONCARTE:
                    if(getAvTrActuel().getCartesDonnables().size()>0){
                        jeu.faireChoixCarte(VueJeu.DON_CARTE, getAvTrActuel().getCartesDonnables(),null);
                        
                    }
                    else{
                        jeu.erreur_DonCarte();
                    }
                    break;
                    
                case CHOIX_TUILE_DEP:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().deplacer(t);
                    jeu.deplacePion(getAvTrActuel().getPion(), t);
                    jeu.actualise();
                    jeu.indic_Passif(this.getAvTrActuel());
                    checkFinTour();
                    break;
                case CHOIX_TUILE_AS:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().assecher(t);
                    jeu.changeEtat(t.getEtat(),t);
                    jeu.actualise();
                    jeu.indic_Passif(getAvTrActuel());
                    checkFinTour();
                    break;
                case CHOIX_DONCARTE:
                    Aventurier receveur = jeu.translate_VueAv_Av(msg.vueAv.get(0), getMesAventuriers());
                    carte = jeu.translate_VueCa_Ca(msg.vueCarte, getAvTrActuel().getCartesDonnables());
                    
                    getAvTrActuel().DonnerCarte(carte, receveur);
                    jeu.ajoutCarte(receveur, carte);
                    jeu.supprimeCarte(getAvTrActuel(), carte);
                    
                    
                    CheckNbCarte(receveur);
                    checkFinTour();
                    break;
                case CHOIX_DEFAUSSER:
                    if(!msg.vueAv.isEmpty() && msg.vueCarte != null){
                        Aventurier av = jeu.translate_VueAv_Av(msg.vueAv.get(0), getMesAventuriers());
                        carte = jeu.translate_VueCa_Ca(msg.vueCarte, av.getCartes());


                        av.SupprimerCarte(carte);
                        getDeckTresor().Defausser(carte);
                        jeu.supprimeCarte(av, carte);
                        CheckNbCarte(av);
                    }
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



