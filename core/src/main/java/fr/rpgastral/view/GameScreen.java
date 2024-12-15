package fr.rpgastral.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameDown;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameLeft;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameM;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameR;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameRight;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameS;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameUp;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameX;
import fr.rpgastral.controler.observerpattern.concreteobserver.Quit;
import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpgastral.model.collectible.Tenue;
import fr.rpgastral.model.entity.Player;

/**
 * ecran de jeu, c'est l'ecran principal du jeu
 * on gere le rendu de la carte tiled, des entites et des collectibles
 * on gere aussi les input permettant de jouer
 */

public class GameScreen implements Screen, sujet {

	final private  RpgMain game;
	private OrthographicCamera camera;
	private ExtendViewport viewport;
	private OrthogonalTiledMapRenderer renderer;
	private float unitScale;
	private Player player;
	private int camwidth;
	private int camheight;
	private Font font;
	private ArrayList<concreteobserver> observers;
	
	public GameScreen(final RpgMain game) {
		this.game = game;
		this.observers = new ArrayList<concreteobserver>();
	    // charger les images et la map
		this.game.getManager().load("pack.png",Texture.class);
		//charger les sons
		
		//observer
		attach(new GameM("M"));
		attach(new GameUp("Up"));
		attach(new GameDown("Down"));
		attach(new GameLeft("Left"));
		attach(new GameRight("Right"));
		attach(new Quit("Quit"));
		attach(new GameR("R"));
		attach(new GameS("S"));
		attach(new GameX("X"));
		
		this.player = new Player(27,25,game);
		
		//rendu
		this.font = new Font();
		int a = this.game.getTiledModel().getTilewidth();
		this.unitScale = (float) (1.0/a);
		this.renderer = new OrthogonalTiledMapRenderer(this.game.getmap(),unitScale);
		this.camera = new OrthographicCamera();
		
		//gestion des dimensions de la camera et viewport en fonction des dimensions de la carte tiled
		if(this.game.getTiledModel().getWidth() < 20) { 
			this.camwidth = this.game.getTiledModel().getWidth();
		}
		else if (this.game.getTiledModel().getHeight()<20) {
			this.camheight = this.game.getTiledModel().getHeight();
		}
		else {
			this.camheight = this.camwidth = 20;
		}
		this.camera.setToOrtho(false, this.camwidth, this.camheight);
		this.viewport = new ExtendViewport(this.camwidth,this.camheight,this.camera); 
		//gestion des tailles de sprite
		this.player.getSprite().setSize(1,1);
		if(this.game.getTiledModel().getPnj() != null ) {
			for (int i=0; i<this.game.getTiledModel().getPnj().size(); i++) {
				this.game.getTiledModel().getPnj().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModel().getPotion() != null ) {
			for (int i=0; i<this.game.getTiledModel().getPotion().size(); i++) {
				this.game.getTiledModel().getPotion().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModel().getTenue() != null ) {
			for (int i=0; i<this.game.getTiledModel().getTenue().size(); i++) {
				this.game.getTiledModel().getTenue().get(i).getSprite().setSize(1, 1);
			}
		}

		//chargement des fonts
		this.font = new Font();
		this.render(0.5f);	
	}
	
	
	private void input() {
		if (Gdx.input.isKeyPressed(Keys.SEMICOLON)) {
			Event event = new Event(this.game,"GameScreen", true, "M");
			notify(event);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			Event event = new Event(this.game,"GameScreen", true, "Q");
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			Event event = new Event (this.game,"GameScreen", true, "UP");
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			Event event = new Event (this.game,"GameScreen", true, "DOWN");
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			Event event = new Event (this.game,"GameScreen", true, "LEFT");
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			Event event = new Event (this.game,"GameScreen", true, "RIGHT");
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.R)) {
			Event event = new Event (this.game,"GameScreen", true, "R");
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			Event event = new Event (this.game,"GameScreen", true, "S");
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.X)) {
			Event event = new Event (this.game,"GameScreen", true, "X");
			notify(event);
		}
	}

