package Vue;

import Controleur.Utils;
import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import Controleur.Utils.TuilesUtils;
import java.awt.Graphics;

public class VueTuile extends JButton {
        private ArrayList<VuePion> mesPions;
        private JPanel mesVuePions;
        private Image image;
        private TuilesUtils tuile;
        private boolean estCoulee;
    VueTuile (Tuile tuile){
        estCoulee=false;
        this.tuile = tuile.getNom();
        mesPions = new ArrayList<>();
        setLayout(new BorderLayout());
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
        mesVuePions = new JPanel(new GridLayout(1,4));
        add(mesVuePions,BorderLayout.SOUTH);
        setImage(this.tuile.getAssecher());
    }
     
    public void setImage(String etat) {
        try {
            this.image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/Images/"+etat));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de "+etat);
        }
    }
    
    public void initVuePion(VuePion vue){
        vue.setMaTuile(this);
    }
    public void addVuePion(VuePion vue){
        mesVuePions.add(vue);
    }
    public void supVuePion(VuePion vue){
        mesVuePions.remove(vue);
    }
    public void changeFond(){
        repaint();
    }
    public void changeEtat(Utils.EtatTuile etat){
        switch(etat){
            case COULEE:
                estCoulee=true;
            case ASSECHEE:
                setImage(tuile.getAssecher());
            case INONDEE:
                setImage(tuile.getInnonder());
        }
        changeFond();
    }
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(!estCoulee){
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            else {
                setVisible(false);
            } 
        }
    
    
        @Override
    public String toString(){
        return tuile.getNom();
    }
}