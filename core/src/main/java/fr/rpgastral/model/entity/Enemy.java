package fr.rpgastral.model.entity;

public abstract class Enemy extends Entity{
    private float damage;

    public Enemy(int x, int y) {
		super(x, y);
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
        this.PV = this.PV - i;
    }
    public void dispawn(Enemy e) {
    	//l'enlever de la collection d'ennemis de la carte
    }
}
