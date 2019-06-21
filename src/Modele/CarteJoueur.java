/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Controleur.Utils.CarteUtils;

/**
 *
 * @author tardy
 */
public abstract class CarteJoueur {
    public final CarteUtils carte;

    public CarteUtils getCarte() {
        return carte;
    }
    CarteJoueur(CarteUtils carte){
        this.carte = carte;
    }
    
    
}
