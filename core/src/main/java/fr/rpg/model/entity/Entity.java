package fr.rpg.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import fr.rpg.controler.RpgMain;

/**
 * les entités du jeu
 */
public abstract class Entity {
	/**
	 * coordonnées en abscisse
	 */
	private int x;
	/**
	 * coordoonnées en ordonnée
	 */
	private int y;
	private float PV;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private AtlasRegion texture;
	private Sprite sprite;
	private RpgMain g;

	public Entity(int x, int y, RpgMain game) {
		this.x = x;
		this.y = y;
		this.g = game;
	}

	public Boolean isAlive(){
		return this.PV>0;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the pV
	 */
	public float getPV() {
		return PV;
	}

	/**
	 * @param pV the pV to set
	 */
	public void setPV(float pV) {
		PV = pV;
	}

	/**
	 * @return the texture
	 */
	public AtlasRegion getTexture() {
		return texture;
	}

	/**
	 * @param texture the texture to set
	 */
	public void setTexture(AtlasRegion texture) {
		this.texture = texture;
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the g
	 */
	public RpgMain getG() {
		return g;
	}

	/**
	 * @param g the g to set
	 */
	public void setG(RpgMain g) {
		this.g = g;
	}
}
