package Vue;

import Controleur.MessageAction;
import Controleur.TypeAction;
import Modele.Grille;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueJeu extends Controleur.Observe {

    private ArrayList<VueAventurier> mesAvs;
    private JButton btnBouger;
    private JButton btnAssecher;
    private JButton btnDonnerCarte;
    private JButton btnGagnerTresor;
    private JButton btnTerminerTour;
    private JButton DeplacerAllier;
    public MonteeDesEaux getMonteeDesEau() {
        return monteeDesEau;
    }
    private JFrame window;
    private MonteeDesEaux monteeDesEau;
    private LabelInfo indications;
    private int Nb_Boutons;
    private class LabelInfo extends JPanel implements Runnable {
        JLabel label;
        String str;
        public LabelInfo(String texte){
            super();
            add(new JLabel("Indications :"));
            str=texte;
            label = new JLabel(texte);
            label.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 14));
            label.setForeground(Color.MAGENTA);
            add(label);
            Thread t = new Thread(this);
            t.start();
          }

          @Override
          public void run(){
            while(true){
                char c = str.charAt(0);
                String rest = str.substring(1);
                str = rest + c;
                label.setText(str);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){ e.printStackTrace();}
            }
        }
    }
    public class MonteeDesEaux extends PanelImage {
        private int niveauEau;
        private MonteeDesEaux() throws IOException { 
            super("Niveau.png");
            this.setPreferredSize(new Dimension(window.getWidth()/7,window.getHeight()));
        }

        public void setNiveauEau(int niveauEau) {
            this.niveauEau = niveauEau;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(15, getHeight()-(niveauEau)*60-130, 130, 10);
        }
    }
    
    public VueJeu(VueGrille grille,ArrayList<VueAventurier> mesAvs) throws IOException {
        this.mesAvs = mesAvs;
        initWindow();
        monteeDesEau = new MonteeDesEaux();
        Iterator it = this.mesAvs.iterator();
        Nb_Boutons = 4;
        //Verifier s'il y a un navigateur
        while (it.hasNext()){
            VueAventurier vue = (VueAventurier) it.next();
            if (vue.getNomAventurier().equals("Navigateur")){
                Nb_Boutons +=1;
            }
        }
        this.mesAvs = mesAvs;
        initHaut(grille);
        initBas();
        
        
         
    }

    public void erreurTresor() {
            // TODO - implement vueJeu.erreurTresor
            throw new UnsupportedOperationException();
    }

    public void PartieGagner() {
            // TODO - implement vueJeu.PartieGagner
            throw new UnsupportedOperationException();
    }

    public void PartiePerdu() {
            // TODO - implement vueJeu.PartiePerdu
            throw new UnsupportedOperationException();
    }



    private void initWindow(){
        window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        window.setSize(tk.getScreenSize().width,tk.getScreenSize().height);
        window.setLayout(new BorderLayout());
        window.setBackground(Color.white);
        
    }
    private void initBas() {
        //InitBas et ses objets
        JPanel bas = new JPanel(new BorderLayout());
        bas.setPreferredSize(new Dimension(window.getWidth(),300));
        window.add(bas,BorderLayout.SOUTH);
        
        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnGagnerTresor = new JButton("GagnerTresor");
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        JPanel decks = new JPanel(new GridLayout(1,2));
        decks.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(decks,BorderLayout.EAST);
        JPanel panelCentre = new JPanel(new BorderLayout());
        bas.add(panelCentre,BorderLayout.CENTER);
        JPanel tresors = new JPanel(new GridLayout(1,4));
        tresors.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(tresors,BorderLayout.WEST);
        //Decks
        //...
        //panelBoutons
        indications = new LabelInfo("Coucou je m'apelle jimmy ");
        indications.setPreferredSize(new Dimension(panelCentre.getWidth(),30));
        panelCentre.add(indications,BorderLayout.NORTH);
        GridLayout g = new GridLayout(1,Nb_Boutons);
        g.setHgap(20);
        //initBoutonsAction (sauf terminertour)
        //Panel Bas -> Centre->Centre
        JPanel BCcentre = new JPanel(new GridLayout(2,2));
        panelCentre.add(BCcentre,BorderLayout.CENTER);
        //Panel Bas -> Centre-> Centre-> Haut
        JPanel BCCHaut = new JPanel(g);
        BCcentre.add(BCCHaut);
        JPanel[] boutonUnitaire = new JPanel[Nb_Boutons];
        for (int i =0;i<Nb_Boutons;i++){
            boutonUnitaire[i] = new JPanel();
            System.out.println(i);
            BCCHaut.add(boutonUnitaire[i]);
        }
        boutonUnitaire[0].add(btnBouger);
        boutonUnitaire[1].add(btnAssecher);
        boutonUnitaire[2].add(btnDonnerCarte);
        boutonUnitaire[3].add(btnGagnerTresor);
        if (Nb_Boutons==5){
            DeplacerAllier=new JButton("Deplacer Allier");
            boutonUnitaire[4].add(DeplacerAllier);
            BCCHaut.add(boutonUnitaire[4]);
        }
        //PANEL Bas->Centre->Centre->Bas
        //initTerminerTour
        JPanel BCCBas = new JPanel();
        BCCBas.add(btnTerminerTour);
        BCcentre.add(BCCBas);
                
        btnBouger.addActionListener(creeActionListener(TypeAction.DEPLACER));
        btnAssecher.addActionListener(creeActionListener(TypeAction.ASSECHER));
        btnTerminerTour.addActionListener(creeActionListener(TypeAction.TERMINER_TOUR));
        
    }
    private ActionListener creeActionListener(TypeAction type){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAction msg = new MessageAction();
                msg.typeact = type;
                notifierMessageAction(msg);
            }
        };
    }
    private JPanel initHaut(VueGrille grille) {
        JPanel haut = new JPanel(new BorderLayout());
        int taille = mesAvs.size();
        //VueAventurier
        JPanel mesVuesAvs = new JPanel(new GridLayout(4,1));
        mesVuesAvs.setBackground(Color.white);
        for (int i=0; i<taille;i++){
            mesVuesAvs.add(mesAvs.get(i).getPrincipal());
        }
        haut.add(monteeDesEau,BorderLayout.WEST);
        haut.add(mesVuesAvs,BorderLayout.EAST);
        haut.add(grille.getVueGrille(),BorderLayout.CENTER);
        window.add(haut,BorderLayout.CENTER);
        return haut;
    }

    public void afficher(boolean b) {
        window.setVisible(b);
    }
    
    public static void main(String[]args) throws IOException{
        VueGrille g = new VueGrille(new Grille(5));
        ArrayList<VueAventurier> mesAvs = new ArrayList<>();
        mesAvs.add(new VueAventurier("Jimmy", "Ingenieur", Color.blue));
        mesAvs.add(new VueAventurier("Jimmy", "Ingenieur", Color.blue));
        VueJeu v = new VueJeu(g,mesAvs);
        v.afficher(true);
    }
    
}