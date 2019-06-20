/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author tardyj
 */
import javax.swing.JPanel;
public class PanelImage extends JPanel{
        protected java.awt.Image image;
        private String nomIm;
        private boolean vide;
        public PanelImage(String nom) throws IOException { 
            vide=false;
            setImage(nom);
        
    }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(vide==false){
                        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }

    public String getNomIm() {
        return nomIm;
    }
    public void toogleVide(){
        vide = !vide;
    }

    public boolean isVide() {
        return vide;
    }
    protected void setImage(String nom){
        setDoubleBuffered(true); 
            setNomIm(nom);
            setBackground(Color.white);
        try {
            this.image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/Images/"+nomIm));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de "+nom);
        }
    }     

    public void setNomIm(String nomIm) {
        this.nomIm = nomIm;
    }
}

