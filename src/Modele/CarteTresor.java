package Modele;

import Controleur.Utils;
import Controleur.Utils.CarteUtils;
import Controleur.Utils.tresor.*;
import Controleur.Utils.tresor;

public class CarteTresor extends CarteJoueur {

    private tresor tresor;

    public CarteTresor(CarteUtils carte) {
        super(carte);
        switch(carte){
                case pierre:
                    tresor = tresor.PIERRE_SACREE;
                    break;
                case zephyr:
                    tresor = tresor.STATUE_ZEPHYR;
                    break;
                case cristal:
                    tresor = tresor.CRISTAL_ARDENT;
                    break;
                default :      //calice
                    tresor = tresor.CALICE_ONDE;
                    break;
            }
        
        this.tresor = tresor;
        
    }
    
    
    
    public tresor getTresor() {
        return this.tresor;    
    }

}