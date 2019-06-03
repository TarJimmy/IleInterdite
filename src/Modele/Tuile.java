/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Utils.nomTuile;
import Controleur.Utils.EtatTuile;
import Controleur.Utils.tresor;

/**
 *
 * @author beaufima
 */
public class Tuile {
    
    private nomTuile nom;
    private EtatTuile etat;
    private int[] coords;
    private tresor tresor;

    //CONSTRUCTEUR 
    //Il faut rajouter Tresor
    public Tuile(nomTuile nom, int[] coords) {
        this.setNom(nom);
        this.setEtat(EtatTuile.ASSECHEE);
        this.setCoords(coords);
    }
    

    //GETTERS
    
    public nomTuile getNom() {
        return nom;
    }

    public EtatTuile getEtat() {
        return etat;
    }

    public int[] getCoords() {
        return coords;
    }

    public tresor getTresor() {
        return tresor;
    }
      
    //SETTERS
    
    private void setNom(nomTuile nom) {
        this.nom = nom;
    }

    private void setEtat(EtatTuile etat) {
        this.etat = etat;
    }
    
    public void setCoords(int[] Coords) {
        this.coords = Coords;
    }

    public void setTresor(tresor tresor) {
        this.tresor = tresor;
    }
    
    //methodes
    
    public boolean estDisponible(){
        return this.etat != EtatTuile.COULEE;
            
    }
    
    public void Inonder(){
        
         switch (etat) {
            case ASSECHEE:
                etat = EtatTuile.INONDEE;
                System.out.println("La tuile est devenue innondee");
                break;
            case INONDEE:
                etat = EtatTuile.COULEE;
                System.out.println("La tuile est devenue coulee");
                break;
            default:
                System.out.println("La tuile est déjà coulée");
                break;
        }
    }
    
    public void Assecher(){
         switch (etat) {
            case INONDEE:
                etat = EtatTuile.ASSECHEE;
                System.out.println("La tuile est devenue assechée");
                break;
            case ASSECHEE:
                System.out.println("La tuile est déjà asséchée");
                break;
            default:
                System.out.println("La tuile est coulée et ne peut être asseché");
                break;
        }
    }
    
    public boolean isInnondee(){
        return this.etat == EtatTuile.INONDEE;
    }
    @Override
    public String toString(){
        return getNom().getNom();
    }
    
}
