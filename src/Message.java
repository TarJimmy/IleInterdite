/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Controleur.TypeMessage;

/**
 *
 * @author tardy
 */
public class Message {
    private TypeMessage type;
    public int nbJoueur;
    public int difficulte;
    public Message(TypeMessage type){
        setType(type);
    }

    private void setType(TypeMessage type) {
        this.type = type;
    }

}
