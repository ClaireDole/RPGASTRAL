package fr.rpgastral.view;

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

public class MainMenuScreen implements Screen {

	final RpgMain game;
	private OrthographicCamera camera;
	private AtlasRegion region;
	private ScreenViewport viewport;
	private BitmapFont font;

	public MainMenuScreen(final RpgMain game) {
		this.game = game;
		game.getManager().load("pack.png", Texture.class);
		region = game.getAtlas().findRegion("Interface/MainMenubackground");
		camera = new OrthographicCamera(800, 800);
		viewport = new ScreenViewport(camera);
		font = new BitmapFont();
	}

	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		viewport.apply();
		game.getBatch().setProjectionMatrix(viewport.getCamera().combined);
		font.setColor(Color.BLACK);
		game.getBatch().begin();
		game.getBatch().draw(region, 200, 200, 500, 500);
		font.draw(game.getBatch(), "Bienvenue sur Tales of Liwa !! ", 200, 600, 350, 450, false);
		font.draw(game.getBatch(), "Un mélange d'action et de tactical", 350, 500);
		font.draw(game.getBatch(), "Appuyer sur la touche espace pour commencer", 300, 400);
		game.getBatch().end();

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			game.setScreen(game.getGamescreen());// set l'écran du jeu sur le gamescreen créé lors de la création de
													// l'occurence RPG
			this.dispose();
		}
	}

	public void resize(int width, int height) {
		this.viewport.update(width, height, true);
	}

	public void show() {
	}

	public void hide() {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
	}
}
