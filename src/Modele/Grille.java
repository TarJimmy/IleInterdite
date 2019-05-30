package Modele;

import Modele.Utils.nomTuile;
import java.util.*;

public final class Grille {

	private Tuile[][] mesTuiles;
	private int EchelonMonteEau;

        
        public Grille(int echelon){
            this.EchelonMonteEau=echelon;
            initTableau();
            System.out.println(mesTuiles[1].length);
        }
        
        
        private void initTableau(){
            mesTuiles = new Tuile[6][6];
            int i =0;
            for (int x = 0;x<6;x++){
                for (int y=0;y<6;y++){
                    if (x+y<2 || x+y>=9 || x+5-y>=9 || x+5-y<2){
                        mesTuiles[x][y]=null;
                    }
                    else{
                        int[] c = {x,y};
                        mesTuiles[x][y] = new Tuile(Utils.nomTuile.values()[i] ,c);
                        i++;
                    }
                }
            }
        }
        
        
        
	public boolean partieGagner() {
		// TODO - implement Grille.partieFini
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Tuile
     * @return 
	 */
	public Tuile[][] getMesTuiles() {
		// TODO - implement Grille.getTuile
		return mesTuiles;
                
	}


	public int[] getVoisins(Tuile depart, int[] coords) {       //je ne vois pas ce que c'est, si tu pouvait m'expliquer à l'occase à quoi elle sert (ça peut peut être m'être utile)
		// TODO - implement Grille.getVoisins
		int[] mesVoisins = new int[4];
                mesVoisins[4]= -1;                                  //cette ligne provoque un NullPointerException
                int i=0;
                while (i< 6 || mesVoisins[4]!=-1){
                    
                }
                
                return mesVoisins;
	}
        public ArrayList<Tuile> getVoisins(Tuile depart, ArrayList<int[]> coords) {
                ArrayList<Tuile> voisins = new ArrayList<>();
                int x,y;
                x = depart.getCoords()[0];
                y = depart.getCoords()[1];
                for(int[] coord : coords){
                    voisins.add(getTuile(x + coord[0],y + coord[1]));
                }
                return voisins;
        }
	


	/**
	 * 
	 * @param coords
	 */
	public Tuile getTuile(int[] coords) {
		// TODO - implement Grille.getTuile
		throw new UnsupportedOperationException();
	}
 public static void main(String[]args){
        Grille grille = new Grille(2);
    }

    private Tuile getTuile(int x, int y) {
        if (x >=0 && x<5 && y >=0 && y<5 ){
            return getMesTuiles()[x][y];
        }else{
            return null;
        }
    }
}