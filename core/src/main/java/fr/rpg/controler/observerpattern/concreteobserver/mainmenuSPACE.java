package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * input barre espace sur un écran de type MainMenuScreen
 * donne accès à un écran de type GameScreen
 */
public class mainmenuSPACE extends concreteobserver{

	public mainmenuSPACE(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if (event.compareTo(new Event(game,"MainMenuScreen",true,"SPACE",null))==0) {
			game.getGameScreens().forEach(screen->{if(screen.getMap().getProperties().get("name", String.class).equals("worldmap")) 
				game.setScreen(screen);
			game.getPlayer().setGamescreen(screen);
			});
		}
	}

}
