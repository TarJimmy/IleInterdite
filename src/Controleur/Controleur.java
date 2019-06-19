/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;


import Modele.*;
import Vue.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;







public class Controleur implements Observateur {
    private VueAccueil accueil;
    private VueGrille vueGrille;
    private VueJeu jeu;
    private ArrayList<VueAventurier> mesVuesAventuriers;
    private ArrayList<Aventurier> mesAventuriers;
    private Grille grille;
    private DeckInnondation deckInnondation;
    private DeckTresor deckTresor;
    private Aventurier AvTrActuel;
    private int index;
    private VueAventurier vueAvTrActuel;
    private ArrayList<VuePion> mesPions;
    
    
    
    Controleur() throws IOException{
        accueil = new VueAccueil();
        accueil.addObservateur(this);
        accueil.afficher(true);
        index = 0;
        
    }

    public HashSet<Utils.tresor> getTresorsRecuperers() {
        return Aventurier.getTresorsRecuperer();
    }
    
    public ArrayList<Aventurier> getMesAventuriers() {
        return mesAventuriers;
    }
    //Actualise le tour de l'aventurier en fonction de l'index
    private void setTrAv() {
        this.AvTrActuel = getMesAventuriers().get(index);
        this.vueAvTrActuel = mesVuesAventuriers.get(index);
    }
    private void addIndex(){
        index = (index+1)/getMesAventuriers().size();
    }
    public void piocher (){
        for (int i = 0; i < 2; i++) {
            CarteJoueur carte = deckTresor.Piocher();
            if(carte instanceof CarteMonteeEau){
                MonteeDesEaux(carte);
            }else{
                getAvTrActuel().AddCarte(carte);
            }
        }
        CheckNbCarte(getAvTrActuel());
    }
    public boolean partieGagne(){
        boolean gagne = true;
        gagne = getTresorsRecuperers().contains(Utils.tresor.values());
        if(gagne){
            boolean helicoptere = false;
            for(Aventurier av : getMesAventuriers()){
                gagne = gagne && av.getMaPos().getNom().equals(Utils.TuilesUtils.heliport);
                if(!helicoptere){
                    for(CarteJoueur c : av.getCartesSpecial()){
                        helicoptere = helicoptere && c instanceof Helicoptere;
                    }
                }
            }
            gagne = gagne && helicoptere;
        }
        return gagne;
    }
    public boolean partiePerdu(){
        boolean perdu = !grille.getTuile(Utils.TuilesUtils.heliport).estDisponible();
        perdu = perdu || grille.getEchelonMonteEau() <= 10;
        if(!perdu){
            HashSet<Utils.tresor> tresors = new HashSet<>();
            tresors.add(null);
            for(Tuile tui : grille.getTuilesDisponibles()){
                tresors.add(tui.getTresor());
            }
            tresors.remove(null);
            perdu = tresors.contains(Utils.tresor.values());
        }
        return perdu;
    }
    
    
    private void debutTour(){
        setTrAv();
        getAvTrActuel().DebutTour();
        for (VueAventurier vue : mesVuesAventuriers){
            if (vue != vueAvTrActuel){
                //vue.activer(false);
            }
            else{
                //vue.activer(true);
            }
        }
    }

