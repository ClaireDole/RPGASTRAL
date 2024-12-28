package fr.rpg.model.entity;

import com.badlogic.gdx.utils.TimeUtils;

import fr.rpg.controler.RpgMain;
import fr.rpg.model.carte.TiledModel;

/**
 * classe abstraite décrivant des fonctionnalités communes à tous les ennemis
 */
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

	/**
	 * constructeur
	 * @param x abscisse
	 * @param y ordonnée
	 * @param name nom
	 * @param g instance de la classe main
	 */
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
    public abstract void takedamage(float i, TiledModel tiledmodel);
    /**
     * gestion de la suppression
     */
    public abstract void dispawn(TiledModel tiledmodel);
    
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
	/**
	 * @return the damage
	 */
	public float getDamage() {
		return damage;
	}
	/**
	 * @param damage the damage to set
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the portee
	 */
	public int getPortee() {
		return portee;
	}
	/**
	 * @param portee the portee to set
	 */
	public void setPortee(int portee) {
		this.portee = portee;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return the cooldownstart
	 */
	public long getCooldownstart() {
		return cooldownstart;
	}
	/**
	 * @param cooldownstart the cooldownstart to set
	 */
	public void setCooldownstart(long cooldownstart) {
		this.cooldownstart = cooldownstart;
	}
}
