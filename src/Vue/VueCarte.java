package Vue;

import java.awt.Image;
import static ile_interdite.CarteUtils.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueCarte {
  
  VueCarte(CarteUtils carte){
    JLabel l = new JLabel();
    l.setIcon(carte.getImage());
       
    add(l);
  }
}
