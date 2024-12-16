package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

/**
 * retour au gamescreen depuis un écran de message
 */
public class MsgP extends concreteobserver{
	
	public MsgP(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"MsgScreen", true, "P"))) {
			game.setScreen(game.getGamescreen());
		}
	}

}
