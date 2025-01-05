package fr.rpg.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observable;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.concreteobserver.Quit;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpg.controler.observerpattern.concreteobserver.mainmenuSPACE;

/**
 * classe décrivant l'affichage du menu principal
 */

public class MainMenuScreen implements Screen, Observable{

	/**
	 * instance de la classe main du jeu
	 */
	final RpgMain game;
	private OrthographicCamera camera;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private AtlasRegion region;
	private ScreenViewport viewport;
	private Font font;
	/**
	 * liste des observers concrets utilisés sur le menu principal
	 * permet de gérer les effets des inputs sur l'écran
	 */
	private ArrayList<concreteobserver> observers;
	private Music sound;

	/**
	 * constructeur, on passe en paramètre l'instance unique de la classe Main afin de récupérer des informations
	 * @param game
	 */
	public MainMenuScreen(final RpgMain game) {
		this.game = game;
		this.observers = new ArrayList<concreteobserver>();
		attach(new mainmenuSPACE("Space"));
		attach(new Quit("Quit"));
		this.sound=Gdx.audio.newMusic(Gdx.files.internal("Son/start.wav"));
		this.sound.setVolume(1.75f);
		this.sound.play();
		game.getManager().load("pack.png", Texture.class);
		region = game.getAtlas().findRegion("Interface/MainMenubackground");
		camera = new OrthographicCamera(800, 800);
		viewport = new ScreenViewport(camera);
		font = new Font();
		this.render(0.5f);	
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		viewport.apply();
		game.getBatch().setProjectionMatrix(viewport.getCamera().combined);
		game.getBatch().begin();
		game.getBatch().draw(region, 200, 200, 500, 500);
		font.Getfont2().draw(game.getBatch(), "Bienvenue sur Tales of Liwa !! ", 200, 600, 350, 450, false);
		font.Getfont1().draw(game.getBatch(), "Un mélange d'action et de tactical", 350, 500);
		font.Getfont1().draw(game.getBatch(), "Appuyer sur la touche espace pour commencer", 300, 400);
		game.getBatch().end();

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			Event event = new Event(game,"MainMenuScreen",true,"SPACE",null);
			notify(event);
			this.dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			Event event = new Event(game,"MainMenuScreen", true, "Q",null);
			notify(event);
		}
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
		this.sound.dispose();
		this.font.dispose();
	}

	/**
	 * @see fr.rpg.controler.observerpattern.Observable
	 */
	@Override
	public void attach(concreteobserver o) {
		this.observers.add(o);

	}

	/**
	 * @see fr.rpg.controler.observerpattern.Observable
	 */
	@Override
	public void unattach(Observer o) {
		this.observers.remove(o);

	}

	/**
	 * @see fr.rpg.controler.observerpattern.Observable
	 */
	@Override
	public void notify(Event e) {
		if(e.compareTo(new Event(game,"GameScreen",true,"Q",null))==0) {
			for(int i=0; i<observers.size(); i++) {
				if(observers.get(i).getName() == "Quit") {
					observers.get(i).update(e);
				}
			}
		}
		this.observers.forEach(observer -> observer.update(e));	
	}
}
