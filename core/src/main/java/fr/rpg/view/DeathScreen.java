package fr.rpg.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observable;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.concreteobserver.Quit;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;

/**
 * écran affiché lors de la mort du joueur
 */
public class DeathScreen implements Screen, Observable{

	/**
	 * instance de la classe main du jeu
	 */
	final RpgMain game;
	private SpriteBatch batch;
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
	private Sound sound;

	/**
	 * constructeur, on passe en paramètre l'instance unique de la classe Main afin de récupérer des informations
	 * @param game instance de la classe main
	 */
	public DeathScreen(final RpgMain game) {
		this.game=game;
		this.batch = new SpriteBatch();
		this.observers = new ArrayList<concreteobserver>();
		attach(new Quit("Quit"));
		this.sound=Gdx.audio.newSound(Gdx.files.internal("Son/gameover.wav"));
		this.sound.play();
		game.getManager().load("pack.png", Texture.class);
		region = game.getAtlas().findRegion("Interface/DeadMenubackground");
		camera = new OrthographicCamera(800, 800);
		viewport = new ScreenViewport(camera);
		font = new Font();
		this.render(0.5f);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		batch.draw(region,200,400,600,600);
		font.Getfont2().draw(batch, "Vous êtes mort", 350,350);
		font.Getfont2().draw(batch, "Pour relancer une partie, relancer le programme.", 250,200);
		batch.end();

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
		this.font.dispose();
		this.batch.dispose();
		this.sound.dispose();
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
