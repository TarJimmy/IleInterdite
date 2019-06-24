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
import java.awt.FlowLayout;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VueJeu extends Observe implements Observateur {


    private ArrayList<VueAventurier> mesVuesAvs;
    private VueGrille vueGrille;
    private JButton btnBouger;
    private JButton btnAssecher;
    private JButton btnDonnerCarte;
    private JButton btnGagnerTresor;
    private JButton btnTerminerTour;
    //private JButton btnDeplacerAllier;
    private JFrame window;
    private MonteeDesEaux monteeDesEau;
    private JLabel indications;
    private int Nb_Boutons;
    private MessageAction msg;
    private VueChoixCarte vChoixCarte;
    private ArrayList<VueAventurier> vueAvsModif;
    private MouseListener choixAvListener;
    private final PanelImage ZEPHYR =  new PanelImage(tresor.STATUE_ZEPHYR.getChemin());
    private final PanelImage CALICE =  new PanelImage(tresor.CALICE_ONDE.getChemin());
    private final PanelImage PIERRE =  new PanelImage(tresor.PIERRE_SACREE.getChemin());
    private final PanelImage CRISTAL =  new PanelImage(tresor.CRISTAL_ARDENT.getChemin());
    private final PanelImage[] mesTresors = {new PanelImage(tresor.STATUE_ZEPHYR.getChemin()),
                                            new PanelImage(tresor.CALICE_ONDE.getChemin()),
                                            new PanelImage(tresor.PIERRE_SACREE.getChemin()),
                                            new PanelImage(tresor.CRISTAL_ARDENT.getChemin())};
    
    
    public static final int CHOIX_DEP=1;
    public static final int CHOIX_AS=2;
    
    
    public static final int DEFAUSSER = 10;
    public static final int DON_CARTE = 11;
    public static final int HELICOPTERE = 12;
    private final String debIndic = "Indications : ";
    private final VueDeck piocheInondation= new VueDeck(VueDeck.DECK_INONDATION);
    private final VueDeck defausseInondation = new VueDeck(VueDeck.DECK_INONDATION);
    private final VueDeck piocheTresor= new VueDeck(VueDeck.DECK_TRESOR);
    private final VueDeck defausseTresor= new VueDeck(VueDeck.DECK_TRESOR);
    private final Font fontToutText = new Font(Font.SERIF, Font.CENTER_BASELINE, 15);
    public void faireChoixTuile(int a, ArrayList<Tuile> deplacement) {
               if(a==CHOIX_AS || a==CHOIX_DEP) {
                   this.indications.setText(debIndic + "Choississez une Tuile parmi celles proposés pour faire l'action choisie.");
                   vueGrille.faireChoixTuile(a, deplacement);
               }
//Suivant sera pour choisir un aventurier
    }
    //Transforme une VueAventurier en aventurier
    public Aventurier translate_VueAv_Av(VueAventurier va,ArrayList<Aventurier> avs){
        Aventurier aventurier = null;
        for(Aventurier av : avs){
            if (av.getPion() == va.getPion()) {
                aventurier = av;
            }
        }
        return aventurier;
    }
    //Transforme un aventurier en VueAventurier
    public VueAventurier translate_Av_VueAv(Aventurier av){
        VueAventurier vueAv = null;
        for(VueAventurier vAv : getMesVuesAvs()){
            if (vAv.getPion() == av.getPion()) {
                vueAv = vAv;
            }
        }
        return vueAv;
    }
    
    //Transforme une carte en VueCarte
    public VueCarte translate_Ca_VueCa(CarteJoueur c,Aventurier av){
        VueAventurier VueAv = translate_Av_VueAv(av);
        VueCarte vueCarte = null;
        for(VueCarte vCa : VueAv.getMesCartes()){
            if(vCa.getCarte() == c.getCarte()){
                vueCarte = vCa;
            }
        }
        return vueCarte;
    }
    //Transforme une vueCarte en carte
    public CarteJoueur translate_VueCa_Ca(VueCarte vueCa,ArrayList<CarteJoueur> caJoueur){
        CarteJoueur caJ = null;
        
        for(CarteJoueur ca : caJoueur){
            if(ca.getCarte() == vueCa.getCarte()){
                caJ = ca;
            }
        }
        return caJ;
    }
    //Porpose le choix d'un aventurier
    public void faireChoixAventurier(ArrayList<Aventurier> avs,int etat){
        vueAvsModif = new ArrayList<>();
        this.indications.setText(debIndic + "Choisissez un Aventurier parmi ceux disponibles pour faire l'action choisie.");
        
        for(Aventurier av : avs){
            for(VueAventurier vueAv : getMesVuesAvs()){
                if(av.getPion() == vueAv.getPion()){
                    vueAvsModif.add(vueAv);
                    vueAv.activer(true);
                }
            }
        }
        for(VueAventurier vueAv: getVueAvsModif()){
            vueAv.addMouseListener(choixAvListener);
        }
        indications.setText(debIndic+ "Cliquer sur l'Aventurier a qui vous voulez donner votre carte (sur sa couleur)");
    }
    //Porpose le choix d'une carte
    public void faireChoixCarte(int etat,ArrayList<Modele.CarteJoueur> carteJ){
        ArrayList<VueCarte> vCarte;
        vCarte = new ArrayList<>();
        for(Modele.CarteJoueur c : carteJ){
            CarteUtils carteU = null;
            if(c instanceof CarteTresor){
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
        
        
        vChoixCarte = new VueChoixCarte(vCarte,etat);
        vChoixCarte.addObservateur(this);
    }
    //Actualise la vue Grille et les VueAventuriers si elle ont été modifié par une action précedente
    public void actualise() {
        vueGrille.actualise();
        monteeDesEau.repaint();
        for(VueAventurier vAv : getVueAvsModif()){
            vAv.removeMouseListener(choixAvListener);
        }
        BtnSetEnabled(true);
        
    }
//Déplace un pion
    private void deplacePion(Utils.Pion pion, Tuile t) {
        vueGrille.deplacePion(pion,t);
    }
    
    public int getCHOIX_AS(){
        return CHOIX_AS;
    }
    public int getCHOIX_DEP(){
        return CHOIX_DEP;
    }
    //Fait le déplacement complet du pion et termine le déplacement
    public void finDeplacement(Aventurier av,Tuile t) {
        this.actualise();
        deplacePion(av.getPion(),t);
    }
    //Indications du nombre d'actions restantes
    public void indic_Passif(Aventurier av ) {
        indications.setText(debIndic+" Vous avez " + av.getActionsRestantes() + ((av.getActionsRestantes() ==1)?" action restante":" actions restantes" ));
    }
    //Affiche le gain du tresor tres
    public void gainTresor(tresor tres) {
        int i=0;
        boolean b=true;
        while (i<mesTresors.length&&b){
            if(mesTresors[i].getNomIm()==tres.getChemin()){
                mesTresors[i].setVisible(true);
                b=false;
            }
            i++;
        }
    }
//Indique au joueur qu'il ne peut pa donner de carte
    public void erreur_DonCarte() {
        indications.setText(debIndic+"Tu n'a aucune carte a donner");
    }
//Indique au joueur qu'il ne peut pas chosiir d'aventurier
    public void erreur_choixAventurier() {
        indications.setText(debIndic+"Tu ne peut donner de carte a aucun Aventurier");
    }
    //Change l'étaat de la vueTuile associé à t
    public void changeEtat(EtatTuile etat, Tuile t) {
        vueGrille.getVueTuile(t.getCoords()).changeEtat(etat);
    }
//initialise les boutons
    private void initBouton(JButton btn) {
            btn.setBackground(Color.yellow);
            btn.setBorderPainted(true);
            btn.setFont(fontToutText);
            btn.setBorderPainted(false);
    }
//Classe de l'image montée des eaux
    public class MonteeDesEaux extends PanelImage {
        private int niveauEau;
        private MonteeDesEaux(int niveau) throws IOException { 
            super("Niveau.png");
            setNiveauEau(niveau);
            this.setPreferredSize(new Dimension(window.getWidth()/7,window.getHeight()));
        }

        private void setNiveauEau(int niveauEau) {
            this.niveauEau = niveauEau;
            repaint();
        }
        public void addNiveau(){
            niveauEau++;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.yellow);
            g.fillRect(getWidth()/15, getHeight()-(niveauEau)*(getHeight()/100)-(18*getHeight()/100), 2*getWidth()/4, 10);
        }
    }
    //Constructeur de la vueJeu
    public VueJeu(Grille grille,ArrayList<Modele.Aventurier> mesAvs, ArrayList<String> mesNoms, int niveauDeau) throws IOException {
        //Initialisation Composant
        initWindow();
        vueAvsModif = new ArrayList<>();
        choixAvListener  = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actualise();
                msg.vueAv.add((VueAventurier) e.getSource());
                msg.typeact = TypeAction.CHOIX_INTER_DONCARTE;
                notifierMessageAction(msg);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MessageAction msg = new MessageAction();
                msg.vueAv.add((VueAventurier) e.getSource());
                msg.typeact = TypeAction.CHOIX_INTER_DONCARTE;
                traiterMessageAction(msg);
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
        monteeDesEau = new MonteeDesEaux(1);
        monteeDesEau.addNiveau();
        initHaut();
        initBas();
        afficher(true);
    }
    public MonteeDesEaux getMonteeDesEau() {
        return monteeDesEau;
    }
    //Indique aux joueurs qu'il ne peut pas Gagner de tresor
    public void erreurTresor() {
            indications.setText(debIndic+"Tu n'a pas assez de carte trésor ou tu n'est pas sur une tuile qui donne de trésor");   
    }
    //Indique a l'utilisateur qu'il a gagné la partie
    public void PartieGagner() {
        BtnSetEnabled(false);
        indications.setText(debIndic + "Vous avez Gagnée !");
    }
    //Indique a l'utilisateur qu'il a perdu la partie
    public void PartiePerdu() {
        BtnSetEnabled(false);
        indications.setText(debIndic + "Vous avez Perdu !");
    }


    //Initialise la fenetre
    private void initWindow(){
        window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        window.setSize(tk.getScreenSize().width,tk.getScreenSize().height);
        window.setLayout(new BorderLayout());
        window.setBackground(Color.white);
    }
    //Initialise la partie bas de la fenetre
    private void initBas() throws IOException {
        //InitBas et ses objets
        JPanel bas = new JPanel(new GridLayout(1,3));
        bas.setPreferredSize(new Dimension(window.getWidth(),(window.getWidth()-2*window.getWidth()/7-window.getHeight())));//Longueur calculer pour avoir une grille carée
        window.add(bas,BorderLayout.SOUTH);
        this.btnBouger = new JButton("Bouger") ;
        initBouton(btnBouger);
        this.btnAssecher = new JButton( "Assecher");
        initBouton(btnAssecher);
        this.btnDonnerCarte = new JButton("Donner Carte");
        initBouton(btnDonnerCarte);
        this.btnGagnerTresor = new JButton("GagnerTresor");
        initBouton(btnGagnerTresor);
        this.btnTerminerTour = new JButton("Terminer Tour");
        initBouton(btnTerminerTour);
        JPanel decks = new JPanel(new BorderLayout());
        decks.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(decks);
        JPanel panelCentre = new JPanel(new BorderLayout());
        bas.add(panelCentre);
        JPanel tresors = new JPanel(new GridLayout(1,4));
        tresors.setPreferredSize(new Dimension(300,bas.getHeight()));
        bas.add(tresors);
        //Tresors
        JPanel[] mesPanelsTresors = new JPanel[4];
        for (int i =0; i<mesPanelsTresors.length;i++){
            mesPanelsTresors[i] = new JPanel();
            mesPanelsTresors[i].setBackground(Color.white);
            JLabel l = new JLabel("");
            //mesPanelsTresors[i].add();
            mesPanelsTresors[i].add(mesTresors[i]);
            mesTresors[i].setPreferredSize(new Dimension(100,158));
            mesTresors[i].setVisible(false);
            tresors.add(mesPanelsTresors[i]);
        }
        //Decks
        GridLayout gDecks = new GridLayout(1,4);
        gDecks.setHgap(50);
        gDecks.setVgap(100);
        JLabel pInondation = new JLabel("Pioche Inondation",JLabel.CENTER);
        JLabel dInondation = new JLabel("Défausse Inondation",JLabel.CENTER);
        JLabel pTresor = new JLabel("Pioche Tresor",JLabel.CENTER);
        JLabel dTresor = new JLabel("Défausse Tresor",JLabel.CENTER);
        JPanel decksHaut = new JPanel(gDecks);
        decksHaut.setBackground(Color.white);
        decksHaut.add(pInondation);
        decksHaut.add(dInondation);
        decksHaut.add(pTresor);
        decksHaut.add(dTresor);
        JPanel decksBas = new JPanel(gDecks);
        decksBas.add(piocheInondation);
        decksBas.add(defausseInondation);
        decksBas.add(piocheTresor);
        decksBas.add(defausseTresor);
        decksBas.setBackground(Color.white);
        decks.add(decksHaut, BorderLayout.NORTH);
        decks.add(decksBas,BorderLayout.CENTER);
        
        //panelBoutons
        indications = new JLabel("Bonne Chance ! ",JLabel.CENTER);
        indications.setPreferredSize(new Dimension(panelCentre.getWidth(),30));
        indications.setFont(fontToutText);
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
            //Règle Simplifié
            /*if (vue.getTypeAventurier().equals("Navigateur")){
                Nb_Boutons +=1;//Si oui mettre emplacement pour boutons deplacer allier
            }*/
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
        //On a finalement pris les règles simplifé
        /*if (Nb_Boutons==5){
            btnDeplacerAllier=new JButton("Déplacer Allier");
            boutonUnitaire[4].add(btnDeplacerAllier);
            initBouton(btnDeplacerAllier);
            boutonUnitaire[4].setBackground(Color.white);
            BCCHaut.add(boutonUnitaire[4]);
        }*/
        //PANEL Bas->Centre->Centre->Bas
        //initTerminerTour
        JPanel BCCBas = new JPanel();
        BCCBas.setBackground(Color.white);
        BCCBas.add(btnTerminerTour);
        BCcentre.add(BCCBas);
        BCcentre.setBackground(Color.white);
                
        btnBouger.addActionListener(creeActionListener(TypeAction.DEPLACER));
        btnAssecher.addActionListener(creeActionListener(TypeAction.ASSECHER));
        btnTerminerTour.addActionListener(creeActionListener(TypeAction.TERMINER_TOUR));
        btnGagnerTresor.addActionListener(creeActionListener(TypeAction.GAGNERTRESOR));
        btnDonnerCarte.addActionListener(creeActionListener(TypeAction.DONNERCARTE));
    }
    //Crée un action listener avec pour msg.Type rentrée en paramètre
    private ActionListener creeActionListener(TypeAction type){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualise();
                MessageAction msg = new MessageAction();
                msg.typeact = type;
                notifierMessageAction(msg);
            }
        };
    }
    //Ajoute une carte (carte) à un aventurier (av)
    public void ajoutCarte(Aventurier av,CarteJoueur carte) throws IOException{
        translate_Av_VueAv(av).ajouterVueCarte(carte.getCarte());
    }
    //Supprime une carte (carte) à un aventurier (av)
    public void supprimeCarte(Aventurier av,CarteJoueur carte) throws IOException{
        translate_Av_VueAv(av).supprimerVueCarte(carte.getCarte());
    }
    
    //Indique aux joueurs que l'action déplacer n'est pas 
    public void erreur_deplacer(){
        this.indications.setText(debIndic+"Il n'y a aucune tuile pour vous deplacer");
    }
    //Indique aux  joueurs que l'action assécher n'est pas disponible
    public void erreur_assecher(){
        this.indications.setText(debIndic+"Il n'y a aucune tuile a assecher présente");
    }
    //Initailise la partie du haut de la vueJeu
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
//Affiche la fenetre
    private void afficher(boolean b) {
        window.setVisible(b);
    }

    public ArrayList<VueAventurier> getMesVuesAvs() {
        return mesVuesAvs;
    }

    public VueGrille getVueGrille() {
        return vueGrille;
    }
    //Crée les VueAventuriers avec pour parametres des aventuriers
    private void creationVuesAventuriers(ArrayList<String> mesNoms,ArrayList<Modele.Aventurier> av) throws IOException{
        mesVuesAvs = new ArrayList<>();
        int taille = av.size();
        for (int i =0;i<taille;i++){
            mesVuesAvs.add(new VueAventurier(mesNoms.get(i), av.get(i),i));
        }
    }
    //Modifie la vue du tour de l'aventurier actuel
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
        actualise();
        switch(msg.typeact){
            case CHOIX_INTER_DONCARTE:
                this.msg.vueAv = msg.vueAv;
                break;
            case CHOIX_DONCARTE:
                this.vChoixCarte.setVisible(false);
                msg.vueAv = this.msg.vueAv;
            break;
            case CHOIX_DEFAUSSER:
                this.vChoixCarte.setVisible(false);
                this.msg.vueCarte = msg.vueCarte;
                break;
        }
        
        notifierMessageAction(msg);
    }

    public ArrayList<VueAventurier> getVueAvsModif() {
        return vueAvsModif;
    }
    //Fais un déplacmeent forcé
    public void deplacementforce(ArrayList<Tuile> deplacement){
        BtnSetEnabled(false);
        faireChoixTuile(CHOIX_DEP, deplacement);
    }
    //Desactive les boutons
    private void BtnSetEnabled(boolean b){
        btnBouger.setEnabled(b);
        btnAssecher.setEnabled(b);
        btnDonnerCarte.setEnabled(b);
        btnGagnerTresor.setEnabled(b);
        btnTerminerTour.setEnabled(b);
        //Règle simplifié
        /*if(Nb_Boutons == 5){
            btnDeplacerAllier.setEnabled(b);
        }*/
    }
    //Label d'indications de début d'un tour
    public void debutTour(Aventurier av){
        indications.setText(debIndic + "Au tour de " + translate_Av_VueAv(av).getNomAventurier());
    }
    
    
}
