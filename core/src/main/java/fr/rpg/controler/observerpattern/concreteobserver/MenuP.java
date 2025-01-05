package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * input P depuis un écran de type MenuScreen
 * retourne sur l'écran de type GameScreen précédent
 */
public class MenuP extends concreteobserver{

	public MenuP(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if (event.compareTo(new Event(game,"MenuScreen",true,"P",null))==0) {
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}
}

