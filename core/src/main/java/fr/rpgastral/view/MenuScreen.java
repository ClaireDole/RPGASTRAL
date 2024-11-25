package fr.rpgastral.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import fr.rpgastral.controler.RpgMain;

public class MenuScreen implements Screen {
	final RpgMain game;
	private AtlasRegion region;
	private OrthographicCamera camera;
	private ScreenViewport viewport;
	private SpriteBatch menubatch;
	private Font font;
	
	public MenuScreen(final RpgMain game) {
		this.game = game;
		game.getManager().load("pack.png",Texture.class);
		region = game.getAtlas().findRegion("Interface/MainMenubackground");
		camera = new OrthographicCamera(800,800);
		viewport = new ScreenViewport(camera);
		menubatch = new SpriteBatch();
		font = new Font();
		}
	
	public void render(float delta) {
		ScreenUtils.clear(Color.CYAN);
		viewport.apply();
		menubatch.setProjectionMatrix(camera.combined);
		menubatch.begin();
		menubatch.draw(region, 100,100,654,640);
		font.Getfont1().draw(menubatch, "Retourner à l'écran de jeu --> P", 200,600);
		menubatch.end();

		if (Gdx.input.isKeyPressed(Keys.P)) {
			game.setScreen(game.getGamescreen());
			dispose();
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
		menubatch.dispose();
	}
}