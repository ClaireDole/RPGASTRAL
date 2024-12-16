package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

/**
 * correspond au choix 1 lors d'un choix entre différents armes ou tenues
 */
public class Msg1 extends concreteobserver{

	public Msg1(String name) {
		super(name);
	}
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"MsgScreen", true, "1"))) {
			game.setScreen(game.getGamescreen());
		}
	}

}
