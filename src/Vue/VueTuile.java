package Vue;

import Controleur.Utils;
import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import Controleur.Utils.TuilesUtils;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public final class VueTuile extends PanelImage {
    private Image image;
    private final TuilesUtils tuile;
    private boolean estCoulee;
    private boolean activer;

    VueTuile (Tuile tuile) throws IOException{
        super(tuile.getNom().getAssecher());
        setDoubleBuffered(true); 
        estCoulee=false;
        this.tuile = tuile.getNom();
        GridLayout g= new GridLayout(2,2);
        g.setHgap(20);
        g.setVgap(20);
        setLayout(g);
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
        setBorder(new BevelBorder(BevelBorder.RAISED,Color.BLUE,Color.BLACK,Color.BLUE,Color.BLACK));
        setImage(this.tuile.getAssecher());
        activer(false);
        
    }

    public TuilesUtils getTuile() {
        return tuile;
    }
    //Modifie l'image
    @Override
    public void setImage(String etat) {
        try {
            this.image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/Images/"+etat));
            repaint();
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de "+etat);
        }
    }
    //Initialise la vue D'un pion
    public void initVuePion(VuePion vue){
        vue.setMaTuile(this);
    }
    //Ajoute la vue D'un pion
    public void addVuePion(VuePion vue){
        add(vue);
    }

    //Suprimme la vue D'un Pion
    public void supVuePion(VuePion vue){
        this.remove(vue);
    }
    //Change l'image de la tuile en fonction de l'état rentré en paramêtre
    public void changeEtat(Utils.EtatTuile etat){
        switch(etat){
            case COULEE:
                estCoulee=true;
            case ASSECHEE:
                setImage(tuile.getAssecher());
            case INONDEE:
                setImage(tuile.getInnonder());
        }
        repaint();
    }
        @Override
        protected void paintComponent(Graphics g1) {
             Graphics2D g = (Graphics2D) g1;
             Stroke sauv = g.getStroke();
            if(!estCoulee){
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
                if (activer){
                    g.setStroke(new BasicStroke(10));
                    g.drawRect(0, 0, this.getWidth(), this.getHeight());
                
                }
                g.setStroke(sauv);
                
            }
            else {
                setVisible(false);
            } 
        }

    
    
        @Override
    public String toString(){
        return tuile.getNom();
    }
//Active ou désactive la tuile en fonction du boolean b
    public void activer(boolean b) {
        activer=b;
        repaint();
        setEnabled(b);
    }
}
