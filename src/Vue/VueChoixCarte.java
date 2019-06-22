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
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Controleur.*;
import static Vue.VueJeu.DON_CARTE;
import java.awt.Color;


/**
 *
 * @author Gholbin
 */
public class VueChoixCarte extends Observe{
        private JFrame window;
        private ArrayList<VueCarte> vueCartes;
        private JPanel panelCarte,panelDesc;
        private JLabel desc;
        private MouseListener listener;
        
        private MessageAction msg;
        
        
        public VueChoixCarte(ArrayList<VueCarte> vueCartes,int etat) {
            
            msg = new MessageAction();
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
                    msg.vueCarte = ((VueCarte) e.getSource());
                    msg.typeact = TypeAction.CHOIX_CARTE;
                    notifierMessageAction(msg);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                VueCarte vue = (VueCarte)e.getSource();
                vue.setBackground(Color.black);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                VueCarte vue = (VueCarte)e.getSource();
                vue.setBackground(Color.white);
                }
                
            };
            
            for(VueCarte v : vueCartes){
                panelCarte.add(v);
                v.addMouseListener(listener);
            }
            window.add(panelCarte,BorderLayout.CENTER);
            
            panelDesc = new JPanel();
            
            desc = new JLabel("Choisissez une carte à" + ((etat == DON_CARTE)?"donner":"defausser"));
            panelDesc.add(desc);
            
            window.add(panelDesc,BorderLayout.SOUTH);

            
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        
            public static void main(String[] args) throws IOException {
                ArrayList<VueCarte> array = new ArrayList<>();
                array.add(new VueCarte(Utils.CarteUtils.calice));
                array.add(new VueCarte(Utils.CarteUtils.pierre));
                array.add(new VueCarte(Utils.CarteUtils.cristal));
                array.add(new VueCarte(Utils.CarteUtils.caverneDesOmbres));
                
                
                
                new VueChoixCarte(array,10);
                
                
                
            }
    }
