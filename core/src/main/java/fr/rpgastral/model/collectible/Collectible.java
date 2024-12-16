package fr.rpgastral.model.collectible;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import fr.rpgastral.controler.RpgMain;

/**
 * classe abstraite décrivant les éléments communs aux trois types de collectibles, les armes, les potions et les tenues
 */
public abstract class Collectible{
	/**
	 * coordonnées en abscisse
	 */
	private int x;
	/**
	 * coordonnées en ordonnée
	 */
	private int y;
	private String name;
	private float damage;
	private Sprite sprite;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private AtlasRegion texture;
	private RpgMain g;
	private String description;

	/**
	 * super constructeur pour potions
	 * @param x
	 * @param y
	 * @param damage
	 * @param name
	 * @param g
	 */
	public Collectible(int x, int y, float damage, String name, RpgMain g) {
		this.x=x;
		this.y=y;
		this.damage=damage;
		this.name=name;
		this.setG(g);
	}
	
	/**
	 * super constructeur pour les tenues et armes
	 * @param x
	 * @param y
	 * @param name
	 * @param g
	 */
	public Collectible(int x, int y, String name, RpgMain g) {
		this.x=x;
		this.y=y;
		this.name=name;
		this.setG(g);
	}

	/**
	 * suppression du collectible
	 * implémentation dans chaque classe concernée
	 */
	public void dispawn() {
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public AtlasRegion getTexture() {
		return texture;
	}

	public void setTexture(AtlasRegion texture) {
		this.texture = texture;
	}

	public RpgMain getG() {
		return g;
	}

	public void setG(RpgMain g) {
		this.g = g;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

