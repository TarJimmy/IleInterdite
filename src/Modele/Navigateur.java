package Modele;


public class Navigateur extends Aventurier {

    public Navigateur() {
        super.setPion(Controleur.Utils.Pion.JAUNE);
    }

    @Override
    public String getNomAventurier() {
        return "Navigateur";
    }


}