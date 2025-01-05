package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * input P sur un écran de type MsgScreen non choix
 * retour au dernier écran de type GameScreen
 */
public class MsgP extends concreteobserver{

	public MsgP(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"MsgScreen", true, "P",null))==0) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}

}
