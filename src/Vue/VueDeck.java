package Vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author beaufima
 */
public class VueDeck extends JPanel{
    
    VueDeck(){
        JLabel l = new JLabel();
        l.setIcon(new ImageIcon(getClass().getResource("Fond Bleu.png")));
        
        add(l);
    }
}
