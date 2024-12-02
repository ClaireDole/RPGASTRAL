package fr.rpgastral.controler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Logger;

import fr.rpgastral.controler.loader.Loader;
import fr.rpgastral.controler.texture.TexturePackerHelper;
import fr.rpgastral.controler.tiled.TiledParser;
import fr.rpgastral.model.TiledModel;
import fr.rpgastral.view.GameScreen;
import fr.rpgastral.view.MainMenuScreen;

/**
 * 
 */
public class RpgMain extends Game {
	
	private TiledParser tiledParser; 
	private TiledModel tiledModel;
	private TexturePackerHelper texturePackerHelper;
	private TextureAtlas atlas;
	private AssetManager manager;
	private SpriteBatch batch;
	private TiledMap map;
	private MainMenuScreen mainMenuScreen;
	private GameScreen gamescreen;
	private Loader loader;
	private Logger logger;
	
	public RpgMain() {
		this.logger = new Logger("début initialisation des variables du jeu");
		this.logger.info("début initialisation des variables du jeu");
    	// creer le parser de carte tiled; le texturepacker; le loader et l'asset manager
    	tiledParser = new TiledParser();
    	texturePackerHelper = new TexturePackerHelper();
    	loader = new Loader(this);
    	manager = new AssetManager(); 
    	this.logger = new Logger("fin initialisation des variables du jeu");
    	this.logger.info("fin initialisation des variables du jeu");
	}
	
    @Override
    public void create() {
		//charge la carte Tiled
		this.map = new  TmxMapLoader().load("carte/worldmap.tmx");
    	batch = new SpriteBatch();
    	new RpgMain();
    	loader.start();
    	loader.print("lecture des textures");
    	// Gestion des textures
    	try {
    		atlas = texturePackerHelper.index();
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Erreur lors du packing");
    		return;
    	}
    	loader.print("fin de la lecture des textures");
    	loader.print("lecture de la carte");
    	
    	// le TiledParser lit la carte tiled et creer le model TiledModel
    	try {
    		tiledModel = tiledParser.parse(map);
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Erreur lors du parsing tiled");
    		return;
    	}
    	loader.print("fin de la lecture de la carte");
    	loader.stop();
    	gamescreen = new GameScreen(this);
    	// screen de demarrage
    	mainMenuScreen = new MainMenuScreen(this);
    	this.setScreen(mainMenuScreen);  
    
    	//cycle de vie du jeu
    	//faire une boucle d'attente des évènements du jeu
    	//sortie de cette boucle si le joueur tape la sortie du jeu
    	
    	//évènement.getreponse() : choix 1 : créer un listener 
    	//choix 2 : implémenter le design pattern observer
    	//choix 3 : faire une boucle temporelle de lecture des évènements toutes les 0.5 secondes
    	
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    	if (mainMenuScreen != null) {
    		mainMenuScreen.dispose();
    	}
    	if (gamescreen != null) {
    		gamescreen.dispose();
    	}
		manager.dispose();
		batch.dispose();
    }

    public TiledMap getmap() {
    	return this.map;
    }
    
	public TiledParser getTiledParser() {
		return tiledParser;
	}

	public void setTiledParser(TiledParser tiledParser) {
		this.tiledParser = tiledParser;
	}

	public TiledModel getTiledModel() {
		return tiledModel;
	}

	public void setTiledModel(TiledModel tiledModel) {
		this.tiledModel = tiledModel;
	}

	public TexturePackerHelper getTexturePackerHelper() {
		return texturePackerHelper;
	}

	public void setTexturePackerHelper(TexturePackerHelper texturePackerHelper) {
		this.texturePackerHelper = texturePackerHelper;
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	public AssetManager getManager() {
		return manager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public GameScreen getGamescreen() {
		return gamescreen;
	}

	public void setGamescreen(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}

}
