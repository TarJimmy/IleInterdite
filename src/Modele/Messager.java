package Modele;

import Controleur.Utils;
import java.util.ArrayList;

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

    @Override
    public ArrayList<Aventurier> getAvsDonsCarte(ArrayList<Aventurier> avs) {
        ArrayList<Aventurier> voisins = new ArrayList<>();
        voisins.addAll(avs);
        voisins.remove(this);
        return voisins;
    }

    @Override
    public String getDescription() {
        return "Donnez des cartes Trésor à un joueur n'importe où sur l'île pour 1 action par carte";
    }
}