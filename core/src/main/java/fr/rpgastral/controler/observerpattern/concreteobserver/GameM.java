package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MenuScreen;

/**
 * lorsque la touche M est frappée sur le GameScreen, on lance un écran de menu du jeu 
 */
public class GameM extends concreteobserver{
	
	public GameM(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		
		RpgMain game = event.getGame();
		
		if (event.compare(new Event(game, "GameScreen",true,"M"))) {
			game.setScreen(new MenuScreen(game));
		}
	}

}


