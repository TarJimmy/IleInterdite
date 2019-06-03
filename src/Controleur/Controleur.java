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
        if (index>mesAventuriers.size()){
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
               throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    private ArrayList<VueAventurier> creationVuesAventuriers(ArrayList<Aventurier> mesAvs, ArrayList<String> mesNoms){
        ArrayList<VueAventurier> mesVues = new ArrayList<>();
        System.out.println("Créé une liste de VueAventurier a partir celui des Aventuriers");
        int taille = mesAvs.size();
        for (int i =0;i<taille;i++){
            mesVues.add(new VueAventurier(mesNoms.get(i),mesAvs.get(i).getNomAventurier(),mesAvs.get(i).getPion().getCouleur()));
            System.out.println("Crée une VueAventurier avec pour parametre : ["+mesNoms.get(i)+"]"
                                                                               + "["+ mesAvs.get(i).getNomAventurier()+"]"
                                                                               + "["+ mesAvs.get(i).getPion().toString()+"]");
            mesVues.get(i).addObservateur(this);
        }
        System.out.println("Liste Finale : " + mesVues);
        return mesVues;
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
                mesVuesAventuriers = creationVuesAventuriers(mesAventuriers,msg.noms);
                System.out.println("------------Création de la VueJeu-------");
                jeu = new VueJeu(vueGrille,mesVuesAventuriers);
                System.out.println("--------------Affiche la VueJeu---------");
                jeu.afficher(true);
                System.out.println("-----------Initialise le tour Actuel de l'aventurier");
                vueGrille.addObservateur(this);
                setTrAv();
                debutTour();
                AvTrActuel.setMaPos(grille.getTuile(3,3));
        }
    }

    @Override
    public void traiterMessage(MessageAction msg) {
        switch (msg.typeact){
            case DEPLACER :
                System.out.println("hey");
                vueGrille.proposeCase(AvTrActuel.getDeplacement(grille));
            case CHOIX_TUILE:
                AvTrActuel.deplacer(msg.tui);
            case ASSECHER:;
            
            case TERMINER_TOUR:
                addIndex();
                setTrAv();
                debutTour();
        }
    }
    public static void main(String[]args ){
        new Controleur();
    }
}