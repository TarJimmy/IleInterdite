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
        ROUGE("Rouge", new Color(255, 0, 0), new Color(176, 79, 79), new Color(255, 145, 145), new Color(226,166,166)),
        VERT("Vert", new Color(0, 230, 0), new Color(79, 153, 79), new Color(145, 255, 145), new Color(166,226,166)),
        BLEU("Bleu", new Color(55,194,198), new Color(100,153,154), new Color(175,221,221), new Color(202,219,219)),
        ORANGE("Orange", new Color(255, 148, 0), new Color(176, 135, 79), new Color(255, 199, 127), new Color(246,198,135)),
        VIOLET("Violet", new Color(204, 94, 255), new Color(146, 115, 176), new Color(211, 164, 234), new Color(202,176,214)),
        JAUNE("Jaune", new Color(255, 255, 0), new Color(176, 176, 79), new Color(255, 255, 140), new Color(245,245,148)) ;    

        private final String libelle ;
        private final Color couleur ;
        private final Color couleurGrisee ;
        private final Color couleurSelectionTuileAssechee ;
        private final Color couleurSelectionTuileInondee ;

        Pion (String libelle, Color couleur, Color couleurGrisee, Color couleurSelectionTuileAssechee, Color couleurSelectionTuileInondee) {
            this.libelle = libelle ;
            this.couleur = couleur ;
            this.couleurGrisee = couleurGrisee ;
            this.couleurSelectionTuileAssechee = couleurSelectionTuileAssechee ;
            this.couleurSelectionTuileInondee = couleurSelectionTuileInondee ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }

        public Color getCouleurGrisee() {
            return this.couleurGrisee ;
        }

        public Color getCouleurSelectionAssechee() {
            return this.couleurSelectionTuileAssechee ;
        }

        public Color getCouleurSelectionInondee() {
            return this.couleurSelectionTuileInondee ;
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

    public static ArrayList<Modele.Aventurier> melangerAventuriers(ArrayList<Modele.Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }
    public enum tresor {
        PIERRE_SACREE("Pierre Sacree","Tresor/pierre.png"),
        STATUE_ZEPHYR("Zéphyr","Tresor/zephyr.png"),
        CRISTAL_ARDENT("Cristal","Tresor/cristal.png"),
        CALICE_ONDE("Calice","Tresor/calice.png");
        
        private String nom;
        private String chemin;
        tresor(String nom,String chemin){
        this.nom = nom;
        this.chemin = chemin;
        }
    //getteurs et setteurs
    //
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getChemin() {
            return chemin;
        }

        public void setChemin(String chemin) {
            this.chemin = chemin;
        }
            @Override
        public String toString(){
            return getNom();
        }
    }
    public enum CarteUtils {
    calice("Calice","cartes/Calice.png"),
    caverneDuBrasier("Caverne Du Brasier","cartes/CaverneDuBrasier.png"),
    cristal("Cristal","cartes/Cristal.png"),
    heliport("Héliport","cartes/Heliport.png"),
    caverneDesOmbres("La Caverne Des Ombres","cartes/LaCaverneDesOmbres.png"),
    foretPoupre("La Forêt Poupre","cartes/LaForetPoupre.png"),
    portedOr("La Porte d'Or","cartes/LaPorteDOr.png"), 
    porteDeBronze("La Porte de Bronze","cartes/LaPorteDeBronze.png"),
    porteDeCuivre("La Porte de Cuivre","cartes/LaPortedeCuivre.png"),
    porteDeFer("La Porte De Fer","cartes/LaPorteDeFer.png"),
    portedArgent("La Porte d'Argent","cartes/LaPortedArgent.png"),
    tourDeGuet("La Tour de Guet","cartes/LaTourDeGuet.png"),
    jardinDesHurlements("Le jardin Des hurlements","cartes/LeJardinDesHurlements.png"),
    jardinDesMurmures("Le jardin des Murmures","cartes/LeJardinDesMurmures.png"),
    lagonPerdu("Le Lagon Perdu","cartes/LeLagonPerdu.png"),
    maraisBrumeux("le Marais brumeux","cartes/LeMaraisBrumeux.png"),
    palaisDeCorail("Le Palais De Corail","cartes/LePalaisDeCorail.png"),
    pontDesAbimes("Le Pont des Abimes","cartes/LePontdesAbimes.png"),
    rocherFantome("Le Rocher Fantome","cartes/LeRocherFantome.png"),
    templeDeLaLune("Le Temple de la Lune","cartes/LeTempleDeLaLune.png"),
    templeDusoleil("Le Temple du Soleil","cartes/LeTempleDuSoleil.png"),
    valDuCrepuscule("Le Val du Crépuscule","cartes/LeValDuCrepuscule.png"),
    dunesDelIllusion("Les Dunes de L'Illusion","cartes/LesDunesDeLIllusion.png"),
    falaisesDelOublie("Les Falaises de L'Oublie","cartes/LesFalaisesDeLOublie.png"),
    monteeDesEaux("Montée des eaux","cartes/MonteeDesEaux.png"),
    observatoire("Observatoire","cartes/Observatoire.png"),
    pierre("Pierre","cartes/Pierre.png"),
    zephyr("Zéphyr","cartes/Zephyr.png"),
    sacsDeSable("Sacs de Sable","cartes/SacsDeSable.png"),
    helicoptere("Helicoptere","cartes/Helicoptere.png"),
    deckInondation("Deck Inondation","cartes/FondBleu.png"),
    deckTresor("Decks Tresor","cartes/FondRouge.png");
    
    
    private String nom;
    private String chemin;
    
    CarteUtils(String nom,String chemin){
        this.nom = nom;
        this.chemin = chemin;
    }
    //getteurs et setteurs
    //
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
    //
    
    public ImageIcon getImage(){
        return new ImageIcon(getClass().getResource(CarteUtils.this.getChemin()));
    }
}
    public enum TuilesUtils {
    pont_des_abimes("Le Pont des Abimes","TuilesAssechees/LePontDesAbimes.png","TuilesInondees/LePontDesAbimes_Inonde.png"),
    heliport("Héliport","TuilesAssechees/Heliport.png","TuilesInondees/Heliport_Inonde.png"),
    porte_de_bronze ("La Porte de Bronze","TuilesAssechees/LaPorteDeBronze.png","TuilesInondees/LaPorteDeBronze_Inonde.png"),
    caverne_des_ombres ("La Carverne des Ombres","TuilesAssechees/LaCarverneDesOmbres.png","TuilesInondees/LaCarverneDesOmbres_Inonde.png"),
    porte_de_fer ("La Porte de Fer","TuilesAssechees/LaPorteDeFer.png","TuilesInondees/LaPorteDeFer_Inonde.png"),
    porte_dor ("La Porte d’Or","TuilesAssechees/LaPortedOr.png","TuilesInondees/LaPortedOr_Inonde.png"),
    falaises_de_loubli ("Les Falaises de l’Oubli","TuilesAssechees/LesFalaisesDeLOubli.png","TuilesInondees/LesFalaisesDeLOubli_Inonde.png"),
    palais_de_corail ("Le Palais de Corail","TuilesAssechees/LePalaisDeCorail.png","TuilesInondees/LePalaisDeCorail_Inonde.png"),
    porte_dargent ("La Porte d’Argent","TuilesAssechees/LaPortedArgent.png","TuilesInondees/LaPortedArgent_Inonde.png"),
    dunes_de_lillusion ("Les Dunes de l’Illusion","TuilesAssechees/LesDunesDeLIllusion.png","TuilesInondees/LesDunesDeLIllusion_Inonde.png"),
    porte_de_cuivre ("La Porte de Cuivre","TuilesAssechees/LaPorteDeCuivre.png","TuilesInondees/LaPorteDeCuivre_Inonde.png"),
    jardin_des_hurlements ("Le Jardin des Hurlements","TuilesAssechees/LeJardinDesHurlements.png","TuilesInondees/LeJardinDesHurlements_Inonde.png"),
    foret_pourpre ("La Foret Pourpre","TuilesAssechees/LaForetPourpre.png","TuilesInondees/LaForetPourpre_Inonde.png"),
    lagon_perdu ("Le Lagon Perdu","TuilesAssechees/LeLagonPerdu.png","TuilesInondees/LeLagonPerdu_Inonde.png"),
    marais_brumeux ("Le Marais Brumeux","TuilesAssechees/LeMaraisBrumeux.png","TuilesInondees/LeMaraisBrumeux_Inonde.png"),
    observatoire ("Observatoire","TuilesAssechees/Observatoire.png","TuilesInondees/Observatoire_Inonde.png"),
    rocher_fantome ("Le Rocher Fantôme","TuilesAssechees/LeRocherFantome.png","TuilesInondees/LeRocherFantome_Inonde.png"),
    caverne_du_brasier ("La Caverne du Brasier","TuilesAssechees/LaCarverneDuBrasier.png","TuilesInondees/LaCarverneDuBrasier_Inonde.png"),
    temple_du_soleil ("Le Temple du Soleil","TuilesAssechees/LeTempleDuSoleil.png","TuilesInondees/LeTempleDuSoleil_Inonde.png"),
    temple_de_la_lune ("Le Temple de La Lune","TuilesAssechees/LeTempleDeLaLune.png","TuilesInondees/LeTempleDeLaLune_Inonde.png"),
    palais_des_marees ("Le Palais des Marees","TuilesAssechees/LePalaisDesMarees.png","TuilesInondees/LePalaisDesMarees_Inonde.png"),
    val_du_crepuscule ("Le Val du Crepuscule","TuilesAssechees/LeValDuCrepuscule.png","TuilesInondees/LeValDuCrepuscule_Inonde.png"),
    tour_du_guet ("La Tour du Guet","TuilesAssechees/LaTourDuGuet.png","TuilesInondees/LaTourDuGuet_Inonde.png"),
    jardin_des_murmures ("Le Jardin des Murmures","TuilesAssechees/LeJardinDesMurmures.png","TuilesInondees/LeJardinDesMurmures_Inonde.png");

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
