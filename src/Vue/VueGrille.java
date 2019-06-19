package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observe;
import Controleur.TypeAction;
import Modele.Aventurier;
import Modele.Grille;
import Modele.Tuile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class VueGrille extends Observe {

	/**
	 * 
	 * @param collecTuiles
	 */
        private final JPanel vueGrille ;
        private VueTuile[][] vuesTuiles;
        private ArrayList<VueTuile> vueModif;
        private MessageAction msg;
        private ArrayList<VuePion> mesPions;
	public VueGrille (Grille grille,ArrayList<Aventurier> avs){
            GridLayout g =new GridLayout(6,6);
            g.setHgap(10);
            g.setVgap(10);
            msg = new MessageAction();
            vueGrille = new JPanel(g);
            vueGrille.setBackground(Color.white);
            vueGrille.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            initGrille(grille);
            creePion(avs);
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

    
        
    public JPanel getVueGrille() {
        return vueGrille;
    }

    public void actualise() {
        for (VueTuile tuile : vueModif){
            tuile.changeFond();
        }
    }

    
    private void creePion(ArrayList<Aventurier> avs){
        mesPions = new ArrayList<>();
        int i=0;
        System.out.println(avs);
        System.out.println("heu");
        for (Aventurier av : avs){
            System.out.println(avs);
            System.out.println("gfgdfs");
            mesPions.add(new VuePion(av.getPion()));
            getVueTuile(av.getMaPos().getCoords()).initVuePion(mesPions.get(i));
            i++;
        }
    }
     private void choixAventurier(ArrayList<VueAventurier> vues){
        }

    public void faireChoixTuile( int a,ArrayList<Tuile> tuiles) {
        vueModif= new ArrayList<>();
            MouseListener act = new MouseListener() {
                                                        @Override
                                                        public void mouseReleased(MouseEvent arg0) {
                                                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                                        }
                                                        @Override
                                                        public void mousePressed(MouseEvent arg0) {
                                                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                                        }
                                                        @Override
                                                        public void mouseExited(MouseEvent arg0) {
                                                            VueTuile vue = (VueTuile) arg0.getSource();
                                                            vue.setFond(null);
                                                        }
                                                        @Override
                                                        public void mouseEntered(MouseEvent arg0) {
                                                           VueTuile vue = (VueTuile) arg0.getSource();
                                                           vue.setFond(Color.red);
                                                        }
                                                        @Override
                                                        public void mouseClicked(MouseEvent arg0) {
                                                            switch(a){
                                                                case 1 : 
                                                                   msg.typeact = TypeAction.CHOIX_TUILE_DEP; 
                                                                   break;
                                                                case 2:
                                                                   msg.typeact = TypeAction.CHOIX_TUILE_AS; 
                                                                   break;
                                                                }
                                                        VueTuile vue = (VueTuile) arg0.getSource();
                                                        msg.coord = getCoords(vue);
                                                        notifierMessageAction(msg);
            }
                                                    
        };
        for (Tuile tui : tuiles){
            
            int x = tui.getCoords()[0];
            int y = tui.getCoords()[1];
            vuesTuiles[x][y].setBackground(Color.gray);
            vuesTuiles[x][y].addMouseListener(act);
            vueModif.add(vuesTuiles[x][y]);
        }
    }

    public void faireChoixVueAventuriers( ArrayList<VueAventurier> translateAve_VueAvs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<VuePion> getMesPions() {
        return mesPions;
    }

    

   

    
     private class choixAv extends JFrame{
         
     }
    }
       

    
    


    

