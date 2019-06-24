package Modele;

import Controleur.Utils;

import java.util.*;

/**
 *
 * @author Gholbin
 */
public abstract class Aventurier {

    private Tuile maPos;
    ArrayList<CarteJoueur> mesCartes;

    /**
     * 
     */
    protected int actionsRestantes;
    Utils.Pion pion;
    private static HashSet<Utils.tresor> tresorsRecuperer = new HashSet<>();

    public static HashSet<Utils.tresor> getTresorsRecuperer() {
        return tresorsRecuperer;
    }

    /**
     * initialise un aventurier avec 3 actions, sans carte
     */
    public Aventurier() {
        setActionsRestantes(3);
        mesCartes = new ArrayList<>();
    }

    /**
     * Met à jour la tuile où il se situe
     * @param maPos
     */
    public Aventurier(Tuile maPos) {
        this.maPos = maPos;
        mesCartes = new ArrayList<>();
    }
    
    /**
     *  
     * @return le pion
     */
    public Utils.Pion getPion() {
        return pion;
    }
    
    /**
     * Met à jour le pion
     * @param pion
     */
    protected void setPion(Utils.Pion pion){
        this.pion = pion;
    }
    
    /**
     * Deplace le joueur sans lui couter de point d'action (Helicoptere, Navigateur,...)
     * @param tuile
     */
    public void deplaceHorsTour(Tuile tuile){
        setMaPos(tuile);
    }
    
    /**
     * Deplace le joueur, cela lui coûte un point d'action
     * @param tuile
     */
    public void deplacer(Tuile tuile) {
        setMaPos(tuile);
        actionsRestantes--;
    }

    /**
     * 
     * @return Le nom de l'aventurier (Pilote, Nvaigateur,...)
     */
    public abstract String getNomAventurier();
    

    /**
     * 
     * @param tuile
     */
    public void assecher(Tuile tuile) {
        tuile.Assecher();
        actionsRestantes--;
    }

    /**
     *
     * @param carte
     * @param av
     */
    public void DonnerCarte(CarteJoueur carte, Aventurier av) {
        av.AddCarte(carte);
        SupprimerCarte(carte);
        actionsRestantes--;
    }

    /**
     *
     * @return le tresor que l'aventurier peut recuperer, null s'il n'y en a pas
     */
    public Utils.tresor checkGagnerTresor(){
        Utils.tresor tres = getMaPos().getTresor();
        int nbCarteTresor = 0;
        if(nbCarte() >= 4 && tres != null){
            for(CarteJoueur carte : getCartes()){
                if(carte instanceof CarteTresor && ((CarteTresor) carte ).getTresor() == tres){
                    nbCarteTresor++;
                }
            }
        }
        return (nbCarteTresor >=4)?tres:null;
    }

    /**
     * Retire les cartes tresor ayant le tresor tres
     * @param tres
     * 
     */
    public ArrayList<CarteJoueur> GagnerTresor(Utils.tresor tres) {
        ArrayList<CarteJoueur> cartes = new ArrayList<>();
        Iterator it = getCartes().iterator();
        while(it.hasNext() && cartes.size() < 4){
            CarteJoueur carte =(CarteJoueur) it.next();
            if(carte instanceof CarteTresor){
                if(((CarteTresor) carte).getTresor() == tres){
                    cartes.add(carte);
                    it.remove();
                }
            }
        }
        getTresorsRecuperer().add(tres);
        actionsRestantes--;
        return cartes;
    }

    /**
     *
     * @return toutes les cartes
     */
    public ArrayList<CarteJoueur> getCartes() {
        return mesCartes;
    }
    
    /**
     *
     * @param avs
     * @return Les aventuriers à portée pour un don de carte
     */
    public ArrayList<Aventurier> getAvsDonsCarte(ArrayList<Aventurier> avs){
        return getAvsHelicoptere(avs);
    }

    /**
     *
     * @return Les cartes qu'il peut donner
     */
    public ArrayList<CarteJoueur> getCartesDonnables(){
        ArrayList<CarteJoueur> donnables = new ArrayList<>();
        for(CarteJoueur c : getCartes()){
            if(!(c instanceof CarteSpecial)){
                donnables.add(c);
            }
        }
        return donnables;
    }

    /**
     *
     * @return Les cartes special qu'il possède
     */
    public ArrayList<CarteJoueur> getCartesSpecial(){
        ArrayList<CarteJoueur> speciales = getCartes();
        speciales.removeAll(getCartesDonnables());
        return speciales;
    }
    
