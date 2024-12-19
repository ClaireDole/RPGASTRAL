package fr.rpgastral.model.entity;

import com.badlogic.gdx.utils.TimeUtils;

import fr.rpgastral.controler.RpgMain;

public abstract class Enemy extends Entity{
    /**
     * puissance de l'ennemi en terme de dégâts
     */
	private float damage;
    private String name;
    /**
     * portée de l'ennemi
     */
    private int portee;
    /**
     * position de l'ennemi dans son cycle de mouvement
     */
    private int position;
    /**
	 * heure à la nanoseconde près lors du lancement du cooldown
	 */
	private long cooldownstart;

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
    
    /**
     * lancement du cooldown
     */
    public void cooldownstart() {
    	this.cooldownstart = TimeUtils.nanoTime();
    }
    
    /**
     * on regarde si le cooldown est terminée
     * @return l'état du cooldown false: en cours et true:finit
     */
    public boolean cooldownover() {
    	return (TimeUtils.nanoTime() - this.cooldownstart) >= 2 * 1_000_000_000L;
    }
    
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
