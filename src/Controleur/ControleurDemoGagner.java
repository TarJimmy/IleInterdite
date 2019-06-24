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

public class ControleurDemoGagner extends Controleur{

    
    ControleurDemoGagner() throws IOException{
        super();
    }

    @Override
    public void traiterMessage(Message msg) {
        try {
            super.traiterMessage(msg);
            getTresorsRecuperers().add(tresor.CALICE_ONDE);
            getJeu().gainTresor(tresor.CALICE_ONDE);
            getTresorsRecuperers().add(tresor.CRISTAL_ARDENT);
            getJeu().gainTresor(tresor.CRISTAL_ARDENT);
            getTresorsRecuperers().add(tresor.STATUE_ZEPHYR);
            getJeu().gainTresor(tresor.STATUE_ZEPHYR);
            for (int i = 0; i < 4; i++) {
                CarteJoueur carte = new CarteTresor(Utils.CarteUtils.pierre);
                getAvTrActuel().AddCarte(carte);
                getJeu().ajoutCarte(getAvTrActuel(), carte);
            }
            CarteJoueur carte = new Helicoptere();
            getAvTrActuel().AddCarte(carte);
            getJeu().ajoutCarte(getAvTrActuel(), carte);
        } catch (IOException ex) {
            Logger.getLogger(ControleurDemoGagner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    @Override
    protected void creationAventurier(int nbAventuriers) {
        super.creationAventurier(0);
        getMesAventuriers().add(new Pilote(getGrille()));
        getMesAventuriers().add(new Explorateur(getGrille()));
    }

    @Override
    public void piochesInitial() {}
    
    
    
    
    public static void main(String[] args) throws IOException {
        new ControleurDemoGagner();
    }
    
}
