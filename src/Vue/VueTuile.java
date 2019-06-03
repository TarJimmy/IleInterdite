package Vue;

import Controleur.Utils;
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
        private Color etat;
        private ArrayList<VuePion> mesPions;
        private JPanel mesVuePions;
    VueTuile (Tuile tuile, int x,int y/*Coordonner a enlever apres la demo*/){
        mesPions = new ArrayList<>();
        setLayout(new BorderLayout());
        setEtat(Color.orange);
        add(new JLabel(tuile.getNom().getNom()),BorderLayout.CENTER);
        add (new JLabel("["+x+","+y+"]"),BorderLayout.SOUTH);//A enlever apres la demo
        setBackground(etat);
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
        mesVuePions = new JPanel(new GridLayout(1,4));
        add(mesVuePions,BorderLayout.SOUTH);
    }
    public void initVuePion(VuePion vue){
        vue.setMaTuile(this);
    }
    public void addVuePion(VuePion vue){
        mesVuePions.add(vue);
    }
    public void supVuePion(VuePion vue){
        mesVuePions.remove(vue);
    }
    private void changeFond(Color etat){
        setBackground(etat);
    }
    public void changeEtat(Utils.EtatTuile etat){
        switch(etat){
            case COULEE:
                setEtat(Color.BLACK);
            case ASSECHEE:
                setEtat(Color.ORANGE);
            case INONDEE:
                setEtat(Color.CYAN);
        }
        changeFond(this.etat);
    }
    

    /**
     *
     * @param etat
     */
    public void setEtat(Color etat) {
        this.etat = etat;
    }
    public String toString(){
        return "Une Vue";
    }
}