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
            getDefausse().add(new CarteTresor(Controleur.Utils.tresor.CALICE_ONDE));
            getDefausse().add(new CarteTresor(Controleur.Utils.tresor.CRISTAL_ARDENT));
            getDefausse().add(new CarteTresor(Controleur.Utils.tresor.PIERRE_SACREE));
            getDefausse().add(new CarteTresor(Controleur.Utils.tresor.STATUE_ZEPHYR));
        }
        for (int i = 0; i < 3; i++) {
            getDefausse().add(new CarteMonteeEau());
            getDefausse().add(new Helicoptere());
        }
        for (int i = 0; i < 2; i++) {
            getDefausse().add(new SacDeSable());
        }
        ResetPioche();
    }
    
    
    @Override
    public void Melanger() {
        Collections.shuffle(getDefausse());
    }

    public ArrayList<CarteJoueur> getPioche() {
        return pioche;
    }

    public ArrayList<CarteJoueur> getDefausse() {
        return defausse;
    }

    
    private CarteJoueur Piocher() {
        if(getPioche().isEmpty()) {
            ResetPioche();
        }
        CarteJoueur c = getPioche().get(getPioche().size()-1);
        getPioche().remove(c);
        return c;
    }

    private void Defausser(CarteJoueur carte) {
        getDefausse().add(carte);
    }

    @Override
    public void ResetPioche() {
        Melanger();
        getPioche().addAll(getDefausse());
        getDefausse().clear();
    }

}