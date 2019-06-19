package Modele;

import Controleur.Utils;
import Controleur.Utils.tresor;
import Controleur.Utils.Pion;

public class Ingenieur extends Aventurier {

    private boolean aAssecher;


    public Ingenieur(Grille grille){
        super(grille.getTuile(Utils.nomTuile.porte_de_bronze));
        setPion(Pion.ROUGE);
    }
    public Ingenieur(Tuile maPos) {
        super(maPos);
    }

    @Override
    public void deplaceHorsTour(Tuile tuile) {
        super.deplaceHorsTour(tuile);
        setaAssecher(false);
    }
    
    
    

    public void setaAssecher(boolean aAssecher) {
        this.aAssecher = aAssecher;
    }
    
    public boolean aAssecher() {
        return aAssecher;
    }
    @Override
    public void assecher(Tuile Tuile) {
        super.assecher(Tuile);
        if(aAssecher()){
            setaAssecher(false);
            actionsRestantes++;
        }else{
            setaAssecher(true);
        }
        
    }

    @Override
    public boolean GagnerTresor(tresor tres) {
        setaAssecher(false);
        return super.GagnerTresor(tres);
    }
    
    @Override
    public void DonnerCarte(CarteJoueur carte, Aventurier Av) {
        super.DonnerCarte(carte, Av);
        setaAssecher(false);
    }

    @Override
    public void deplacer(Tuile tuile) {
        super.deplacer(tuile);
        setaAssecher(false);
    }
    
    @Override
    public void DebutTour() {
        super.DebutTour();
        setaAssecher(false);
    }

    @Override
    public String getNomAventurier() {
     return "Ingénieur";
    }

    @Override
    public String getDescription() {
        return "Asséchez 2 tuiles pour 1 action";
    }
    
}