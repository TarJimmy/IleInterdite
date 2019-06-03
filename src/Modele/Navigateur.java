package Modele;

import Controleur.Utils;


public class Navigateur extends Aventurier {

    public Navigateur() {
        super.setPion(Controleur.Utils.Pion.JAUNE);
    }
    public Navigateur(Grille grille){
        super(grille.getTuile(Utils.nomTuile.porte_dor));
    }

    @Override
    public String getNomAventurier() {
        return "Navigateur";
    }


}