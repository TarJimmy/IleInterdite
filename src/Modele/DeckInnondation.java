package Modele;

import java.util.*;

public abstract class DeckInnondation implements Deck {

    ArrayList<CarteInnondation> pioche,defausse;

    @Override
    public void initDeck() {
        
    }

    @Override
    public void Melanger() {
        Collections.shuffle(getDefausse());
    }
        
    public ArrayList<CarteInnondation> getPioche() {
        return pioche;
    }

    public ArrayList<CarteInnondation> getDefausse() {
        return defausse;
    }

    public void Innondation(int niveauDeau){
        int nbCartes = ( niveauDeau + ((niveauDeau <=3)?1:0) )/2+1;
        for (int i = 0; i < nbCartes; i++) {
            Piocher();
        }
        
    }
    private void Piocher() {
        if(getPioche().isEmpty()) {
            ResetPioche();
        }
        CarteInnondation c = getPioche().get(getPioche().size()-1);
        getPioche().remove(c);
        c.innondee();
        if(c.aGarder()){
            Defausser(c);
        }
    }

    public void Defausser(CarteInnondation carte) {
        getDefausse().add(carte);
    }
    

    @Override
    public void ResetPioche() {
        Melanger();
        getPioche().addAll(getDefausse());
        getDefausse().clear();
    }
  
        
        
        
        
        
        
        
        
        
}