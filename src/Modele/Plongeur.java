package Modele;

import Modele.Utils.Pion;

public class Plongeur extends Aventurier {
    
    @Override
    public boolean checkDeplacement(Tuile tui) {
        return true;
    }
}