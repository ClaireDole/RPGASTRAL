package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * lancement du gamescreen et donc du jeu
 */
public class mainmenuSPACE extends concreteobserver{

	public mainmenuSPACE(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if (event.compare(new Event(game,"MainMenuScreen",true,"SPACE",null))) {
			game.getGameScreens().forEach(screen->{if(screen.getMap().getProperties().get("name", String.class).equals("worldmap")) 
				game.setScreen(screen);
				game.getPlayer().setGamescreen(screen);
			});
		}
	}

}
