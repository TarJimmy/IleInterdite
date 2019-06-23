package Modele;

import java.util.*;

public class DeckTresor {

    private ArrayList<CarteJoueur> pioche,defausse;

    public DeckTresor() {
        initDeck();
    }
    
    
    public void initDeck(){
        pioche = new ArrayList<>();
        defausse = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            getPioche().add(new CarteTresor(Controleur.Utils.CarteUtils.calice));
            getPioche().add(new CarteTresor(Controleur.Utils.CarteUtils.cristal));
            getPioche().add(new CarteTresor(Controleur.Utils.CarteUtils.pierre));
            getPioche().add(new CarteTresor(Controleur.Utils.CarteUtils.zephyr));
        }
        for (int i = 0; i < 3; i++) {
            getPioche().add(new CarteMonteeEau());
            getPioche().add(new Helicoptere());
        }
        for (int i = 0; i < 2; i++) {
            getPioche().add(new SacDeSable());
        }
        Melanger();
    }
    
    
    
    public void Melanger() {
        Collections.shuffle(getPioche());
    }

    public ArrayList<CarteJoueur> getPioche() {
        return pioche;
    }

    public ArrayList<CarteJoueur> getDefausse() {
        return defausse;
    }

    
    public CarteJoueur Piocher() {
        if(getPioche().isEmpty()) {
            ResetPioche();
        }
        CarteJoueur c = getPioche().get(getPioche().size()-1);
        getPioche().remove(c);
        return c;
    }

    public void Defausser(CarteJoueur carte) {
        getDefausse().add(carte);
    }

   
    public void ResetPioche() {
        getPioche().addAll(getDefausse());
        getDefausse().clear();
        Melanger();
    }

}