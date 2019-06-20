package Modele;

import java.util.*;

public class DeckTresor implements Deck {

    private ArrayList<CarteJoueur> pioche,defausse;

    public DeckTresor() {
        initDeck();
    }
    
    @Override
    public void initDeck(){
        pioche = new ArrayList<>();
        defausse = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            getPioche().add(new CarteTresor(Controleur.Utils.tresor.CALICE_ONDE));
            getPioche().add(new CarteTresor(Controleur.Utils.tresor.CRISTAL_ARDENT));
            getPioche().add(new CarteTresor(Controleur.Utils.tresor.PIERRE_SACREE));
            getPioche().add(new CarteTresor(Controleur.Utils.tresor.STATUE_ZEPHYR));
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
    
    
    @Override
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

    @Override
    public void ResetPioche() {
        getPioche().addAll(getDefausse());
        getDefausse().clear();
        Melanger();
    }

}