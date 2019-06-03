package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observe;
import Controleur.TypeAction;
import Modele.CarteJoueur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;


public class VueAventurier extends Observe implements Controleur.Observateur{

	/**
	 * 
	 * @param carte
	 */
    private final JPanel principal;
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger  ;
    private final JButton btnAssecher;
    private final JButton btnValider;
    private final JButton btnTerminerTour;
    private int[] coords;
    private JTextField position;
    //A enlever apres demo
    private String nomAventurier;
	public void ajouterVueCarte(CarteJoueur carte) {
		// TODO - implement VueAventurier.ajouterVueCarte
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}


    public void setPos(int[] coord){
        position.setText("x= " + coord[0] +" / y= "+coord[1]);
    }
    public JPanel getPrincipal() {
        return principal;
    }

    public VueAventurier(String nomJoueur, String nomAventurier, Color couleur){
        //A enelevr apres demo
        nomAventurier = nomAventurier;
        //le titre = nom du joueur 
        principal = new JPanel(new BorderLayout());
        principal.add(new JLabel(nomJoueur), BorderLayout.NORTH);
        mainPanel = new JPanel(new BorderLayout());
        principal.add(mainPanel,BorderLayout.CENTER);
        
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Position", SwingConstants.CENTER));
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnValider = new JButton("Valider") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.panelBoutons.add(btnBouger);
        btnBouger.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAction msg = new MessageAction();
                msg.typeact = TypeAction.DEPLACER;
                notifierMessageAction(msg);
            }
        });
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnValider);
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAction msg = new MessageAction();
                msg.typeact = TypeAction.CHOIX_TUILE;
                msg.coord = coords;
                notifierMessageAction(msg);
            }
        });
        this.panelBoutons.add(btnTerminerTour);
        btnTerminerTour.addActionListener(new ActionListener(){
                 @Override
            public void actionPerformed(ActionEvent e) {
                MessageAction msg = new MessageAction();
                msg.typeact = TypeAction.TERMINER_TOUR;
                     notifierMessageAction(msg);
            }
        });
        
    }
    
  
    public void setPosition(String pos) {
        this.position.setText(pos);
    }
    
     public JButton getBtnValider() {
        return btnValider;
    }
    
    public String getPosition() {
        return position.getText();
    }

    public JButton getBtnBouger() {
        return btnBouger;
    }
    
    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
 
    public String toString(){
        return "[VueAventurier : "+ nomAventurier+"]";
    }
    public void activer(boolean b){
        btnBouger.setEnabled(b);
        btnAssecher.setEnabled(b);
        btnValider.setEnabled(b);
        btnTerminerTour.setEnabled(b);
    }

    @Override
    public void traiterMessage(Message msg) {
        
    }

    @Override
    public void traiterMessage(MessageAction msg) {
        if(msg.typeact == TypeAction.CHOIX_TUILE){
           this.coords = msg.coord;
        }
    }
}
