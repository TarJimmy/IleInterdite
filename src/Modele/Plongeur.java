package Modele;

import Controleur.Utils;
import Controleur.Utils.Pion;
import java.util.ArrayList;
import java.util.Iterator;


public class Plongeur extends Aventurier {

    public Plongeur() {
        super.setPion(Controleur.Utils.Pion.VIOLET);
    }
    public Plongeur(Grille grille){
        super(grille.getTuile(Utils.TuilesUtils.porte_de_fer));
        setPion(Pion.VIOLET);
    }

    @Override
    public ArrayList<Tuile> getDeplacement(Grille grille) {
        //ArrayList<Tuile> collecTuiles = grille.getVoisins(getMaPos(),getCoordsProche());
        ArrayList<Tuile> deplacements = new ArrayList<>();
        ArrayList<Tuile> mouilleesTraites = new ArrayList<>();
        /*for(Tuile tui : collecTuiles){
            if(checkDeplacement(tui)){
                if(!deplacements.contains(tui)) {
                    deplacements.add(tui);
                }
            }
            if(tui.estMouillee()){*/
                recurInnondee(getMaPos(),deplacements,mouilleesTraites,grille);
            /*}
        }
        if(deplacements.contains(getMaPos())){
            deplacements.remove(getMaPos());
        }*/
        return deplacements;
    }
    
    public void recurInnondee(Tuile depart,ArrayList<Tuile> deplacements,ArrayList<Tuile> mouilleesTraites,Grille grille){
        ArrayList<Tuile> newDep = grille.getVoisins(depart,getCoordsProche());
        mouilleesTraites.add(depart);
        for(Tuile tui : newDep){
            if(!mouilleesTraites.contains(tui)){
                if(checkDeplacement(tui)){
                    if(!deplacements.contains(tui)) {
                        deplacements.add(tui);
                    }
                }
                if(tui.estMouillee()){
                    recurInnondee(tui,deplacements,mouilleesTraites,grille);
                }
            }
        }
    }
    

    public Plongeur(Tuile maPos) {
        super(maPos);
    }
    

    @Override
    public String getNomAventurier() {
        return "Plongeur";
    }
    @Override
    public String getDescription() {
        return  super.getDescription()+"Passez par 1 ou plusieurs tuiles adjacentes\ninnondées et/ou manquantes pour 1 action\n(vous devez terminer votre tour sur une tuile)";
    }
    
}