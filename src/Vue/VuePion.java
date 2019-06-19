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
        int diametre = getWidth()<getHeight()?9*getWidth()/10 : 9*getHeight()/10; //Si width<height -> diametre = 90% taille Width Sinon diametre = 90% taille de Heigth
        g2d.setColor(pion.getCouleur());
        g2d.fillOval((getWidth()-diametre)/2,(getHeight()-diametre)/2,diametre, diametre); //Place un cercle en haut a gauche de taille diametre
        
    }
}
