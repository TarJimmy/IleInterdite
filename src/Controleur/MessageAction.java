/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Tuile;
import Vue.VueAventurier;
import Vue.VueCarte;
import java.util.ArrayList;

/**
 *
 * @author tardy
 */
public class MessageAction extends Message {
    public TypeAction typeact;
    public Tuile tui;
    public int[] coord;
    public ArrayList<VueAventurier> vueAv;
    public VueCarte vueCarte;
    public MessageAction(){
        super.type = TypeMessage.ACTION;
        vueAv = new ArrayList<>();
    }

}
