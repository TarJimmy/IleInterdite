package Modele;

public class Messager extends Aventurier {

    public Messager() {
        super.setPion(Controleur.Utils.Pion.GRIS);
    }

    @Override
    public String getNomAventurier() {
       return "Messager";
    }



}