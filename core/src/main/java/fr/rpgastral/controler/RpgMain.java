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
 * classe principale qui sert de point d'entrée dans le jeu
 * on y lance la configuration à partir de la carte, le traitement des assets, la création de l'écran de jeu ainsi que le spritebatch principal
 */
public class RpgMain extends Game {
	
	/**
	 * permet la gestion de la carte tiled
	 */
	private TiledParser tiledParser; 
	/**
	 * stockage des informations traitées par la carte tiled
	 */
	private TiledModel tiledModel;
	/**
	 * gestion des assets
	 */
	private TexturePackerHelper texturePackerHelper;
	/**
	 * atlas pour retrouver les différentes assets
	 */
	private TextureAtlas atlas;
	private AssetManager manager;
	/**
	 * batch permettant d'afficher un rendu
	 */
	private SpriteBatch batch;
	/**
	 * carte tiled sous format tmx
	 */
	private TiledMap map;
	private MainMenuScreen mainMenuScreen;
	/**
	 * on lie le gamescreen à la classe main pour éviter les erreurs comme la remise à zéro de l'écran de jeu après l'ouverture d'un menu
	 * on garantit ainsi une unique instance du gamescreen 
	 */
	private GameScreen gamescreen;
	/**
	 * gestion du chargement du jeu
	 */
	private Loader loader;
	private Logger logger;
	
	/**
	 * constructeur lancé automatiquement lors de l'exécution
	 * vient des propriétés de la classe Game
	 */
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
	
	/**
	 * création du jeu
	 * override une méthode de Game
	 * chargement de la carte tiled, des assets, gestion du parsing de la carte et des screen pour démarrer
	 */
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
    		tiledModel = tiledParser.parse(map,this);
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Erreur lors du parsing tiled");
    		return;
    	}
    	loader.print("fin de la lecture de la carte");
    	loader.dispose();
    	gamescreen = new GameScreen(this);
    	// screen de demarrage
    	mainMenuScreen = new MainMenuScreen(this);
    	this.setScreen(mainMenuScreen);  	
    }

    /**
     * gestion du rendu du jeu
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * gestion pour la suppression du jeu
     */
    @Override
    public void dispose() {
		this.getGamescreen().dispose();
		this.getMainMenuScreen().dispose();
		this.getLoader().dispose();
		this.getMap().dispose();
		this.getBatch().dispose();
		this.getManager().dispose();
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
	
	public void setManager(AssetManager manager) {
		this.manager = manager;
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

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public Loader getLoader() {
		return loader;
	}

	public void setLoader(Loader loader) {
		this.loader = loader;
	}

}
