package Modele;

import Controleur.Utils.tresor;

public class CarteTresor extends CarteJoueur {

    private tresor tresor;

    public CarteTresor(tresor tresor) {
        this.tresor = tresor;
    }
    
    
    
    public tresor getTresor() {
        return this.tresor;    
    }

}