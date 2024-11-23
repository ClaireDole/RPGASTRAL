package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {
    protected int x;
    protected int y;
    protected float PV;
    protected Texture texture;
    
    public Entity(int x, int y) {
    	this.x = x;
    	this.y = y;
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
}
