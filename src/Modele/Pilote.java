package Modele;

import Controleur.Utils;
import Controleur.Utils.Pion;
import java.util.ArrayList;

public class Pilote extends Aventurier {

    private boolean aVoler;

    public Pilote(Grille grille){
        super(grille.getTuile(Utils.TuilesUtils.heliport));
        setPion(Pion.BLEU);
    }

    public Pilote(Tuile maPos) {
        super(maPos);
    }
    
    
    /**
     *
     * @param grille
     * @return
     */
    @Override
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

    @Override
    public void DebutTour() {
            super.DebutTour();
            setaVoler(false);
    }

    @Override
    public void deplacer(Tuile tuile) {
        int[] depart = getMaPos().getCoords();
        int[] arrivee = tuile.getCoords();
        int d =  Math.abs(depart[0]- arrivee[0]) + (depart[1]- arrivee[1]);
        if(d!=1){
            setaVoler(true);
        }
        super.deplacer(tuile);
    }

    @Override
    public String getNomAventurier() {
       return "Pilote";
    }

    @Override
    public String getDescription() {
        return "Une fois par tour, volez jusqu'à \nn'importe quelle tuile de l'île pour 1 action";
    }
    
    
    
}