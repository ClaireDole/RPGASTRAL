package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

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
		if (event.compare(new Event(game,"MenuScreen",true,"P",null))) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}

}

