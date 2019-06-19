/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Utils.Pion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author tardyj
 */

public class VuePion extends JPanel {
    private VueTuile maTuile;
    private Pion pion;
    public VuePion(Pion pion){
        this.pion = pion;
        setBackground(Color.white);
        setDoubleBuffered(true);
    }

    public void setMaTuile(VueTuile maTuile) {
        if (this.maTuile!=null){
            this.maTuile.supVuePion(this);
        }
        this.maTuile = maTuile;
        maTuile.addVuePion(this);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        
        Dimension dimension = getSize(); // Taille de la zone de dessin
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);//En rouge
        g.fillArc(0,0,50,50,0,180);//Demi cercle plein
        g.setColor(Color.black);//couleur noire
        g.drawArc(0,0,50,50,0,180);//Demicercle vide
    }
}
