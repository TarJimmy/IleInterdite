package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observe;
import Controleur.TypeAction;
import Modele.Aventurier;
import Modele.CarteJoueur;
import Modele.Grille;
import Modele.Ingenieur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;


public class VueAventurier extends JPanel {

	/**
	 * 
	 * @param carte
	 */
    private final JPanel panelCentre ;
    private final JPanel panelAventurier;
    Color couleur ;
    private int[] coords;
    private JTextField position;
    private JScrollPane pouvoir;
    //A enlever apres demo
    private String nomAventurier;
    private JLabel[] mesText;
	public void ajouterVueCarte(CarteJoueur carte) {
	}

	public void choixCarte() {
		// TODO - implement VueAventurier.choixCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param carte
	 */
	public void SupprimerVueCarte(CarteJoueur carte) {
		// TODO - implement VueAventurier.SupprimerVueCarte
	}


    public void setPos(int[] coord){
        position.setText("x= " + coord[0] +" / y= "+coord[1]);
    }

    public String getNomAventurier() {
        return nomAventurier;
    }

    public VueAventurier(String nomJoueur, Aventurier av){
        this.nomAventurier = av.toString();
        couleur = av.getPion().getCouleur();
        
        //le titre = nom du joueur 
        setLayout(new BorderLayout());
        add (new JLabel(nomJoueur), BorderLayout.NORTH);
        setBackground(new Color(230, 230, 230));
        setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion
        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,JLabel.CENTER));
        add(panelAventurier, BorderLayout.SOUTH);
        
        // =================================================================================
        // CENTRE : DescrptionPouvoir
        
        pouvoir = new JScrollPane();
       /* for(char c : av.getDescription()){
            
        }*/
        this.panelCentre = new JPanel(new GridLayout(1, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel (av.getMaPos().getNom().getNom(), SwingConstants.CENTER));
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);


      
    
    }
    public void setPosition(String pos) {
        this.position.setText(pos);
    }
    
    public static void main(String[]args) throws IOException{
        JFrame j = new JFrame("Test");
        j.setSize(300,300);
        VueAventurier av = new VueAventurier("Jimmy",new Ingenieur(new Grille(5)));
        j.add(av);
        j.setVisible(true);
    }

    
}
        

