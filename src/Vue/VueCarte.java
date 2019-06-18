package Vue;

import java.awt.Image;
import static Vue.CarteUtils.*;
import java.io.IOException;

public class VueCarte extends PanelImage {
  
    VueCarte(CarteUtils carte) throws IOException{
        super(carte.getChemin());
    }
}
