/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile_interdite;

/**
 *
 * @author beaufima
 */
public class Tuile {
    
    private nomTuile nom;
    private Utils.EtatTuile etat;
    private int[] coords;
    //private Tresor tresor;

    //CONSTRUCTEUR 
    //Il faut rajouter Tresor
    public Tuile(nomTuile nom, Utils.EtatTuile etat, int[] coords) {
        this.setNom(nom);
        this.setEtat(etat);
        this.setCoords(coords);
    }

    //GETTERS
    
    public nomTuile getNom() {
        return nom;
    }

    public Utils.EtatTuile getEtat() {
        return etat;
    }

    public int[] getCoords() {
        return coords;
    }

    /*public Tresor getTresor() {
     *   return tresor;
    }*/
      
    //SETTERS
    
    private void setNom(nomTuile nom) {
        this.nom = nom;
    }

    private void setEtat(Utils.EtatTuile etat) {
        this.etat = etat;
    }
    
    private void setCoords(int[] Coords) {
        this.coords = Coords;
    }

    /*public void setTresor(Tresor tresor) {
     *   this.tresor = tresor;
    }*/
    
    //methodes
    
    public boolean estDisponible(){
        return this.etat != Utils.EtatTuile.COULEE;
            
    }
    
    public void Inonder(){
        
        if(etat == Utils.EtatTuile.ASSECHEE){
            etat = Utils.EtatTuile.INONDEE;
        }
        else if(etat == Utils.EtatTuile.INONDEE){
            etat = Utils.EtatTuile.COULEE;
        }
        else {
            System.out.println("La tuile est coulée");
        }
    }
    
    public void Assecher(){
        if(etat == Utils.EtatTuile.INONDEE){
            etat = Utils.EtatTuile.ASSECHEE;
        }
        else if(etat == Utils.EtatTuile.ASSECHEE) {
            System.out.println("La tuile est déjà asséchée");
        }
        else {
            System.out.println("La tuile est coulée");
        }
    }
    
    public boolean isInonder(){
        return this.etat == Utils.EtatTuile.INONDEE;
    }
    
    
}
