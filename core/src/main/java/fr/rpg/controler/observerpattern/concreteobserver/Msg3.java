package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.model.collectible.Armes;

public class Msg3 extends concreteobserver {

	public Msg3(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"MsgScreen", true, "3",null))) {
			event.getEcran().getTiledmodel().getArmes().add(game.getPlayer().getMg());
			game.getPlayer().setMg((Armes) event.getC());
			event.getC().dispawn(event.getEcran().getTiledmodel());
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}
}
