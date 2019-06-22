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
     
        @Override
    public void setImage(String etat) {
        try {
            this.image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/Images/"+etat));
            repaint();
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de "+etat);
        }
    }
    
    public void initVuePion(VuePion vue){
        vue.setMaTuile(this);
    }
    public void addVuePion(VuePion vue){
        add(vue);
    }


    public void supVuePion(VuePion vue){
        this.remove(vue);
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
        repaint();
    }
        @Override
        protected void paintComponent(Graphics g1) {
             Graphics2D g = (Graphics2D) g1;
            if(!estCoulee){
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
                if (activer){
                    g.setStroke(new BasicStroke(10));
                
                }
                //g.setStroke(new BasicStroke(4));
                
            }
            else {
                setVisible(false);
            } 
        }

    
    
        @Override
    public String toString(){
        return tuile.getNom();
    }

    public void activer(boolean b) {
        activer=b;
        repaint();
        setEnabled(b);
        
    }
    public static void main(String[]args) throws IOException{
        JFrame j = new JFrame("test");
        j.setLocationRelativeTo(null);
        j.setSize(500, 500);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(new VueTuile(new Tuile(TuilesUtils.heliport)));
        j.setVisible(true);
    }
    
}
