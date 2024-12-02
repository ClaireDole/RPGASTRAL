package fr.rpgastral.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.mainmenuSPACE;

public class MainMenuScreen implements Screen, sujet{

	final RpgMain game;
	private OrthographicCamera camera;
	private AtlasRegion region;
	private ScreenViewport viewport;
	private Font font;
	private ArrayList<Observer> list;

	public MainMenuScreen(final RpgMain game) {
		this.game = game;
		this.list = new ArrayList<Observer>();
		attach(new mainmenuSPACE(this.game));
		game.getManager().load("pack.png", Texture.class);
		region = game.getAtlas().findRegion("Interface/MainMenubackground");
		camera = new OrthographicCamera(600, 600);
		viewport = new ScreenViewport(camera);
		font = new Font();
	}

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
			Event event = new Event("MainMenuScreen",true,"SPACE");
			notify(event);
			this.dispose();
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
	}

	@Override
	public void attach(Observer o) {
		this.list.add(o);
		
	}
	@Override
	public void unattach(Observer o) {
		this.list.remove(o);
		
	}
	@Override
	public void notify(Event e) {
		this.list.forEach(observer -> observer.update(e));
		
	}
}
