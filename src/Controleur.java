/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import Vue.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author tardy
 */
public class Controleur implements Observateur {
    private VueAccueil accueil;
    private VueGrille vueGrille;
    private VueJeu vueJeu;
    private ArrayList<Vue.VueAventurier> mesVuesAventuriers;
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

    

    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            case DEBUTJEU:
                grille= new Grille(msg.difficulte);
                
        }
    }
}