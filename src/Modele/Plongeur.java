package Modele;

import Controleur.Utils;


public class Plongeur extends Aventurier {

    public Plongeur() {
        super.setPion(Controleur.Utils.Pion.NOIR);
    }
    public Plongeur(Grille grille){
        super(grille.getTuile(Utils.nomTuile.porte_de_fer));
    }
    
    @Override
    public boolean checkDeplacement(Tuile tui) {
        return true;
    }

    @Override
    public String getNomAventurier() {
        return "Plongeur";
    }
}