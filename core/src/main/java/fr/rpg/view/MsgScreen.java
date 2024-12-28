package fr.rpg.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.sujet;
import fr.rpg.controler.observerpattern.concreteobserver.Msg1;
import fr.rpg.controler.observerpattern.concreteobserver.Msg2;
import fr.rpg.controler.observerpattern.concreteobserver.Msg3;
import fr.rpg.controler.observerpattern.concreteobserver.MsgP;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpg.model.collectible.Armes;
import fr.rpg.model.collectible.Collectible;
import fr.rpg.model.collectible.Tenue;

/**
 * écran gérant l'affichage des messages PNJ et des choix de collectibles
 */

public class MsgScreen implements Screen, sujet{
	
	private RpgMain game;
	private OrthographicCamera camera;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private AtlasRegion window;
	private ScreenViewport viewport;
	private Font font;
	/**
	 * liste des observers concrets utilisés sur le menu principal
	 * permet de gérer les effets des inputs sur l'écran
	 */
	private ArrayList<concreteobserver> observers;
	private String msg;
	private Boolean choix;
	private Collectible c;
	
	/**
	 * constructeur privé 
	 * @param g
	 */
	private MsgScreen(RpgMain g) {
		this.game=g;
		this.camera = new OrthographicCamera(800,800);
		this.viewport = new ScreenViewport(camera);
		this.font = new Font();
		this.choix = false;
		this.msg=null;
		game.getManager().load("pack.png", Texture.class);
		this.window = this.game.getAtlas().findRegion("Interface/window");
		this.observers = new ArrayList<concreteobserver>();
		attach(new MsgP("P"));
		attach(new Msg1("1"));
		attach(new Msg2("2"));
		attach(new Msg3("3"));
		this.render(0.5f);	
	}
	
	/**
	 * constructeur utilisé si l'écran a pour but d'afficher un message
	 * @param g
	 * @param m
	 */
	public MsgScreen(RpgMain g, String m) {
		this(g);
		this.msg = m;
		this.choix = false;
	}
	
	/**
	 * constructeur si l'écran propose un choix au player entre différents collectibles (tenues, armes)
	 * @param g
	 * @param c
	 */
	public MsgScreen(RpgMain g, Collectible c) {
		this(g);
		this.choix = true;
		this.c = c;
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
		this.font.dispose();
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

	/**
	 * gestion de l'affichage
	 */
	private void draw() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		this.viewport.apply();
		this.game.getBatch().setProjectionMatrix(this.viewport.getCamera().combined);
		this.game.getBatch().begin();
		this.game.getBatch().draw(this.window,0,0,800,800);
		//on affiche le msg s'il existe
		if(this.msg!=null) {
			this.font.Getfont1().draw(this.game.getBatch(), msg, 50, 400);
		}
		if (this.c != null && choix) {
			//dans le cas où il s'agit d'un choix pour une tenue
			if (c instanceof Tenue) {
				this.font.Getfont1().draw(this.game.getBatch(),
						"Choix 1 : " + this.game.getPlayer().getTenue().getName(), 50, 400);
				this.font.Getfont1().draw(this.game.getBatch(),
						this.game.getPlayer().getTenue().getDescription(), 100, 350);
				this.font.Getfont1().draw(this.game.getBatch(), "Choix 2 : " + c.getName(), 50, 250);
				this.font.Getfont1().draw(this.game.getBatch(), c.getDescription(), 100, 200);
			}
			//dans le cas où il s'agit d'un choix pour une arme
			if(c instanceof Armes) {
				this.font.Getfont1().draw(this.game.getBatch(),
						"Choix 1 : " + this.game.getPlayer().getMd().getName() +" "+ this.game.getPlayer().getMg().getName(), 50, 500);
				this.font.Getfont1().draw(this.game.getBatch(),
						this.game.getPlayer().getMd().getDescription(), 100, 450);
				this.font.Getfont1().draw(this.game.getBatch(),this.game.getPlayer().getMg().getDescription(), 100, 400);
				if(((Armes) c).getMaindouble()) {
					this.font.Getfont1().draw(this.game.getBatch(), "Choix 2 : " + c.getName(), 50, 200);
					this.font.Getfont1().draw(this.game.getBatch(), c.getDescription(), 100, 150);
				}
				else {
					this.font.Getfont1().draw(this.game.getBatch(), "Choix 2 : remplacer l'arme " + this.game.getPlayer().getMd().getName()+ " par "+ c.getName(), 50, 300);
					this.font.Getfont1().draw(this.game.getBatch(), "actuellement : " + this.game.getPlayer().getMd().getDescription(), 100, 250);
					this.font.Getfont1().draw(this.game.getBatch(), "remplacer par : "+c.getDescription(), 100, 200);
					
					this.font.Getfont1().draw(this.game.getBatch(), "Choix 3 : remplacer l'arme " + this.game.getPlayer().getMg().getName()+ " par "+ c.getName(), 50, 150);
					this.font.Getfont1().draw(this.game.getBatch(), "actuellement : " + this.game.getPlayer().getMg().getDescription(), 100, 100);
					this.font.Getfont1().draw(this.game.getBatch(), "remplacer par : "+c.getDescription(), 100, 50);
				}
			}
		} 
		//on propose de retourner au jeu si il ne s'agit pas d'un choix à faire
		if(!choix) {
			this.font.Getfont1().draw(this.game.getBatch(), "Appuie sur P pour retourner au jeu", 100, 100);
		}
		this.game.getBatch().end();
	}
	
	/**
	 * gestion des inputs
	 */
	private void input() {
		if(!choix) {
			if (Gdx.input.isKeyPressed(Input.Keys.P)) {
				Event event = new Event(this.game,"MsgScreen",true,"P",game.getPlayer().getGamescreen());
				notify(event);
				this.dispose();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			Event event = new Event(this.game,"MsgScreen",true,"1",game.getPlayer().getGamescreen());
			notify(event);
			this.dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			Event event = new Event(this.game,"MsgScreen",true,"2",game.getPlayer().getGamescreen(),c);
			notify(event);
			this.dispose();
		}
		if(c instanceof Armes) {
			if(!(((Armes) c).getMaindouble())) {
				if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
					Event event = new Event(this.game,"MsgScreen",true,"3",game.getPlayer().getGamescreen(),c);
					notify(event);
					this.dispose();
				}
			}
		}
	}
}