    /**
     * Retire carte de la main du joueur
     * @param carte
     */
    public void SupprimerCarte(CarteJoueur carte) {
        getCartes().remove(carte);
    }

    /** 
     * Ajoute la carte à la main du joueur
     * @param carte
     */
    public void AddCarte(CarteJoueur carte) {
        getCartes().add(carte);
    }

    /**
     *
     * @return le nombre de cartes que possède le joueur
     */
    public int nbCarte() {
        return getCartes().size();
    }

    /**
     *
     * @param tuile
     * @return Vrai si le joueur peut aller sur la tuile
     */
    public boolean checkDeplacement(Tuile tuile) {
        return tuile.estDisponible();
    }

    /**
     *
     * @param grille
     * @return Les tuiles où le navigateur peut deplacer le joueur
     */
    public ArrayList<Tuile> getDeplacementNav(Grille grille){
        ArrayList<Tuile> collecTuiles1 = grille.getVoisins(getMaPos(),getCoordsProche());
        ArrayList<Tuile> collecTuiles2 = new ArrayList<>();
        Iterator it = collecTuiles1.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)){
                it.remove();
            }
        }
        collecTuiles2.addAll(collecTuiles1);
        for(Tuile tui : collecTuiles1){
            collecTuiles2.addAll(grille.getVoisins(tui,getCoordsProche()));
        }
        it = collecTuiles2.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)){
                it.remove();
            }
        }
        
        return collecTuiles2;
    }
    
    /**
     *
     * @param grille
     * @return Les tuiles où le joueur peut aller
     */
    public ArrayList<Tuile> getDeplacement(Grille grille) {
        ArrayList<Tuile> collecTuiles = grille.getVoisins(getMaPos(),getCoordsProche());
        Iterator it = collecTuiles.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)) it.remove();
        }
        return collecTuiles;
    }

    /**
     * Reinitialise les actions pour le debut du tour
     */
    public void DebutTour() {
        setActionsRestantes(3);
    }

    /**
     *
     * @return Les coordonnées relatives à portée du joueur
     */
    public ArrayList<int[]> getCoordsProche() {
        ArrayList<int[]> coords = new ArrayList<>();
        int d;
        for(int x = -1;x<=1;x++){
            for(int y = -1;y <= 1;y++){
                d = Math.abs(x+y);
                if(d == 1){
                    coords.add(new int[] {x,y});
                }
            }
        }
        return coords;
    }
     
    /**
     *
     * @param grille
     * @return Les tuiles assechables à portée du joueur
     */
    public ArrayList<Tuile> getAssechement(Grille grille) {
        ArrayList<int[]> coords = getCoordsProche();
        coords.add(new int[] {0,0});
        ArrayList<Tuile> collecTuiles = grille.getVoisins(getMaPos(),coords);
        Iterator it = collecTuiles.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkAssechement(tui)) it.remove();
        }
        return collecTuiles;
    }

    /**
     *
     * @param tui
     * @return Vrai si la tuile est assechable
     */
    public boolean checkAssechement(Tuile tui) {
        return tui.isInnondee();
    }

    /**
     *
     * @return La position du joueur
     */
    public Tuile getMaPos() {
        return maPos;
    }

    /**
     * Met à jour la position du joueur
     * @param maPos
     */
    private void setMaPos(Tuile maPos) {
        this.maPos = maPos;
    }

    /**
     *
     * @return Le nombre d'actions restantes
     */
    public int getActionsRestantes() {
        return actionsRestantes;
    }

    public void setActionsRestantes(int actionsRestantes) {
        this.actionsRestantes = actionsRestantes;
    }
    @Override
    public String toString(){
        return getNomAventurier();
    }
    
    /**
     *
     * @param avs
     * @return renvoie les Aventurier avec qui il peut partir en helicoptere 
     */
    public ArrayList<Aventurier> getAvsHelicoptere(ArrayList<Aventurier> avs){
        ArrayList<Aventurier> voisins = new ArrayList<>();
        for(Aventurier av : avs){
            if (av != this && av.getMaPos() == this.getMaPos()){
                voisins.add(av);
            }
        }
        return voisins;
    }
    
    /**
     *
     * @return renvoie la description des pouvoirs de l'aventurier
     */
    public  String getDescription(){
        return "Pouvoir :\n";
    };
    
    
}