	private void draw() {
	    ScreenUtils.clear(Color.GOLD);
	    int x = this.player.Getx();
	    int y = this.player.Gety();
	    this.camera.position.x = x;
	    this.camera.position.y = y;
	    this.camera.update();
	    this.game.getBatch().setProjectionMatrix(this.camera.combined);
	    this.renderer.setView(this.camera);
	    //SetPositionPNJ
	    this.player.getSprite().setPosition(this.player.Getx(), this.player.Gety());
	    if(this.game.getTiledModel().getPnj() != null ) {
	    	for (int i=0; i<this.game.getTiledModel().getPnj().size(); i++) {
	    		this.game.getTiledModel().getPnj().get(i).getSprite().setPosition(this.game.getTiledModel().getPnj().get(i).Getx(), this.game.getTiledModel().getPnj().get(i).Gety());
	    	}
	    }
	    //SetPositionPotion
	    if(this.game.getTiledModel().getPotion() != null ) {
	    	for (int i=0; i<this.game.getTiledModel().getPotion().size(); i++) {
	    		this.game.getTiledModel().getPotion().get(i).getSprite().setPosition(this.game.getTiledModel().getPotion().get(i).getX(), this.game.getTiledModel().getPotion().get(i).getY());
	    	}
	    }
	    //SetPositionTenue
	    if(this.game.getTiledModel().getTenue() != null ) {
	    	for (int i=0; i<this.game.getTiledModel().getTenue().size(); i++) {
	    		this.game.getTiledModel().getTenue().get(i).getSprite().setPosition(this.game.getTiledModel().getTenue().get(i).getX(), this.game.getTiledModel().getTenue().get(i).getY());
	    	}
	    }
	    this.viewport.apply();
	    this.game.getBatch().begin();
	    this.renderer.render();
	    this.player.getSprite().draw(this.game.getBatch());
	    //PNJ
	    if(this.game.getTiledModel().getPnj() != null ) {
	    	for (int i=0; i<this.game.getTiledModel().getPnj().size(); i++) {
	    		if(this.game.getTiledModel().getPnj().get(i).getRace() == "Elfe") {
	    			this.game.getTiledModel().getPnj().get(i).getSprite().draw(this.game.getBatch());
	    		}
	    		else {
	    			this.game.getTiledModel().getPnj().get(i).getSprite().draw(this.game.getBatch());
	    		}
	    	}
	    }
	    //Potion
	    if(this.game.getTiledModel().getPotion() != null ) {
	    	for (int i=0; i<this.game.getTiledModel().getPotion().size(); i++) {
	    		this.game.getTiledModel().getPotion().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //Tenue
	    if(this.game.getTiledModel().getTenue() != null ) {
	    	for (int i=0; i<this.game.getTiledModel().getTenue().size(); i++) {
	    		this.game.getTiledModel().getTenue().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    this.game.getBatch().end();
	}
	
	@Override
	public void attach(concreteobserver o) {
		this.observers.add(o);	
	}
	
	@Override
	public void unattach(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notify(Event e) {
		if(e.compare(new Event(this.game,"GameScreen",true,"Q"))) {
			for(int i=0; i<this.observers.size(); i++) {
				if(this.observers.get(i).getName() == "Quit") {
					this.observers.get(i).update(e);
				}
			}
		}
		this.observers.forEach(observer -> observer.update(e));	
	}

	@Override
	public void show() {
	}
	
	@Override
	
	public void render(float delta) {
		draw();
		input();
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, true);
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}
	
	@Override
	public void dispose() {
		font.dispose();
		renderer.dispose();
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public int getCamwidth() {
		return camwidth;
	}

	public void setCamwidth(int camwidth) {
		this.camwidth = camwidth;
	}

	public int getCamheight() {
		return camheight;
	}

	public void setCamheight(int camheight) {
		this.camheight = camheight;
	}
}
