package fr.rpgastral.controler.tiled;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.TiledModel;
import fr.rpgastral.model.carte.Teleport;
import fr.rpgastral.model.carte.Terrain;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Potion;
import fr.rpgastral.model.collectible.Tenue;
import fr.rpgastral.model.entity.Enemy;
import fr.rpgastral.model.entity.EnemyHuman;
import fr.rpgastral.model.entity.Monstre;
import fr.rpgastral.model.entity.PNJ;

/**
 * classe de gestion des informations de la carte tiled
 * effectue la transformation des infos en objets pour le jeu
 * renvoie un tiledmodel qui contient tous les objets ainsi crées
 * on gère en premier les TileLayers de la carte pour la gestion des terrains
 * ensuite, on gère les ObjectLayer pour le reste des informations : collectibles, entités, point de téléportation
 * Attention : la classe TiledMapObjectLayer n'étant pas disponible, on itère sur les MapLayer de façon générale pour la seconde partie
 */
public class TiledParser {

	public TiledModel parse(TiledMap map, RpgMain game) {
		TiledModel tiledmodel = new TiledModel();
		//gestion des dimensions de la carte et des tiles
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		int x = layer.getWidth();
		int y = layer.getHeight();
		int z = layer.getTileWidth();
		int w = layer.getTileHeight();
		tiledmodel.setHeight(y);
		tiledmodel.setWidth(x);
		tiledmodel.setTilewidth(z);
		tiledmodel.setTileheight(w);
		//gestion des layers
		for (int i=0; i<map.getLayers(). getCount(); i++) {
			//gestion des TileLayers
			if(map.getLayers().get(i) instanceof TiledMapTileLayer) {
				MapProperties a = map.getLayers().get(i).getProperties();
				TiledMapTileLayer t = (TiledMapTileLayer) map.getLayers().get(i);
				//terrain eau
				if((boolean) a.get("eau",Boolean.class)) {
					ArrayList<Terrain> eau = new ArrayList<Terrain>();
					//parcours du tilelayer
					for (int width = 0; width < t.getWidth() ; width++) {
					    for (int height = 0; height < t.getHeight(); height++) {
					        TiledMapTileLayer.Cell cell = t.getCell(width, height);
					        //création des objets terrain et stockage dans une liste
					        if(cell!=null) {
					        	Terrain e = new Terrain(width,height,"eau");
					        	eau.add(e);
					        }
					    }
					}
					//ajout de la liste dans le tiledmodel
					tiledmodel.setEau(eau);
				}
				else if ((boolean) a.get("volcanique",Boolean.class)) {
					ArrayList<Terrain> volcanique = new ArrayList<Terrain>();
					//parcours du tilelayer
					for (int width = 0; width < t.getWidth() ; width++) {
					    for (int height = 0; height < t.getHeight(); height++) {
					        TiledMapTileLayer.Cell cell = t.getCell(width, height);
					        //création des objets terrain et stockage dans une liste
					        if(cell!=null) {
					        	Terrain e = new Terrain(width,height,"volcanique");
					        	volcanique.add(e);
					        }
					    }
					}
					//ajout de la liste dans le tiledmodel
					tiledmodel.setVolcaniques(volcanique);
				}
				else if (!(boolean)a.get("franchir",Boolean.class)) {
					//créer la liste des obstacles
					ArrayList<Terrain> obstacle = new ArrayList<Terrain>();
					//parcours du tilelayer
					for (int width = 0; width < t.getWidth() ; width++) {
					    for (int height = 0; height < t.getHeight(); height++) {
					        TiledMapTileLayer.Cell cell = t.getCell(width, height);
					        //création des objets terrain et stockage dans une liste
					        if(cell!=null) {
					        	Terrain e = new Terrain(width,height,"obstacle");
					        	obstacle.add(e);
					        }
					    }
					}
					//ajout de la liste dans le tiledmodel
					tiledmodel.setObstacles(obstacle);
				}
				else if ((boolean) a.get("franchir",Boolean.class) && (String)a.get("Nom",String.class) == "Chemin") {
					//créer la liste des chemins
					ArrayList<Terrain> chemin = new ArrayList<Terrain>();
					//parcours du tilelayer
					for (int width = 0; width < t.getWidth() ; width++) {
					    for (int height = 0; height < t.getHeight(); height++) {
					        TiledMapTileLayer.Cell cell = t.getCell(width, height);
					        //création des objets terrain et stockage dans une liste
					        if(cell!=null) {
					        	Terrain e = new Terrain(width,height,"chemin");
					        	chemin.add(e);
					        }
					    }
					}
					//ajout de la liste dans le tiledmodel
					tiledmodel.setChemins(chemin);
				}
				else if ((boolean) a.get("franchir",Boolean.class)){
					//créer la liste Plaine
					ArrayList<Terrain> plaine = new ArrayList<Terrain>();
					//parcours du tilelayer
					for (int width = 0; width < t.getWidth() ; width++) {
					    for (int height = 0; height < t.getHeight(); height++) {
					        TiledMapTileLayer.Cell cell = t.getCell(width, height);
					        //création des objets terrain et stockage dans une liste
					        if(cell!=null) {
					        	Terrain e = new Terrain(width,height,"plaine");
					        	plaine.add(e);
					        }
					    }
					}
					//ajout de la liste dans le tiledmodel
					tiledmodel.setPlaines(plaine);
				}
			}
				//gestion des ObjectLayer -> layers restants
			else if((map.getLayers().get(i) instanceof MapLayer)) {
				MapLayer u = (MapLayer) map.getLayers().get(i);
					if (u.getName().equals("NPC")){
						//creation liste
						ArrayList<PNJ> pnjs = new ArrayList<PNJ>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							if(element.getName().equals("Elfe")) {
								pnjs.add(new PNJ(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),"Elfe",game,element.getProperties().get("msg", String.class)));
							}
							else if (element.getName().equals("Humain")) {
								pnjs.add(new PNJ (element.getProperties().get("X", int.class), element.getProperties().get("Y", int.class), "Humain",game,element.getProperties().get("msg", String.class)));
							}
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setPnjs(pnjs);
					}
					if (u.getName().equals("Potions")){
						//creation liste
						ArrayList<Potion> potions = new ArrayList<Potion>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							if(element.getName().equals("Bonus")) {
								if(element.getProperties().get("Type",String.class).equals("PV")) {
									potions.add(new Potion(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getProperties().get("Quantité", float.class),"PV",game));
								}
								if(element.getProperties().get("Type",String.class).equals("Mana")) {
									potions.add(new Potion(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getProperties().get("Quantité", float.class),"Mana",game));
								}
								if(element.getProperties().get("Type",String.class).equals("Attaque")) {
									potions.add(new Potion(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getProperties().get("Quantité", float.class),"Attaque",game));
								}
							}
							else if (element.getName().equals("Malus")) {
								if(element.getProperties().get("Type",String.class).equals("PV")) {
									potions.add(new Potion(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),-(element.getProperties().get("Quantité", float.class)),"PV",game));
								}
								if(element.getProperties().get("Type",String.class).equals("Mana")) {
									potions.add(new Potion(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),-(element.getProperties().get("Quantité", float.class)),"Mana",game));
								}
								if(element.getProperties().get("Type",String.class).equals("Attaque")) {
									potions.add(new Potion(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),-(element.getProperties().get("Quantité", float.class)),"Attaque",game));
								}
							}
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setPotions(potions);
					}
					if (u.getName().equals("Tenues")){
						//creation liste
						ArrayList<Tenue> tenues = new ArrayList<Tenue>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							tenues.add(new Tenue(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getName(),game));
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setTenues(tenues);
					}
					if (u.getName().equals("Armes")){
						//creation liste
						ArrayList<Armes> armes = new ArrayList<Armes>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							if(element.getName().equals("Bâton")){
								armes.add(new Armes(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),"Bâton",game));
							}
							else if(element.getName().equals("Hâche")) {
								armes.add(new Armes(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),"Hâche",game));
							}
							else if(element.getName().equals("Arc")) {
								armes.add(new Armes(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),"Arc",game));
							}
							else if(element.getName().equals("Sceptre")) {
								armes.add(new Armes(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),"Sceptre",game));
							}
							else if(element.getName().equals("Epée")) {
								armes.add(new Armes(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),"Epée",game));
							}
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setArmes(armes);
					}
					//gestion des ennemis, creation de la liste des ennemis obligatoire pour terminer le jeu
					ArrayList<Enemy> enemys = new ArrayList<Enemy>();
					//gestion des ennemis de type Monstre
					if (u.getName().equals("Monstres")){
						//creation liste
						ArrayList<Monstre> monstres = new ArrayList<Monstre>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							monstres.add(new Monstre(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getName(),game));
							if((boolean) element.getProperties().get("obligatoire", Boolean.class)) {
								enemys.add(new Monstre(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getName(),game));
							}
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setMonstres(monstres);
					}
					//gestion des ennemis de type Humains
					if (u.getName().equals("EnemyHumans")){
						//creation liste
						ArrayList<EnemyHuman> ehumans = new ArrayList<EnemyHuman>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							ehumans.add(new EnemyHuman(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getName(),game));
							if((boolean) element.getProperties().get("obligatoire", Boolean.class)) {
								enemys.add(new EnemyHuman(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),element.getName(),game));
							}
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setEhumans(ehumans);
						tiledmodel.setEnemys(enemys);
					}
					//gestion des points de téléportation
					if (u.getName().equals("Teleport")){
						//creation liste
						ArrayList<Teleport> teleports = new ArrayList<Teleport>();
						//parcours des objets du layer
						for (MapObject element : u.getObjects()) {
							teleports.add(new Teleport(element.getProperties().get("X", int.class),element.getProperties().get("Y", int.class),u.getName()));
						}
						//ajout liste dans le tiledmodel
						tiledmodel.setTeleports(teleports);
					}
				}
		}
		return tiledmodel;
	}

}
