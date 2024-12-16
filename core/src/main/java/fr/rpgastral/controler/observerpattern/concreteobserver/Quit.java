package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

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
		
		if (event.compare(new Event(game,"MenuScreen",true,"Q")) | event.compare(new Event(game,"GameScreen",true,"Q")) 
			|event.compare(new Event(game,"MainMenuScreen",true,"Q")) | event.compare(new Event(game,"MsgScreen",true,"Q"))) {
			
			game.dispose();
		}
	}

}

