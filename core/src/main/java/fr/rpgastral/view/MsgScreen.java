package fr.rpgastral.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.Msg1;
import fr.rpgastral.controler.observerpattern.concreteobserver.Msg2;
import fr.rpgastral.controler.observerpattern.concreteobserver.MsgP;
import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpgastral.model.collectible.Collectible;
import fr.rpgastral.model.collectible.Tenue;

public class MsgScreen implements Screen, sujet{

	private RpgMain game;
	private OrthographicCamera camera;
	private AtlasRegion window;
	private ScreenViewport viewport;
	private Font font;
	private ArrayList<concreteobserver> observers;
	private String msg;
	private Boolean choix;
	private Collectible c;
	
	public MsgScreen(RpgMain g) {
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
		this.render(0.5f);	
	}
	
	public MsgScreen(RpgMain g, String m) {
		this(g);
		this.msg = m;
		this.choix = false;
	}
	
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

	private void draw() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		this.viewport.apply();
		this.game.getBatch().setProjectionMatrix(this.viewport.getCamera().combined);
		this.game.getBatch().begin();
		this.game.getBatch().draw(this.window,0,0,800,800);
		if(this.msg!=null) {
			this.font.Getfont1().draw(this.game.getBatch(), msg, 50, 400);
		}
		if(this.c != null && choix) {
			if(c instanceof Tenue) {
				this.font.Getfont1().draw(this.game.getBatch(),"Choix 1 : " + this.game.getGamescreen().getPlayer().getTenue().getName(), 50, 400);
				this.font.Getfont1().draw(this.game.getBatch(),this.game.getGamescreen().getPlayer().getTenue().getDescription(), 100, 350);
				this.font.Getfont1().draw(this.game.getBatch(),"Choix 2 : " + c.getName(), 50, 250);
				this.font.Getfont1().draw(this.game.getBatch(),c.getDescription(), 100, 200);
			}
		}
		if(!choix) {
			this.font.Getfont1().draw(this.game.getBatch(), "Appuie sur P pour retourner au jeu", 100, 100);
		}
		this.game.getBatch().end();
	}
	
	private void input() {
		if(!choix) {
			if (Gdx.input.isKeyPressed(Input.Keys.P)) {
				Event event = new Event(this.game,"MsgScreen",true,"P");
				notify(event);
				this.dispose();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			Event event = new Event(this.game,"MsgScreen",true,"1");
			notify(event);
			this.dispose();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			Event event = new Event(this.game,"MsgScreen",true,"2",c);
			notify(event);
			this.dispose();
		}
	}
}
