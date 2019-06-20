package Modele;

import Controleur.Utils;
import java.util.ArrayList;


public class Navigateur extends Aventurier {

    public Navigateur() {
        super.setPion(Controleur.Utils.Pion.JAUNE);
    }
    public Navigateur(Grille grille){
        super(grille.getTuile(Utils.TuilesUtils.porte_dor));
        super.setPion(Controleur.Utils.Pion.JAUNE);
    }

    @Override
    public String getNomAventurier() {
        return "Navigateur";
    }
    
    @Override
    public String getDescription() {
        return "Déplacez un autre joueur d'1 ou 2 tuiles adjacentes pour 1 action";
    }

    @Override
    public ArrayList<Tuile> getDeplacementNav(Grille grille) {
        return getDeplacement(grille);
    }
    
    
    
}