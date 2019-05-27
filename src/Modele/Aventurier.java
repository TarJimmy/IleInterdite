package Modele;

import Vue.MesEnums.tresor;
import Vue.MesEnums.typeAction;
import java.util.*;

public abstract class Aventurier {

	private Tuile maPos;
	Collection<CarteJoueur> mesCartes;
	private ArrayList<tresor> mesTresors;
	private int actionsRestantes;

	/**
	 * 
	 * @param tuile
	 */
	public void deplacer(Tuile tuile) {
		// TODO - implement Aventurier.deplacer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Tuile
	 */
	public void assecher(Tuile Tuile) {
		// TODO - implement Aventurier.assecher
		throw new UnsupportedOperationException();
	}

	public void donnerCarte() {
		// TODO - implement Aventurier.donnerCarte
		throw new UnsupportedOperationException();
	}

	public void GagnerTresor() {
		// TODO - implement Aventurier.GagnerTresor
		throw new UnsupportedOperationException();
	}

	public ArrayList<typeAction> getActionsPossibles() {
		// TODO - implement Aventurier.getActionsPossibles
		throw new UnsupportedOperationException();
	}

	public ArrayList<CarteJoueur> getCartes() {
		// TODO - implement Aventurier.getCartes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param carte
	 * @param Av
	 */
	public void DonnerCarte(CarteJoueur carte, Aventurier Av) {
		// TODO - implement Aventurier.DonnerCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param carte
	 */
	public void SupprimerCarte(CarteJoueur carte) {
		// TODO - implement Aventurier.SupprimerCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param carte
	 */
	public void AddCarte(CarteJoueur carte) {
		// TODO - implement Aventurier.AddCarte
		throw new UnsupportedOperationException();
	}

	public Tuile getTuile() {
		// TODO - implement Aventurier.getTuile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tres
	 */
	public void addTresor(tresor tres) {
		// TODO - implement Aventurier.addTresor
		throw new UnsupportedOperationException();
	}

	public int nbCarte() {
		// TODO - implement Aventurier.nbCarte
		throw new UnsupportedOperationException();
	}

	public boolean checkDeplacement() {
		// TODO - implement Aventurier.checkDeplacement
		throw new UnsupportedOperationException();
	}

	public ArrayList<Tuile> getDeplacement() {
		// TODO - implement Aventurier.getDeplacement
		throw new UnsupportedOperationException();
	}

}