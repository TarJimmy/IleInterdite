package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observateur;
import Controleur.Utils;
import Controleur.Utils.CarteUtils;
import Controleur.Utils.Pion;
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


public class VueAventurier extends JPanel {

	/**
	 * 
	 * @param carte
	 */
    private final JPanel panelCentre ;
    private final JPanel panelAventurier;
    private final JPanel panelJScrool;
    private Pion pion ;
    private JTextField position;
    private final JScrollPane pouvoir;
    //A enlever apres demo
    private String nomJoueur;
    private VueCarte[] mesCartes;
    private final int num; //numero de l'index de l'aventurier associer
    private final int NB_CARTE_MAX = 5;
    private JPanel panelCartes;
	private void ajouterVueCarte(CarteUtils carte) throws IOException {
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
	private void SupprimerVueCarte(CarteUtils carte) throws IOException {
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

    public VueAventurier(String nomJoueur, Aventurier av, int num) throws IOException{
        this.nomJoueur = nomJoueur+" : "+av.toString();
        pion = av.getPion();
        
        //le titre = nom du joueur 
        setLayout(new BorderLayout());
        add (new JLabel(nomJoueur), BorderLayout.NORTH);
        setBackground(new Color(230, 230, 230));
        setBorder(BorderFactory.createLineBorder(getPion().getCouleur(), 2)) ;

        this.num = num;
        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion
        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(getPion().getCouleur());
        panelAventurier.add(new JLabel(this.nomJoueur,JLabel.CENTER));
        add(panelAventurier, BorderLayout.NORTH);
        
        // =================================================================================
        //PanelCentre
        GridLayout b = new GridLayout(2,1);
        this.panelCentre = new JPanel(b);
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, getPion().getCouleur()));
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
        for (int i =0; i <mesCartes.length;i++){
            mesCartes[i] = new VueCarte(Utils.CarteUtils.calice);
            mesCartes[i].toogleVide();
            panelCartes.add(mesCartes[i]);
        }
        panelCentre.add(panelCartes,BorderLayout.CENTER);
        activer(false);
    }
    public void setPosition(String pos) {
        this.position.setText(pos);
    }
    
    public void activer(boolean b){
        Color couleur = (b)? pion.getCouleur():pion.getCouleurGrisee();
        setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
        panelAventurier.setBackground(couleur);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, (couleur)));
    }
        
    public Pion getPion() {
        return pion;
    }

   
    public void modifierVueCarte(boolean b,CarteUtils carte) throws IOException{
        if(b){
            ajouterVueCarte(carte);
        }
        else{
            SupprimerVueCarte(carte);
        }
    }

    

}
        

