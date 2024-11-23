package fr.rpgastral.controler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
	private MainMenuScreen mainMenuScreen;
	private GameScreen gamescreen;
	
    @Override
    public void create() {
    	
    	// Gestion des textures
    	try {
    		texturePackerHelper = new TexturePackerHelper();
    		atlas = texturePackerHelper.index();
    	} catch (Exception e) {
    		e.printStackTrace();
    		//System.out.println("Erreur lors du packing");
    		return;
    	}
    	
    	// creer le parser de carte tiled
    	tiledParser = new TiledParser();
    	
    	// le TiledParser lit la carte tiled et creer le model TiledModel
    	try {
    		tiledModel = tiledParser.parse();
    	} catch (Exception e) {
    		e.printStackTrace();
    		//System.out.println("Erreur lors du parsing tiled");
    		return;
    	}
    	
    	// creation de l'assetmanager met en cache tous les assets
    	manager = new AssetManager(); 
    	
    	// declaration d'un batch en charge de l'affichage des screens
    	batch = new SpriteBatch();
   
    	// declaration des screens
     	mainMenuScreen = new MainMenuScreen(this);
    	gamescreen = new GameScreen(this);
    	
    	// screen de demarrage
    	this.setScreen(mainMenuScreen);   
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

	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
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

	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

	public GameScreen getGamescreen() {
		return gamescreen;
	}

	public void setGamescreen(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}
}
