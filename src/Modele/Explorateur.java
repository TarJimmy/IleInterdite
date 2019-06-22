package Modele;
import Controleur.Utils;
import java.util.ArrayList;
import java.util.Iterator;

public class Explorateur extends Aventurier {

    public Explorateur(Tuile maPos) {
        super(maPos);
    }
    public Explorateur(Grille grille){
        super(grille.getTuile(Utils.TuilesUtils.porte_de_cuivre));
        setPion(Utils.Pion.VERT);
    }
    
    
    @Override
    public ArrayList<int[]> getCoordsProche() {
        ArrayList<int[]> coords = new ArrayList<>();
        for (int x = -1;x <=1;x++){
            for (int y = -1;y <=1;y++){
                if(!(x == 0 && y == 0)){
                    coords.add(new int[] {x,y});
                }
            }
        }
        return coords;
    }

    @Override
    public String getNomAventurier() {
        return "Explorateur";
    }

    @Override
    public ArrayList<Tuile> getDeplacementNav(Grille grille) {
        ArrayList<Tuile> collecTuiles = grille.getVoisins(getMaPos(),super.getCoordsProche());
        Iterator it = collecTuiles.iterator();
        while(it.hasNext()){
            Tuile tui = (Tuile) it.next();
            if(!checkDeplacement(tui)) it.remove();
        }
        return collecTuiles;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+"Déplacez-vous et/ou asséchez diagonalement";
    }
    
}