package fr.rpg.view;

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

/**
 * écran de victoire lancé après l'élimination de tous les ennemis obligatoires
 */

public class VictoryScreen implements Screen {

	/**
	 * instance de la classe main du jeu
	 */
	final RpgMain game;
	private OrthographicCamera camera;
	/**
	 * region de l'image utilisée lors de l'affichage
	 */
	private AtlasRegion region;
	private ScreenViewport viewport;
	private Font font;
	private SpriteBatch batch;
	private Sound sound;

	/**
	 * constructeur, on passe en paramètre l'instance unique de la classe Main afin de récupérer des informations
	 * @param game
	 */
	public VictoryScreen(final RpgMain game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.sound=Gdx.audio.newSound(Gdx.files.internal("Son/victory.mp3"));
		this.sound.play();
		game.getManager().load("pack.png", Texture.class);
		region = game.getAtlas().findRegion("Interface/laurier");
		camera = new OrthographicCamera(800, 800);
		viewport = new ScreenViewport(camera);
		font = new Font();
		this.render(0.5f);	
	}
	@Override
	public void show() {
	}

	/**
	 * gestion de l'affichage
	 */
	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		batch.draw(region, 200,350,400,450);
		font.Getfont2().draw(batch, "Bravo, vous avez terminé le jeu !", 300,300);
		font.Getfont2().draw(batch, "Vous pouvez fermer la fenêtre pour quitter.", 250,200);
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
		this.sound.dispose();
	}

}
