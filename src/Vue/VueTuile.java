package Vue;

import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class VueTuile extends JButton {
        Color etat;
        Tuile tui;
    VueTuile (Tuile tuile, int x,int y/*Coordonner a enlever apres la demo*/){
        setLayout(new BorderLayout());
        setEtat(Color.orange);
        add(new JLabel(tuile.getNom().getNom()),BorderLayout.CENTER);
        add (new JLabel("["+x+","+y+"]"),BorderLayout.SOUTH);//A enlever apres la demo
        setBackground(etat);
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));

    }
    

    

    /**
     *
     * @param etat
     */
    public void setEtat(Color etat) {
        this.etat = etat;
    }
}