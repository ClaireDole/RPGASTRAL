package fr.rpg.controler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Logger;

import fr.rpg.controler.loader.Loader;
import fr.rpg.controler.texture.TexturePackerHelper;
import fr.rpg.controler.tiled.TiledParser;
import fr.rpg.model.carte.TiledModel;
import fr.rpg.model.entity.Player;
import fr.rpg.view.GameScreen;
import fr.rpg.view.MainMenuScreen;

/**
 * classe principale qui sert de point d'entrée dans le jeu
 * on y lance la configuration à partir de la carte, le traitement des assets, la création de l'écran de jeu ainsi que le spritebatch principal
 */
public class RpgMain extends Game {

	/**
	 * stocke le chemin vers le dossier où sont stockées les cartes
	 */
	private Path cartes;
	/**
	 * permet la gestion de la carte tiled
	 */
	private TiledParser tiledParser; 
	/**
	 * liste des cartes chargées
	 */
	private ArrayList<TiledMap> maps;
	/**
	 * stockage des modèles des différentes cartes
	 */
	private ArrayList<TiledModel> TiledModels;
	/**
	 * gestion des assets
	 */
	private TexturePackerHelper texturePackerHelper;
	/**
	 * atlas pour retrouver les différentes assets
	 */
	private TextureAtlas atlas;
	/**
	 * gestionaire d'asset
	 */
	private AssetManager manager;
	/**
	 * batch permettant d'afficher un rendu
	 */
	private SpriteBatch batch;
	/**
	 * Screen de démarrage
	 */
	private MainMenuScreen mainMenuScreen;

	private ArrayList<GameScreen> GameScreens;
	/**
	 * gestion du chargement du jeu
	 */
	private Loader loader;
	private Logger logger;
	/**
	 * joueur
	 */
	private Player player;

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
		//Attention : il faut absolument que le path corresponde pour pouvoir charger les cartes et lancer le jeu !
		cartes = Paths.get("C:\\Users\\agnf3\\Documents\\RPG\\Projet\\RPGASTRAL\\assets\\carte");
		maps = new ArrayList<TiledMap>();
		TiledModels = new ArrayList<TiledModel>();
		GameScreens = new ArrayList<GameScreen>();
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
		//chargement des cartes Tiled et stockage dans la liste maps
		try (Stream<Path> paths = Files.list(cartes)) {
			paths
			.filter(path -> path.toString().endsWith(".tmx"))
			.forEach(path -> {
				if (Files.isRegularFile(path)) {
					System.out.println("Fichier Tiled trouvé : " + path);
					maps.add(new TmxMapLoader().load(path.toString()));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		// Interprétation des cartes et stockage dans la liste TiledModels
		try {
			maps.forEach(map -> TiledModels.add(tiledParser.parse(map,this)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors du parsing tiled");
			return;
		}
		loader.print("fin de la lecture de la carte");
		//création du player
		this.player = new Player(0,0,this);
		//Création des gamescreen correspondant aux différentes cartes
		try {
			maps.forEach(map -> GameScreens.add(new GameScreen(map,this)));
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Erreur lors de la création des écrans de jeu");
			return;
		}
		loader.dispose();
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
		GameScreens.forEach(screen -> screen.dispose());
		if(this.getMainMenuScreen()!=null) {
			this.getMainMenuScreen().dispose();
		}
		this.getLoader().dispose();
		maps.forEach(map -> map.dispose());
		this.getBatch().dispose();
		this.getManager().dispose();
	}

	/**
	 * @return the cartes
	 */
	public Path getCartes() {
		return cartes;
	}

	/**
	 * @param cartes the cartes to set
	 */
	public void setCartes(Path cartes) {
		this.cartes = cartes;
	}

	/**
	 * @return the tiledParser
	 */
	public TiledParser getTiledParser() {
		return tiledParser;
	}

	/**
	 * @param tiledParser the tiledParser to set
	 */
	public void setTiledParser(TiledParser tiledParser) {
		this.tiledParser = tiledParser;
	}

	/**
	 * @return the maps
	 */
	public ArrayList<TiledMap> getMaps() {
		return maps;
	}

	/**
	 * @param maps the maps to set
	 */
	public void setMaps(ArrayList<TiledMap> maps) {
		this.maps = maps;
	}

	/**
	 * @return the tiledModels
	 */
	public ArrayList<TiledModel> getTiledModels() {
		return TiledModels;
	}

	/**
	 * @param tiledModels the tiledModels to set
	 */
	public void setTiledModels(ArrayList<TiledModel> tiledModels) {
		TiledModels = tiledModels;
	}

	/**
	 * @return the texturePackerHelper
	 */
	public TexturePackerHelper getTexturePackerHelper() {
		return texturePackerHelper;
	}

	/**
	 * @param texturePackerHelper the texturePackerHelper to set
	 */
	public void setTexturePackerHelper(TexturePackerHelper texturePackerHelper) {
		this.texturePackerHelper = texturePackerHelper;
	}

	/**
	 * @return the atlas
	 */
	public TextureAtlas getAtlas() {
		return atlas;
	}

	/**
	 * @param atlas the atlas to set
	 */
	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}

	/**
	 * @return the manager
	 */
	public AssetManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(AssetManager manager) {
		this.manager = manager;
	}

	/**
	 * @return the batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * @param batch the batch to set
	 */
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	/**
	 * @return the mainMenuScreen
	 */
	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	/**
	 * @param mainMenuScreen the mainMenuScreen to set
	 */
	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

	/**
	 * @return the loader
	 */
	public Loader getLoader() {
		return loader;
	}

	/**
	 * @param loader the loader to set
	 */
	public void setLoader(Loader loader) {
		this.loader = loader;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the gameScreens
	 */
	public ArrayList<GameScreen> getGameScreens() {
		return GameScreens;
	}

	/**
	 * @param gameScreens the gameScreens to set
	 */
	public void setGameScreens(ArrayList<GameScreen> gameScreens) {
		GameScreens = gameScreens;
	}
}
