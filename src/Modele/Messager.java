package Modele;

import Controleur.Utils;
import java.util.ArrayList;

public class Messager extends Aventurier {

    public Messager() {
        super.setPion(Controleur.Utils.Pion.ORANGE);
    }
    public Messager(Grille grille){
        super(grille.getTuile(Utils.TuilesUtils.porte_dargent));
        super.setPion(Controleur.Utils.Pion.ORANGE);
    }

    @Override
    public String getNomAventurier() {
       return "Messager";
    }

    @Override
    public ArrayList<Aventurier> getAvsDonsCarte(ArrayList<Aventurier> avs) {
        ArrayList<Aventurier> voisins = new ArrayList<>();
        voisins.addAll(avs);
        voisins.remove(this);
        return voisins;
    }

    @Override
    public String getDescription() {
        return  super.getDescription()+"Donnez des cartes Trésor à un joueur \nn'importe où sur l'île pour 1 action par \ncarte";
    }
}