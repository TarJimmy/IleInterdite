package Modele;

public class CarteInnondation {

    Tuile maTuile;

    CarteInnondation(Tuile maTuile) {
        this.maTuile = maTuile;
    }

    public Tuile getMaTuile() {
        return maTuile;
    }
    
    
    public void innondee(){
        getMaTuile().Innonder();
    }
    public boolean aGarder(){
        return !getMaTuile().isInnondee();
    }

}