package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

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
		if(event.compare(new Event(game,"MsgScreen", true, "P",null))) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}

}
