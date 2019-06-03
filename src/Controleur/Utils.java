/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Eric
 */
public class Utils {
 
    public static enum EtatTuile {
        ASSECHEE("Asséchée"), 
        INONDEE("Inondée"),
        COULEE("Coulée");

        String libelle ;
        
        EtatTuile(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
    }

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0)),
        VERT("Vert", new Color(0, 195, 0)),
        BLEU("Bleu", new Color(55,194,198)),
        ORANGE("Orange", new Color(255, 148, 0)),
        VIOLET("Violet", new Color(204, 94, 255)),
        JAUNE("Jaune", new Color(255, 255, 0)),  
        GRIS("Blanc", Color.LIGHT_GRAY),
        NOIR("Noir", Color.darkGray);
        private final String libelle ;
        private final Color couleur ;


        Pion (String libelle, Color couleur) {
            this.libelle = libelle ;
            this.couleur = couleur ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }

        static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) return ROUGE ;
            if (VERT.name().equals(name)) return VERT ;
            if (BLEU.name().equals(name)) return BLEU ;
            if (ORANGE.name().equals(name)) return ORANGE ;
            if (VIOLET.name().equals(name)) return VIOLET ;
            if (JAUNE.name().equals(name)) return JAUNE ;
            return null ;
        }
    }

    /*public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }*/
    public enum tresor {
        PIERRE_SACREE,
        STATUE_ZEPHYR,
        CRISTAL_ARDENT,
        CALICE_ONDE;
    }
    public enum nomTuile {
    pont_des_abimes ("Le Pont des Abimes"),
    porte_de_bronze ("La Porte de Bronze"),
    caverne_des_ombres ("La Caverne des Ombres"),
    porte_de_fer ("La Porte de Fer"),
    porte_dor ("La Porte d’Or"),
    falaises_de_loubli ("Les Falaises de l’Oubli"),
    palais_de_corail ("Le Palais de Corail"),
    porte_dargent ("La Porte d’Argent"),
    dunes_de_lillusion ("Les Dunes de l’Illusion"),
    heliport ("Héliport"),
    porte_de_cuivre ("La Porte de Cuivre"),
    jardin_des_hurlements ("Le Jardin des Hurlements"),
    foret_pourpre ("La Foret Pourpre"),
    lagon_perdu ("Le Lagon Perdu"),
    marais_brumeux ("Le Marais Brumeux"),
    observatoire ("Observatoire"),
    rocher_fantome ("Le Rocher Fantôme"),
    caverne_du_brasier ("La Caverne du Brasier"),
    temple_du_soleil ("Le Temple du Soleil"),
    temple_de_la_lune ("Le Temple de La Lune"),
    palais_des_marees ("Le Palais des Marees"),
    val_du_crepuscule ("Le Val du Crepuscule"),
    tour_du_guet ("La Tour du Guet"),
    jardin_des_murmures ("Le Jardin des Murmures");

        public String getNom() {
            return nom;
        }

    private String nom = "";
    
    //Constructeur
    nomTuile(String nom){
        this.nom = nom;
    }
    }
    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }    
    
    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher 
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
}
