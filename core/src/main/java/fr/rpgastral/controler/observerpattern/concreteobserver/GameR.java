package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

/**
 * si la touche R est pressée sur le GameScreen alors :
 * 		on vérifie s'il existe un PNJ juste à côté de nous
 * 		si oui, on lance son dialogue
 * 		sinon, on prévient que cela ne fonctionne pas
 */
public class GameR extends concreteobserver{

	public GameR(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		Boolean valid = true;
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "R"))) {
			for(int i=0; i<game.getTiledModel().getPnjs().size();i++) {
				if(game.getTiledModel().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX()) {
					if(game.getTiledModel().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY() +1 |
							game.getTiledModel().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY() -1) {
						if(game.getTiledModel().getPnjs().get(i).getMessage() != null) {
							game.setScreen(new MsgScreen(game,game.getTiledModel().getPnjs().get(i).getMessage()));
							valid=false;
						}
					}
				}
				else if(game.getTiledModel().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY()) {
					if(game.getTiledModel().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX() +1 |
							game.getTiledModel().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX() -1) {
						if(game.getTiledModel().getPnjs().get(i).getMessage() != null) {
							game.setScreen(new MsgScreen(game,game.getTiledModel().getPnjs().get(i).getMessage()));
							valid=false;
						}
					}
				}
			}
			if(valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
		}
	}
}

