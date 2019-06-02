/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import Vue.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    
    Controleur(){
        accueil = new VueAccueil();
        accueil.addObservateur(this);
        accueil.afficher(true);
    }
    public void piocher (Aventurier av){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean partieGagne(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void FaireAction(String Action){
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
        mesAvs.add(new Ingenieur());
        mesAvs.add(new Messager());
        mesAvs.add(new Navigateur());
        mesAvs.add(new Explorateur());
        mesAvs.add(new Plongeur());
        mesAvs.add(new Pilote());
        for (int i=0;i<(6-nbAventuriers);i++){
            int r1= (int) (Math.random() * (6-i));
            mesAvs.remove(mesAvs.get(r1));
        }
        return mesAvs;
    }
    private ArrayList<VueAventurier> creationVuesAventuriers(ArrayList<Aventurier> mesAvs, ArrayList<String> mesNoms){
        ArrayList<VueAventurier> mesVues = new ArrayList<>();
        int taille = mesAvs.size();
        System.out.println("hey1");
        for (int i =0;i<taille;i++){
            mesVues.add(new VueAventurier(mesNoms.get(i),mesAvs.get(i).getNomAventurier(),mesAvs.get(i).getPion().getCouleur()));
            System.out.println("hey");
        }
        return mesVues;
    }
    
    
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            case DEBUTJEU:
                accueil.afficher(false);
                grille= new Grille(3);
                vueGrille = new VueGrille(grille);
                mesAventuriers = creationAventurier(msg.nbJoueur);
                mesVuesAventuriers = creationVuesAventuriers(mesAventuriers,msg.noms);
                jeu = new VueJeu(vueGrille,mesVuesAventuriers);
                jeu.afficher(true);
        }
    }
    public static void main(String[]args ){
        new Controleur();
    }
}