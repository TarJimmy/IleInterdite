package Modele;

import Controleur.Utils;

public class Messager extends Aventurier {

    public Messager() {
        super.setPion(Controleur.Utils.Pion.GRIS);
    }
    public Messager(Grille grille){
        super.setMaPos(grille.getTuile(Utils.nomTuile.porte_dargent));
        super.setPion(Controleur.Utils.Pion.GRIS);
    }

    @Override
    public String getNomAventurier() {
       return "Messager";
    }



}