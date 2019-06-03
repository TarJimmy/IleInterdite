package Modele;


public class Plongeur extends Aventurier {

    public Plongeur() {
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