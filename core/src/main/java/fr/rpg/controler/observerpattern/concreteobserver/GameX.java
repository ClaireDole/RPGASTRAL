package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.StatInventaire;

/**
 * input X sur un écran de type GameScreen
 * création d'un écran statistique et inventaire du player
 */
public class GameX extends concreteobserver {

	public GameX(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"GameScreen", true, "X",null))==0) {
			game.setScreen(new StatInventaire(game));
		}
	}

}
