package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * permet de quitter le jeu
 */
public class Quit extends concreteobserver{

	public Quit(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {

		RpgMain game = event.getGame();

		if (event.compareTo(new Event(game,"MenuScreen",true,"Q",null))==0 | event.compareTo(new Event(game,"GameScreen",true,"Q",null)) ==0
				|event.compareTo(new Event(game,"MainMenuScreen",true,"Q",null))==0 | event.compareTo(new Event(game,"MsgScreen",true,"Q",null))==0) {

			game.dispose();
		}
	}

}

