package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.DeathScreen;

/**
 * gestion de la mort du joueur
 */
public class GameOver extends concreteobserver {

	public GameOver(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game=event.getGame();
		if(event.compare(new Event(game,"Player",false,"gameover"))) {
			game.getGamescreen().getBackground().stop();
			game.setScreen(new DeathScreen(game));
		}
	}

}
