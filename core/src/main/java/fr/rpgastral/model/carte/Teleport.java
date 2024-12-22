package fr.rpgastral.model.carte;


/**
 * définit les points de téléportation
 * pour la gestion des relations entre différentes cartes tiled
 * pour retenir des coordonnées
 */
public class Teleport {

	/**
	 * abscisse
	 */
	private int X;
	/**
	 * ordonnée
	 */
	private int Y;
	/**
	 * nom de la carte sur laquelle on veut pouvoir aller
	 */
	private String name;
	
	public Teleport(int X, int Y, String name) {
		this.X = X;
		this.Y = Y;
		this.name = name;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
