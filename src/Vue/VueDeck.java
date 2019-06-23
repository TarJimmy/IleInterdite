package Vue;

import Controleur.Utils;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author beaufima
 */
public class VueDeck extends PanelImage{
    public static String DECK_INONDATION =Utils.CarteUtils.deckInondation.getChemin();
    public static String DECK_TRESOR =Utils.CarteUtils.deckTresor.getChemin();
    VueDeck(String choix) throws IOException{
        super(choix);
    }
   
   
    
}
