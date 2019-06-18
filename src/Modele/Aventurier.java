package Modele;

import Controleur.Utils;

import java.util.*;

public abstract class Aventurier {

    private Tuile maPos;
    ArrayList<CarteJoueur> mesCartes;
    protected int actionsRestantes;
    Utils.Pion pion;
    private static HashSet<Utils.tresor> tresorsRecuperer = new HashSet<>();
    public Aventurier() {
        setActionsRestantes(3);
        mesCartes = new ArrayList<>();
    }

    public Aventurier(Tuile maPos) {
        this.maPos = maPos;
    }
    

    public Utils.Pion getPion() {
        return pion;
    }
    
    protected void setPion(Utils.Pion pion){
        this.pion = pion;
    }
    
    public void deplaceHorsTour(Tuile tuile){
        setMaPos(tuile);
    }
    
    public void deplacer(Tuile tuile) {
        setMaPos(tuile);
        actionsRestantes--;
    }
    public abstract String getNomAventurier();
    public static HashSet<Utils.tresor> getTresorsRecuperer() {
        return tresorsRecuperer;
    }


    public void assecher(Tuile tuile) {
        tuile.Assecher();
        actionsRestantes--;
    }

    public void DonnerCarte(CarteJoueur carte, Aventurier av) {
        av.AddCarte(carte);
        SupprimerCarte(carte);
        actionsRestantes--;
    }


    public boolean GagnerTresor(Utils.tresor tres) {
            if(getTresorsRecuperer().contains(tres)){
                int nbRetirés = 0;
                Iterator it = getCartes().iterator();
                while(it.hasNext()){
                    CarteJoueur carte =(CarteJoueur) it.next();
                    if(carte instanceof CarteTresor){
                        if( ((CarteTresor) carte).getTresor() == tres)
                        it.remove();
                        nbRetirés++;
                    }
                }
            }
            return true;
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

    

    public int nbCarte() {
        return getCartes().size();
    }

    public boolean checkDeplacement(Tuile tuile) {
        return tuile.estDisponible();
    }
    public ArrayList<Tuile> getDeplacementNav(Grille grille){
        ArrayList<Tuile> collecTuiles1 = grille.getVoisins(getMaPos(),getCoordsProche());
        ArrayList<Tuile> collecTuiles2 = new ArrayList<>();
        Iterator it = collecTuiles1.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)){
                it.remove();
            }
        }
        collecTuiles2.addAll(collecTuiles1);
        for(Tuile tui : collecTuiles1){
            collecTuiles2.addAll(grille.getVoisins(tui,getCoordsProche()));
        }
        it = collecTuiles2.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)){
                it.remove();
            }
        }
        
        return collecTuiles2;
    }
    
    
    public ArrayList<Tuile> getDeplacement(Grille grille) {
        ArrayList<Tuile> collecTuiles = grille.getVoisins(getMaPos(),getCoordsProche());
        Iterator it = collecTuiles.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)) it.remove();
        }
        return collecTuiles;
    }

    public void DebutTour() {
        setActionsRestantes(3);
    }


    public ArrayList<int[]> getCoordsProche() {
        ArrayList<int[]> coords = new ArrayList<>();
        int d;
        for(int x = -1;x<=1;x++){
            for(int y = -1;y <= 1;y++){
                d = Math.abs(x+y);
                if(d == 1){
                    coords.add(new int[] {x,y});
                }
            }
        }
        return coords;
    }
     



    public ArrayList<Tuile> getAssechement(Grille grille) {
        ArrayList<int[]> coords = getCoordsProche();
        coords.add(new int[] {0,0});
        ArrayList<Tuile> collecTuiles = grille.getVoisins(getMaPos(),coords);
        Iterator it = collecTuiles.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkAssechement(tui)) it.remove();
        }
        return collecTuiles;
    }


    public boolean checkAssechement(Tuile tui) {
        return tui.isInnondee();
    }

    public Tuile getMaPos() {
        return maPos;
    }

    public void setMaPos(Tuile maPos) { //Doit etre changer en private apres demo
        this.maPos = maPos;
    }

    public int getActionsRestantes() {
        return actionsRestantes;
    }

    private void setActionsRestantes(int actionsRestantes) {
        this.actionsRestantes = actionsRestantes;
    }
    public String toString(){
        return getNomAventurier();
    }
    
    public ArrayList<Aventurier> getAvsHelicoptere(ArrayList<Aventurier> avs){
        ArrayList<Aventurier> voisins = new ArrayList<>();
        for(Aventurier av : avs){
            if (av != this && av.getMaPos() == this.getMaPos()){
                voisins.add(av);
            }
        }
        return voisins;
    }
    
    public ArrayList<Aventurier> getAvsDonsCarte(ArrayList<Aventurier> avs){
        return getAvsHelicoptere(avs);
    }
    public ArrayList<CarteJoueur> getCartesDonnables(){
        ArrayList<CarteJoueur> donnables = new ArrayList<>();
        for(CarteJoueur c : getCartes()){
            if(!(c instanceof CarteSpecial)){
                donnables.add(c);
            }
        }
        return donnables;
    }
    
    
}
