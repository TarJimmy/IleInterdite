package Modele;

import Controleur.Utils;
import Controleur.Utils.Pion;


public class Plongeur extends Aventurier {

    public Plongeur() {
        super.setPion(Controleur.Utils.Pion.NOIR);
    }
    public Plongeur(Grille grille){
        super(grille.getTuile(Utils.nomTuile.porte_de_fer));
        setPion(Pion.NOIR);
    }

    public Plongeur(Tuile maPos) {
        super(maPos);
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