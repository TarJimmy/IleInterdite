package Modele;

import Modele.Utils.Pion;

public class Ingenieur extends Aventurier {

    private boolean aAssecher;

    public void setaAssecher(boolean aAssecher) {
        this.aAssecher = aAssecher;
    }
    
    public boolean aAssecher() {
        return aAssecher;
    }
    @Override
    public void assecher(Tuile Tuile) {
        super.assecher(Tuile);
        if(aAssecher()){
            setaAssecher(false);
            actionsRestantes++;
        }else{
            setaAssecher(true);
        }
        
    }

    @Override
    public boolean GagnerTresor(Utils.tresor tres) {
        setaAssecher(false);
        return super.GagnerTresor(tres);
    }
    
    @Override
    public void DonnerCarte(CarteJoueur carte, Aventurier Av) {
        super.DonnerCarte(carte, Av);
        setaAssecher(false);
    }

    @Override
    public void deplacer(Tuile tuile) {
        super.deplacer(tuile);
        setaAssecher(false);
    }
    
    @Override
    public void DebutTour() {
        super.DebutTour();
        setaAssecher(false);
    }

}