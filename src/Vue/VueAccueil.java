package Vue;

import Controleur.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import Controleur.Message;
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
public class VueAccueil extends Controleur.Observe{
    private JFrame window;
    private JButton jouer; 
    private ArrayList<String> mesNoms=new ArrayList<>();; //Utilisé pour que les joueurs se repères lors du jeu
    private final int NB_JOUEUR_MAX=4; //Constante du nb de joueur max sur une partie
    private int nbJoueur =0;//Modifier apres une action sur valider (GHcentre-DROIT)
    private final Color VERT_FONCER = new Color(0, 220, 0);
    private final Color ROUGE_FONCER = new Color(240, 0, 0);
    private final Color COULEUR_PRINCIPAL = Color.white;
    JRadioButton[] mesDif;
    private final int NB_DIFFICULTE =4;
    private final String[] StrDif = {"Novice","Normal","Elite","Légendaire"};
    public VueAccueil(){
        //Configure Fenetre
        window = new JFrame();
        window.setTitle("Accueil");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLocation(300, 300);
        window.setResizable(false);
        window.setSize(680, 480);
        window.setLayout(new GridLayout(1,2)); //Séparer par GAUCHE  et DROITE
        window.setBackground(Color.black);
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
        JPanel DROIT = new JPanel(new FlowLayout());
        
        window.add(GAUCHE);
        window.add(DROIT);
        // Fin Initialisation 
        //GAUCHE -> Haut -> Nord
        GHnord.setBackground(COULEUR_PRINCIPAL);
        jouer = new JButton("C'est Parti !");
        jouer.setBackground(COULEUR_PRINCIPAL);
        
        GHnord.add(jouer);
        
       
        
        //GAUCHE -> Centre
        //GAUCHE ->Centre -> Haut
        JPanel centreH = new JPanel(new GridLayout(1,2));
        
        //GAUCHE -> Centre -> Haut-> Gauche
        JPanel centreHG = new JPanel();
        centreHG.setBackground(COULEUR_PRINCIPAL);
        //Bouton ajJoueur
        JButton ajJoueur = new JButton("Ajouter Joueur");
        ajJoueur.setBackground(COULEUR_PRINCIPAL);
        centreHG.add(ajJoueur);
        centreH.add(centreHG);
        //GAUCHE -> Centre -> Haut-> Droit
        //Bouton supJoueur
        JPanel centreHD = new JPanel();
        centreHD.setBackground(COULEUR_PRINCIPAL);
        JButton supJoueur = new JButton("Supprimer Joueur");
        supJoueur.setBackground(COULEUR_PRINCIPAL);
        centreHD.add(supJoueur);
        centreH.add(centreHD);
        GHcentre.add(centreH);
        //GAUCHE -> Haut -> Centre -> Centre+Bas
        //Label pour indiquer les limites a l'en tete
        JLabel indicNbJoueur=new JLabel();
        indicNbJoueur.setVisible(false);
        indicNbJoueur.setBackground(COULEUR_PRINCIPAL);
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
         Gbas.add(indicDifficultes);
         
         //GAUCHE -> Bas -> Reste
         ButtonGroup group = new ButtonGroup();
         mesDif = new JRadioButton[NB_DIFFICULTE];
         for (int i = 0; i<NB_DIFFICULTE;i++){
             mesDif[i]= new JRadioButton(StrDif[i]);
             mesDif[i].setBackground(COULEUR_PRINCIPAL);
             group.add(mesDif[i]);
             Gbas.add(mesDif[i]);
             
         }
         mesDif[0].setSelected(true);
         
        
        
        
        //Image à Droite
        DROIT.setBackground(COULEUR_PRINCIPAL);
        JLabel image = new JLabel();
        DROIT.add(image,BorderLayout.CENTER);
        image.setIcon(new ImageIcon("C:\\Users\\tardy\\Downloads\\IleInterdite-master\\src\\Images\\imAccueil.jpg"));
        
        
        
        
        
        
        
        
        //MouseListener de jouer
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
                                                            //m.difficulte = DonneEchelon();
                                                            m.noms = mesNoms;
                                                            notifier(m);
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
    /*private int DonneEchelon(){
        int i=0;
        while (i<mesDif.length || !mesDif[i].isSelected()){
            i++;
        }
        return i;
    }*/
    
    public void afficher(boolean b){
            window.setVisible(b);
    }

    public static void main(String[] args){
        VueAccueil a = new VueAccueil();
        a.afficher(true);
    }
}