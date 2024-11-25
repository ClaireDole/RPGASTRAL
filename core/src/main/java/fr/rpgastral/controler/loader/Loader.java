package fr.rpgastral.controler.loader;

import com.badlogic.gdx.utils.Logger;
import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.view.LoadingScreen;

public class Loader {
//afficher un screen de loading
//implémenter start et stop(faire le dispose de l'écran de loading)
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
	public void stop() {
		loadingscreen.dispose();
	}
}
