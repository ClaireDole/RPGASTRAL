package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.StatInventaire;

public class GameX extends concreteobserver {

	public GameX(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "X"))) {
			game.setScreen(new StatInventaire(game));
		}
	}

}
