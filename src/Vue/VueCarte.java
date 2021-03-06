package Vue;

import java.awt.Image;
import Controleur.Utils.CarteUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;

public class VueCarte extends PanelImage {
    private CarteUtils carte;
    
    VueCarte(CarteUtils carte) throws IOException{
        super(carte.getChemin());
        this.carte = carte;
        
    }

    public CarteUtils getCarte() {
        return carte;
    }

    public void setCarte(CarteUtils carte) {
        this.carte = carte;
        super.setImage(this.carte.getChemin());
    }
}