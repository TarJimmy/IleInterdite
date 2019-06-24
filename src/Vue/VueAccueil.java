package Vue;

import Controleur.Message;
import Controleur.Observe;
import Controleur.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author tardy
 */
 /* - AddListener des boutons en bas du constructeur
    - Tout les objet sont intialiser seulement il ne peuvent pas être tous visibles,
    - C'est fait de cette manière pour optimisé le temps d'attente des interractions*/
public class VueAccueil extends Observe{
    private JFrame window;
    private final JButton jouer; 
    private ArrayList<String> mesNoms=new ArrayList<>();; //Utilisé pour que les joueurs se repères lors du jeu
    private final int NB_JOUEUR_MAX=4; //Constante du nb de joueur max sur une partie
    private int nbJoueur =0;//Modifier apres une action sur valider (GHcentre-DROIT)
    private final Color VERT_FONCER = new Color(0, 180, 0);
    private final Color ROUGE_FONCER = new Color(240, 0, 0);
    private final Color ORANGE_FONCER = new Color(255,69,0);
    private final Color COULEUR_PRINCIPAL = Color.white;
    private final int NB_DIFFICULTE=4; //Doit etre egale aux nb d'éléments dans stringDif et ColorDif
    JSlider slider;
    JLabel choixDif;
    JButton regle;
    private final String[] stringDif = {"Novice","Normal","Elite","Extreme"};
    private final Color[] ColorDif ={VERT_FONCER,ORANGE_FONCER,Color.MAGENTA,Color.BLACK};
    private final Font fontGeneral = new Font(Font.SERIF,Font.TYPE1_FONT,14);
    public VueAccueil() throws IOException{
        //Configure Fenetre
        initWindow();
        //Ajout a l'avance pour un debbugage éfficae si nécessaire
        //GAUCHE
        JPanel GAUCHE = new JPanel(new GridLayout(2,1));
        GAUCHE.setBackground(COULEUR_PRINCIPAL);
        //GAUCHE -> Haut
        JPanel GHaut = new JPanel(new BorderLayout());
        //GAUCHE -> Haut -> Nord
        JPanel GHnord = new JPanel();
        GHaut.add(GHnord,BorderLayout.NORTH);
        //GAUCHE -> Haut -> Centre+Bas
        JPanel GHcentre = new JPanel(new GridLayout(NB_JOUEUR_MAX+2,1));//est developper en amont 
        GHaut.add(GHcentre,BorderLayout.CENTER);
        
        GAUCHE.add(GHaut);
        //GAUCHE -> Bas
        JPanel Gbas = new JPanel(new GridLayout(NB_DIFFICULTE+1,1));
        GAUCHE.add(Gbas);
        
        //DROITE
        PanelImage DROIT = new PanelImage("imageAccueil.jpg");
        
        window.add(GAUCHE);
        window.add(DROIT);
        // Fin Initialisation 
        //GAUCHE -> Haut -> Nord
        regle = new JButton("Regle >>");
        regle.setBackground(Color.gray);
        regle.setFont(fontGeneral);
        regle.setBackground(Color.white);
        GHnord.add(regle);
        
       
        
        //GAUCHE -> Centre
        //GAUCHE ->Centre -> Haut
        JPanel centreH = new JPanel(new GridLayout(1,2));
        
        //GAUCHE -> Centre -> Haut-> Gauche
        JPanel centreHG = new JPanel();
        centreHG.setBackground(COULEUR_PRINCIPAL);
        //Bouton ajJoueur
        JButton ajJoueur = new JButton("Ajouter Joueur");
        ajJoueur.setBackground(COULEUR_PRINCIPAL);
        ajJoueur.setFocusable(false);
        centreHG.add(ajJoueur);
        centreH.add(centreHG);
        //GAUCHE -> Centre -> Haut-> Droit
        //Bouton supJoueur
        JPanel centreHD = new JPanel();
        centreHD.setBackground(COULEUR_PRINCIPAL);
        JButton supJoueur = new JButton("Supprimer Joueur");
        supJoueur.setBackground(COULEUR_PRINCIPAL);
        supJoueur.setFocusable(false);
        centreHD.add(supJoueur);
        centreH.add(centreHD);
        GHcentre.add(centreH);
        //GAUCHE -> Haut -> Centre -> Centre+Bas
        //Label pour indiquer les limites a l'en tete du nombre de joueur
        JLabel indicNbJoueur=new JLabel();
        indicNbJoueur.setVisible(false);
        indicNbJoueur.setBackground(COULEUR_PRINCIPAL);
        indicNbJoueur.setFont(fontGeneral);
        indicNbJoueur.setHorizontalAlignment(JLabel.CENTER);
        GHcentre.add(indicNbJoueur);
        //Reste de GAUCHE -> Haut -> Centre -> Centre+Bas
        GHcentre.setBackground(COULEUR_PRINCIPAL);
        JTextField[] mesChamps = new JTextField[NB_JOUEUR_MAX];   
        //Creation des champs textes et positionnement dans la fenetre
        for (int i=0;i<NB_JOUEUR_MAX;i++){
            mesChamps[i] = new JTextField("Nom "+(i+1));
            GHcentre.add(mesChamps[i]);
            //Affichage minimal de 2 champs Textes
            if(i<2){
                mesChamps[i].setVisible(true);
                nbJoueur++;
            }
            else{
                mesChamps[i].setVisible(false);
            }
        }
        
         //GAUCHE -> Bas -> En tete
         Gbas.setBackground(COULEUR_PRINCIPAL);
         JLabel indicDifficultes = new JLabel("Choisir la difficulté :");
         indicDifficultes.setFont(fontGeneral);
         Gbas.add(indicDifficultes);
         Gbas.add(monSlider());

         //GAUCHE -> Bas -> Reste
        JPanel GbasJouer = new JPanel();
        GbasJouer.setBackground(Color.white);
        GHnord.setBackground(COULEUR_PRINCIPAL);
        jouer = new JButton("C'est Parti !");
        jouer.setBackground(COULEUR_PRINCIPAL);
        jouer.setFont(fontGeneral);
        jouer.setBorderPainted(true);
        jouer.setFocusable(false);
        GbasJouer.add(jouer);
        
        
        Gbas.add(GbasJouer);
        
        
        
        
        
        
        
        //MouseListener ou ActionListener des boutons
        regle.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    new VueRegles();
                } catch (IOException ex) {
                    Logger.getLogger(VueAccueil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        jouer.addMouseListener(new MouseListener(){  @Override
                                                        public void mouseClicked(MouseEvent e){
                                                            mesNoms = new ArrayList<>();
                                                            for (JTextField mesChamp : mesChamps) {
                                                                if(mesChamp.isVisible()){
                                                                    mesNoms.add(mesChamp.getText());
                                                                }
                                                            }
                                                            Message m = new Message();
                                                            m.type = TypeMessage.DEBUTJEU;
                                                            m.nbJoueur = nbJoueur;
                                                            m.difficulte = DonneEchelon();
                                                            m.noms = mesNoms;
                                                            notifierMessage(m);
                                                        }
                                                        @Override
                                                        public void mousePressed(MouseEvent e) {}
                                                        @Override
                                                        public void mouseReleased(MouseEvent e) {}
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {}
                                                        @Override
                                                        public void mouseExited(MouseEvent e) {}
        });
        supJoueur.addMouseListener(new MouseListener(){
                                                        @Override
                                                        public void mouseClicked(MouseEvent e){
                                                            if(nbJoueur>2){
                                                                mesChamps[nbJoueur-1].setVisible(false);
                                                                nbJoueur--;
                                                            }
                                                        }
                                                        @Override
                                                        public void mousePressed(MouseEvent e) {}
                                                        @Override
                                                        public void mouseReleased(MouseEvent e) {
                                                            if (nbJoueur<=2){
                                                                    supJoueur.setEnabled(false);
                                                                    indicNbJoueur.setVisible(true);
                                                                    indicNbJoueur.setForeground(ROUGE_FONCER);
                                                                    indicNbJoueur.setText("Nombre minimum de joueur atteint");
                                                                }}
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                        supJoueur.setBackground(ROUGE_FONCER);
                                                            if (nbJoueur<=2){
                                                                supJoueur.setEnabled(false);
                                                                indicNbJoueur.setVisible(true);
                                                                indicNbJoueur.setForeground(ROUGE_FONCER);
                                                                indicNbJoueur.setText("Nombre minimum de joueur atteint");
                                                            }}
                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                        supJoueur.setBackground(COULEUR_PRINCIPAL);
                                                        indicNbJoueur.setVisible(false);
                                                        supJoueur.setEnabled(true);}
        });
        ajJoueur.addMouseListener(new MouseListener(){
                                                        @Override
                                                        public void mouseClicked(MouseEvent e){
                                                            if(nbJoueur<NB_JOUEUR_MAX){
                                                                mesChamps[nbJoueur].setVisible(true);
                                                                nbJoueur++;
                                                            }
                                                        }
                                                        @Override
                                                        public void mousePressed(MouseEvent e) {}
                                                        @Override
                                                        public void mouseReleased(MouseEvent e) {
                                                            if (nbJoueur>=NB_JOUEUR_MAX){
                                                                ajJoueur.setEnabled(false);
                                                                indicNbJoueur.setVisible(true);
                                                                indicNbJoueur.setForeground(VERT_FONCER);
                                                                indicNbJoueur.setText("Nombre maximum de joueur atteint");
                                                            }
                                                        }
                                                        @Override
                                                        public void mouseEntered(MouseEvent e) {
                                                            ajJoueur.setBackground(VERT_FONCER);
                                                            if (nbJoueur>=NB_JOUEUR_MAX){
                                                                ajJoueur.setEnabled(false);
                                                                indicNbJoueur.setVisible(true);
                                                                indicNbJoueur.setForeground(VERT_FONCER);
                                                                indicNbJoueur.setText("Nombre maximum de joueur atteint");
                                                            }
                                                        }
                                                        @Override
                                                        public void mouseExited(MouseEvent e) {
                                                        indicNbJoueur.setVisible(false);
                                                        ajJoueur.setEnabled(true);
                                                        ajJoueur.setBackground(COULEUR_PRINCIPAL);}
        });  
    }
    
    public ArrayList<String> getMesNoms() {
        return mesNoms;
    }

    public int getNB_JOUEUR_MAX() {
        return NB_JOUEUR_MAX;
    }
    //Renvoie la valeur du slide
    private int DonneEchelon(){
        return slider.getValue();
    }
    
    public void afficher(boolean b){
            window.setVisible(b);
    }

    public static void main(String[] args) throws IOException{
        VueAccueil a = new VueAccueil();
        a.afficher(true);
    }
    public JPanel monSlider(){
        GridLayout g = new GridLayout(2,3);
        g.setHgap(5);
        JPanel pan = new JPanel(g);
        JLabel gauche = new JLabel(stringDif[0]);
        gauche.setFont(fontGeneral);
        gauche.setBackground(COULEUR_PRINCIPAL);
        gauche.setForeground(ColorDif[0]);
        gauche.setHorizontalAlignment(JLabel.RIGHT);
        JLabel droite = new JLabel(stringDif[3]);
        droite.setBackground(COULEUR_PRINCIPAL);
        droite.setForeground(ColorDif[3]);
        droite.setFont(fontGeneral);
        droite.setHorizontalAlignment(JLabel.LEFT);
        slider =  new JSlider(SwingConstants.HORIZONTAL, 0, NB_DIFFICULTE-1, 0);
        slider.setBackground(COULEUR_PRINCIPAL);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                JSlider slid = (JSlider) arg0.getSource();
                choixDif.setText(stringDif[slid.getValue()]);
                choixDif.setForeground(ColorDif[slid.getValue()]);
                choixDif.setFont(fontGeneral);
            }
        });
        choixDif = new JLabel(stringDif[0]);
        choixDif.setForeground(ColorDif[0]);
        choixDif.setHorizontalAlignment(JLabel.CENTER);
        choixDif.setBackground(COULEUR_PRINCIPAL);
        choixDif.setFont(fontGeneral);
        pan.add(gauche);
        pan.add(slider);
        pan.add(droite);
        JPanel PanelVide = new JPanel();
        PanelVide.setBackground(COULEUR_PRINCIPAL);
        pan.add(PanelVide);
        pan.add(choixDif);
        pan.setBackground(COULEUR_PRINCIPAL);
        return pan;
    }


    private void initWindow() {
        window = new JFrame();
        window.setTitle("Accueil");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLocation(300, 300);
        window.setResizable(false);
        window.setSize(680, 480);
        window.setLayout(new GridLayout(1,2)); //Séparer par GAUCHE  et DROITE
        window.setBackground(Color.black);
    }
}