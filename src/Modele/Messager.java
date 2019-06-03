package Modele;

import Controleur.Utils;

public class Messager extends Aventurier {

    public Messager() {
        super.setPion(Controleur.Utils.Pion.GRIS);
    }
    public Messager(Grille grille){
        super(grille.getTuile(Utils.nomTuile.porte_dargent));
    }

    @Override
    public String getNomAventurier() {
       return "Messager";
    }



}