/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Controleur.Message;
import Controleur.TypeMessage;
import Vue.MesEnums;

/**
 *
 * @author tardy
 */
public class MessageAction extends Message {
    private TypeAction typeact;
    MessageAction(TypeAction act){
        super(TypeMessage.ACTION);
        setTypeact(act);
    }

    public void setTypeact(TypeAction typeact) {
        this.typeact = typeact;
    }
}
