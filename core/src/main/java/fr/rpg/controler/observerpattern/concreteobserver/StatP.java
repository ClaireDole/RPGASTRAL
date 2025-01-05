package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * input P sur un écran de type StatInventaireScreen
 * retour au dernier écran de type GameScreen
 */
public class StatP extends concreteobserver{

	public StatP(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"StatInventaireScreen", true, "P",null))==0) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}

}
