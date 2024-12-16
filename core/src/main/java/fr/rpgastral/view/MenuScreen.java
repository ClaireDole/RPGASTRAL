package fr.rpgastral.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.MenuP;
import fr.rpgastral.controler.observerpattern.concreteobserver.Quit;
import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;

/**
 * ecran de menu
 */
public class MenuScreen implements Screen, sujet {
	final RpgMain game;
	private AtlasRegion region;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private OrthographicCamera camera;
	private ScreenViewport viewport;
	private Font font;
	/**
	 * liste des observers concrets utilisés sur le menu principal
	 * permet de gérer les effets des inputs sur l'écran
	 */
	private ArrayList<concreteobserver> observers;
	
	/**
	 * constructeur
	 * @param game
	 */
	public MenuScreen(final RpgMain game) {
		this.game = game;
		this.observers = new ArrayList<concreteobserver>();
		attach(new MenuP("P"));
		attach(new Quit("Quit"));
		this.game.getManager().load("pack.png",Texture.class);
		this.region = game.getAtlas().findRegion("Interface/MainMenubackground");
		this.camera = new OrthographicCamera(800,800);
		this.viewport = new ScreenViewport(camera);
		this.font = new Font();
		this.render(0.5f);	
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
		this.font.dispose();
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
		if(e.compare(new Event(game,"GameScreen",true,"Q"))) {
			for(int i=0; i<observers.size(); i++) {
				if(observers.get(i).getName() == "Quit") {
					observers.get(i).update(e);
				}
			}
		}
		this.observers.forEach(observer -> observer.update(e));	
	}
	
	/**
	 * gestion de l'affichage
	 */
	private void draw() {
		ScreenUtils.clear(Color.CYAN);
		viewport.apply();
		game.getBatch().setProjectionMatrix(this.camera.combined);
		game.getBatch().begin();
		game.getBatch().draw(this.region, 100,100,654,640);
		font.Getfont1().draw(game.getBatch(), "Retourner à l'écran de jeu --> P", 200,600);
		game.getBatch().end();
	}
	
	/**
	 * gestion des inputs
	 */
	private void input() {
		if (Gdx.input.isKeyPressed(Keys.P)) {
			Event event = new Event(this.game,"MenuScreen", true, "P");
			notify(event);
			dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			Event event = new Event(this.game,"MenuScreen", true, "Q");
			notify(event);
		}
	}
}