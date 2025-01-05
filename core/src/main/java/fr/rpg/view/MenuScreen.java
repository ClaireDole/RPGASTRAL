package fr.rpg.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observable;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.concreteobserver.MenuP;
import fr.rpg.controler.observerpattern.concreteobserver.Quit;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;

/**
 * ecran de menu
 */
public class MenuScreen implements Screen, Observable {
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
	private Sound sound;
	
	/**
	 * constructeur
	 * @param game
	 */
	public MenuScreen(final RpgMain game) {
		this.game = game;
		this.observers = new ArrayList<concreteobserver>();
		attach(new MenuP("P"));
		attach(new Quit("Quit"));
		this.sound=Gdx.audio.newSound(Gdx.files.internal("Son/menu.mp3"));
		this.sound.play();
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
	
	/**
	 * gestion de l'affichage
	 */
	private void draw() {
		ScreenUtils.clear(Color.CYAN);
		viewport.apply();
		game.getBatch().setProjectionMatrix(this.camera.combined);
		game.getBatch().begin();
		game.getBatch().draw(this.region, 100,100,654,640);
		font.Getfont1().draw(game.getBatch(), "P --> Retourner à l'écran de jeu", 300,600);
		font.Getfont1().draw(game.getBatch(), "A --> Attaquer avec main gauche", 300,550);
		font.Getfont1().draw(game.getBatch(), "E --> Attaquer avec main droite", 300,500);
		font.Getfont1().draw(game.getBatch(), "Z --> Convaincre", 300,450);
		font.Getfont1().draw(game.getBatch(), "R --> Parler à un PNJ", 300,400);
		font.Getfont1().draw(game.getBatch(), "S --> Se soigner grâce aux elfes", 300,350);
		font.Getfont1().draw(game.getBatch(), "X --> Menu inventaire et statistiques", 300,300);
		font.Getfont1().draw(game.getBatch(), "M --> Menu aide", 300,250);
		font.Getfont1().draw(game.getBatch(), "flèches --> Se déplacer", 300,200);
		game.getBatch().end();
	}
	
	/**
	 * gestion des inputs
	 */
	private void input() {
		if (Gdx.input.isKeyPressed(Keys.P)) {
			Event event = new Event(this.game,"MenuScreen", true, "P",game.getPlayer().getGamescreen());
			notify(event);
			dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			Event event = new Event(this.game,"MenuScreen", true, "Q",null);
			notify(event);
		}
	}
}