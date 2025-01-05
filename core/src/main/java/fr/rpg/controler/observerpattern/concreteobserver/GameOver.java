package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.DeathScreen;

/**
 * gestion de la mort du joueur
 */
public class GameOver extends concreteobserver {

	public GameOver(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game=event.getGame();
		if(event.compareTo(new Event(game,"Player",false,"gameover",null))==0) {
			game.getGameScreens().forEach(screen -> screen.getBackground().stop());
			game.setScreen(new DeathScreen(game));
		}
	}
}
