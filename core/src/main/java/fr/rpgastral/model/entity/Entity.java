package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import fr.rpgastral.controler.RpgMain;

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
    
	public RpgMain getG() {
		return g;
	}
	public void setG(RpgMain g) {
		this.g = g;
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

	public float getPV() {
		return PV;
	}

	public void setPV(float pV) {
		PV = pV;
	}

	public AtlasRegion getTexture() {
		return texture;
	}

	public void setTexture(AtlasRegion texture) {
		this.texture = texture;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
