package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MenuScreen;

/**
 * input M sur un écran de type GameScreen
 * on lance un écran de menu du jeu 
 */
public class GameM extends concreteobserver{

	public GameM(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {

		RpgMain game = event.getGame();

		if (event.compareTo(new Event(game, "GameScreen",true,"M",null))==0) {
			game.setScreen(new MenuScreen(game));
		}
	}
}


