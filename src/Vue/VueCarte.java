package Vue;

import java.awt.Image;
import Vue.CarteUtils;
import java.io.IOException;
import javax.swing.JFrame;

public class VueCarte extends PanelImage {
  
    VueCarte(CarteUtils carte) throws IOException{
        super(carte.getChemin());
    }
      /* public static void main(String[]ntmMalo) throws IOException{
        JFrame j = new JFrame("Test");
        j.setSize(300, 200);
        j.add(new VueCarte(CarteUtils.));
        j.setVisible(true);
    }*/
}
