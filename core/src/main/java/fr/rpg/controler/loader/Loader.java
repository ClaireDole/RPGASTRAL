package fr.rpg.controler.loader;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;

import fr.rpg.controler.RpgMain;
import fr.rpg.view.LoadingScreen;

/**
 * classe permettant d'afficher au besoin une erreur si celle-ci arrive lors du parsing de la carte, 
 * du packing des assets ou de la création du jeu
 */
public class Loader implements Disposable {

	private Logger logger;
	private LoadingScreen loadingscreen;
	private RpgMain game;

	public Loader(final RpgMain game) {
		this.game = game;
		this.loadingscreen = new LoadingScreen(game);

	}

	public void start(){
		game.setScreen(loadingscreen);
	}
	public void print(String msg) {
		this.logger= new Logger(msg);
		this.logger.info(msg);
		this.loadingscreen.Setprint(true);
		this.loadingscreen.Setmsg(msg);	
	}

	@Override
	public void dispose() {
		loadingscreen.dispose();
	}
}
