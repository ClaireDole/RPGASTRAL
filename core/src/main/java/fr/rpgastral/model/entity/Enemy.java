package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;

public abstract class Enemy extends Entity{
    private float damage;

    public Enemy(int x, int y, RpgMain g) {
		super(x, y,g);
	}
    public float Getdamage() {
    	return this.damage;
    }
    public void Setdamage(float i) {
    	this.damage = i;
    }
    public void attaque(Player p){ 
        p.takedamage(this.damage);
    }
    public void takedamage(float i){
        setPV(this.getPV() - i);
    }
    public void dispawn(Enemy e) {
    	//l'enlever de la collection d'ennemis de la carte
    }
}
