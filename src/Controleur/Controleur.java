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







public class Controleur implements Observateur {
    private VueAccueil accueil;
    private VueGrille vueGrille;
    private VueJeu jeu;
    private ArrayList<VueAventurier> mesVuesAventuriers;
    private ArrayList<Aventurier> mesAventuriers;
    private Grille grille;
    private DeckInnondation deckInnondation;
    private DeckTresor deckTresor;
    private Aventurier AvTrActuel;
    private int index;
    private VueAventurier vueAvTrActuel;
    private ArrayList<VuePion> mesPions;
    
    
    
    Controleur(){
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

    private void setTrAv() {
        this.AvTrActuel = getMesAventuriers().get(index);
        System.out.println("Initialise aux tour de l'aventurier : "+ getAvTrActuel());
        this.vueAvTrActuel = mesVuesAventuriers.get(index);
        System.out.println("Initialise aux tour de la VueAventurier :" + vueAvTrActuel);
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
                gagne = gagne && av.getMaPos().getNom().equals(Utils.nomTuile.heliport);
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
        boolean perdu = !grille.getTuile(Utils.nomTuile.heliport).estDisponible();
        perdu = perdu || grille.getEchelonMonteEau() <= 10;
        if(!perdu){
            HashSet<Utils.tresor> tresors = new HashSet<>();
            tresors.add(null);
            for(Tuile tui : grille.getTuilesDisponibles()){
                tresors.add(tui.getTresor());
            }
            tresors.remove(null);
            perdu = tresors.contains(Utils.tresor.values());
        }
        return perdu;
    }
    
    
    private void debutTour(){
        setTrAv();
        getAvTrActuel().DebutTour();
        for (VueAventurier vue : mesVuesAventuriers){
            if (vue != vueAvTrActuel){
                vue.activer(false);
                System.out.println("Desactive " + vue);
            }
            else{
                vue.activer(true);
            }
        }
    }

    public Aventurier getAvTrActuel(){
        return AvTrActuel;
    }
    
    
    public void MonteeDesEaux(CarteJoueur c){
        grille.MonterNiveauDeau();
        deckInnondation.ResetPioche();
        deckTresor.Defausser(c);
    }
    public void faireDefausser(Aventurier av,int nbCartes){
            //defausser des cartes via l'ihm
            //Defausser(int nbCartes) avec nbCartes = getAvTrActuel().nbCarte()
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void checkFinTour(){
        if(getAvTrActuel().getActionsRestantes() == 0 ){
            if(getAvTrActuel() instanceof Ingenieur && ((Ingenieur) getAvTrActuel()).aAssecher()){
                //ihm fonction uniquement assecher
            }
            finTour();
        }
    }
    
    
    public void finTour(){
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
        for (int i=0;i<(6-nbAventuriers);i++){
            int r1= (int) (Math.random() * (6-i));
            mesAventuriers.remove(mesAventuriers.get(r1));
        }
    }
    private void creationVuesAventuriers( ArrayList<String> mesNoms){
        mesVuesAventuriers = new ArrayList<>();
        int taille = getMesAventuriers().size();
        for (int i =0;i<taille;i++){
            mesVuesAventuriers.add(new VueAventurier(mesNoms.get(i),getMesAventuriers().get(i).getNomAventurier(),getMesAventuriers().get(i).getPion().getCouleur()));
        }
    }
    private void creePion(){
        mesPions = new ArrayList<>();
        int i=0;
        for (Aventurier av : getMesAventuriers()){
            mesPions.add(new VuePion(av.getPion()));
            vueGrille.getVueTuile(av.getMaPos().getCoords()).initVuePion(mesPions.get(i));
            i++;
        }
        
    }
    
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            case DEBUTJEU:
                index = 0;
                accueil.afficher(false);
                grille= new Grille(msg.difficulte);
                vueGrille = new VueGrille(grille);
                creationAventurier(msg.nbJoueur);
                creationVuesAventuriers(msg.noms);
                creePion();
        {
            try {
                jeu = new VueJeu(vueGrille,mesVuesAventuriers);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                jeu.afficher(true);
                setTrAv();
                debutTour();
                //A enlever apres demo
                for (VueAventurier av : mesVuesAventuriers){
                    av.addObservateur(this);
                     vueGrille.addObservateur(this);
                }
                grille.getTuile(2, 2).Innonder();
                vueGrille.getVueTuile(new int[] {2,2}).changeEtat(grille.getTuile(2, 2).getEtat());
                grille.getTuile(4, 4).Innonder();
                vueGrille.getVueTuile(new int[] {4,4}).changeEtat(grille.getTuile(4, 4).getEtat());
                 grille.getTuile(3, 4).Innonder();
                vueGrille.getVueTuile(new int[] {3,4}).changeEtat(grille.getTuile(3, 4).getEtat());
                 grille.getTuile(3, 3).Innonder();
                vueGrille.getVueTuile(new int[] {3,3}).changeEtat(grille.getTuile(3, 3).getEtat());
                break;
                
                
            case ACTION:
                
                break;
        }
    }

    @Override
    public void traiterMessageAction(MessageAction msg) {
        Tuile t;
        switch(msg.typeact){
            case DEPLACER:
                vueGrille.proposeCaseDEP(getAvTrActuel().getDeplacement(grille));
                /*for (Tuile tui :getAvTrActuel().getDeplacement(grille)){
                    System.out.println(tui.getCoords()[0] +"   "+ tui.getCoords()[1]);
                }*/
                break;
                
        
            case CHOIX_TUILE_DEP:
                t = grille.getTuile(msg.coord[0],msg.coord[1]);
                getAvTrActuel().deplacer(t);
                mesPions.get(index).setMaTuile(vueGrille.getVueTuile(new int[] {t.getCoords()[0],t.getCoords()[1]}));
                System.out.println(getAvTrActuel().getMaPos().getCoords()[0] + "\t" + getAvTrActuel().getMaPos().getCoords()[1]);
                vueGrille.actualise();
                if (getAvTrActuel().getActionsRestantes()==0){
                    finTour();
                }
                break;
                
                
            case CHOIX_TUILE_AS:
                t = grille.getTuile(msg.coord[0],msg.coord[1]);
                getAvTrActuel().assecher(t);
                mesPions.get(index).setMaTuile(vueGrille.getVueTuile(new int[] {t.getCoords()[0],t.getCoords()[1]}));
                System.out.println(getAvTrActuel().getMaPos().getCoords()[0] + "\t" + getAvTrActuel().getMaPos().getCoords()[1]);

                if (getAvTrActuel().getActionsRestantes()==0){
                    finTour();
                }
                break;
                
                
            case ASSECHER:
                vueGrille.proposeCaseAS(getAvTrActuel().getAssechement(grille));
                break;
                
                
            case TERMINER_TOUR:
                finTour();
                break;
        }
    }
    
    

    
    public static void main(String[]args ){
        new Controleur();
    }
}