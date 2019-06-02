package Vue;

import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class VueTuile extends JButton {
    Color etat;
    VueTuile (Tuile tuile){
        setLayout(new BorderLayout());
        setEtat(Color.orange);
        add(new JLabel(tuile.getNom().getNom()),BorderLayout.CENTER);
        setBackground(etat);
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));

    }
    VueTuile (){
        setEnabled(false);
        setBackground(Color.white);
    }

    

    /**
     *
     * @param etat
     */
    public void setEtat(Color etat) {
        this.etat = etat;
    }
}