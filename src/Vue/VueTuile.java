package Vue;

import Controleur.TypeAction;
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
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import Controleur.Utils.TuilesUtils;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;

public final class VueTuile extends JButton {
        private ArrayList<VuePion> mesPions;
        private JPanel mesVuesPions;
        private Image image;
        private TuilesUtils tuile;
        private boolean estCoulee;
        private Color fond;
    VueTuile (Tuile tuile){
        fond = null;
        estCoulee=false;
        this.tuile = tuile.getNom();
        mesPions = new ArrayList<>();
        setLayout(new BorderLayout());
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
        mesVuesPions = new JPanel(new GridLayout(2,2));
        add(mesVuesPions,BorderLayout.CENTER);
        for (int i =0;i<5;i++){
            mesVuesPions.add(new JPanel());
        }
        setImage(this.tuile.getAssecher());
        activer(false);
            
    }

    public TuilesUtils getTuile() {
        return tuile;
    }
    public void setFond(Color fond) {
        this.fond = fond;
    }

    public Color getFond() {
        return fond;
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
        mesVuesPions.add(vue);
    }


    public void supVuePion(VuePion vue){
        this.remove(vue);
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
                //g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
                g.setColor(Color.white);
                g.drawRect(0, 0, this.getWidth(), this.getHeight());
                
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
        setEnabled(b);
        if (b){
            setBackground(Color.red);
        }
        else{
            setBackground(Color.white);
        }
    }

    
}