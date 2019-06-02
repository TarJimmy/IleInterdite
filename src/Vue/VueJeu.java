package Vue;

import Modele.Aventurier;
import Modele.CarteJoueur;
import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueJeu {

	private ArrayList<VueAventurier> mesAvs;
        private JFrame window = new JFrame();
        public VueJeu(VueGrille grille,ArrayList<VueAventurier> mesAvs){
            initWindow(mesAvs);
            int taille = mesAvs.size();
            JPanel mesVuesAvs = new JPanel(new GridLayout(taille,1));
            for (int i=0; i<taille;i++){
                mesVuesAvs.add(mesAvs.get(i));
            }
            window.add(mesVuesAvs,BorderLayout.EAST);
            window.add(grille,BorderLayout.CENTER);
            
            
        }
	/**
	 * 
	 * @param ActionsPossible
	 */

	public void erreurTresor() {
		// TODO - implement vueJeu.erreurTresor
		throw new UnsupportedOperationException();
	}

	public void PartieGagner() {
		// TODO - implement vueJeu.PartieGagner
		throw new UnsupportedOperationException();
	}

	public void PartiePerdu() {
		// TODO - implement vueJeu.PartiePerdu
		throw new UnsupportedOperationException();
	}

	public void TrDeJeu() {
		// TODO - implement vueJeu.TrDeJeu
		throw new UnsupportedOperationException();
	}
	
        private void initWindow(ArrayList<VueAventurier> mesAvs){
            int heigth = mesAvs.size()*200;
            int width = mesAvs.size()*400;
            window.setResizable(false);
            window.setDefaultLookAndFeelDecorated(true);
            
            window.setSize(width,heigth);
            
            window.setLayout(new BorderLayout());
        }

        public void afficher(boolean b) {
            window.setVisible(b);
        }

}