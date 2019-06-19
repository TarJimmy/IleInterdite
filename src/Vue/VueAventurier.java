package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observateur;
import Modele.Aventurier;
import Modele.CarteJoueur;
import Modele.Grille;
import Modele.Messager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;


public class VueAventurier extends JPanel implements Observateur {

	/**
	 * 
	 * @param carte
	 */
    private final JPanel panelCentre ;
    private final JPanel panelAventurier;
    private final JPanel panelJScrool;
    Color couleur ;
    private int[] coords;
    private JTextField position;
    private JScrollPane pouvoir;
    //A enlever apres demo
    private String nomJoueur;
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
        return nomJoueur;
    }

    public VueAventurier(String nomJoueur, Aventurier av){
        this.nomJoueur = av.toString();
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
        panelAventurier.add(new JLabel(this.nomJoueur,JLabel.CENTER));
        add(panelAventurier, BorderLayout.SOUTH);
        
        // =================================================================================
        // CENTRE : DescrptionPouvoir
        String[] mesTextes = av.getDescription().split("\n");
        this.panelCentre = new JPanel(new BorderLayout());
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        add(this.panelCentre, BorderLayout.CENTER);
        panelJScrool = new JPanel(new GridLayout(mesTextes.length,1));
        //Initialisation du texte du JScroll
        String s = new String();
        System.out.println(s);
        
        for (int i = 0;i<mesTextes.length;i++){
            s += " " +mesTextes[i];
            if (i%1==0 || i==mesTextes.length-1){
                panelJScrool.add(new JLabel(s,JLabel.LEFT));
                s= new String();
            }
        }
        pouvoir = new JScrollPane(panelJScrool);
        panelCentre.add(pouvoir,BorderLayout.NORTH);
        pouvoir.setPreferredSize(new Dimension(getWidth(),50));
        JPanel j =new JPanel();
        j.setBackground(Color.white);
        panelCentre.add(j,BorderLayout.CENTER);
        
    
    }
    public void setPosition(String pos) {
        this.position.setText(pos);
    }
    
    public static void main(String[]args) throws IOException{
        JFrame j = new JFrame("Test");
        j.setSize(300,300);
        VueAventurier av = new VueAventurier("Jimmy",new Messager(new Grille(5)));
        j.add(av);
        j.setVisible(true);
        
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   

    @Override
    public void traiterMessage(Message msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void traiterMessageAction(MessageAction msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
        

