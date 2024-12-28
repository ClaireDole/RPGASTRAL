package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * retour au gamescreen depuis l'écran d'inventaire et statistiques
 */
public class StatP extends concreteobserver{

	public StatP(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"StatInventaireScreen", true, "P",null))) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}

}
