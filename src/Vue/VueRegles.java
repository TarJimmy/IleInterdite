/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton plus;
    private JButton moins;
    private JPanel center;
    private final CardLayout c1 = new CardLayout();
    VueRegles() throws IOException{
        window = new JFrame("Règle");
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(675,1000);
        window.setBackground(Color.white);
        
        JPanel panelBoutons = new JPanel(new GridLayout(1,2));
        panelBoutons.setPreferredSize(new Dimension(window.getWidth(),100));
        window.add(panelBoutons,BorderLayout.NORTH);
        //Dimension et taille du textes des boutons
        Dimension dim =new Dimension(200,80);
        Font f = new Font(Font.SERIF,Font.BOLD,dim.height/2);
        //boutons mois et plus
        JPanel panelMoins = new JPanel();
        panelMoins.setBackground(Color.white);
        panelBoutons.add(panelMoins);
        JPanel panelPlus= new JPanel();
        panelPlus.setBackground(Color.white);
        panelBoutons.add(panelPlus );
        plus = new JButton(">>");
        plus.setFont(f);
        plus.setBackground(Color.gray);
        plus.setPreferredSize(dim);
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c1.next(center);
            }
        });
        moins = new JButton("<<");
        moins.setBackground(Color.gray);
        moins.setFont(f);
        moins.setPreferredSize(dim);
        
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
    
}
