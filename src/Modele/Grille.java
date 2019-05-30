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

	/**
	 * 
	 * @param depart
	 * @param coords
	 */
	public int[] getVoisins(Tuile depart, int[] coords) {
		// TODO - implement Grille.getVoisins
		int[] mesVoisins = new int[4];
                mesVoisins[4]= -1;
                int i=0;
                while (i< 6 || mesVoisins[4]!=-1){
                    
                }
                
                return mesVoisins;
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
}