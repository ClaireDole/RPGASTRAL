package fr.rpgastral.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.rpgastral.controler.RpgMain;

/**
 * écran de chargement qui permet l'affichage du problème s'il est rencontré lors de la création de la Main classe
 */
public class LoadingScreen implements Screen{

	private ScreenViewport viewport;
	private RpgMain game;
	private OrthographicCamera camera;
	private Font font;
	private Boolean print;
	private String msg;
	
	public LoadingScreen(final RpgMain game) {
		this.game=game;
		this.camera = new OrthographicCamera();
		viewport = new ScreenViewport(camera);
		font = new Font();
		print = false;
	}
	@Override
	public void show() {	
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.GOLD);
		viewport.apply();
		game.getBatch().begin();
		font.Getfont1().draw(game.getBatch(), ". . .", 290,300);
		if (print) {
			font.Getfont1().draw(game.getBatch(),this.msg,250,100);
		}
		game.getBatch().end();
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
	}
	
	public void Setprint(Boolean a) {
		this.print = a;
	}
	public void Setmsg(String m) {
		this.msg = m;
	}

}
