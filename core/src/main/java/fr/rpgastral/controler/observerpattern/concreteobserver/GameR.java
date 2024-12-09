package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

public class GameR extends concreteobserver{

	public GameR(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		Boolean valid = true;
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "R"))) {
			for(int i=0; i<game.getTiledModel().getPnj().size();i++) {
				if(game.getTiledModel().getPnj().get(i).Getx() == game.getGamescreen().getPlayer().Getx()) {
					if(game.getTiledModel().getPnj().get(i).Gety() == game.getGamescreen().getPlayer().Gety() +1 |
							game.getTiledModel().getPnj().get(i).Gety() == game.getGamescreen().getPlayer().Gety() -1) {
						if(game.getTiledModel().getPnj().get(i).getMessage() != null) {
							game.setScreen(new MsgScreen(game,game.getTiledModel().getPnj().get(i).getMessage()));
							valid=false;
						}
						else {
							game.setScreen(new MsgScreen(game));
							valid=false;
						}
					}
				}
				else if(game.getTiledModel().getPnj().get(i).Gety() == game.getGamescreen().getPlayer().Gety()) {
					if(game.getTiledModel().getPnj().get(i).Getx() == game.getGamescreen().getPlayer().Getx() +1 |
							game.getTiledModel().getPnj().get(i).Getx() == game.getGamescreen().getPlayer().Getx() -1) {
						if(game.getTiledModel().getPnj().get(i).getMessage() != null) {
							game.setScreen(new MsgScreen(game,game.getTiledModel().getPnj().get(i).getMessage()));
							valid=false;
						}
						else {
							game.setScreen(new MsgScreen(game));
							valid=false;
						}
					}
				}
			}
			if(event.compare(new Event(game,"GameScreen", true, "R")) && valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
		}
	}
}

