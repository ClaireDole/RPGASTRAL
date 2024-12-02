package fr.rpgastral.controler.tiled;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import fr.rpgastral.model.TiledModel;
import fr.rpgastral.model.carte.Terrain;

public class TiledParser {

	public TiledModel parse(TiledMap map) {
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
					tiledmodel.setVolcanique(volcanique);
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
					tiledmodel.setChemin(chemin);
				}
				else {
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
					tiledmodel.setPlaine(plaine);
				}
			}
			//gestion des ObjectLayers
		}
		return tiledmodel;
	}

}
