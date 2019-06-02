package Vue;

import Modele.Aventurier;
import Modele.CarteJoueur;
import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueJeu {

	private ArrayList<VueAventurier> mesAvs;
        private JFrame window = new JFrame();
        public VueJeu(VueGrille grille,ArrayList<VueAventurier> mesAvs){
            System.out.println("Initialise la fenetre VueJeu (Layout : BorderLayout");
            initWindow(this.mesAvs);
            System.out.println("Recupere le nombre d'aventurier");
            int taille = mesAvs.size();
            System.out.println("Change le layout en GridLayout(tailleRecuperer,1)");
            JPanel mesVuesAvs = new JPanel(new GridLayout(4,1));
            mesVuesAvs.setBackground(Color.white);
            for (int i=0; i<taille;i++){
                mesVuesAvs.add(mesAvs.get(i).getPrincipal());
                System.out.println("Ajoute " + mesAvs.get(i) + "a liste des VueAventuriers");
                
            }
            System.out.println("Ajoute mesVuesAvs à la fenetre (A l'est");
            window.add(mesVuesAvs,BorderLayout.EAST);
            System.out.println("Ajoute la grille au centre");
            window.add(grille.getVueGrille(),BorderLayout.CENTER);
            
            
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
            this.mesAvs = mesAvs;
            window.setResizable(false);
            window.setDefaultLookAndFeelDecorated(true);
            window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            window.setSize(1600,800);
            
            window.setLayout(new BorderLayout());
        }

        public void afficher(boolean b) {
            window.setVisible(b);
        }

}