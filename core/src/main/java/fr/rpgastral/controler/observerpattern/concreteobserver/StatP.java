package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

public class StatP extends concreteobserver{

	public StatP(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"StatInventaireScreen", true, "P"))) {
			game.setScreen(game.getGamescreen());
		}
	}

}
