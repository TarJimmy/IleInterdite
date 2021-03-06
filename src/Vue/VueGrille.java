package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observe;
import Controleur.TypeAction;
import Controleur.Utils;
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
import java.io.IOException;
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
        private MouseListener act;
	public VueGrille (Grille grille,ArrayList<Aventurier> avs) throws IOException{
            GridLayout g =new GridLayout(6,6);
            g.setHgap(10);
            g.setVgap(10);
            msg = new MessageAction();
            vueGrille = new JPanel(g);
            vueGrille.setBackground(Color.white);
            vueGrille.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            initGrille(grille);
            creePion(avs);
            vueModif = new ArrayList<>();
        }
        //Initailise les cases de la grille
        private void initGrille(Grille grille) throws IOException{
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
        //return la tuile avec pour coordonnées coords
        public VueTuile getVueTuile(int[] coords){
            return vuesTuiles[coords[0]][coords[1]];
        }
        
        
        private int[] getCoords(VueTuile vue) { // renvoie la VueTuile vue de la Vuegrille
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
    //Actualise la vueGrille (tout les élements de la vueModis)
    public void actualise() {
        for (VueTuile tuile : vueModif){
            tuile.removeMouseListener(act);
            tuile.activer(false);
        }
        vueModif.clear();
    }

    //Crée des pions sur leurs cases associés
    private void creePion(ArrayList<Aventurier> avs){
        mesPions = new ArrayList<>();
        int i=0;
        for (Aventurier av : avs){
            mesPions.add(new VuePion(av.getPion()));
            getVueTuile(av.getMaPos().getCoords()).initVuePion(mesPions.get(i));
            i++;
        }
    }
    //Fais le choix de la tuile est envoie un type message different si c'est pour assecher une tuile ou se déplacer dessus
    //Toute les vues afficher sont sauvegardé dans vueModifs
    public void faireChoixTuile( int a,ArrayList<Tuile> tuiles) {
            act = new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent arg0) {}
                @Override
                public void mousePressed(MouseEvent arg0) {}
                @Override
                public void mouseExited(MouseEvent arg0) {
                    VueTuile vue = (VueTuile) arg0.getSource();
                    vue.setBackground(Color.red);
                }
                @Override
                public void mouseEntered(MouseEvent arg0) {
                    VueTuile vue = (VueTuile) arg0.getSource();
                    vue.setBackground(Color.green);
                }
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    switch(a){
                        case VueJeu.CHOIX_DEP :
                            msg.typeact = TypeAction.CHOIX_TUILE_DEP;
                            break;
                        case VueJeu.CHOIX_AS :
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
            vuesTuiles[x][y].activer(true);
            vuesTuiles[x][y].addMouseListener(act);
            vueModif.add(vuesTuiles[x][y]);
        }
    }
    
    public ArrayList<VuePion> getMesPions() {
        return mesPions;
    }
    //Déplace le pion pion sur la tuile t
    public void deplacePion(Utils.Pion pion, Tuile t) {
        for (VuePion vuePion : mesPions){
            if (vuePion.getPion()==pion){
                vuePion.setMaTuile(this.getVueTuile(t.getCoords()));
            }
        }
    }
}
       

    
    


    

