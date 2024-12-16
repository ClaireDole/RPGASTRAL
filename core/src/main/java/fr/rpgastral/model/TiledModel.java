package fr.rpgastral.model;

import java.util.ArrayList;
import fr.rpgastral.model.carte.Terrain;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Potion;
import fr.rpgastral.model.collectible.Tenue;
import fr.rpgastral.model.entity.PNJ;

/**
 * classe qui décrit le monde du jeu
 * elle résulte des informations de la carte tiled
 * on retrouve les dimensions, les terrains, les collectibles et les ennemis
 * sous cette forme, les informations sont utilisables dans le programme 
 */
public class TiledModel {
	
	private int width;
	private int height;
	private int tilewidth;
	private int tileheight;
	private ArrayList<Terrain> eau;
	private ArrayList<Terrain> volcaniques;
	private ArrayList<Terrain> obstacles;
	private ArrayList<Terrain> chemins;
	private ArrayList<Terrain> plaines;
	private ArrayList<PNJ> pnjs;
	private ArrayList<Potion> potions;
	private ArrayList<Tenue> tenues;
	private ArrayList<Armes> armes;
	
	public ArrayList<Terrain> getEau() {
		return eau;
	}
	public void setEau(ArrayList<Terrain> eau) {
		this.eau = eau;
	}
	public ArrayList<Terrain> getVolcanique() {
		return volcaniques;
	}
	public void setVolcanique(ArrayList<Terrain> volcanique) {
		this.volcaniques = volcanique;
	}
	public ArrayList<Terrain> getObstacles() {
		return obstacles;
	}
	public void setObstacles(ArrayList<Terrain> obstacles) {
		this.obstacles = obstacles;
	}
	public ArrayList<Terrain> getChemin() {
		return chemins;
	}
	public void setChemin(ArrayList<Terrain> chemin) {
		this.chemins = chemin;
	}
	public ArrayList<Terrain> getPlaine() {
		return plaines;
	}
	public void setPlaines(ArrayList<Terrain> plaine) {
		this.plaines = plaine;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getTilewidth() {
		return tilewidth;
	}
	public void setTilewidth(int tilewidth) {
		this.tilewidth = tilewidth;
	}
	public int getTileheight() {
		return tileheight;
	}
	public void setTileheight(int tileheight) {
		this.tileheight = tileheight;
	}
	
	public ArrayList<PNJ> getPnjs() {
		return pnjs;
	}
	public void setPnjs(ArrayList<PNJ> pnjs) {
		this.pnjs = pnjs;
	}
	public ArrayList<Potion> getPotions() {
		return potions;
	}
	public void setPotions(ArrayList<Potion> potions) {
		this.potions = potions;
	}
	public ArrayList<Tenue> getTenues() {
		return tenues;
	}
	public void setTenues(ArrayList<Tenue> tenues) {
		this.tenues = tenues;
	}
	public ArrayList<Armes> getArmes() {
		return armes;
	}
	public void setArmes(ArrayList<Armes> armes) {
		this.armes = armes;
	}
	
	
}
