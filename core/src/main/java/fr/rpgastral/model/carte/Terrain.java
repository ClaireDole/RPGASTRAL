package fr.rpgastral.model.carte;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Terrain{
    private int x;
    private int y;
    private Boolean franchissement;
    private String name;

    //si sur chemin alors player peut pas prendre de damage
    //plaine = terrain classique
    //roche volcanique = damage tous les x temps
    //obstacle = franchissement impossible
    //eau = cas à part
    
    public Terrain(int x, int y, String n) {
    	this.x = x;
    	this.y = y;
    	this.name = n;
    	if(this.name =="plaine" | this.name =="chemin" | this.name =="volcanique") {
    		this.franchissement = true;
    	}
    	else {
    		this.franchissement = false;
    	}
    }
    
    public int Getx() {
    	return this.x;
    }
    public int Gety() {
    	return this.y;
    }
    public Boolean Getfranchissement() {
    	return this.franchissement;
    }
    public String Getname() {
    	return this.name;
    }
}