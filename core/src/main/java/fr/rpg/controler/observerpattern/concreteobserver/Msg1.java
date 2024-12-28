package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

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
		if(event.compare(new Event(game,"MsgScreen", true, "1",null))) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}

}