    public Aventurier getAvTrActuel(){
        return AvTrActuel;
    }
    
    
    public void MonteeDesEaux(CarteJoueur c){
        grille.MonterNiveauDeau();
        deckInnondation.ResetPioche();
        deckTresor.Defausser(c);
    }
    public void faireDefausser(Aventurier av,int nbCartes){
            //defausser des cartes via l'ihm
            //Defausser(int nbCartes) avec nbCartes = getAvTrActuel().nbCarte()
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void checkFinTour(){
        if(getAvTrActuel().getActionsRestantes() == 0 ){
            if(getAvTrActuel() instanceof Ingenieur && ((Ingenieur) getAvTrActuel()).aAssecher()){
                //ihm fonction uniquement assecher
               // VueJeu.IngenieurAssecherFT();
            }
            finTour();
        }
    }
    
    
    public void finTour(){
        if(!partieGagne()){
            piocher();
            addIndex();
            debutTour();
        }

    }
    public void CheckNbCarte(Aventurier av){
        if( av.nbCarte() > 5){
            faireDefausser(av, av.nbCarte()-5);
        }
    }
    
    private void creationAventurier(int nbAventuriers){
        mesAventuriers = new ArrayList<>();
        mesAventuriers.add(new Ingenieur(grille));
        mesAventuriers.add(new Messager(grille));
        mesAventuriers.add(new Navigateur(grille));
        mesAventuriers.add(new Explorateur(grille));
        mesAventuriers.add(new Plongeur(grille));
        mesAventuriers.add(new Pilote(grille));
        for (int i=0;i<(6-nbAventuriers);i++){
            int r1= (int) (Math.random() * (6-i));
            mesAventuriers.remove(mesAventuriers.get(r1));
        }
    }
    private void creationVuesAventuriers(ArrayList<String> mesNoms){
        mesVuesAventuriers = new ArrayList<>();
        int taille = getMesAventuriers().size();
        for (int i =0;i<taille;i++){
            mesVuesAventuriers.add(new VueAventurier(mesNoms.get(i),getMesAventuriers().get(i)));
        }
    }
    private void creePion(){
        mesPions = new ArrayList<>();
        int i=0;
        for (Aventurier av : getMesAventuriers()){
            mesPions.add(new VuePion(av.getPion()));
            vueGrille.getVueTuile(av.getMaPos().getCoords()).initVuePion(mesPions.get(i));
            i++;
        }
    }
    private ArrayList<VueAventurier> translateAve_VueAvs(ArrayList<Aventurier> avs){
        return null;
    }
    private CarteJoueur translate_VueCarte_Carte(VueCarte vueCarte){
        return null;
    }
    @Override
    public void traiterMessage(Message msg) {
        switch(msg.type){
            case DEBUTJEU:
                accueil.afficher(false);
                grille= new Grille(msg.difficulte);
                vueGrille = new VueGrille(grille);
                creationAventurier(msg.nbJoueur);
                creationVuesAventuriers(msg.noms);
                creePion();
        {
            try {
                jeu = new VueJeu(vueGrille,mesVuesAventuriers);
            } catch (IOException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                jeu.afficher(true);
                debutTour();
                //A enlever apres demo
                for (VueAventurier av : mesVuesAventuriers){
                    av.addObservateur(this);
                    vueGrille.addObservateur(this);
                }
                jeu.addObservateur(this);
                grille.getTuile(2, 2).Innonder();
                vueGrille.getVueTuile(new int[] {2,2}).changeEtat(grille.getTuile(2, 2).getEtat());
                grille.getTuile(4, 4).Innonder();
                vueGrille.getVueTuile(new int[] {4,4}).changeEtat(grille.getTuile(4, 4).getEtat());
                 grille.getTuile(3, 4).Innonder();
                vueGrille.getVueTuile(new int[] {3,4}).changeEtat(grille.getTuile(3, 4).getEtat());
                 grille.getTuile(3, 3).Innonder();
                 grille.getTuile(3, 3).Innonder();
                vueGrille.getVueTuile(new int[] {3,3}).changeEtat(grille.getTuile(3, 3).getEtat());
                System.out.println(vueGrille.getVueTuile(new int[] {3,4}).getWidth());
                System.out.println(vueGrille.getVueTuile(new int[] {3,4}).getHeight());
                break;
                
                
            case ACTION:
                
                break;
        }
    }

    @Override
    public void traiterMessageAction(MessageAction msg) {
        Tuile t;
        switch(msg.typeact){
            case DEPLACER:
                int a=0;
                vueGrille.faireChoixTuile(a, getAvTrActuel().getDeplacement(grille)); //a faire 
                break;
            case ASSECHER:
            int b = 0;
            vueGrille.faireChoixTuile(b,getAvTrActuel().getAssechement(grille));
            break;
            case TERMINER_TOUR:
                finTour();
                break;
            case GAGNERTRESOR://A remplir
                break;
            case DONNERCARTE:
                int c=0;
                vueGrille.faireChoixVueAventuriers(translateAve_VueAvs(getAvTrActuel().getAvsDonsCarte(getMesAventuriers())));
                break;
            case CHOIX_TUILE_DEP:
                t = grille.getTuile(msg.coord[0],msg.coord[1]);
                getAvTrActuel().deplacer(t);
                mesPions.get(index).setMaTuile(vueGrille.getVueTuile(t.getCoords()));
                vueGrille.actualise();
                checkFinTour();
                break;
            case CHOIX_TUILE_AS:
                t = grille.getTuile(msg.coord[0],msg.coord[1]);
                getAvTrActuel().assecher(t);
                mesPions.get(index).setMaTuile(vueGrille.getVueTuile(t.getCoords()));
                checkFinTour();
                break;
            case CHOIX_AV_DONCARTE:
                Aventurier receveur = getMesAventuriers().get(getMesVuesAventuriers().indexOf(msg.VueAv));
                CarteJoueur carte = this.translate_VueCarte_Carte(msg.vueCarte);
                getAvTrActuel().DonnerCarte(carte, receveur);
                CheckNbCarte(receveur);//finir
                 break;
            
                
                
        }
    }

    public ArrayList<VueAventurier> getMesVuesAventuriers() {
        return mesVuesAventuriers;
    }
    
    

    
    public static void main(String[]args ) throws IOException{
        new Controleur();
    }
}