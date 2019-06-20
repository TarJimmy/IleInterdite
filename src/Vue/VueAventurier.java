package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observateur;
import Controleur.Utils;
import Controleur.Utils.CarteUtils;
import Modele.Aventurier;
import Modele.CarteJoueur;
import Modele.Grille;
import Modele.Messager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private VueCarte[] mesCartes;
    private final int NB_CARTE_MAX = 4;
    private JPanel panelCartes;
	public void ajouterVueCarte(CarteUtils carte) throws IOException {
            boolean b = false;
            int i = 0;
            while (!b &&i<NB_CARTE_MAX ){
                if( mesCartes[i].isVide() ){
                    mesCartes[i].toogleVide();
                    mesCartes[i].setCarte(carte);
                    b=true;
                    mesCartes[i].repaint();
                }
                i++;
            }
	}

	public void choixCarte() {
		// TODO - implement VueAventurier.choixCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param carte
	 */
	public void SupprimerVueCarte(CarteUtils carte) throws IOException {
            boolean b = false;
            int i = 0;
            while (!b &&i<NB_CARTE_MAX ){
                if(mesCartes[i].getCarte()==carte && !mesCartes[i].isVide() ){
		mesCartes[i].toogleVide();
                b=true;
                mesCartes[i].repaint();
                }
                i++;
            }    
	}


    public String getNomAventurier() {
        return nomJoueur;
    }

    public VueAventurier(String nomJoueur, Aventurier av) throws IOException{
        this.nomJoueur = nomJoueur+" : "+av.toString();
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
        add(panelAventurier, BorderLayout.NORTH);
        
        // =================================================================================
        //PanelCentre
        GridLayout b = new GridLayout(2,1);
        this.panelCentre = new JPanel(b);
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        add(this.panelCentre, BorderLayout.CENTER);
        // CENTRE : DescrptionPouvoir
        String[] mesTextes = av.getDescription().split("\n");
        panelJScrool = new JPanel(new GridLayout(mesTextes.length,1));
        //Initialisation du texte du JScroll
        String s = new String();
        for (int i = 0;i<mesTextes.length;i++){
            s += " " +mesTextes[i];
            if (i%1==0 || i==mesTextes.length-1){
                panelJScrool.add(new JLabel(s,JLabel.LEFT));
                s= new String();
            }
        }
        pouvoir = new JScrollPane(panelJScrool);
        panelCentre.setBackground(Color.white);
        panelCentre.add(pouvoir,BorderLayout.NORTH);
        pouvoir.setPreferredSize(new Dimension(getWidth(),50));
        //Cartes
        GridLayout g = new GridLayout(1,NB_CARTE_MAX);
        g.setHgap(10);
        
        panelCartes =new JPanel(g);
        panelCartes.setBackground(Color.white);
        mesCartes = new VueCarte[NB_CARTE_MAX];
        //A enlever apres test
        mesCartes[0] = new VueCarte(Utils.CarteUtils.calice);
        mesCartes[1] = new VueCarte(Utils.CarteUtils.calice);
        mesCartes[2] = new VueCarte(Utils.CarteUtils.caverneDesOmbres);
        mesCartes[3] = new VueCarte(Utils.CarteUtils.zephyr);
        for (int i =0; i <mesCartes.length;i++){
            panelCartes.add(mesCartes[i]);
        }
        panelCentre.add(panelCartes,BorderLayout.CENTER);
        SupprimerVueCarte(CarteUtils.calice);
        SupprimerVueCarte(Utils.CarteUtils.zephyr);
        ajouterVueCarte(CarteUtils.cristal);
        ajouterVueCarte(CarteUtils.calice);
    }
    public void setPosition(String pos) {
        this.position.setText(pos);
    }
    
    public static void main(String[]args) throws IOException{
        JFrame j = new JFrame("Test");
        j.setSize(500,300);
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
        

