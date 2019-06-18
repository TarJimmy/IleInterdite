package Vue;

import java.awt.Image;
import static Vue.CarteUtils.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueCarte extends PanelImage {
  
    VueCarte(CarteUtils carte) throws IOException{
        super(carte.getChemin());
    }
}
