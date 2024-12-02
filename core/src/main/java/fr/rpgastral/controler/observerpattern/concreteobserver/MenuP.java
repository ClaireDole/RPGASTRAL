package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;

public class MenuP implements Observer{

	private RpgMain g;
	
	public MenuP (RpgMain game){
		this.g = game;
	}
	
	@Override
	public void update(Event event) {
		if (event.compare(new Event("MenuScreen",true,"P"))) {
			g.setScreen(g.getGamescreen());
		}
	}

}
