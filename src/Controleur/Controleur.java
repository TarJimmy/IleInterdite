/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;


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
    private DeckInnondation deckInnondation;
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
    public void piocher () throws IOException{
        for (int i = 0; i < 2; i++) {
            CarteJoueur carte = deckTresor.Piocher();
            if(carte instanceof CarteMonteeEau){
                MonteeDesEaux(carte);
            }else{
                getAvTrActuel().AddCarte(carte);
                jeu.ajoutCarte(getAvTrActuel(), carte);
            }
        }
        aPiocher = true;
        CheckNbCarte(getAvTrActuel());
    }
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
    public boolean partiePerdu(){
        boolean perdu = !grille.getTuile(Utils.TuilesUtils.heliport).estDisponible();
        perdu = perdu || grille.getEchelonMonteEau() <= 10;
        if(!perdu){
            HashSet<Utils.tresor> tresors = new HashSet<>();
            for(Tuile tui : grille.getTuilesDisponibles()){
                tresors.add(tui.getTresor());
            }
            perdu = tresors.contains(Utils.tresor.values());
        }
        return perdu;
    }
    
    
    private void debutTour(){
        aPiocher = false;
        setTrAv();
        getAvTrActuel().DebutTour();
    }

    public Aventurier getAvTrActuel(){
        return AvTrActuel;
    }
    
    
    public void MonteeDesEaux(CarteJoueur c){
        grille.MonterNiveauDeau();
        deckInnondation.ResetPioche();
        deckTresor.Defausser(c);
        //actualiser dans ihm
    }
    public void faireDefausser(Aventurier av,int nbCartes){
            //defausser des cartes via l'ihm
            //Defausser(int nbCartes) avec nbCartes = getAvTrActuel().nbCarte()
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void checkFinTour() throws IOException{
        if(getAvTrActuel().getActionsRestantes() <= 0 ){
            if(getAvTrActuel() instanceof Ingenieur && ((Ingenieur) getAvTrActuel()).aAssecher()){
                //ihm fonction uniquement assecher
               // VueJeu.IngenieurAssecherFT();
            }
            finTour();
        }
    }
    
    
    public void finTour() throws IOException{
        if(!partieGagne()){
            piocher();
            addIndex();
            debutTour();
            
        }

    }
    public void CheckNbCarte(Aventurier av){
        if( av.nbCarte() > 5){
            faireDefausser(av, av.nbCarte()-5);
        }
    }
    
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
    /**
     *
     * @param msg
     */
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            case DEBUTJEU:
                accueil.afficher(false);
                deckTresor = new DeckTresor();
                //deckInnondation= new DeckInnondation() {};

                grille= new Grille(msg.difficulte);
                creationAventurier(msg.nbJoueur);
            try {
                jeu = new VueJeu(grille,mesAventuriers,msg.noms,msg.difficulte);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                jeu.afficher(true);
                jeu.addObservateur(this);
                debutTour();
                CarteTresor ca = new CarteTresor(Utils.CarteUtils.calice);
                getAvTrActuel().AddCarte(ca);
                
                getAvTrActuel().AddCarte(ca);
                getAvTrActuel().AddCarte(ca);
                getAvTrActuel().AddCarte(ca);
        {
            try {
                jeu.ajoutCarte(AvTrActuel, ca);
                jeu.ajoutCarte(AvTrActuel, ca);
                jeu.ajoutCarte(AvTrActuel, ca);
                jeu.ajoutCarte(AvTrActuel, ca);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }

    @Override
    public void traiterMessageAction(MessageAction msg) {
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
                    
                    finTour();
                    break;
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
                    if(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()).size()>0){
                        jeu.faireChoixAventurier(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()), VueJeu.DON_CARTE);
                    }
                    else{
                        jeu.erreur_choixAventurier();
                    }
                    break;
                case CHOIX_INTER_DONCARTE:
                    if(getAvTrActuel().getCartesDonnables().size()>0){
                        jeu.faireChoixCarte(VueJeu.DON_CARTE, getAvTrActuel().getCartesDonnables());
                        
                    }
                    else{
                        jeu.erreur_DonCarte();
                    }
                    break;
                    
                case CHOIX_TUILE_DEP:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().deplacer(t);
                    jeu.actualise();
                    jeu.deplacePion(getAvTrActuel().getPion(), t);
                    checkFinTour();
                    jeu.indic_Passif(this.getAvTrActuel());
                    break;
                case CHOIX_TUILE_AS:
                    t = grille.getTuile(msg.coord[0],msg.coord[1]);
                    getAvTrActuel().assecher(t);
                    jeu.actualise();
                    jeu.indic_Passif(getAvTrActuel());
                    checkFinTour();
                    break;
                case CHOIX_DONCARTE:
                    System.out.println(jeu.getMesVuesAvs().indexOf(msg.vueAv));
                    Aventurier receveur = getMesAventuriers().get(jeu.getMesVuesAvs().indexOf(msg.vueAv));
                    CarteJoueur carte = null;
                    Utils.tresor tres = null;
                    Iterator it = getAvTrActuel().getCartesDonnables().iterator();
                    while(carte == null && it.hasNext()){
                        CarteTresor ca = (CarteTresor) it.next();
                        if(ca.getTresor() == tres){
                            carte = ca;
                        }
                    }
                    
                    getAvTrActuel().DonnerCarte(carte, receveur);
                    CheckNbCarte(receveur);//finir*/
                    
                    
                    
                    
                    
                    
                    
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    

    
    public static void main(String[]args ) throws IOException{
        new Controleur();
    }

    
}



