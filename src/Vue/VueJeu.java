package Vue;

import Controleur.Message;
import Controleur.MessageAction;
import Controleur.Observe;
import Controleur.Observateur;
import Controleur.TypeAction;
import Controleur.Utils;
import Controleur.Utils.*;
import Modele.*;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JFrame window;
    private MonteeDesEaux monteeDesEau;
    private JLabel indications;
    private int Nb_Boutons;
    private MessageAction msg;
    private VueChoixCarte vChoixCarte;
    private ArrayList<VueAventurier> vueAvsModif;
    private MouseListener choixAvListener;
    
    
    
    public static final int DEFAUSSER = 10;
    public static final int DON_CARTE = 11;
    public static final int HELICOPTERE = 12;
    private final String debIndic = "Indications : ";
    
    
    public void faireChoixTuile(int a, ArrayList<Tuile> deplacement) {
               if(a==vueGrille.CHOIX_AS || a==vueGrille.CHOIX_DEP) {
                   this.indications.setText(debIndic + "Choississez une Tuile parmis celle proposé pour faire l'action choisie.");
                   vueGrille.faireChoixTuile(a, deplacement);
               }
//Suivant sera pour choisir un aventurier
    }
    
    
    public void faireChoixAventurier(ArrayList<Aventurier> avs,int etat){
        vueAvsModif = new ArrayList<>();
        for(Aventurier av : avs){
            for(VueAventurier vueAv : getMesAvs()){
                if(av.getPion().getCouleur() == vueAv.getColor()){
                    vueAvsModif.add(vueAv);
                }
            }
        }
        for(VueAventurier vueAv: getVueAvsModif()){
            vueAv.addMouseListener(choixAvListener);
        }
    }
    public void faireChoixCarte(int etat,ArrayList<Modele.CarteJoueur> carteJ){
        ArrayList<VueCarte> vCarte;
        vCarte = new ArrayList<>();
        for(Modele.CarteJoueur c : carteJ){
            CarteUtils carteU = null;
            switch(((CarteTresor) c).getTresor()){
                case CALICE_ONDE:
                    carteU = CarteUtils.calice;
                    break;
                case PIERRE_SACREE:
                    carteU = CarteUtils.pierre;
                    break;
                case STATUE_ZEPHYR:
                    carteU = CarteUtils.zephyr;
                    break;
                case CRISTAL_ARDENT:
                    carteU = CarteUtils.cristal;
                    break;
            }
            if(c instanceof CarteSpecial){
                if(c instanceof Helicoptere){
                    carteU = CarteUtils.helicoptere;
                }else if(c instanceof SacDeSable){
                    carteU = CarteUtils.sacsDeSable;
                }
            }
            try {
                vCarte.add(new VueCarte(carteU));
            } catch (IOException ex) {
                Logger.getLogger(VueJeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
            
        String desc = "Choisissez une carte à" + ((etat == DON_CARTE)?"donner":"defausser");
        vChoixCarte = new VueChoixCarte(vCarte,etat);
        vChoixCarte.addObservateur(this);
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

    public void indic_Passif(Aventurier av ) {
        indications.setText(debIndic+" Il vous reste "+av.getActionsRestantes()+" actions restantes");
    }

    public class MonteeDesEaux extends PanelImage {
        private int niveauEau;
        private MonteeDesEaux(int niveau) throws IOException { 
            super("Niveau.png");
            setNiveauEau(niveau);
            this.setPreferredSize(new Dimension(window.getWidth()/7,window.getHeight()));
        }

        private void setNiveauEau(int niveauEau) {
            this.niveauEau = niveauEau;
        }
        public void addNiveau(){
            niveauEau++;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.yellow);
            g.fillRect(getWidth()/15, getHeight()-(niveauEau)*((9/10)*getHeight()/100)-(18*getHeight()/100), 2*getWidth()/4, 10);
        }
    }
    
    public VueJeu(Grille grille,ArrayList<Modele.Aventurier> mesAvs, ArrayList<String> mesNoms, int niveauDeau) throws IOException {
        //Initialisation Composant
        initWindow();
        vueAvsModif = new ArrayList<>();
        choixAvListener  = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                msg.vueAv.add((VueAventurier) e.getSource());
                msg.typeact = TypeAction.CHOIX_INTER_DONCARTE;
                notifierMessageAction(msg);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        
        msg = new MessageAction();
        creationVuesAventuriers(mesNoms, mesAvs);
        vueGrille = new VueGrille(grille,mesAvs);
        vueGrille.addObservateur(this);
        monteeDesEau = new MonteeDesEaux(niveauDeau);
        initHaut();
        initBas();
    }
    public MonteeDesEaux getMonteeDesEau() {
        return monteeDesEau;
    }
    public void erreurTresor() {
            indications.setText(debIndic+"Tu n' pas assez de carte trésor ou tu n'est pas sur une tuile qui donne de trésor");   
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
        JPanel bas = new JPanel(new GridLayout(1,3));
        bas.setPreferredSize(new Dimension(window.getWidth(),(window.getWidth()-2*window.getWidth()/7-window.getHeight())));//Longueur calculer pour avoir une grille carée
        window.add(bas,BorderLayout.SOUTH);
        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnGagnerTresor = new JButton("GagnerTresor");
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        JPanel decks = new JPanel(new BorderLayout());
        decks.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(decks);
        JPanel panelCentre = new JPanel(new BorderLayout());
        bas.add(panelCentre);
        JPanel tresors = new JPanel(new GridLayout(1,4));
        tresors.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(tresors);
        //Tresors
        tresors.add(new PanelImage(tresor.STATUE_ZEPHYR.getChemin()));
        tresors.add(new PanelImage((tresor.CALICE_ONDE.getChemin())));
        tresors.add(new PanelImage((tresor.PIERRE_SACREE.getChemin())));
        tresors.add(new PanelImage((tresor.CRISTAL_ARDENT.getChemin())));
        //Decks
        GridLayout gDecks = new GridLayout(1,2);
        gDecks.setHgap(50);
        gDecks.setVgap(100);
        JLabel pioche = new JLabel("Pioche",JLabel.CENTER);
        JLabel defausse = new JLabel("Défausse",JLabel.CENTER);
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
        indications = new JLabel("Bonne Chance ! ",JLabel.CENTER);
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
        btnGagnerTresor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAction msg = new MessageAction();
                msg.typeact = TypeAction.GAGNERTRESOR;
                notifierMessageAction(msg);
            }
        });
        btnDonnerCarte.addActionListener(creeActionListener(TypeAction.DONNERCARTE));
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
    public void ajoutCarte(Aventurier av,CarteJoueur carte){
        int i = 0;
        boolean b=false;
        while (i<mesVuesAvs.size() && !b){
            if (mesVuesAvs.get(i).getPion()==av.getPion()){
                //translateVue
            }
        }
        
    }
    public void erreur_deplacer(){
        this.indications.setText(debIndic+"Il n'y a aucune tuile pour vous deplacer");
    }
    public void erreur_assecher(){
        this.indications.setText(debIndic+"Il n'y a aucune tuile a assecher présente");
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
    public void setTrActuel(Aventurier av) {
        for (VueAventurier vue : mesVuesAvs){
            if (vue.getPion()==av.getPion()){
                vue.activer(true);
            }
            else{
                vue.activer(false);
            }
        }
    }
    @Override 
    public void traiterMessage(Message msg){
        notifierMessage(msg);
    }
    @Override
    public void traiterMessageAction(MessageAction msg){
        switch(msg.typeact){
            case CHOIX_INTER_DONCARTE:
                this.msg.typeact = TypeAction.CHOIX_DONCARTE;
                break;
            case CHOIX_CARTE:
                if(this.msg.typeact == TypeAction.CHOIX_INTER_DONCARTE){
                    msg.typeact = TypeAction.CHOIX_DONCARTE;
                }else {
                    msg.typeact = TypeAction.CHOIX_DEFAUSSER;
                }
        }
        notifierMessageAction(msg);
    }

    public ArrayList<VueAventurier> getVueAvsModif() {
        return vueAvsModif;
    }
    
}
