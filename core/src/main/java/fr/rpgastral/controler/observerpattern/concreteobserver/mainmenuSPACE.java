package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;

public class mainmenuSPACE implements Observer{

	private RpgMain g;
	
	public mainmenuSPACE (RpgMain game){
		this.g = game;
	}
	
	@Override
	public void update(Event event) {
		if (event.compare(new Event("MainMenuScreen",true,"SPACE"))) {
			g.setScreen(g.getGamescreen());
		}
	}

}
