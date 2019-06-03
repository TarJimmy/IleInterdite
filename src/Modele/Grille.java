package Modele;

import Controleur.Utils;
import Controleur.Utils.nomTuile;
import java.util.*;

public final class Grille {

    public static Tuile[][] mesTuiles;
    private int EchelonMonteEau;
    private Tuile depIng;
    private Tuile depExplo;
    private Tuile depMes;
    private Tuile depPlon;
    private Tuile depPilote;
    private Tuile depNav;
    
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
        int x=0, y=0;
        Tuile tui = null;
        while (x<6 && tui == null){
           while (y<6 && tui == null){
                if (getTuile(x,y) != null){
                    if (getTuile(x,y).getNom().name().equals(nom.name())){
                        tui = getTuile(x,y);
                    }
                }
                y++;
            }
            x++;
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
                    int[] c = {x,y};
                    mesTuiles[x][y] = new Tuile(Utils.nomTuile.values()[i] ,c);
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
                    System.out.println("r1 = " + r1 + "\tr2 = " + r2);
                    Tuile sauv = mesTuiles[x][y];
                    mesTuiles[x][y]= mesTuiles[r1][r2];
                    mesTuiles[r1][r2]= sauv;
                }
            }
        }
        //Recupere depart
        depPilote = getTuile(nomTuile.heliport); // erreur sur getTuile
        depExplo = getTuile(nomTuile.porte_de_cuivre);
        depIng = getTuile(nomTuile.porte_de_bronze);
        depNav = getTuile(nomTuile.porte_dor);
        depPlon = getTuile(nomTuile.porte_de_fer);
        depMes = getTuile(nomTuile.porte_dargent);
        
    }

    public Tuile getDepIng() {
        return depIng;
    }

    public Tuile getDepExplo() {
        return depExplo;
    }

    public Tuile getDepMes() {
        return depMes;
    }

    public Tuile getDepPlon() {
        return depPlon;
    }

    public Tuile getDepPilote() {
        return depPilote;
    }

    public Tuile getDepNav() {
        return depNav;
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
        ArrayList<int[]> coords = new ArrayList<>();
        coords.add(new int[] {0,1});
        coords.add(new int[] {1,0});
        System.out.println("on peut bien ajouter des valeur dans ce vecteur");
        Tuile depart = grille.getTuile(3,3);
        System.out.println("la tuile est bien recuperer");
        
        ArrayList<Tuile> voisins = grille.getVoisins(depart,coords);
            System.out.println("la methode getVoisins a marche\n\n");
                
        /*for(Tuile tui : voisins){
            int[] coord = tui.getCoords();
            System.out.println("les coordonnees sont : x = " + coord[0] + " y = " + coord[1]);
        }*/
        for (int x=0;x<grille.mesTuiles.length;x++){
            for(int y=0;y<grille.mesTuiles[x].length;y++){
               if(grille.mesTuiles[x][y] != null){
                    System.out.println(grille.mesTuiles[x][y].getNom());
               }else{
                   System.out.println("NULL");
               }
            }
        } 
        System.out.println("coucou");
        System.out.println("test : " + grille.getTuile(nomTuile.caverne_du_brasier));

                
    }
}