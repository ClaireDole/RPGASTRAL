package fr.rpgastral.view;

import java.util.ArrayList;

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
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameM;
import fr.rpgastral.model.entity.Player;

public class GameScreen implements Screen, sujet {

	final private  RpgMain game;
	private OrthographicCamera camera;
	private ExtendViewport viewport;
	private OrthogonalTiledMapRenderer renderer;
	private Player player;
	private int camwidth;
	private int camheight;
	private ArrayList<Observer> list;
	
	public GameScreen(final RpgMain game) {
		this.game = game;
		this.list = new ArrayList<Observer>();
	    // charger les images et la map
		game.getManager().load("pack.png",Texture.class);
		//charger les sons
		
		//observer
		attach(new GameM(game));
		
		player = new Player(27,25,game);
		
		//rendu
		int a = game.getTiledModel().getTilewidth();
		float unitScale = (float) (1.0/a);
		renderer = new OrthogonalTiledMapRenderer(game.getmap(),unitScale);
		camera = new OrthographicCamera();
		
		//gestion des dimensions de la camera et viewport en fonction des dimensions de la carte tiled
		if(game.getTiledModel().getWidth() < 20) { 
			this.camwidth = game.getTiledModel().getWidth();
		}
		else if (game.getTiledModel().getHeight()<20) {
			this.camheight = game.getTiledModel().getHeight();
		}
		else {
			this.camheight = this.camwidth = 20;
		}
		camera.setToOrtho(false, camwidth, camheight);
		viewport = new ExtendViewport(camwidth,camheight,camera); 
		player.getSprite().setSize(1,1);
		this.render(0.01f);	
	}
	
	public void render(float delta) {
		draw();
		input();
		logic();
	}
	
	private void input() {
		if (Gdx.input.isKeyPressed(Keys.SEMICOLON)) {
			Event event = new Event("GameScreen", true, "M");
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
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
	    int x = this.player.Getx();
	    int y = this.player.Gety();
	    camera.position.x = x;
	    camera.position.y = y;
	    camera.update();
	    game.getBatch().setProjectionMatrix(camera.combined);
	    renderer.setView(camera);
	    player.getSprite().setPosition(player.Getx(), player.Gety());
	    viewport.apply();
	    game.getBatch().begin();
	    renderer.render();
	    player.getSprite().draw(game.getBatch());
	    game.getBatch().end();
	}
	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, true);
	}
	@Override
	public void show() {
	}
	@Override
	public void hide() {
	}
	@Override
	public void pause() {
	}
	@Override
	public void resume() {
	}
	@Override
	public void dispose() {
	}
	@Override
	public void attach(Observer o) {
		this.list.add(o);
		
	}
	@Override
	public void unattach(Observer o) {
		this.list.remove(o);
		
	}
	@Override
	public void notify(Event e) {
		this.list.forEach(observer -> observer.update(e));
		
	}

}
