package fr.rpgastral.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

/**
 * si la touche R est pressée sur le GameScreen alors :
 * 		on vérifie s'il existe un PNJ de type elfe juste à côté de nous
 * 		si oui, on lance le soin du joueur
 * 		sinon, on prévient que cela ne fonctionne pas
 */
public class GameS extends concreteobserver{
	
	public GameS(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compare(new Event(game,"GameScreen", true, "S"))) {
			for(int i=0; i<game.getTiledModelGame().getPnjs().size();i++) {
				if(game.getTiledModelGame().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX()) {
					if(game.getTiledModelGame().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY() +1 |
							game.getTiledModelGame().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY() -1) {
						if(game.getTiledModelGame().getPnjs().get(i).getRace().equals("Elfe")) {
							game.getTiledModelGame().getPnjs().get(i).Soin(game.getGamescreen().getPlayer());
							valid=false;
							Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/heal.wav"));
							sound.play();
						}	
					}
				}
				else if(game.getTiledModelGame().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY()) {
					if(game.getTiledModelGame().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX() +1 |
							game.getTiledModelGame().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX() -1) {
						if(game.getTiledModelGame().getPnjs().get(i).getRace().equals("Elfe")) {
							game.getTiledModelGame().getPnjs().get(i).Soin(game.getGamescreen().getPlayer());
							valid = false;
							Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/heal.wav"));
							sound.play();
					}
				}
			}
			}
			if(event.compare(new Event(game,"GameScreen", true, "S")) && valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
		}
	}
}

