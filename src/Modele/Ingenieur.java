package Modele;

import Controleur.Utils.tresor;
import Controleur.Utils.Pion;

public class Ingenieur extends Aventurier {

    private boolean aAssecher;

    public Ingenieur() {
        super.setPion(Pion.ROUGE);
    }

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
    public boolean GagnerTresor(tresor tres) {
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

    @Override
    public String getNomAventurier() {
     return "Ingénieur";
    }

}