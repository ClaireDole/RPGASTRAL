package fr.rpg.model.collectible;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import fr.rpg.controler.RpgMain;
import fr.rpg.model.carte.TiledModel;

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
	 * super constructeur pour les tenues et armes
	 * @param x abscisse
	 * @param y ordonnée
	 * @param name nom
	 * @param g instance de la classe main
	 */
	public Collectible(int x, int y, String name, RpgMain g) {
		this.x=x;
		this.y=y;
		this.name=name;
		this.g = g;;
	}

	/**
	 * super constructeur pour potions
	 * @param x abscisse
	 * @param y ordonnée
	 * @param damage dégât
	 * @param name nom
	 * @param g instance de la classe main
	 */
	public Collectible(int x, int y, float damage, String name, RpgMain g) {
		this(x,y,name,g);
		this.damage=damage;
	}


	/**
	 * suppression du collectible
	 * implémentation dans chaque classe concernée
	 */
	public abstract void dispawn(TiledModel tiledmodel);

	/**
	 * getter
	 * @return abscisse
	 */
	public int getX() {
		return x;
	}

	/**
	 * setter
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * getter
	 * @return ordonnée
	 */
	public int getY() {
		return y;
	}

	/**
	 * setter
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * getter
	 * @return nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter
	 * @return dégâts du collectible
	 */
	public float getDamage() {
		return damage;
	}

	/**
	 * setter
	 * @param damage
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}

	/**
	 * getter
	 * @return sprite du collectible
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * setter
	 * @param sprite
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * getter
	 * @return la region à dessiner correspondant au collectible
	 */
	public AtlasRegion getTexture() {
		return texture;
	}

	/**
	 * setter
	 * @param texture
	 */
	public void setTexture(AtlasRegion texture) {
		this.texture = texture;
	}

	/**
	 * getter
	 * @return instance de la classe main
	 */
	public RpgMain getG() {
		return g;
	}

	/**
	 * getter
	 * @return description du collectible
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}

