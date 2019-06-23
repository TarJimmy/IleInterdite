package Modele;

import Controleur.Parameters;
import java.util.*;

public class DeckInondation {

    ArrayList<CarteInondation> pioche,defausse;

    public DeckInondation(Grille grille) {
        pioche = new ArrayList<>();
        defausse = new ArrayList<>();
        Tuile[][] tab = grille.getMesTuiles();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if(tab[i][j] != null){
                    Defausser(new CarteInondation(tab[i][j]));
                }
            }
        }
        ResetPioche();
    }

    
    
    
    
    public void initDeck() {
        
    }

    
    public void Melanger() {
        Collections.shuffle(getDefausse());
    }
        
    public ArrayList<CarteInondation> getPioche() {
        return pioche;
    }

    public ArrayList<CarteInondation> getDefausse() {
        return defausse;
    }

    public ArrayList<Tuile> Inondation(int niveauDeau){
        ArrayList<Tuile> tuiles = new ArrayList<>();
        int nbCartes = ( niveauDeau + ((niveauDeau <=3)?1:0) )/2+1;
        for (int i = 0; i < nbCartes; i++) {
            Tuile tui = Piocher();
            tuiles.add(tui);
        }
        return tuiles;
    }
    public ArrayList<Tuile> InondationInitial(){
        ArrayList<Tuile> tuiles = new ArrayList<>();
        int nbCartes = Parameters.NB_INONDATIONS_INITIALES;
        for (int i = 0; i < nbCartes; i++) {
            Tuile tui = Piocher();
            tuiles.add(tui);
        }
        return tuiles;
    }
    private Tuile Piocher() {
        if(getPioche().isEmpty()) {
            ResetPioche();
        }
        CarteInondation c = getPioche().get(getPioche().size()-1);
        getPioche().remove(c);
        c.innondee();
        if(c.aGarder()){
            Defausser(c);
        }
        return c.getMaTuile();
    }

    public void Defausser(CarteInondation carte) {
        getDefausse().add(carte);
    }
    

    
    public void ResetPioche() {
        Melanger();
        getPioche().addAll(getDefausse());
        getDefausse().clear();
    }
  
        
        
        
        
        
        
        
        
        
}