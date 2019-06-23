package Modele;

public class CarteInondation {

    private Tuile maTuile;

    CarteInondation(Tuile maTuile) {
        this.maTuile = maTuile;
    }

    public Tuile getMaTuile() {
        return maTuile;
    }
    
    public void innondee(){
        getMaTuile().Innonder();
    }
    public boolean aGarder(){
        return getMaTuile().estDisponible();
    }

}