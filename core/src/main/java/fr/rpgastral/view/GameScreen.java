package fr.rpgastral.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameA;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameDown;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameE;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameLeft;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameM;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameR;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameRight;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameS;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameUp;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameX;
import fr.rpgastral.controler.observerpattern.concreteobserver.GameZ;
import fr.rpgastral.controler.observerpattern.concreteobserver.Quit;
import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;
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
	/**
	 * classe spéciale permetant le rendu d'une carte tiled format tmx
	 */
	private OrthogonalTiledMapRenderer renderer;
	/**
	 * gestion de l'échelle entre les pixels et les gameunit
	 */
	private float unitScale;
	private Player player;
	private int camwidth;
	private int camheight;
	private Font font;
	/**
	 * liste des observers concrets utilisés sur le menu principal
	 * permet de gérer les effets des inputs sur l'écran
	 */
	private ArrayList<concreteobserver> observers;
	private Music background;
	
	/**
	 * constructeur
	 * @param game
	 */
	public GameScreen(final RpgMain game) {
		this.game = game;
		this.observers = new ArrayList<concreteobserver>();
	    // charger les images et la map
		this.game.getManager().load("pack.png",Texture.class);
		//charger les sons
		this.background = Gdx.audio.newMusic(Gdx.files.internal("Son/mainbackground.mp3"));
		this.background.setLooping(true);
		this.background.setVolume(0.5f);
		this.background.play();
		//concreteobservers
		gestionobservers();
		//création du player
		
		if (this.game.getTiledModelGame().getTeleports() != null) {
			//on récupère le point de téléportation correspondant au spawn du player sur la map
			for (int i = 0; i < this.game.getTiledModelGame().getTeleports().size(); i++) {
				if (this.game.getTiledModelGame().getTeleports().get(i).getName().equals("Player")) {
					this.player = new Player(this.game.getTiledModelGame().getTeleports().get(i).getX(),
							this.game.getTiledModelGame().getTeleports().get(i).getY(), this.game);
				}
			} 
		}
		else {
			this.player = new Player(27,24,this.game);
		}
		//rendu
		this.font = new Font();
		int a = this.game.getTiledModelGame().getTilewidth();
		this.unitScale = (float) (1.0/a);
		this.renderer = new OrthogonalTiledMapRenderer(this.game.getGamemap(),unitScale);
		this.camera = new OrthographicCamera();
		
		//gestion des dimensions de la camera et viewport en fonction des dimensions de la carte tiled
		if(this.game.getTiledModelGame().getWidth() < 20) { 
			this.camwidth = this.game.getTiledModelGame().getWidth();
		}
		else if (this.game.getTiledModelGame().getHeight()<20) {
			this.camheight = this.game.getTiledModelGame().getHeight();
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
	 * @see controler/observerpattern/sujet
	 */
	@Override
	public void attach(concreteobserver o) {
		this.observers.add(o);	
	}
	
	/**
	 * @see controler/observerpattern/sujet
	 */
	@Override
	public void unattach(Observer o) {
		this.observers.remove(o);
	}

	/**
	 * @see controler/observerpattern/sujet
	 */
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
		this.player.getSprite().setSize(1,1);
		if(this.game.getTiledModelGame().getPnjs() != null ) {
			for (int i=0; i<this.game.getTiledModelGame().getPnjs().size(); i++) {
				this.game.getTiledModelGame().getPnjs().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModelGame().getPotions() != null ) {
			for (int i=0; i<this.game.getTiledModelGame().getPotions().size(); i++) {
				this.game.getTiledModelGame().getPotions().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModelGame().getTenues() != null ) {
			for (int i=0; i<this.game.getTiledModelGame().getTenues().size(); i++) {
				this.game.getTiledModelGame().getTenues().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModelGame().getArmes() != null ) {
			for (int i=0; i<this.game.getTiledModelGame().getArmes().size(); i++) {
				this.game.getTiledModelGame().getArmes().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModelGame().getMonstres() != null ) {
			for (int i=0; i<this.game.getTiledModelGame().getMonstres().size(); i++) {
				this.game.getTiledModelGame().getMonstres().get(i).getSprite().setSize(1, 1);
			}
		}
		if(this.game.getTiledModelGame().getEhumans() != null ) {
			for (int i=0; i<this.game.getTiledModelGame().getEhumans().size(); i++) {
				this.game.getTiledModelGame().getEhumans().get(i).getSprite().setSize(1, 1);
			}
		}
	}
	
	/**
	 * gestion de l'affichage
	 */
	private void draw() {
	    ScreenUtils.clear(Color.BLACK);
	    int x = this.player.getX();
	    int y = this.player.getY();
	    this.camera.position.x = x;
	    this.camera.position.y = y;
	    this.camera.update();
	    this.game.getBatch().setProjectionMatrix(this.camera.combined);
	    this.renderer.setView(this.camera);
	    setposition();
	    this.viewport.apply();
	    this.game.getBatch().begin();
	    this.renderer.render();
	    this.player.getSprite().draw(this.game.getBatch());
	    //PNJ
	    if(this.game.getTiledModelGame().getPnjs() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getPnjs().size(); i++) {
	    		this.game.getTiledModelGame().getPnjs().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    //Potion
	    if(this.game.getTiledModelGame().getPotions() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getPotions().size(); i++) {
	    		this.game.getTiledModelGame().getPotions().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //Tenue
	    if(this.game.getTiledModelGame().getTenues() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getTenues().size(); i++) {
	    		this.game.getTiledModelGame().getTenues().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    //Arme
	    if(this.game.getTiledModelGame().getArmes() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getArmes().size(); i++) {
	    		this.game.getTiledModelGame().getArmes().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //Monstre
	    if(this.game.getTiledModelGame().getMonstres() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getMonstres().size(); i++) {
	    		this.game.getTiledModelGame().getMonstres().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	  //EnemyHumans
	    if(this.game.getTiledModelGame().getEhumans() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getEhumans().size(); i++) {
	    		this.game.getTiledModelGame().getEhumans().get(i).getSprite().draw(this.game.getBatch());
	    	}
	    }
	    this.game.getBatch().end();
	}
	
	/**
	 * gestion des positions des différents sprites
	 */
	private void setposition() {
		//SetPositionPNJ
		this.player.getSprite().setPosition(this.player.getX(), this.player.getY());
	    if(this.game.getTiledModelGame().getPnjs() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getPnjs().size(); i++) {
	    		this.game.getTiledModelGame().getPnjs().get(i).getSprite().setPosition(this.game.getTiledModelGame().getPnjs().get(i).getX(), this.game.getTiledModelGame().getPnjs().get(i).getY());
	    	}
	    }
	    //SetPositionPotion
	    if(this.game.getTiledModelGame().getPotions() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getPotions().size(); i++) {
	    		this.game.getTiledModelGame().getPotions().get(i).getSprite().setPosition(this.game.getTiledModelGame().getPotions().get(i).getX(), this.game.getTiledModelGame().getPotions().get(i).getY());
	    	}
	    }
	    //SetPositionTenue
	    if(this.game.getTiledModelGame().getTenues() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getTenues().size(); i++) {
	    		this.game.getTiledModelGame().getTenues().get(i).getSprite().setPosition(this.game.getTiledModelGame().getTenues().get(i).getX(), this.game.getTiledModelGame().getTenues().get(i).getY());
	    	}
	    }
	  //SetPositionArme
	    if(this.game.getTiledModelGame().getArmes() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getArmes().size(); i++) {
	    		this.game.getTiledModelGame().getArmes().get(i).getSprite().setPosition(this.game.getTiledModelGame().getArmes().get(i).getX(), this.game.getTiledModelGame().getArmes().get(i).getY());
	    	}
	    }
	  //SetPositionMonstre
	    if(this.game.getTiledModelGame().getMonstres() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getMonstres().size(); i++) {
	    		this.game.getTiledModelGame().getMonstres().get(i).getSprite().setPosition(this.game.getTiledModelGame().getMonstres().get(i).getX(), this.game.getTiledModelGame().getMonstres().get(i).getY());
	    	}
	    }
	  //SetPositionMonstre
	    if(this.game.getTiledModelGame().getEhumans() != null ) {
	    	for (int i=0; i<this.game.getTiledModelGame().getEhumans().size(); i++) {
	    		this.game.getTiledModelGame().getEhumans().get(i).getSprite().setPosition(this.game.getTiledModelGame().getEhumans().get(i).getX(), this.game.getTiledModelGame().getEhumans().get(i).getY());
	    	}
	    }
	}
	
	/**
	 * gestion des inputs sur l'écran
	 */
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
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			Event event = new Event (this.game,"GameScreen", true, "A");
			notify(event);
		}
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			Event event = new Event (this.game,"GameScreen", true, "E");
			notify(event);
		}
		if(Gdx.input.isKeyPressed(Keys.W)) {
			Event event = new Event (this.game,"GameScreen", true, "Z");
			notify(event);
		}
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

	public Music getBackground() {
		return background;
	}

	public void setBackground(Music background) {
		this.background = background;
	}
}
