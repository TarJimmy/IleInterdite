package Modele;

import Controleur.Utils;
import java.util.*;

/**
 *
 * @author Gholbin
 */
public final class Grille {

    /**
     * contient toutes les tuiles de la grille
     */
    public Tuile[][] mesTuiles;
    
    
    /**
    * echelon de montee d'eau, va de 1 à 10
    */
    private int EchelonMonteEau;
 
    /**
     * crée une grille et l'initialise
     * @param echelon
     */
    public Grille(int echelon){
        this.EchelonMonteEau=echelon;
        initTableau();
    }

    /**
     *Monte le niveau d'eau (echelon)
     */
    public void MonterNiveauDeau(){
        EchelonMonteEau++;
    }

    /**
     *
     * @return le niveau d'eau (de 1 à 10)
     */
    public int getEchelonMonteEau() {
        return EchelonMonteEau;
    }
    
    /**
     *
     * @param nom
     * @return la tuile qui s'appelle nom
     */
    public Tuile getTuile(Utils.TuilesUtils nom){
        Tuile tui = null;
        for(int x=0;x<mesTuiles.length;x++){
           for(int y=0;y<mesTuiles[x].length;y++){
                if (getTuile(x,y) != null){
                    if (getTuile(x,y).getNom().equals(nom)){
                        tui = getTuile(x,y);
                    }
                }
            }
        }
        return tui;
    }
    
    private void initTableau(){
        //Cree les tuiles du tableau
        mesTuiles = new Tuile[6][6];
        int i = 0; //index pour recuperer le bon nomTuile
        for (int x = 0;x<6;x++){
            for (int y=0;y<6;y++){
                if (x+y<2 || x+y>=9 || x+5-y>=9 || x+5-y<2){
                    mesTuiles[x][y]=null;
                    //System.out.println("Ajoute NULL");
                }
                else{
                    mesTuiles[x][y] = new Tuile(Utils.TuilesUtils.values()[i]);
                    //System.out.println("Ajoute " + mesTuiles[x][y]);
                    i++;
                }
            }
        }
        //Melange le tableau
        //System.out.println("Mélange Le Tableau");
        for (int x=0;x<mesTuiles.length;x++){
            for(int y=0;y<mesTuiles[x].length;y++){
                if (!(x+y<2 || x+y>=9 || x+5-y>=9 || x+5-y<2)){
                    int r1 = (int) (Math.random() * 6);
                    int r2 = (int) (Math.random() * 6);
                    while ((r1+r2<2 || r1+r2>=9 || r1+5-r2>=9 || r1+5-r2<2)){
                        r1 = (int) (Math.random() * 6);
                        r2 = (int) (Math.random() * 6);
                    }
                    Tuile sauv = mesTuiles[x][y];
                    //System.out.println("Echange " + sauv + " et "+ mesTuiles[r1][r2]);
                    mesTuiles[x][y]= mesTuiles[r1][r2];
                    mesTuiles[r1][r2]= sauv;
                }
            }
            for (x=0;x<mesTuiles.length;x++){
                for(int y=0;y<mesTuiles[x].length;y++){
                    if (getTuile(x,y) != null){
                        getTuile(x,y).setCoords(new int[] {x,y});
                    }
                }
            }
        }
        
    }



    /**
     * 
    * @return un tableau de 6 par 6 de Tuile
     */
    public Tuile[][] getMesTuiles() {
            return mesTuiles;
    }
    
    /**
     *
     * @param depart
     * @param coords
     * @return renvoie les tuiles aux coordonnées relatives coords par rapport à depart
     */
    public ArrayList<Tuile> getVoisins(Tuile depart, ArrayList<int[]> coords) {
            ArrayList<Tuile> voisins = new ArrayList<>();
            int x,y;
            x = depart.getCoords()[0];
            y = depart.getCoords()[1];
            for(int[] coord : coords){
                Tuile tui = getTuile(x + coord[0],y + coord[1]);
                if(tui != null){
                    voisins.add(tui);
                }
            }
            return voisins;
    }



    /**
     * 
     * @param x de 0 à 5
     * @param y de 0 à 5
     * @return la tuile au coordonnées x et y si elle existe null sinon
     */


    public Tuile getTuile(int x, int y) { // est change en public pour demo
        if (x >=0 && x<=5 && y >=0 && y<=5 ){
            return getMesTuiles()[x][y];
        }else{
            return null;
        }
    }

    /**
     *
     * @return toutes les tuiles disponibles
     */
    public ArrayList<Tuile> getTuilesDisponibles() {
        ArrayList<Tuile> tuilesDispos = new ArrayList<>();
        for (Tuile[] mesTuile : mesTuiles) {
            for (Tuile mesTuile1 : mesTuile) {
                if(mesTuile1 != null){
                    if (mesTuile1.estDisponible()) {
                        tuilesDispos.add(mesTuile1);
                    }
                }
            }
        }
        return tuilesDispos;
    }

}