package Modele;

public abstract class CarteInnondation {

    Tuile maTuile;

    public CarteInnondation(Tuile maTuile) {
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