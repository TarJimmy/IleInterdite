/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.ImageIcon;

/**
 *
 * @author beaufima
 */
public enum CarteUtils {
    calice("Calice","cartes/Calice.png"),
    caverneDuBrasier("Caverne Du Brasier","cartes/CaverneDuBrasier.png"),
    cristal("Cristal","cartes/Cristal.png"),
    heliport("Héliport","cartes/Heliport.png"),
    caverneDesOmbres("La Caverne Des Ombres","cartes/LaCaverneDesOmbres.png"),
    foretPoupre("La Forêt Poupre","cartes/LaForetPoupre.png"),
    portedOr("La Porte d'Or","cartes/LaPorteDOr.png"), 
    porteDeBronze("La Porte de Bronze","cartes/LaPorteDeBronze.png"),
    porteDeCuivre("La Porte de Cuivre","cartes/LaPorteDeCuivre.png"),
    porteDeFer("La Porte De Fer","cartes/LaPorteDeFer.png"),
    portedArgent("La Porte d'Argent","cartes/LaPortedArgent.png"),
    tourDeGuet("La Tour de Guet","cartes/LaTourDeGuet.png"),
    jardinDesHurlements("Le jardin Des hurlements","cartes/LeJardinDesHurlements.png"),
    jardinDesMurmures("Le jardin des Murmures","cartes/LeJardinDesMurmures.png"),
    lagonPerdu("Le Lagon Perdu","cartes/LeLagonPerdu.png"),
    maraisBrumeux("le Marais brumeux","cartes/LeMaraisBrumeux.png"),
    palaisDeCorail("Le Palais De Corail","cartes/LePalaisDeCorail.png"),
    pontDesAbimes("Le Pont des Abimes","cartes/LePontDesAbimes.png"),
    rocherFantome("Le Rocher Fantome","cartes/LeRocherFantome.png"),
    templeDeLaLune("Le Temple de la Lune","cartes/LeTempleDeLaLune.png"),
    templeDusoleil("Le Temple du Soleil","cartes/LeTempleDuSoleil.png"),
    valDuCrepuscule("Le Val du Crépuscule","cartes/LeValDuCrecupuscule.png"),
    dunesDelIllusion("Les Dunes de L'Illusion","cartes/LesDunesDeLIllusion.png"),
    falaisesDelOublie("Les Falaises de L'Oublie","cartes/LesFalaisesDeLOublie.png"),
    monteeDesEaux("Montée des eaux","cartes/MonteeDesEaux.png"),
    observatoire("Observatoire","cartes/Observatoire.png"),
    pierre("Pierre","cartes/Pierre.png"),
    zephyr("Zéphyr","cartes/Zephyr.png"),
    sacsDeSable("Sacs de Sable","cartes/SacsDeSable.png");
    
    
    
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
