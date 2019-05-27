package projetileinterdite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
public class Accueil  {
    private final JFrame window;
    private JButton jouer; 
    private ArrayList<String> mesNoms=new ArrayList<>();; //Utilisé pour que les joueurs se repères lors du jeu
    private final int NB_JOUEUR_MAX=4; //Constante du nb de joueur max sur une partie
    private int nbJoueur =0;//Modifier apres une action sur valider (GHcentre-DROIT)
    private final Color VERT_FONCER = new Color(0, 220, 0);
    private final Color ROUGE_FONCER = new Color(240, 0, 0);
    Accueil(VueJeu jeu){
        //Configure Fenetre
        window = new JFrame("Accueil");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLocation(400, 300);
        window.setResizable(false);
        window.setSize(780, 585);
        window.setLayout(new GridLayout(1,2)); //Séparer par GAUCHE  et DROITE
        window.setBackground(Color.black);
        //Ajout a l'avance pour un debbugage éfficae si nécessaire
        //GAUCHE
        JPanel GAUCHE = new JPanel();
        GAUCHE.setBackground(Color.white);
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
        JPanel Gbas = new JPanel(new GridLayout(4,1));
        
        GAUCHE.add(Gbas);
        
        //DROITE
        JPanel DROIT = new JPanel(new FlowLayout());
        
        window.add(GAUCHE);
        window.add(DROIT);
        // Fin Initialisation 
        //GAUCHE -> Haut -> Nord
        GHnord.setBackground(Color.white);
        jouer = new JButton("C'est Parti !");
        jouer.setBackground(Color.white);
        
        GHnord.add(jouer);
        
       
        
        //GAUCHE -> Centre
        //GAUCHE ->Centre -> Haut
        JPanel centreH = new JPanel(new GridLayout(1,2));
        
        //GAUCHE -> Centre -> Haut-> Gauche
        JPanel centreHG = new JPanel();
        centreHG.setBackground(Color.white);
        //Bouton ajJoueur
        JButton ajJoueur = new JButton("Ajouter Joueur");
        ajJoueur.setBackground(Color.white);
        centreHG.add(ajJoueur);
        centreH.add(centreHG);
        //GAUCHE -> Centre -> Haut-> Droit
        //Bouton supJoueur
        JPanel centreHD = new JPanel();
        JButton supJoueur = new JButton("Supprimer Joueur");
        supJoueur.setBackground(Color.white);
        centreHD.add(supJoueur);
        centreH.add(centreHD);
        GHcentre.add(centreH);
        //GAUCHE -> Haut -> Centre -> Centre+Bas
        //Label pour indiquer les limites a l'en tete
        JLabel indicNbJoueur=new JLabel();
        indicNbJoueur.setVisible(false);
        indicNbJoueur.setBackground(Color.white);
        indicNbJoueur.setHorizontalAlignment(JLabel.CENTER);
        GHcentre.add(indicNbJoueur);
        //Reste de GAUCHE -> Haut -> Centre -> Centre+Bas
        GHcentre.setBackground(Color.white);
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
        
         

        
        
        
        //Image à Droite
        DROIT.setBackground(Color.white);
        JLabel image = new JLabel();
        DROIT.add(image,BorderLayout.CENTER);
        image.setIcon(new ImageIcon(getClass().getResource("../Images/imageAccueil.jpg")));
        
        
        
        
        
        
        
        
        //MouseListener de jouer
        jouer.addMouseListener(new MouseListener(){  @Override
                                                        public void mouseClicked(MouseEvent e){
                                                            mesNoms = new ArrayList<>();
                                                            for (JTextField mesChamp : mesChamps) {
                                                                if(mesChamp.isVisible()){
                                                                    mesNoms.add(mesChamp.getText());
                                                                }
                                                            }
                                                            System.out.println(mesNoms);
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
                                                        supJoueur.setBackground(Color.white);
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
                                                        ajJoueur.setBackground(Color.white);}
        });  
    }
    
    public ArrayList<String> getMesNoms() {
        return mesNoms;
    }

    public int getNB_JOUEUR_MAX() {
        return NB_JOUEUR_MAX;
    }
    
    
    public void afficher(boolean b){
            window.setVisible(b);
    }
}