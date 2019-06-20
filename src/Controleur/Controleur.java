/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;


import Modele.*;
import Vue.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
        jeu.setTrActuel(index);
    }
    private void addIndex(){
        index = (index+1)/getMesAventuriers().size();
    }
    public void piocher (){
        for (int i = 0; i < 2; i++) {
            CarteJoueur carte = deckTresor.Piocher();
            if(carte instanceof CarteMonteeEau){
                MonteeDesEaux(carte);
            }else{
                getAvTrActuel().AddCarte(carte);
            }
        }
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
    public void checkFinTour(){
        if(getAvTrActuel().getActionsRestantes() <= 0 ){
            if(getAvTrActuel() instanceof Ingenieur && ((Ingenieur) getAvTrActuel()).aAssecher()){
                //ihm fonction uniquement assecher
               // VueJeu.IngenieurAssecherFT();
            }
            finTour();
        }
    }
    
    
    public void finTour(){
        if(!partieGagne()){
//            piocher();
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
                grille= new Grille(msg.difficulte);
                creationAventurier(msg.nbJoueur);
            try {
                jeu = new VueJeu(grille,mesAventuriers,msg.noms);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                jeu.afficher(true);
                jeu.addObservateur(this);
                debutTour(); 
            case ACTION:
                
                break;
        }
    }
    public VueAventurier translateAve_VueAvs(){
        
    }

    @Override
    public void traiterMessageAction(MessageAction msg) {
        Tuile t;
        switch(msg.typeact){
            case DEPLACER:
                jeu.faireChoixTuile(jeu.getCHOIX_DEP(), getAvTrActuel().getDeplacement(grille)); 
                break;
            case ASSECHER:
                jeu.faireChoixTuile(jeu.getCHOIX_AS(),getAvTrActuel().getAssechement(grille));
            break;
            case TERMINER_TOUR:
                finTour();
                break;
            case GAGNERTRESOR://A remplir
                break;
            case DONNERCARTE:
                int c=0;
                jeu.faireChoixVueAventuriers(getAvTrActuel().getAvsDonsCarte(getMesAventuriers()));
                
                break;
            case CHOIX_TUILE_DEP:
                System.out.println(getAvTrActuel().getMaPos().getNom());
                t = grille.getTuile(msg.coord[0],msg.coord[1]);
                getAvTrActuel().deplacer(t);
                jeu.actualise();
                System.out.println(getAvTrActuel().getMaPos().getNom());
                jeu.deplacePion(getAvTrActuel().getPion(),t);
                checkFinTour();
                break;
            case CHOIX_TUILE_AS:
                t = grille.getTuile(msg.coord[0],msg.coord[1]);
                getAvTrActuel().assecher(t);
                checkFinTour();
                break;
            case CHOIX_DONCARTE:
                /*Aventurier receveur = getMesAventuriers().get(getMesVuesAventuriers().indexOf(msg.VueAv));
                
                getAvTrActuel().DonnerCarte(carte, receveur);
                CheckNbCarte(receveur);//finir*/
                
                
                
                
                
                
                
                break;
        }
    }

    
    

    
    public static void main(String[]args ) throws IOException{
        new Controleur();
    }

    
}



