/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;


import Modele.*;
import Vue.*;
import java.util.ArrayList;

/**
 *
 * @author tardy
 */
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
    }

    private void setTrAv() {
        this.AvTrActuel = mesAventuriers.get(index);
        System.out.println("Initialise aux tour de l'aventurier : "+ AvTrActuel);
        this.vueAvTrActuel = mesVuesAventuriers.get(index);
        System.out.println("Initialise aux tour de la VueAventurier :" + vueAvTrActuel);
    }
    private void addIndex(){
        index++;
        if (index>=mesAventuriers.size()){
            index=0;
        }
    }
    public void piocher (Aventurier av){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean partieGagne(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void debutTour(){
        
        setTrAv();
        AvTrActuel.DebutTour();
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
    public void TourDeJeu(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Aventurier getTourActuelAventurier(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean partiePerdu(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void MonteeDesEaux(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void faireDefausser(Aventurier av){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void finTour(){
            addIndex();
            debutTour();
            
    }
    public void CheckNbCarte(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void CheckMonteeEau(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private ArrayList<Aventurier> creationAventurier(int nbAventuriers){
        ArrayList<Aventurier> mesAvs = new ArrayList<>();
        mesAvs.add(new Ingenieur(grille));
        mesAvs.add(new Messager(grille));
        mesAvs.add(new Navigateur(grille));
        mesAvs.add(new Explorateur(grille));
        mesAvs.add(new Plongeur(grille));
        mesAvs.add(new Pilote(grille));
        System.out.println("Créé un ArrayList d'aventurier : "+ mesAvs);
        System.out.println("Supprime "+(6-nbAventuriers)+" aventuriers de la liste pour avoir une taille de "+nbAventuriers+" Aventuriers");
        for (int i=0;i<(6-nbAventuriers);i++){
            int r1= (int) (Math.random() * (6-i));
            System.out.println("Supprime "+ mesAvs.get(r1).getNomAventurier() + " ( n°"+ r1+ " de la liste)");
            mesAvs.remove(mesAvs.get(r1));
        }
        System.out.println("La liste contient : "+ mesAvs);
        
        return mesAvs;
    }
    private ArrayList<VueAventurier> creationVuesAventuriers( ArrayList<String> mesNoms){
        ArrayList<VueAventurier> mesVues = new ArrayList<>();
        System.out.println("Créé une liste de VueAventurier a partir celui des Aventuriers");
        int taille = mesAventuriers.size();
        for (int i =0;i<taille;i++){
            mesVues.add(new VueAventurier(mesNoms.get(i),mesAventuriers.get(i).getNomAventurier(),mesAventuriers.get(i).getPion().getCouleur()));
            System.out.println("Crée une VueAventurier avec pour parametre : ["+mesNoms.get(i)+"]"
                                                                               + "["+ mesAventuriers.get(i).getNomAventurier()+"]"
                                                                               + "["+ mesAventuriers.get(i).getPion().toString()+"]");
            System.out.println(mesAventuriers.get(i).getMaPos());
            //mesVues.get(i).setPos(mesAventuriers.get(i).getMaPos().getCoords());
            mesVues.get(i).addObservateur(this);
            vueGrille.addObservateur(mesVues.get(i));
        }
        System.out.println("Liste Finale : " + mesVues);
        return mesVues;
    }
    private void creePion(){
        mesPions = new ArrayList<>();
        int i=0;
        for (Aventurier av : mesAventuriers){
            mesPions.add(new VuePion(av.getPion()));
            vueGrille.getVueTuile(av.getTuile().getCoords()).initVuePion(mesPions.get(i));
            i++;
        }
        
    }
    
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            case DEBUTJEU:
                index = 0;
                System.out.println("------------Desactive L'accueil---------");
                accueil.afficher(false);
                System.out.println("------------Creation de la Grille-------");
                grille= new Grille(msg.difficulte);
                System.out.println("-----------Création de la VueGrille-----");
                vueGrille = new VueGrille(grille);
                System.out.println("----------Creations des Aventuriers-----");
                mesAventuriers = creationAventurier(msg.nbJoueur);
                System.out.println("----------Création des VuesAventuriers--");
                mesVuesAventuriers = creationVuesAventuriers(msg.noms);
                creePion();
                System.out.println("------------Création de la VueJeu-------");
                jeu = new VueJeu(vueGrille,mesVuesAventuriers);
                System.out.println("--------------Affiche la VueJeu---------");
                jeu.afficher(true);
                System.out.println("-----------Initialise le tour Actuel de l'aventurier");
                setTrAv();
                debutTour();
                //A enlever apres demo
                grille.getTuile(2, 2).Inonder();
                vueGrille.getVueTuile(new int[] {2,2}).changeEtat(grille.getTuile(2, 2).getEtat());
                grille.getTuile(4, 4).Inonder();
                vueGrille.getVueTuile(new int[] {4,4}).changeEtat(grille.getTuile(2, 2).getEtat());
        }
    }

    @Override
    public void traiterMessage(MessageAction msg) {
        if (msg.typeact==TypeAction.DEPLACER){
                vueGrille.proposeCase(AvTrActuel.getDeplacement(grille));
                System.out.println((AvTrActuel.getDeplacement(grille)));
                for (Tuile tui :AvTrActuel.getDeplacement(grille)){
                    System.out.println(tui.getCoords()[0] +"   "+ tui.getCoords()[1]);
                }
        }
        else if(msg.typeact== TypeAction.CHOIX_TUILE){
            Tuile t = grille.getTuile(msg.coord[0],msg.coord[1]);
            AvTrActuel.deplacer(t);
            mesPions.get(index).setMaTuile(vueGrille.getVueTuile(new int[] {t.getCoords()[0],t.getCoords()[1]}));
            if (AvTrActuel.getActionsRestantes()==0){
                finTour();
            }
        }
        else if (msg.typeact==TypeAction.ASSECHER){

        }
        else if(msg.typeact== TypeAction.TERMINER_TOUR){
            finTour();
        }
    }
    
    public static void main(String[]args ){
        new Controleur();
    }
}