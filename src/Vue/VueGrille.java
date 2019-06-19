package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.TypeAction;
import Modele.Grille;
import Modele.Tuile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class VueGrille extends Controleur.Observe {

	/**
	 * 
	 * @param collecTuiles
	 */
        private final JPanel vueGrille ;
        private VueTuile[][] vuesTuiles;
        private ArrayList<VueTuile> vueModif;
        private MessageAction msg;
	public VueGrille (Grille grille){
            GridLayout g =new GridLayout(6,6);
            g.setHgap(10);
            g.setVgap(10);
            msg = new MessageAction();
            vueGrille = new JPanel(g);
            vueGrille.setBackground(Color.white);
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
                        vuesTuiles[x][y]= new VueTuile(tuiles[x][y]);
                        vueGrille.add(vuesTuiles[x][y]);
                        vuesTuiles[x][y].setPreferredSize(new Dimension(vueGrille.getWidth()/6,vueGrille.getHeight()/6));
                    }
                }
            }
        }
        public VueTuile getVueTuile(int[] coords){
            if(vuesTuiles[coords[0]][coords[1]]!= null){
                return vuesTuiles[coords[0]][coords[1]];
            }
            else{
                return null;
            }
        }
        
        
        public int[] getCoords(VueTuile vue) { // est change en public pour demo
            int[] c = new int[2];
            for( int x=0;x<vuesTuiles.length;x++){
                for (int y=0;y<vuesTuiles[x].length;y++){
                    if (vuesTuiles[x][y]==vue){
                        c = new int[]{x,y};
                    }
                }
            } 
            return c;
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

    public void actualise() {
        for (VueTuile tuile : vueModif){
            tuile.changeFond();
        }
    }

    public void faireAction(int a, ArrayList<Tuile> deplacement) {
        vueModif= new ArrayList<>();
            ActionListener act = new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        switch(a){
                                                            case 1 : 
                                                               msg.typeact = TypeAction.CHOIX_TUILE_DEP; 
                                                               break;
                                                            case 2:
                                                               msg.typeact = TypeAction.CHOIX_TUILE_AS; 
                                                               break;
                                                            case 3:
                                                                msg.typeact = TypeAction.CHOIX_TUILE_DEP;
                                                                break;
                                                        }
                                                        
                                                        VueTuile vue = (VueTuile) e.getSource();
                                                        //m.coord = getCoords(vue);
                                                       // notifierMessageAction(m);
                                                    }
                                                    };
               /* for (Tuile tui : //tuiles){
                    int x = tui.getCoords()[0];
                    int y = tui.getCoords()[1];
                    vuesTuiles[x][y].setBackground(Color.gray);
                    vuesTuiles[x][y].addActionListener(act);
                    vueModif.add(vuesTuiles[x][y]);
                }
                */
            }
     private void choixAventurier(ArrayList<VueAventurier> vues){
        }

    public void faireChoixTuile(int b, ArrayList<Tuile> assechement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void faireChoixVueAventuriers(int c, ArrayList<VueAventurier> translateAve_VueAvs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void faireChoixVueAventuriers(ArrayList<VueAventurier> translateAve_VueAvs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /* public void faireChoixVueAventuriers(ArrayList<VueAventurier> translateAve_VueAvs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void faireChoixVueAventuriers(ArrayList<VueAventurier> translateAve_VueAvs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void faireChoixVueAventuriers(ArrayList<VueAventurier> translateAve_VueAvs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   */

    
     private class choixAv extends JFrame{
         
     }
    }
       

    
    


    

