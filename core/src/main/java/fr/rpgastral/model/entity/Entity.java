package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import fr.rpgastral.controler.RpgMain;

public abstract class Entity {
    protected int x;
    protected int y;
    protected float PV;
    protected AtlasRegion texture;
    private RpgMain g;
    
    public Entity(int x, int y, RpgMain game) {
    	this.x = x;
    	this.y = y;
    	this.g = game;
    }
    public int Getx() {
    	return this.x;
    }
    public int Gety() {
    	return this.y;
    }
    public float GetPV() {
    	return this.PV;
    }
    public void Setx(int x) {
    	this.x = x;
    }
    public void Sety (int y) {
    	this.y = y;
    }
    public Boolean isAlive(){
        if (PV> 0){
            return true;
        } 
        else return false;
    }
	public RpgMain getG() {
		return g;
	}
	public void setG(RpgMain g) {
		this.g = g;
	}
}
