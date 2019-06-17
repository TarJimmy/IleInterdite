package Vue;

import Modele.Grille;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueJeu  {

    private ArrayList<VueAventurier> mesAvs;

    public MonteeDesEaux getMonteeDesEau() {
        return monteeDesEau;
    }
    private JFrame window;
    private MonteeDesEaux monteeDesEau;
    private JLabel indications;
    public class MonteeDesEaux extends PanelImage {
        private int niveauEau;
        private MonteeDesEaux() throws IOException { 
            super("Niveau.png");
            this.setPreferredSize(new Dimension(window.getWidth()/7,window.getHeight()));
        }

        public void setNiveauEau(int niveauEau) {
            this.niveauEau = niveauEau;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(15, getHeight()-(niveauEau)*60-130, 130, 10);
        }
    }
    
    public VueJeu(VueGrille grille,ArrayList<VueAventurier> mesAvs) throws IOException {
        
        initWindow(this.mesAvs);
        monteeDesEau = new MonteeDesEaux();
        this.mesAvs = mesAvs;
        initHaut(grille);
        initBas();
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



    private void initWindow(ArrayList<VueAventurier> mesAvs){
        window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        window.setSize(tk.getScreenSize().width,tk.getScreenSize().height);
        window.setLayout(new BorderLayout());
    }
    private void initBas() {
        JPanel bas = new JPanel();
        bas.setPreferredSize(new Dimension(window.getWidth(),300));
        window.add(bas,BorderLayout.SOUTH);
    }

    private JPanel initHaut(VueGrille grille) {
        JPanel haut = new JPanel(new BorderLayout());
        int taille = mesAvs.size();
        //VueAventurier
        JPanel mesVuesAvs = new JPanel(new GridLayout(4,1));
        mesVuesAvs.setBackground(Color.white);
        for (int i=0; i<taille;i++){
            mesVuesAvs.add(mesAvs.get(i).getPrincipal());
        }
        haut.add(monteeDesEau,BorderLayout.WEST);
        haut.add(mesVuesAvs,BorderLayout.EAST);
        haut.add(grille.getVueGrille(),BorderLayout.CENTER);
        window.add(haut,BorderLayout.CENTER);
        return haut;
    }

    public void afficher(boolean b) {
        window.setVisible(b);
    }
    public static void main(String[]args) throws IOException{
        VueGrille g = new VueGrille(new Grille(5));
        ArrayList<VueAventurier> mesAvs = new ArrayList<>();
        mesAvs.add(new VueAventurier("Jimmy", "Ingenieur", Color.blue));
        mesAvs.add(new VueAventurier("Jimmy", "Ingenieur", Color.blue));
        VueJeu v = new VueJeu(g,mesAvs);
        v.afficher(true);
    }

}