package fr.rpg.model.carte;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;

import fr.rpg.model.collectible.Armes;
import fr.rpg.model.collectible.Potion;
import fr.rpg.model.collectible.Tenue;
import fr.rpg.model.entity.Enemy;
import fr.rpg.model.entity.EnemyHuman;
import fr.rpg.model.entity.Monstre;
import fr.rpg.model.entity.PNJ;

/**
 * classe qui décrit le monde du jeu
 * elle résulte des informations de la carte tiled
 * on retrouve les dimensions, les terrains, les collectibles et les ennemis
 * sous cette forme, les informations sont utilisables dans le programme 
 */
public class TiledModel {

	private TiledMap map;
	private int width;
	private int height;
	private int tilewidth;
	private int tileheight;
	private ArrayList<Terrain> eau;
	private ArrayList<Terrain> volcaniques;
	private ArrayList<Terrain> obstacles;
	private ArrayList<Terrain> plaines;
	private ArrayList<PNJ> pnjs;
	private ArrayList<Potion> potions;
	private ArrayList<Tenue> tenues;
	private ArrayList<Armes> armes;
	private ArrayList<Enemy> enemys;
	private ArrayList<Monstre> monstres;
	private ArrayList<EnemyHuman> ehumans;
	private ArrayList<Teleport> teleports;

	/**
	 * @return the map
	 */
	public TiledMap getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(TiledMap map) {
		this.map = map;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the tilewidth
	 */
	public int getTilewidth() {
		return tilewidth;
	}
	/**
	 * @param tilewidth the tilewidth to set
	 */
	public void setTilewidth(int tilewidth) {
		this.tilewidth = tilewidth;
	}
	/**
	 * @return the tileheight
	 */
	public int getTileheight() {
		return tileheight;
	}
	/**
	 * @param tileheight the tileheight to set
	 */
	public void setTileheight(int tileheight) {
		this.tileheight = tileheight;
	}
	/**
	 * @return the eau
	 */
	public ArrayList<Terrain> getEau() {
		return eau;
	}
	/**
	 * @param eau the eau to set
	 */
	public void setEau(ArrayList<Terrain> eau) {
		this.eau = eau;
	}
	/**
	 * @return the volcaniques
	 */
	public ArrayList<Terrain> getVolcaniques() {
		return volcaniques;
	}
	/**
	 * @param volcaniques the volcaniques to set
	 */
	public void setVolcaniques(ArrayList<Terrain> volcaniques) {
		this.volcaniques = volcaniques;
	}
	/**
	 * @return the obstacles
	 */
	public ArrayList<Terrain> getObstacles() {
		return obstacles;
	}
	/**
	 * @param obstacles the obstacles to set
	 */
	public void setObstacles(ArrayList<Terrain> obstacles) {
		this.obstacles = obstacles;
	}
	/**
	 * @return the plaines
	 */
	public ArrayList<Terrain> getPlaines() {
		return plaines;
	}
	/**
	 * @param plaines the plaines to set
	 */
	public void setPlaines(ArrayList<Terrain> plaines) {
		this.plaines = plaines;
	}
	/**
	 * @return the pnjs
	 */
	public ArrayList<PNJ> getPnjs() {
		return pnjs;
	}
	/**
	 * @param pnjs the pnjs to set
	 */
	public void setPnjs(ArrayList<PNJ> pnjs) {
		this.pnjs = pnjs;
	}
	/**
	 * @return the potions
	 */
	public ArrayList<Potion> getPotions() {
		return potions;
	}
	/**
	 * @param potions the potions to set
	 */
	public void setPotions(ArrayList<Potion> potions) {
		this.potions = potions;
	}
	/**
	 * @return the tenues
	 */
	public ArrayList<Tenue> getTenues() {
		return tenues;
	}
	/**
	 * @param tenues the tenues to set
	 */
	public void setTenues(ArrayList<Tenue> tenues) {
		this.tenues = tenues;
	}
	/**
	 * @return the armes
	 */
	public ArrayList<Armes> getArmes() {
		return armes;
	}
	/**
	 * @param armes the armes to set
	 */
	public void setArmes(ArrayList<Armes> armes) {
		this.armes = armes;
	}
	/**
	 * @return the enemys
	 */
	public ArrayList<Enemy> getEnemys() {
		return enemys;
	}
	/**
	 * @param enemys the enemys to set
	 */
	public void setEnemys(ArrayList<Enemy> enemys) {
		this.enemys = enemys;
	}
	/**
	 * @return the monstres
	 */
	public ArrayList<Monstre> getMonstres() {
		return monstres;
	}
	/**
	 * @param monstres the monstres to set
	 */
	public void setMonstres(ArrayList<Monstre> monstres) {
		this.monstres = monstres;
	}
	/**
	 * @return the ehumans
	 */
	public ArrayList<EnemyHuman> getEhumans() {
		return ehumans;
	}
	/**
	 * @param ehumans the ehumans to set
	 */
	public void setEhumans(ArrayList<EnemyHuman> ehumans) {
		this.ehumans = ehumans;
	}
	/**
	 * @return the teleports
	 */
	public ArrayList<Teleport> getTeleports() {
		return teleports;
	}
	/**
	 * @param teleports the teleports to set
	 */
	public void setTeleports(ArrayList<Teleport> teleports) {
		this.teleports = teleports;
	}

}
