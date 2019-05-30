package Modele;

import Modele.Utils.Pion;
import java.util.ArrayList;

public class Pilote extends Aventurier {

    private boolean aVoler;

        
    public ArrayList<Tuile> getDeplacement(Grille grille) {
        if(aVoler){
            return super.getDeplacement(grille);
        }else{
            return grille.getTuilesDisponibles();
        }
    }

    public boolean aVoler() {
        return aVoler;
    }

    public void setaVoler(boolean aVoler) {
        this.aVoler = aVoler;
    }

    public void DebutTour() {
            setaVoler(false);
    }

    public void deplacer(Tuile tuile) {
        int[] depart = getTuile().getCoords();
        int[] arrivee = tuile.getCoords();
        int d =  Math.abs(depart[0]- arrivee[0]) + (depart[1]- arrivee[1]);
        if(d!=1){
            setaVoler(true);
        }
        super.deplacer(tuile);
    }

}