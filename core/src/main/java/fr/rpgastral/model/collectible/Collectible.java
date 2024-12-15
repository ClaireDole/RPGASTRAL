package fr.rpgastral.model.collectible;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.entity.Player;

//passer sur du polymorphisme plutôt que des else if pour les potions
public abstract class Collectible{
 private int x;
 private int y;
 private String name;
 private float damage;
 private Sprite sprite;
 private AtlasRegion texture;
 private RpgMain g;
 private String description;
 
 public Collectible(int x, int y, float damage, String name, RpgMain g) {
	 this.x=x;
	 this.y=y;
	 this.damage=damage;
	 this.name=name;
	 this.setG(g);
 }
 public Collectible(int x, int y, String name, RpgMain g) {
	 this.x=x;
	 this.y=y;
	 this.name=name;
	 this.setG(g);
 }
 public Collectible(Player p, String s) {
	 this.x = p.Getx();
	 this.y = p.Gety();
	 this.name = s;
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
public void dispawn() {
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

