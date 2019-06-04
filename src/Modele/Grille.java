package Modele;

import Controleur.Utils;
import Controleur.Utils.nomTuile;
import java.util.*;

public final class Grille {

    public static Tuile[][] mesTuiles;
    private int EchelonMonteEau;
 
    public Grille(int echelon){
        this.EchelonMonteEau=echelon;
        initTableau();
    }
    
    /**
     *
     * @param nom
     * @return
     */
    
    public Tuile getTuile(nomTuile nom){
        Tuile tui = null;
        System.out.println(nom);
        for(int x=0;x<mesTuiles.length;x++){
           for(int y=0;y<mesTuiles[x].length;y++){
                if (getTuile(x,y) != null){
                    if (getTuile(x,y).getNom().equals(nom)){
                        System.out.println(getTuile(x,y).getNom());
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
                    System.out.println("Ajoute NULL");
                }
                else{
                    mesTuiles[x][y] = new Tuile(Utils.nomTuile.values()[i]);
                    System.out.println("Ajoute " + mesTuiles[x][y]);
                    i++;
                }
            }
        }
        //Melange le tableau
        System.out.println("Mélange Le Tableau");
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
                    System.out.println("Echange " + sauv + " et "+ mesTuiles[r1][r2]);
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

        
              
    




    public boolean partieGagner() {
        return false;
            // TODO - implement Grille.partieFini
            
    }

    /**
     * 
     * @param Tuile
 * @return 
     */
    public Tuile[][] getMesTuiles() {
            return mesTuiles;
    }
    
    public ArrayList<Tuile> getVoisins(Tuile depart, ArrayList<int[]> coords) {
            ArrayList<Tuile> voisins = new ArrayList<>();
            int x,y;
            x = depart.getCoords()[0];
            y = depart.getCoords()[1];
            for(int[] coord : coords){
                Tuile tui =getTuile(x + coord[0],y + coord[1]);
                if(tui != null){
                    voisins.add(tui);
                }
            }
            return voisins;
    }



    /**
     * 
     * @param coords
     */


    public Tuile getTuile(int x, int y) { // est change en public pour demo
        if (x >=0 && x<=5 && y >=0 && y<=5 ){
            return getMesTuiles()[x][y];
        }else{
            return null;
        }
    }

    public ArrayList<Tuile> getTuilesDisponibles() {
        ArrayList<Tuile> tuilesDispos = new ArrayList<>();
        for (Tuile[] mesTuile : mesTuiles) {
            for (Tuile mesTuile1 : mesTuile) {
                if (mesTuile1.estDisponible()) {
                    tuilesDispos.add(mesTuile1);
                }
            }
        }
        return tuilesDispos;
    }
    
    public static void main(String[]args){
        Grille grille = new Grille(2);
        Ingenieur ing = new Ingenieur(grille);
        System.out.println(ing.getDeplacement(grille));
//        ArrayList<int[]> coords = new ArrayList<>();
//        coords.add(new int[] {0,1});
//        coords.add(new int[] {1,0});
//        System.out.println("on peut bien ajouter des valeur dans ce vecteur");
//        Tuile depart = grille.getTuile(3,3);
//        System.out.println("la tuile est bien recuperer");
//        
//       ArrayList<Tuile> voisins = grille.getVoisins(depart,coords);
            System.out.println(grille.getTuile(nomTuile.caverne_du_brasier));
//                
//        //for(Tuile tui : voisins){
//            int[] coord = depart.getCoords();
//            System.out.println("les coordonnees sont : x = " + coord[0] + " y = " + coord[1]);
//        //}
//        for (int x=0;x<grille.mesTuiles.length;x++){
//            for(int y=0;y<grille.mesTuiles[x].length;y++){
//               if(grille.mesTuiles[x][y] != null){
//                    System.out.println(grille.mesTuiles[x][y].getNom());
//               }else{
//                   System.out.println("NULL");
//               }
//            }
//        } 
//        System.out.println("coucou");

                
    }
}