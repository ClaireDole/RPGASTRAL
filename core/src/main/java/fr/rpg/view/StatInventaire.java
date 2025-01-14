package fr.rpg.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observable;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.concreteobserver.StatP;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;

/**
 * ecran pour les statistiques et inventaire du joueur
 */

public class StatInventaire implements Screen, Observable{

	private RpgMain game;
	private OrthographicCamera camera;
	private ScreenViewport viewport;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private AtlasRegion window;
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
	public StatInventaire(final RpgMain game) {
		this.game = game;
		this.camera = new OrthographicCamera(800,800);
		this.viewport = new ScreenViewport(camera);
		this.font = new Font();
		this.sound=Gdx.audio.newSound(Gdx.files.internal("Son/menu.mp3"));
		this.sound.play();
		this.game.getManager().load("pack.png", Texture.class);
		this.window = this.game.getAtlas().findRegion("Interface/window");
		this.font = new Font();
		this.observers = new ArrayList<concreteobserver>();
		attach(new StatP("StatP"));
		this.render(0.5f);	
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
		if(e.compareTo(new Event(this.game,"GameScreen",true,"Q",null))==0) {
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
		sound.dispose();
	}

	/**
	 * gestion de l'affichage
	 */
	private void draw() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		this.viewport.apply();
		this.game.getBatch().setProjectionMatrix(this.viewport.getCamera().combined);
		this.game.getBatch().begin();
		this.game.getBatch().draw(this.window,0,0,800,800);
		this.font.Getfont1().draw(this.game.getBatch(), "PV :",50,600,75,650,false);
		this.font.Getfont2().draw(this.game.getBatch(),this.game.getPlayer().getPV() + "",80,600,105, 650, false);
		this.font.Getfont1().draw(this.game.getBatch(), "Mana :",250,600,275,650,false);
		this.font.Getfont2().draw(this.game.getBatch(),this.game.getPlayer().getMana() + "", 300,600,325, 650, false);
		this.font.Getfont1().draw(this.game.getBatch(), "Bonus Attaque :",400,600,450,650,false);
		this.font.Getfont2().draw(this.game.getBatch(),this.game.getPlayer().getBonusAttaque() + "", 500,600,525, 650, false);
		if(this.game.getPlayer().getMg() == null) {
			this.font.Getfont1().draw(this.game.getBatch(), "Arme main gauche :",100,400,175,450,false);
			this.font.Getfont3().draw(this.game.getBatch(), "pas d'arme pour le moment", 250, 400, 300, 450, false);
		}
		else {
			this.font.Getfont1().draw(this.game.getBatch(), "Arme main gauche :",100,400,175,450,false);
			this.font.Getfont3().draw(this.game.getBatch(), this.game.getPlayer().getMg().getName(), 250, 400, 300, 450, false);
		}
		if(this.game.getPlayer().getMd() == null) {
			this.font.Getfont1().draw(this.game.getBatch(), "Arme main droite :",100,300,175,350,false);
			this.font.Getfont3().draw(this.game.getBatch(), "pas d'arme pour le moment", 250, 300, 300, 350, false);
		}
		else {
			this.font.Getfont1().draw(this.game.getBatch(), "Arme main droite :",100,300,175,350,false);
			this.font.Getfont3().draw(this.game.getBatch(), this.game.getPlayer().getMd().getName(), 250, 300, 300, 350, false);
		}
		if(this.game.getPlayer().getTenue() == null) {
			this.font.Getfont1().draw(this.game.getBatch(), "Tenue :",100,200,150,250,false);
			this.font.Getfont3().draw(this.game.getBatch(), "pas de tenue pour le moment", 250, 200, 300, 250, false);
		}
		else {
			this.font.Getfont1().draw(this.game.getBatch(), "Tenue :",100,200,150,250,false);
			this.font.Getfont3().draw(this.game.getBatch(), this.game.getPlayer().getTenue().getName(), 250, 200, 300, 250, false);
		}
		this.game.getBatch().end();
	}

	/**
	 * gestion des inputs
	 */
	private void input() {
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			Event event = new Event(this.game,"StatInventaireScreen",true,"P",game.getPlayer().getGamescreen());
			notify(event);
			this.dispose();
		}
	}

}
