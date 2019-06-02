package Vue;

import Controleur.MessageAction;
import Controleur.TypeAction;
import Modele.Grille;
import Modele.Tuile;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class VueGrille extends Controleur.Observe {

	/**
	 * 
	 * @param collecTuiles
	 */
        private JPanel vueGrille ;
        private VueTuile[][] vuesTuiles;
	public VueGrille (Grille grille){
            vueGrille = new JPanel(new GridLayout(6,6));
            vueGrille.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            initGrille(grille);
        }
        
        private void initGrille(Grille grille){
            vuesTuiles = new VueTuile[6][6];
            Tuile[][] tuiles = grille.getMesTuiles();
            for (int x=0;x<tuiles.length;x++){
                for (int y=0;y<tuiles[x].length;y++){
                    if (x+y<2 || x+y>=9 || x+5-y>=9 || x+5-y<2){
                        JPanel j = new JPanel();
                        j.setBackground(Color.white);
                        vueGrille.add(j);
                        vuesTuiles[x][y]= null;
                    }
                    else{
                        vuesTuiles[x][y]= new VueTuile(tuiles[x][y],x,y);
                        vueGrille.add(vuesTuiles[x][y]);
                    }
                }
            }
        }
        public VueTuile getTuile(int x, int y) { // est change en public pour demo
        if (x >=0 && x<=5 && y >=0 && y<=5 ){
            return vuesTuiles[x][y];
        }else{
            return null;
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
        public void proposeCase(ArrayList<Tuile> tuiles){
            for (int x=0;x<vuesTuiles.length;x++){
                for (int y=0;y<vuesTuiles[x].length;y++){
                    for (Tuile tui:tuiles){
                        int x1 = tui.getCoords()[0];
                        int y1 = tui.getCoords()[1];
                        if (x==x1&&y==y1){
                            vuesTuiles[x][y].setEnabled(true);
                            vuesTuiles[x][y].addActionListener(new ActionListener() {
                                                                                    @Override
                                                                                    public void actionPerformed(ActionEvent e) {
                                                                                        MessageAction m= new MessageAction();
                                                                                        m.typeact = TypeAction.CHOIX_TUILE;
                                                                                        m.tui = tui;
                                                                                        notifierMessageAction(m);
                                                                                    }
                                                                                    });
                        }
                        else{
                            vuesTuiles[x][y].setEnabled(false);
                        }
                    }
                }
                
            }
        }

    public JPanel getVueGrille() {
        return vueGrille;
    }
}