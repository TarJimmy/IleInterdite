package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observe;
import Controleur.Observateur;
import Controleur.TypeAction;
import Controleur.Utils;
import Modele.Aventurier;
import Modele.Grille;
import Modele.Tuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueJeu extends Observe implements Observateur {


    private ArrayList<VueAventurier> mesVuesAvs;
    private VueGrille vueGrille;
    private JButton btnBouger;
    private JButton btnAssecher;
    private JButton btnDonnerCarte;
    private JButton btnGagnerTresor;
    private JButton btnTerminerTour;
    private JButton DeplacerAllier;
    private VueAventurier vueAvTrActuel;
    private JFrame window;
    private MonteeDesEaux monteeDesEau;
    private JLabel indications;
    private int Nb_Boutons;
    private MessageAction msg;
    
    
    
    public void faireChoixTuile(int a, ArrayList<Tuile> deplacement) {
               if(a==vueGrille.CHOIX_AS || a==vueGrille.CHOIX_DEP) {
                   vueGrille.faireChoixTuile(a, deplacement);
               }
//Suivant sera pour choisir un aventurier
    }

    public void actualise() {
        vueGrille.actualise();
        monteeDesEau.repaint();
        
    }

    public void deplacePion(Utils.Pion pion, Tuile t) {
        vueGrille.deplacePion(pion,t);
    }
    public int getCHOIX_AS(){
        return VueGrille.CHOIX_AS;
    }
    public int getCHOIX_DEP(){
        return VueGrille.CHOIX_DEP;
    }

    public void faireChoixAventuriers(ArrayList<Aventurier> avsDonsCarte) {
       
    }

    public void finDeplacement(Aventurier av,Tuile t) {
        this.actualise();
        deplacePion(av.getPion(),t);
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
    
    public VueJeu(Grille grille,ArrayList<Modele.Aventurier> mesAvs, ArrayList<String> mesNoms) throws IOException {
        //Initialisation Composant
        initWindow();
        
        creationVuesAventuriers(mesNoms, mesAvs);
        vueGrille = new VueGrille(grille,mesAvs);
        vueGrille.addObservateur(this);
        monteeDesEau = new MonteeDesEaux();
        initHaut();
        initBas();
    }
    public MonteeDesEaux getMonteeDesEau() {
        return monteeDesEau;
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
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        window.setSize(tk.getScreenSize().width,tk.getScreenSize().height);
        window.setLayout(new BorderLayout());
        window.setBackground(Color.white);
        
    }
    private void initBas() throws IOException {
        //InitBas et ses objets
        JPanel bas = new JPanel(new BorderLayout());
        bas.setPreferredSize(new Dimension(window.getWidth(),(window.getWidth()-2*window.getWidth()/7-window.getHeight())));//Longueur calculer pour avoir une grille carée
        window.add(bas,BorderLayout.SOUTH);
        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnGagnerTresor = new JButton("GagnerTresor");
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        JPanel decks = new JPanel(new BorderLayout());
        decks.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(decks,BorderLayout.EAST);
        JPanel panelCentre = new JPanel(new BorderLayout());
        bas.add(panelCentre,BorderLayout.CENTER);
        JPanel tresors = new JPanel(new GridLayout(1,4));
        tresors.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(tresors,BorderLayout.WEST);
        //Tresors
        //tresors.add(new PanelImage())
        //Decks
        GridLayout gDecks = new GridLayout(1,2);
        gDecks.setHgap(50);
        gDecks.setVgap(100);
        JLabel pioche = new JLabel("Pioche");
        JLabel defausse = new JLabel("Défausse");
        JPanel decksHaut = new JPanel(gDecks);
        decksHaut.setBackground(Color.white);
        decksHaut.add(pioche);
        decksHaut.add(defausse);
        JPanel decksBas = new JPanel(gDecks);
        decksBas.add(new VueDeck());
        decksBas.add(new VueDeck());
        decksBas.setBackground(Color.white);
        decks.add(decksHaut, BorderLayout.NORTH);
        decks.add(decksBas,BorderLayout.CENTER);
        //panelBoutons
        indications = new JLabel("Coucou je m'apelle jimmy ",JLabel.CENTER);
        indications.setPreferredSize(new Dimension(panelCentre.getWidth(),30));
        indications.setBackground(Color.white);
        panelCentre.add(indications,BorderLayout.NORTH);
        GridLayout g = new GridLayout(1,Nb_Boutons);
        g.setHgap(20);
        //initBoutonsAction (sauf terminertour)
        //Panel Bas -> Centre->Centre
        JPanel BCcentre = new JPanel(new GridLayout(2,2));
        BCcentre.setBackground(Color.white);
        panelCentre.add(BCcentre,BorderLayout.CENTER);
        panelCentre.setBackground(Color.white);
        //Panel Bas -> Centre-> Centre-> Haut
        JPanel BCCHaut = new JPanel(g);
        BCCHaut.setBackground(Color.white);
        BCcentre.add(BCCHaut);
        //Verifier s'il y a un navigateur
        Iterator it = this.mesVuesAvs.iterator();
        Nb_Boutons = 4;
        while (it.hasNext()){
            VueAventurier vue = (VueAventurier) it.next();
            vue.setPreferredSize(new Dimension(window.getWidth()/7,window.getHeight()));
            if (vue.getNomAventurier().equals("Navigateur")){
                Nb_Boutons +=1;//Si oui mettre emplacement pour boutons deplacer allier
            }
        }
        JPanel[] boutonUnitaire = new JPanel[Nb_Boutons];
        for (int i =0;i<Nb_Boutons;i++){
            boutonUnitaire[i] = new JPanel();
            boutonUnitaire[i].setBackground(Color.white);
            BCCHaut.add(boutonUnitaire[i]);
        }
        boutonUnitaire[0].add(btnBouger);
        boutonUnitaire[1].add(btnAssecher);
        boutonUnitaire[2].add(btnDonnerCarte);
        boutonUnitaire[3].add(btnGagnerTresor);
        if (Nb_Boutons==5){
            DeplacerAllier=new JButton("Deplacer Allier");
            boutonUnitaire[4].add(DeplacerAllier);
            boutonUnitaire[4].setBackground(Color.white);
            BCCHaut.add(boutonUnitaire[4]);
        }
        //PANEL Bas->Centre->Centre->Bas
        //initTerminerTour
        JPanel BCCBas = new JPanel();
        BCCBas.add(btnTerminerTour);
        BCcentre.add(BCCBas);
        BCcentre.setBackground(Color.white);
                
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
    private JPanel initHaut() {
        JPanel haut = new JPanel(new BorderLayout());
        int taille = mesVuesAvs.size();
        //VueAventurier
        GridLayout g = new GridLayout(4,1);
        g.setVgap(20);
        JPanel mesVuesAvs = new JPanel(g);
        mesVuesAvs.setBackground(Color.white);
        for (int i=0; i<taille;i++){
            mesVuesAvs.add(this.mesVuesAvs.get(i));
        }
        haut.add(monteeDesEau,BorderLayout.WEST);
        haut.add(mesVuesAvs,BorderLayout.EAST);
        haut.add(vueGrille.getVueGrille(),BorderLayout.CENTER);
        window.add(haut,BorderLayout.CENTER);
        return haut;
    }

    public void afficher(boolean b) {
        window.setVisible(b);
    }

    public ArrayList<VueAventurier> getMesAvs() {
        return mesVuesAvs;
    }

    public VueGrille getVueGrille() {
        return vueGrille;
    }
    private void creationVuesAventuriers(ArrayList<String> mesNoms,ArrayList<Modele.Aventurier> av) throws IOException{
        mesVuesAvs = new ArrayList<>();
        int taille = av.size();
        for (int i =0;i<taille;i++){
            mesVuesAvs.add(new VueAventurier(mesNoms.get(i), av.get(i),i));
        }
    }
    public void setTrActuel(int index) {
        for (VueAventurier vue : mesVuesAvs){
            if (vue==mesVuesAvs.get(index)){
                //vue.activer(false);
            }
            else{
                //vue.activer(true);
            }
        }
    }
    @Override 
    public void traiterMessage(Message msg){
        notifierMessage(msg);
    }
    @Override
    public void traiterMessageAction(MessageAction msg){
        if(msg.typeact == TypeAction.CHOIX_AV_DONCARTE){
            //choix carte
        }else{
            if(msg.typeact == TypeAction.CHOIX_CARTE_DONCARTE){
                this.msg.vueCarte = msg.vueCarte;
                this.msg.typeact = TypeAction.CHOIX_DONCARTE;
            }
            notifierMessageAction(msg);
        }

    }
    
}