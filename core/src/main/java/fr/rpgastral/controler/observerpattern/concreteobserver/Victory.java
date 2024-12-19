package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.VictoryScreen;

/**
 * vérification de si le joueur à gagner en éliminant tous les monstres obligatoires
 */
public class Victory extends concreteobserver {
	
	public Victory(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"Player",false,"victory"))) {
			if(game.getTiledModel().getEnemys().isEmpty()) {
				game.setScreen(new VictoryScreen(game));
				game.getGamescreen().dispose();
			}
		}
	}
}
