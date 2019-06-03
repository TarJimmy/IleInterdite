package Vue;

import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class VueTuile extends JButton {
        Color etat;
        Tuile tui;
        private ArrayList<VuePion> mesPions;
    VueTuile (Tuile tuile, int x,int y/*Coordonner a enlever apres la demo*/){
        mesPions = new ArrayList<>();
        setLayout(new BorderLayout());
        setEtat(Color.orange);
        add(new JLabel(tuile.getNom().getNom()),BorderLayout.CENTER);
        add (new JLabel("["+x+","+y+"]"),BorderLayout.SOUTH);//A enlever apres la demo
        setBackground(etat);
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
        JPanel mesVuePions = new JPanel(new GridLayout(1,4));
        add(mesVuePions,BorderLayout.SOUTH);
    }
        

    

    /**
     *
     * @param etat
     */
    public void setEtat(Color etat) {
        this.etat = etat;
    }
}