package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;

public abstract class Enemy extends Entity{
    private float damage;
    private String name;
    private int portee;
    private int position;

    public Enemy(int x, int y,String name, RpgMain g) {
		super(x, y,g);
		this.setName(name);
	}
    /**
     * gestion de l'attaque
     * @param p le joueur 
     */
    public abstract void attaque(Player p);
    /**
     * gestion des dégâts reçus
     * @param i nombre de dégâts
     */
    public abstract void takedamage(float i);
    /**
     * gestion de la suppression
     */
    public abstract void dispawn();
    
    public float Getdamage() {
    	return this.damage;
    }
    public void Setdamage(float i) {
    	this.damage = i;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPortee() {
		return portee;
	}
	public void setPortee(int portee) {
		this.portee = portee;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
