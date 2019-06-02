package Vue;

import Modele.Grille;
import Modele.Tuile;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueGrille extends Controleur.Observe {

	/**
	 * 
	 * @param collecTuiles
	 */
        private JPanel vueGrille ;
	public VueGrille (Grille grille){
            vueGrille = new JPanel(new GridLayout(6,6));
            initGrille(grille);
        }
        
        private void initGrille(Grille grille){
            Tuile[][] tuiles = grille.getMesTuiles();
            for (int x=0;x<tuiles.length;x++){
                for (int y=0;y<tuiles[x].length;y++){
                    if (x+y<2 || x+y>=9 || x+5-y>=9 || x+5-y<2){
                        JPanel j = new JPanel();
                        j.setBackground(Color.white);
                        vueGrille.add(j);
                    }
                    else{
                        vueGrille.add(new VueTuile(tuiles[x][y]));
                    }
                }
            }
        }
        public static void main (String[] args){
            JFrame f = new JFrame("Test");
            f.setSize(1000,500);
            System.out.println("cou");
            Grille g = new Grille(2);
            System.out.println("cou2");
            VueGrille gr = new VueGrille(g);
            f.add(gr.vueGrille);
            f.setVisible(true);
        }

    public JPanel getVueGrille() {
        return vueGrille;
    }
}