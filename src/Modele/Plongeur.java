package Modele;

import Controleur.Utils;


public class Plongeur extends Aventurier {

    public Plongeur() {
        super.setPion(Controleur.Utils.Pion.NOIR);
    }
    public Plongeur(Grille grille){
        super.setMaPos(grille.getTuile(Utils.nomTuile.porte_de_fer));
        super.setPion(Controleur.Utils.Pion.NOIR);
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