package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.model.collectible.Armes;

public class Msg3 extends concreteobserver {

	public Msg3(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"MsgScreen", true, "3"))) {
			game.getTiledModelGame().getArmes().add(game.getGamescreen().getPlayer().getMg());
			game.getGamescreen().getPlayer().setMg((Armes) event.getC());
			event.getC().dispawn();
			game.setScreen(game.getGamescreen());
		}
	}
}
