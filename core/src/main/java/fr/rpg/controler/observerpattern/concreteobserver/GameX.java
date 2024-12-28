package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.StatInventaire;

/**
 * quand la touche X est pressée sur le gamescreen, on lance un écran de statistiques et inventaires du joueur
 */
public class GameX extends concreteobserver {

	public GameX(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "X",null))) {
			game.setScreen(new StatInventaire(game));
		}
	}

}
