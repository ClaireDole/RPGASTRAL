package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

/**
 * lancement du gamescreen et donc du jeu
 */
public class mainmenuSPACE extends concreteobserver{

	public mainmenuSPACE(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if (event.compare(new Event(game,"MainMenuScreen",true,"SPACE"))) {
			game.setScreen(game.getGamescreen());
		}
	}

}
