/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author beaufima
 */
public enum TuileUtils {
    pont_des_abimes("Le Pont des Abimes","/Images/TuilesAssechees/LePontDesAbimes.png","/Images/TuilesInondees/LePontDesAbimes_Inonde.png"),
    heliport("Héliport","/Images/TuilesAssechees/Heliport.png","/Images/TuilesInondees/Heliport_Inonde.png"),
    porte_de_bronze ("La Porte de Bronze","/Images/TuilesAssechees/LaPorteDeBronze.png","/Images/TuilesInondees/LaPorteDeBronze_Inonde.png"),
    carverne_des_ombres ("La Carverne des Ombres","/Images/TuilesAssechees/LaCarverneDesOmbres.png","/Images/TuilesInondees/LaCarverneDesOmbres_Inonde.png"),
    porte_de_fer ("La Porte de Fer","/Images/TuilesAssechees/LaPorteDeFer.png","/Images/TuilesInondees/LaPorteDeFer_Inonde.png"),
    porte_dor ("La Porte d’Or","/Images/TuilesAssechees/LaPortedOr.png","/Images/TuilesInondees/LaPortedOr_Inondee.png"),
    falaises_de_loubli ("Les Falaises de l’Oubli","/Images/TuilesAssechees/LesFalaisesDeLOubli.png","/Images/TuilesInondees/LesFalaisesDeLOubli_Inonde.png"),
    palais_de_corail ("Le Palais de Corail","/Images/TuilesAssechees/LePalaisDeCorail.png","/Images/TuilesInondees/LePalaisDeCorail_Inonde.png"),
    porte_dargent ("La Porte d’Argent","/Images/TuilesAssechees/LaPortedArgent.png","/Images/TuilesInondees/LaPortedArgent_Inonde.png"),
    dunes_de_lillusion ("Les Dunes de l’Illusion","/Images/TuilesAssechees/LesDunesDelIllusion.png","/Images/TuilesInondees/LesDunesDelIllusion_Inonde.png"),
    porte_de_cuivre ("La Porte de Cuivre","/Images/TuilesAssechees/LaPorteDeCuivre.png","/Images/TuilesInondees/LaPorteDeCuivre_Inonde.png"),
    jardin_des_hurlements ("Le Jardin des Hurlements","/Images/TuilesAssechees/LeJardinDesHurlements.png","/Images/TuilesInondees/LeJardinDesHurlements_Inonde.png"),
    foret_pourpre ("La Foret Pourpre","/Images/TuilesAssechees/LaForetPourpre.png","/Images/TuilesInondees/LaForetPourpre_Inonde.png"),
    lagon_perdu ("Le Lagon Perdu","/Images/TuilesAssechees/LeLagonPerdu.png","/Images/TuilesInondees/LeLagonPerdu_Inonde.png"),
    marais_brumeux ("Le Marais Brumeux","/Images/TuilesAssechees.LeMaraisBrumeux.png","/Images/TuilesInondees.LeMaraisBrumeux_Inonde.png"),
    observatoire ("Observatoire","/Images/TuilesAssechees/Observatoire.png","/Images/TuilesInondees/Observatoire_Inonde.png"),
    rocher_fantome ("Le Rocher Fantôme","/Images/TuilesAssechees/LeRocherFantome.png","/Images/TuilesInondees/LeRocherFantome_Inonde.png"),
    carverne_du_brasier ("La Carverne du Brasier","/Images/TuilesAssechees/LaCarverneDuBrasier.png","/Images/TuilesInondees/LaCarverneDuBrasier_Inonde.png"),
    temple_du_soleil ("Le Temple du Soleil","/Images/TuilesAssechees/LeTempleDuSoleil.png","/Images/TuilesInondees/LeTempleDuSoleil_Inonde.png"),
    temple_de_la_lune ("Le Temple de La Lune","/Images/TuilesAssechees/LeTempleDeLaLune.png","/Images/TuilesInondees/LeTempleDeLaLune_Inonde.png"),
    palais_des_marees ("Le Palais des Marees","/Images/TuilesAssechees/LePalaisDesMarees.png","Images/TuilesInondees/LePalaisDesMarees_Inonde.png"),
    val_du_crepuscule ("Le Val du Crepuscule","/Images/TuilesAssechees/LeValDuCrepuscule.png","Images/TuilesInondees/LeValDuCrepuscule_Inonde.png"),
    tour_du_guet ("La Tour du Guet","/Images/TuilesAssechees/LaTourDuGuet.png","Images/TuilesInondees/LaTourDuGuet_Inonde.png"),
    jardin_des_murmures ("Le Jardin des Murmures","/Images/TuilesAssechees/LeJardinDesMurmures.png","Images/TuilesInondees/LeJardinDesMurmures_Inonde.png");

    private String nom;
    private String assecher;
    private String innondee;
    //Constructeur
    TuileUtils(String nom,String assecher, String innondee){
        this.nom = nom;
        this.assecher = assecher;
        this.innondee = innondee;
    }
    
    public ImageIcon getImageAssechee(){
        return new ImageIcon(getClass().getResource(TuileUtils.this.getAssecher()));
    }
    
     public ImageIcon getImageInondee(){
        return new ImageIcon(getClass().getResource(TuileUtils.this.getInnonder()));
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
}