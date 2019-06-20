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
import java.awt.Point;
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
    @SuppressWarnings("empty-statement")
    public void paintComponent(Graphics g) {
        
        Dimension dimension = getSize(); // Taille de la zone de dessin
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);//Couleur du détour
        
        int taille;
        taille = (int)Math.min(dimension.getWidth()*0.3,dimension.getHeight()*0.3);
        int start;
        start = (int)(dimension.getWidth()/2)-(taille/2);
        int end;
        end = (int)(dimension.getWidth()/2)+(taille/2);   
        
        g.drawOval(start, 0, taille, taille);
        
        int x[];
        x = new int[] {start-(taille/2),end+(taille/2),(int)(dimension.getWidth()/2)};//contour du cercle
        
        int y[];
        y = new int[] {(int)(dimension.getHeight()),(int)(dimension.getHeight()),taille/2};
        
        g.drawPolygon(x, y, 3);//contour du triangle
        
        g.setColor(pion.getCouleur());//couleur du pion
        g.fillOval(start, 0, taille, taille);
        g.fillPolygon(x, y, 3);
    }
}
