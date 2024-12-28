package fr.rpg.model.carte;


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
	/**
	 * abscisse d'arrivée
	 */
	private int destX;
	/**
	 * ordonnée d'arrivée
	 */
	private int destY;
	
	public Teleport(int X, int Y, String name) {
		this.X = X;
		this.Y = Y;
		this.name = name;
	}
	public Teleport(int X, int Y, String name, int dX, int dY) {
		this(X,Y,name);
		this.destX = dX;
		this.destY = dY;
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
	/**
	 * @return the destX
	 */
	public int getDestX() {
		return destX;
	}
	/**
	 * @param destX the destX to set
	 */
	public void setDestX(int destX) {
		this.destX = destX;
	}
	/**
	 * @return the destY
	 */
	public int getDestY() {
		return destY;
	}
	/**
	 * @param destY the destY to set
	 */
	public void setDestY(int destY) {
		this.destY = destY;
	}
}
