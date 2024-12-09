package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

public class GameS extends concreteobserver{
	
	public GameS(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compare(new Event(game,"GameScreen", true, "S"))) {
			for(int i=0; i<game.getTiledModel().getPnj().size();i++) {
				if(game.getTiledModel().getPnj().get(i).Getx() == game.getGamescreen().getPlayer().Getx()) {
					if(game.getTiledModel().getPnj().get(i).Gety() == game.getGamescreen().getPlayer().Gety() +1 |
							game.getTiledModel().getPnj().get(i).Gety() == game.getGamescreen().getPlayer().Gety() -1) {
						if(game.getTiledModel().getPnj().get(i).getRace().equals("Elfe")) {
							game.getTiledModel().getPnj().get(i).Soin(game.getGamescreen().getPlayer());
							valid=false;
						}	
					}
				}
				else if(game.getTiledModel().getPnj().get(i).Gety() == game.getGamescreen().getPlayer().Gety()) {
					if(game.getTiledModel().getPnj().get(i).Getx() == game.getGamescreen().getPlayer().Getx() +1 |
							game.getTiledModel().getPnj().get(i).Getx() == game.getGamescreen().getPlayer().Getx() -1) {
						if(game.getTiledModel().getPnj().get(i).getRace().equals("Elfe")) {
							game.getTiledModel().getPnj().get(i).Soin(game.getGamescreen().getPlayer());
							valid = false;
					}
				}
			}
			}
			if(event.compare(new Event(game,"GameScreen", true, "S")) && valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
		}
	}
}

