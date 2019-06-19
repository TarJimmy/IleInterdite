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
import javax.swing.ImageIcon;
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
    public enum TuilesUtils {
    pont_des_abimes("Le Pont des Abimes","TuilesAssechees/LePontDesAbimes.png","TuilesInondees/LePontDesAbimes_Inonde.png"),
    heliport("Héliport","TuilesAssechees/Heliport.png","TuilesInondees/Heliport_Inonde.png"),
    porte_de_bronze ("La Porte de Bronze","TuilesAssechees/LaPorteDeBronze.png","TuilesInondees/LaPorteDeBronze_Inonde.png"),
    carverne_des_ombres ("La Carverne des Ombres","TuilesAssechees/LaCarverneDesOmbres.png","TuilesInondees/LaCarverneDesOmbres_Inonde.png"),
    porte_de_fer ("La Porte de Fer","TuilesAssechees/LaPorteDeFer.png","TuilesInondees/LaPorteDeFer_Inonde.png"),
    porte_dor ("La Porte d’Or","TuilesAssechees/LaPortedOr.png","TuilesInondees/LaPortedOr_Inondee.png"),
    falaises_de_loubli ("Les Falaises de l’Oubli","TuilesAssechees/LesFalaisesDeLOubli.png","TuilesInondees/LesFalaisesDeLOubli_Inonde.png"),
    palais_de_corail ("Le Palais de Corail","TuilesAssechees/LePalaisDeCorail.png","TuilesInondees/LePalaisDeCorail_Inonde.png"),
    porte_dargent ("La Porte d’Argent","TuilesAssechees/LaPortedArgent.png","TuilesInondees/LaPortedArgent_Inonde.png"),
    dunes_de_lillusion ("Les Dunes de l’Illusion","TuilesAssechees/LesDunesDelIllusion.png","TuilesInondees/LesDunesDelIllusion_Inonde.png"),
    porte_de_cuivre ("La Porte de Cuivre","TuilesAssechees/LaPorteDeCuivre.png","TuilesInondees/LaPorteDeCuivre_Inonde.png"),
    jardin_des_hurlements ("Le Jardin des Hurlements","TuilesAssechees/LeJardinDesHurlements.png","TuilesInondees/LeJardinDesHurlements_Inonde.png"),
    foret_pourpre ("La Foret Pourpre","TuilesAssechees/LaForetPourpre.png","TuilesInondees/LaForetPourpre_Inonde.png"),
    lagon_perdu ("Le Lagon Perdu","TuilesAssechees/LeLagonPerdu.png","TuilesInondees/LeLagonPerdu_Inonde.png"),
    marais_brumeux ("Le Marais Brumeux","TuilesAssechees.LeMaraisBrumeux.png","TuilesInondees.LeMaraisBrumeux_Inonde.png"),
    observatoire ("Observatoire","TuilesAssechees/Observatoire.png","TuilesInondees/Observatoire_Inonde.png"),
    rocher_fantome ("Le Rocher Fantôme","TuilesAssechees/LeRocherFantome.png","TuilesInondees/LeRocherFantome_Inonde.png"),
    carverne_du_brasier ("La Carverne du Brasier","TuilesAssechees/LaCarverneDuBrasier.png","TuilesInondees/LaCarverneDuBrasier_Inonde.png"),
    temple_du_soleil ("Le Temple du Soleil","TuilesAssechees/LeTempleDuSoleil.png","TuilesInondees/LeTempleDuSoleil_Inonde.png"),
    temple_de_la_lune ("Le Temple de La Lune","TuilesAssechees/LeTempleDeLaLune.png","TuilesInondees/LeTempleDeLaLune_Inonde.png"),
    palais_des_marees ("Le Palais des Marees","TuilesAssechees/LePalaisDesMarees.png","Images/TuilesInondees/LePalaisDesMarees_Inonde.png"),
    val_du_crepuscule ("Le Val du Crepuscule","TuilesAssechees/LeValDuCrepuscule.png","Images/TuilesInondees/LeValDuCrepuscule_Inonde.png"),
    tour_du_guet ("La Tour du Guet","TuilesAssechees/LaTourDuGuet.png","Images/TuilesInondees/LaTourDuGuet_Inonde.png"),
    jardin_des_murmures ("Le Jardin des Murmures","TuilesAssechees/LeJardinDesMurmures.png","Images/TuilesInondees/LeJardinDesMurmures_Inonde.png");

    private String nom;
    private String assecher;
    private String innondee;
    //Constructeur
    TuilesUtils(String nom, String assecher, String innondee){
        this.nom = nom;
        this.assecher = assecher;
        this.innondee = innondee;
    }
    public String getNom() {
        return nom;
    }

    public String getAssecher() {
        return assecher;
    }

    public String getInnonder() {
        return innondee;
    }

        public tresor getTresor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
