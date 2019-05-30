package Modele;

import Vue.MesEnums.tresor;
import Vue.MesEnums.typeAction;

import java.util.*;

public abstract class Aventurier {

    private Tuile maPos;
    ArrayList<CarteJoueur> mesCartes;
    private int actionsRestantes;
    private Pion pion;
    private static HashSet<tresor> tresorsRecuperer = new HashSet<>();


    public void deplacer(Tuile tuile) {
        setMaPos(tuile);
    }

    public static HashSet<tresor> getTresorsRecuperer() {
        return tresorsRecuperer;
    }


    public void assecher(Tuile tuile) {
    tuile.assecher();
    actionsRestantes--;
     }

    public void DonnerCarte(CarteJoueur carte, Aventurier av) {
        av.AddCarte(carte);
        SupprimerCarte(carte);
        actionsRestantes--;
    }


    public boolean GagnerTresor(tresor tres) {
            if(getTresorsRecuperer().contains(tres)){
                int nbRetirés = 0;
                Iterator it = getCartes().iterator();
                while(it.hasNext()){
                    CarteJoueur carte =(CarteJoueur) it.next();
                    if(carte instanceof CarteTresor){
                        if( carte.getTresor() == tres)
                        it.remove();
                        nbRetirés++;
                    }
                }
            }
    }

    public ArrayList<CarteJoueur> getCartes() {
        return mesCartes;
    }


    public void SupprimerCarte(CarteJoueur carte) {
        getCartes().remove(carte);
    }

    public void AddCarte(CarteJoueur carte) {
        getCartes().add(carte);
    }

    public Tuile getTuile() {
        return maPos;
    }

    public int nbCarte() {
        return getCartes().size();
    }

    public boolean checkDeplacement(Tuile tuile) {
        return tuile.estDisponible();
    }

    public ArrayList<Tuile> getDeplacement(Grille grille) {
        grille.getVoisins(getTuile(),getCoordsProche());
        //TO DO
        return null;
    }

    public void DebutTour() {
        setActionsRestantes(3);
    }

    public void Piocher(DeckTresor deck) {
        // TODO - implement Aventurier.Piocher
        throw new UnsupportedOperationException();
    }

    public ArrayList<int[]> getCoordsProche() {
        ArrayList<int[]> coords = new ArrayList<>();
        int d;
        for(int x = -1;x<=1;x++){
            for(int y = -1;y <= 1;y++){
                d = Math.abs(x+y);
                coords.add((d == 1)? new int[] {x,y} :null);
            }
        }
        return coords;
    }



    public ArrayList<Tuile> getAssechement(Grille grille) {
        // TODO - implement Aventurier.getAssechement
        throw new UnsupportedOperationException();
    }


    public boolean checkAssechement(Tuile tui) {
        return tui.isInnondee();
    }

    public Tuile getMaPos() {
        return maPos;
    }

    private void setMaPos(Tuile maPos) {
        this.maPos = maPos;
    }

    public int getActionsRestantes() {
        return actionsRestantes;
    }

    private void setActionsRestantes(int actionsRestantes) {
        this.actionsRestantes = actionsRestantes;
    }
}
