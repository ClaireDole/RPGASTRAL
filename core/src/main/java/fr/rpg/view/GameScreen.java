package fr.rpg.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.sujet;
import fr.rpg.controler.observerpattern.concreteobserver.GameA;
import fr.rpg.controler.observerpattern.concreteobserver.GameDown;
import fr.rpg.controler.observerpattern.concreteobserver.GameE;
import fr.rpg.controler.observerpattern.concreteobserver.GameLeft;
import fr.rpg.controler.observerpattern.concreteobserver.GameM;
import fr.rpg.controler.observerpattern.concreteobserver.GameR;
import fr.rpg.controler.observerpattern.concreteobserver.GameRight;
import fr.rpg.controler.observerpattern.concreteobserver.GameS;
import fr.rpg.controler.observerpattern.concreteobserver.GameUp;
import fr.rpg.controler.observerpattern.concreteobserver.GameX;
import fr.rpg.controler.observerpattern.concreteobserver.GameZ;
import fr.rpg.controler.observerpattern.concreteobserver.Quit;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpg.model.carte.TiledModel;

/**
 * ecran de jeu, c'est l'ecran principal du jeu
 * on gere le rendu de la carte tiled, des entites et des collectibles
 * on gere aussi les input permettant de jouer
 */

public class GameScreen implements Screen, sujet {

	final private  RpgMain game;
	/**
	 * carte Tiled représentée par le screen
	 */
	private TiledMap map;
	/**
	 * informations contenues dans la carte
	 */
	private TiledModel tiledmodel;
	private OrthographicCamera camera;
	private ExtendViewport viewport;
	/**
	 * classe spéciale permetant le rendu d'une carte tiled format tmx
	 */
	private OrthogonalTiledMapRenderer renderer;
	/**
	 * gestion de l'échelle entre les pixels et les gameunit
	 */
	private float unitScale;
	private int camwidth;
	private int camheight;
	private Font font;
	/**
	 * liste des observers concrets utilisés sur le menu principal
	 * permet de gérer les effets des inputs sur l'écran
	 */
	private ArrayList<concreteobserver> observers;
	/**
	 * son d'ambiance
	 */
	private Music background;
	/**
	 * validation de première apparition du joueur sur la carte
	 */
	private Boolean start;
	
	/**
	 * constructeur
	 * @param game
	 */
	public GameScreen(TiledMap map,final RpgMain game) {
		this.game = game;
		this.start = true;
		this.map = map;
		//on retrouve le tiledmodel correspondant à la carte
		this.game.getTiledModels().forEach(
				model -> { if(model.getMap().getProperties().get("name",String.class).equals(this.map.getProperties().get("name", String.class))) {
					this.tiledmodel = model;
				}});
		this.observers = new ArrayList<concreteobserver>();
	    // charger les images
		this.game.getManager().load("pack.png",Texture.class);
		//charger les sons
		this.background = Gdx.audio.newMusic(Gdx.files.internal("Son/mainbackground.mp3"));
		this.background.setLooping(true);
		this.background.setVolume(0.5f);
		this.background.play();
		//concreteobservers
		gestionobservers();
		//création du player
		
		if (this.tiledmodel.getTeleports() != null) {
			//on récupère le point de téléportation correspondant au spawn du player sur la map
			for (int i = 0; i < this.tiledmodel.getTeleports().size(); i++) {
				if (this.tiledmodel.getTeleports().get(i).getName().equals("Player") && this.map.getProperties().get("name",String.class).equals("worldmap") && start) {
					this.game.getPlayer().setX(this.tiledmodel.getTeleports().get(i).getX());
					this.game.getPlayer().setY(this.tiledmodel.getTeleports().get(i).getY());
					this.start = false;
				}
			} 
		}
	
		//rendu
		this.font = new Font();
		int a = this.tiledmodel.getTilewidth();
		this.unitScale = (float) (1.0/a);
		this.renderer = new OrthogonalTiledMapRenderer(this.map,unitScale);
		this.camera = new OrthographicCamera();
		
		//gestion des dimensions de la camera et viewport en fonction des dimensions de la carte tiled
		if(this.tiledmodel.getWidth() < 20) { 
			this.camwidth = this.tiledmodel.getWidth();
		}
		else if (this.tiledmodel.getHeight()<20) {
			this.camheight = this.tiledmodel.getHeight();
		}
		else {
			this.camheight = this.camwidth = 20;
		}
		this.camera.setToOrtho(false, this.camwidth, this.camheight);
		this.viewport = new ExtendViewport(this.camwidth,this.camheight,this.camera); 
		
		gestionsprites();

		//chargement des fonts
		this.font = new Font();
		this.render(0.5f);	
	}
	
