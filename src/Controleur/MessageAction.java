/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Controleur.Message;
import Controleur.TypeMessage;
import Modele.Tuile;

/**
 *
 * @author tardy
 */
public class MessageAction extends Message {
    public TypeAction typeact;
    public Tuile tui;
    public int[] coord;
    public MessageAction(){
        super.type = TypeMessage.ACTION;
    }

}
