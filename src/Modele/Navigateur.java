package Modele;

import Controleur.Utils;


public class Navigateur extends Aventurier {

    public Navigateur() {
        super.setPion(Controleur.Utils.Pion.JAUNE);
    }
    public Navigateur(Grille grille){
        super.setMaPos(grille.getTuile(Utils.nomTuile.porte_dor));
        super.setPion(Controleur.Utils.Pion.JAUNE);
    }

    @Override
    public String getNomAventurier() {
        return "Navigateur";
    }


}