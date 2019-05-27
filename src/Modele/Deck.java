package Modele;

public interface Deck {

	void Melanger();

	void Piocher();

	/**
	 * 
	 * @param carte
	 */
	void Defausser(CarteJoueur carte);

}