package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

/**
 * retour au gamescreen depuis le menu du jeu
 */
public class MenuP extends concreteobserver{
	
	public MenuP(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if (event.compare(new Event(game,"MenuScreen",true,"P"))) {
			game.setScreen(game.getGamescreen());
		}
	}

}

