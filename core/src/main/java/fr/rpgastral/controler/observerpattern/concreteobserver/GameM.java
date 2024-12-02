package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.view.MenuScreen;

public class GameM implements Observer{

	private RpgMain g;
	
	public GameM (RpgMain game){
		this.g = game;
	}
	
	@Override
	public void update(Event event) {
		if (event.compare(new Event("GameScreen",true,"M"))) {
			g.setScreen(new MenuScreen(g));
		}
	}

}


