/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author tardyj
 */
public class VueRegles {
    private int i;
    private JFrame window;
    private PanelImage[] image;
    private BoutonsPerso plus;
    private BoutonsPerso moins;
    private JPanel center;
    private final CardLayout c1 = new CardLayout();
    VueRegles() throws IOException{
        window = new JFrame("Règle");
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(675,1000);
        window.setBackground(Color.white);
        
        JPanel panelBoutons = new JPanel(new GridLayout(1,2));
        panelBoutons.setPreferredSize(new Dimension(window.getWidth(),100));
        window.add(panelBoutons,BorderLayout.NORTH);
        //Dimension et taille du textes des boutons
        final Dimension dim =new Dimension(200,80);
        final Font f = new Font(Font.SERIF,Font.BOLD,dim.height/4);
        //boutons mois et plus
        JPanel panelMoins = new JPanel();
        panelMoins.setBackground(Color.white);
        panelBoutons.add(panelMoins);
        JPanel panelPlus= new JPanel();
        panelPlus.setBackground(Color.white);
        panelBoutons.add(panelPlus );
        plus = new BoutonsPerso("Suivant >>");
        plus.setFont(f);
        plus.setPreferredSize(dim);
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.next(center);
            }
        });
        moins = new BoutonsPerso("<< Précedent");
        moins.setPreferredSize(dim);
        moins.setFont(f);
        moins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.previous(center);
            }
        });
        panelMoins.add(moins);
        panelPlus.add(plus);
        center = new JPanel(c1);
        initImageRegle();
        window.add(center);
        window.setVisible(true);
    }
    //Ajoute les images des règles
    private void initImageRegle() throws IOException{
        image = new PanelImage[8];
        for (int i =0;i<image.length;i++){
            image[i] = new PanelImage("Regles/Ile_Interdite-regles"+(i+1)+".png");
            center.add(image[i],i);
        }
    }
       public static void main(String[] args) throws IOException{
           new VueRegles();
       }
       //Permet la customisations des Boutons
    private class BoutonsPerso extends JButton implements MouseListener{
        private final Color couleur = Color.lightGray;
        public BoutonsPerso(String nom){
            super(nom);
        setFocusable(false);
        setBackground(couleur);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setColor(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 3),
            (getHeight() - 3), 12, 8));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(4, getHeight() - 3, getWidth() - 4, getHeight() - 3);
        g2.dispose();
    } 

        @Override
        public void mouseClicked(MouseEvent arg0) {}

        @Override
        public void mousePressed(MouseEvent arg0) {
            setBackground(Color.white);
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            setBackground(couleur);
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {}

        @Override
        public void mouseExited(MouseEvent arg0) {}
    }
}
