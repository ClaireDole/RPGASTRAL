package fr.rpgastral.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.entity.Player;

public class GameScreen implements Screen {

	final private  RpgMain game;
	private OrthographicCamera camera;
	private ExtendViewport viewport;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Player player;
	private Boolean fenetre;
	
	public GameScreen(final RpgMain game) {
		this.game = game;
	    // charger les images et la map
		game.getManager().load("pack.png",Texture.class);
		map = new  TmxMapLoader().load("carte/worldmap.tmx");
		//si la taille de la map est inférieure à la taille minimum ...faire des if permettant de gérer les games units en fonction de la taille de la worldmap
		//charger les sons
		
		//booléens pour les input
		fenetre = false;
		
		//rendu
		float unitScale = 1 / 32f; //les tiles de la map sont en 32px32p et correspondent à 1 unit game
		renderer = new OrthogonalTiledMapRenderer(map, unitScale, game.getBatch());
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 20, 20); //30 unit game par 20 unit game
		viewport = new ExtendViewport(20,20,camera); //le viewport doit être de la taille de la map et camera permet de gérer la taille de ce que l'on voit à l'écran
		this.render(0.01f);
		
		//création des objets avec les layers de la map
		for(int i = 0;i< map.getLayers().getCount(); i++) {
			//
		}
		player = new Player(5,5);
		
	}
	
	public void render(float delta) {
		draw();
		input();
		logic();
	}
	
	private void input() {
		if (Gdx.input.isKeyPressed(Keys.SEMICOLON)) {
			this.game.setScreen(new MenuScreen(this.game));
		}
		if(Gdx.input.isKeyPressed(Keys.R)) {
			if(fenetre) {
				fenetre = false;
			}
			else {
				fenetre = true;
			}
		}
	}
	private void logic() {
		//exemple si le player faisait 64 par 64
		//if (bucket.x < 0)
			//bucket.x = 0;
		//if (bucket.x > 800 - 64)
			//bucket.x = 800 - 64;
	}
	private void draw() {
	    ScreenUtils.clear(Color.GOLD);
	    camera.update();
	    game.getBatch().setProjectionMatrix(camera.combined);
	    renderer.setView(camera);
	    viewport.apply();
	    game.getBatch().begin();
	    renderer.renderTileLayer((TiledMapTileLayer)map.getLayers().get(0));
	    if (fenetre) {
	    	game.getBatch().draw(game.getAtlas().findRegion("Interface/window"), 0, 0, 20,7);
	    }
	    game.getBatch().end();
	}
	public void resize(int width, int height) {
		this.viewport.update(width, height, true);
	}
	public void show() {
	}
	public void hide() {
	}
	public void pause() {
	}
	public void resume() {
	}
	public void dispose() {
		map.dispose();
	}

}