	/**
	 * @see fr.rpg.controler.observerpattern.sujet
	 */
	@Override
	public void attach(concreteobserver o) {
		this.observers.add(o);	
	}
	
	/**
	 * @see fr.rpg.controler.observerpattern.sujet
	 */
	@Override
	public void unattach(Observer o) {
		this.observers.remove(o);
	}

	/**
	 * @see fr.rpg.controler.observerpattern.sujet
	 */
	@Override
	public void notify(Event e) {
		if(e.compare(new Event(this.game,"GameScreen",true,"Q",null))) {
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
		background.dispose();
	}

	/**
	 * on attache les différents concreteobservers nécessaire pour l'écran à observers
	 */
	private void gestionobservers() {
		attach(new GameM("M"));
		attach(new GameUp("Up"));
		attach(new GameDown("Down"));
		attach(new GameLeft("Left"));
		attach(new GameRight("Right"));
		attach(new Quit("Quit"));
		attach(new GameR("R"));
		attach(new GameS("S"));
		attach(new GameX("X"));
		attach(new GameA("A"));
		attach(new GameE("E"));
		attach(new GameZ("Z"));
	}
	
	/**
	 * mise à l'échelle des différents sprites utilisés lors de l'affichage
	 */
	private void gestionsprites() {
		this.game.getPlayer().getSprite().setSize(1,1);
		if(this.tiledmodel.getPnjs() != null ) {
			for (int i=0; i<this.tiledmodel.getPnjs().size(); i++) {
				this.tiledmodel.getPnjs().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.tiledmodel.getPotions() != null ) {
			for (int i=0; i<this.tiledmodel.getPotions().size(); i++) {
				this.tiledmodel.getPotions().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.tiledmodel.getTenues() != null ) {
			for (int i=0; i<this.tiledmodel.getTenues().size(); i++) {
				this.tiledmodel.getTenues().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.tiledmodel.getArmes() != null ) {
			for (int i=0; i<this.tiledmodel.getArmes().size(); i++) {
				this.tiledmodel.getArmes().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.tiledmodel.getMonstres() != null ) {
			for (int i=0; i<this.tiledmodel.getMonstres().size(); i++) {
				this.tiledmodel.getMonstres().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.tiledmodel.getEhumans() != null ) {
			for (int i=0; i<this.tiledmodel.getEhumans().size(); i++) {
				this.tiledmodel.getEhumans().get(i).getSprite().setSize(1, 1);
			}
		}
	}
	
	/**
	 * gestion de l'affichage
	 */
	private void draw() {
	    ScreenUtils.clear(Color.BLACK);
	    int x = this.game.getPlayer().getX();
	    int y = this.game.getPlayer().getY();
	    this.camera.position.x = x;
	    this.camera.position.y = y;
	    this.camera.update();
	    this.game.getBatch().setProjectionMatrix(this.camera.combined);
	    this.renderer.setView(this.camera);
	    setposition();
	    this.viewport.apply();
	    this.game.getBatch().begin();
	    this.renderer.render();
	    this.game.getPlayer().getSprite().draw(this.game.getBatch());
	    //PNJ
	    if(this.tiledmodel.getPnjs() != null ) {
	    	for (int i=0; i<this.tiledmodel.getPnjs().size(); i++) {
	    		this.tiledmodel.getPnjs().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    //Potion
	    if(this.tiledmodel.getPotions() != null ) {
	    	for (int i=0; i<this.tiledmodel.getPotions().size(); i++) {
	    		this.tiledmodel.getPotions().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //Tenue
	    if(this.tiledmodel.getTenues() != null ) {
	    	for (int i=0; i<this.tiledmodel.getTenues().size(); i++) {
	    		this.tiledmodel.getTenues().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    //Arme
	    if(this.tiledmodel.getArmes() != null ) {
	    	for (int i=0; i<this.tiledmodel.getArmes().size(); i++) {
	    		this.tiledmodel.getArmes().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //Monstre
	    if(this.tiledmodel.getMonstres() != null ) {
	    	for (int i=0; i<this.tiledmodel.getMonstres().size(); i++) {
	    		this.tiledmodel.getMonstres().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //EnemyHumans
	    if(this.tiledmodel.getEhumans() != null ) {
	    	for (int i=0; i<this.tiledmodel.getEhumans().size(); i++) {
	    		this.tiledmodel.getEhumans().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    this.game.getBatch().end();
	}
	
	/**
	 * gestion des positions des différents sprites
	 */
	private void setposition() {
		//SetPositionPNJ
		this.game.getPlayer().getSprite().setPosition(this.game.getPlayer().getX(), this.game.getPlayer().getY());
	    if(this.tiledmodel.getPnjs() != null ) {
	    	for (int i=0; i<this.tiledmodel.getPnjs().size(); i++) {
	    		this.tiledmodel.getPnjs().get(i).getSprite().setPosition(this.tiledmodel.getPnjs().get(i).getX(),this.tiledmodel.getPnjs().get(i).getY());
	    	}
	    }
	    //SetPositionPotion
	    if(this.tiledmodel.getPotions() != null ) {
	    	for (int i=0; i<this.tiledmodel.getPotions().size(); i++) {
	    		this.tiledmodel.getPotions().get(i).getSprite().setPosition(this.tiledmodel.getPotions().get(i).getX(),this.tiledmodel.getPotions().get(i).getY());
	    	}
	    }
	    //SetPositionTenue
	    if(this.tiledmodel.getTenues() != null ) {
	    	for (int i=0; i<this.tiledmodel.getTenues().size(); i++) {
	    		this.tiledmodel.getTenues().get(i).getSprite().setPosition(this.tiledmodel.getTenues().get(i).getX(),this.tiledmodel.getTenues().get(i).getY());
	    	}
	    }
	  //SetPositionArme
	    if(this.tiledmodel.getArmes() != null ) {
	    	for (int i=0; i<this.tiledmodel.getArmes().size(); i++) {
	    		this.tiledmodel.getArmes().get(i).getSprite().setPosition(this.tiledmodel.getArmes().get(i).getX(),this.tiledmodel.getArmes().get(i).getY());
	    	}
	    }
	  //SetPositionMonstre
	    if(this.tiledmodel.getMonstres() != null ) {
	    	for (int i=0; i<this.tiledmodel.getMonstres().size(); i++) {
	    		this.tiledmodel.getMonstres().get(i).getSprite().setPosition(this.tiledmodel.getMonstres().get(i).getX(),this.tiledmodel.getMonstres().get(i).getY());
	    	}
	    }
	  //SetPositionMonstre
	    if(this.tiledmodel.getEhumans() != null ) {
	    	for (int i=0; i<this.tiledmodel.getEhumans().size(); i++) {
	    		this.tiledmodel.getEhumans().get(i).getSprite().setPosition(this.tiledmodel.getEhumans().get(i).getX(), this.tiledmodel.getEhumans().get(i).getY());
	    	}
	    }
	}
	
	/**
	 * gestion des inputs sur l'écran
	 */
	private void input() {
		if (Gdx.input.isKeyPressed(Keys.SEMICOLON)) {
			Event event = new Event(this.game,"GameScreen", true, "M",this);
			notify(event);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			Event event = new Event(this.game,"GameScreen", true, "Q",this);
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			Event event = new Event (this.game,"GameScreen", true, "UP",this);
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			Event event = new Event (this.game,"GameScreen", true, "DOWN",this);
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			Event event = new Event (this.game,"GameScreen", true, "LEFT",this);
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			Event event = new Event (this.game,"GameScreen", true, "RIGHT",this);
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.R)) {
			Event event = new Event (this.game,"GameScreen", true, "R",this);
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			Event event = new Event (this.game,"GameScreen", true, "S",this);
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.X)) {
			Event event = new Event (this.game,"GameScreen", true, "X",this);
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			Event event = new Event (this.game,"GameScreen", true, "A",this);
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			Event event = new Event (this.game,"GameScreen", true, "E",this);
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.W)) {
			Event event = new Event (this.game,"GameScreen", true, "Z",this);
			notify(event);
		}
	}

	/**
	 * @return the map
	 */
	public TiledMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(TiledMap map) {
		this.map = map;
	}

	/**
	 * @return the tiledmodel
	 */
	public TiledModel getTiledmodel() {
		return tiledmodel;
	}

	/**
	 * @param tiledmodel the tiledmodel to set
	 */
	public void setTiledmodel(TiledModel tiledmodel) {
		this.tiledmodel = tiledmodel;
	}

	/**
	 * @return the camera
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

	/**
	 * @param camera the camera to set
	 */
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	/**
	 * @return the camwidth
	 */
	public int getCamwidth() {
		return camwidth;
	}

	/**
	 * @param camwidth the camwidth to set
	 */
	public void setCamwidth(int camwidth) {
		this.camwidth = camwidth;
	}

	/**
	 * @return the camheight
	 */
	public int getCamheight() {
		return camheight;
	}

	/**
	 * @param camheight the camheight to set
	 */
	public void setCamheight(int camheight) {
		this.camheight = camheight;
	}

	/**
	 * @return the observers
	 */
	public ArrayList<concreteobserver> getObservers() {
		return observers;
	}

	/**
	 * @param observers the observers to set
	 */
	public void setObservers(ArrayList<concreteobserver> observers) {
		this.observers = observers;
	}

	/**
	 * @return the background
	 */
	public Music getBackground() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground(Music background) {
		this.background = background;
	}
}