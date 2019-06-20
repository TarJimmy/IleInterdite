/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Gholbin
 */
public class VueChoixCarte{
        private JFrame window;
        private ArrayList<VueCarte> vueCartes;
        private JPanel panelCarte,panelDesc;
        private JLabel desc;
        private MouseListener listener;

        public VueChoixCarte(ArrayList<VueCarte> vueCartes) {
            this.vueCartes = vueCartes;
            window = new JFrame("Choix d'une carte");
            window.setSize(vueCartes.size()*200,300);
            window.setResizable(false);
            
            GridLayout g = new GridLayout(1,vueCartes.size());
            g.setHgap(20);
            
            panelCarte = new JPanel();
            panelCarte.setLayout(g);
            
            listener = new MouseListener(){

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("vous avez choisi le : " 
                            + ((VueCarte)e.getSource()).getCarte().getNom());
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
                
            };
            
            for(VueCarte v : vueCartes){
                panelCarte.add(v);
                v.addMouseListener(listener);
            }
            window.add(panelCarte,BorderLayout.CENTER);
            
            panelDesc = new JPanel();
            
            desc = new JLabel("Choisissez une carte a defausser");
            panelDesc.add(desc);
            
            window.add(panelDesc,BorderLayout.SOUTH);

            
            window.setVisible(true);
        }
    }
