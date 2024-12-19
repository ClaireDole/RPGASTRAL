package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

public class GameZ extends concreteobserver{

	public GameZ(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compare(new Event(game,"GameScreen",true,"Z"))) {
			if (game.getTiledModel().getEhumans()!=null) {
				//présence d'ennemis à convaincre
				for (int i = 0; i < game.getTiledModel().getEhumans().size(); i++) {
					int x = game.getTiledModel().getEhumans().get(i).getX();
					int y = game.getTiledModel().getEhumans().get(i).getY();
					int p = game.getTiledModel().getEhumans().get(i).getPortee();
					if (x == game.getGamescreen().getPlayer().getX() + (p + 1)
							&& y == game.getGamescreen().getPlayer().getY()) {
						valid = false;
						game.getGamescreen().getPlayer().Convaincre(game.getTiledModel().getEhumans().get(i));
					} else if (x == game.getGamescreen().getPlayer().getX() - (p + 1)
							&& y == game.getGamescreen().getPlayer().getY()) {
						valid = false;
						game.getGamescreen().getPlayer().Convaincre(game.getTiledModel().getEhumans().get(i));
					} else if (x == game.getGamescreen().getPlayer().getX()
							&& y == game.getGamescreen().getPlayer().getY() + (p + 1)) {
						valid = false;
						game.getGamescreen().getPlayer().Convaincre(game.getTiledModel().getEhumans().get(i));
					} else if (x == game.getGamescreen().getPlayer().getX()
							&& y == game.getGamescreen().getPlayer().getY() - (p + 1)) {
						valid = false;
						game.getGamescreen().getPlayer().Convaincre(game.getTiledModel().getEhumans().get(i));
					}
				} 
			}
			if(valid) {
				game.setScreen(new MsgScreen(game,"Il n'y a pas d'ennemis à convaincre aux environs."));
			}
		}
	}

}
