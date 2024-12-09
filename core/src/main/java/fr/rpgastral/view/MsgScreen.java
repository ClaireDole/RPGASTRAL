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
import fr.rpgastral.controler.observerpattern.concreteobserver.MsgP;
import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;

public class MsgScreen implements Screen, sujet{

	private RpgMain game;
	private OrthographicCamera camera;
	private AtlasRegion window;
	private ScreenViewport viewport;
	private Font font;
	private ArrayList<concreteobserver> observers;
	private String msg;
	
	public MsgScreen(RpgMain g) {
		this.game=g;
		this.camera = new OrthographicCamera(800,800);
		this.viewport = new ScreenViewport(camera);
		this.font = new Font();
		game.getManager().load("pack.png", Texture.class);
		this.window = this.game.getAtlas().findRegion("Interface/window");
		this.observers = new ArrayList<concreteobserver>();
		attach(new MsgP("P"));
		this.render(0.5f);	
	}
	
	public MsgScreen(RpgMain g, String m) {
		this(g);
		this.msg = m;
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
		this.font.Getfont1().draw(this.game.getBatch(), msg, 50, 400);
		this.font.Getfont1().draw(this.game.getBatch(), "Appuie sur P pour retourner au jeu", 100, 200);
		this.game.getBatch().end();
	}
	
	private void input() {
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			Event event = new Event(this.game,"MsgScreen",true,"P");
			notify(event);
			this.dispose();
		}
	}
}
