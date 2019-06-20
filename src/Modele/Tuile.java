/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Utils.TuilesUtils;
import Controleur.Utils.EtatTuile;
import Controleur.Utils.tresor;

/**
 *
 * @author beaufima
 */
public class Tuile {
    
    private TuilesUtils nom;
    private EtatTuile etat;
    private int[] coords;
    private tresor tresor;

    //CONSTRUCTEUR 
    //Il faut rajouter Tresor

    /**
     * Cree une tuile assechee
     * @param nom
     */
    public Tuile(TuilesUtils nom) {
        this.setNom(nom);
        this.setEtat(EtatTuile.ASSECHEE);
    }

    

    //GETTERS
    
    /**
     *
     * @return
     */
        
    public TuilesUtils getNom() {
        return nom;
    }

    /**
     *
     * @return
     */
    public EtatTuile getEtat() {
        return etat;
    }

    /**
     *
     * @return
     */
    public int[] getCoords() {
        return coords;
    }

    /**
     *
     * @return
     */
    public tresor getTresor() {
        return tresor;
    }
      
    //SETTERS
    
    private void setNom(TuilesUtils nom) {
        this.nom = nom;
    }

    private void setEtat(EtatTuile etat) {
        this.etat = etat;
    }
    
    /**
     *
     * @param Coords
     */
    public void setCoords(int[] Coords) {
        this.coords = Coords;
    }

    /**
     * Met à jour le tresor
     * @param tresor
     */
    public void setTresor(tresor tresor) {
        this.tresor = tresor;
    }
    
    //methodes
    
    /**
     *
     * @return vrai si la tuile est mouillee (pas assechée)
     */
        
    public boolean estMouillee(){
        return this.etat != EtatTuile.ASSECHEE;
    }
    
    /**
     *
     * @return vrai si la tuile est disponible (pas coulée)
     */
    public boolean estDisponible(){
        return this.etat != EtatTuile.COULEE;
    }
    
    /**
     * Innonde la tuile
     */
    public void Innonder(){
        
         switch (etat) {
            case ASSECHEE:
                etat = EtatTuile.INONDEE;
                break;
            case INONDEE:
                etat = EtatTuile.COULEE;
                break;
            default:
                break;
        }
    }
    
    /**
     * Asseche la tuile
     */
    public void Assecher(){
         if (etat == EtatTuile.INONDEE){
                etat = EtatTuile.ASSECHEE;
        }
    }
    
    /**
     *
     * @return Vrai si la tuile est innondée
     */
    public boolean isInnondee(){
        return this.etat == EtatTuile.INONDEE;
    }
    @Override
    public String toString(){
        return getNom().getNom();
    }
    
}
