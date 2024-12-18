package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

/**
 * si la touche R est pressée sur le GameScreen alors :
 * 		on vérifie s'il existe un PNJ de type elfe juste à côté de nous
 * 		si oui, on lance le soin du joueur
 * 		sinon, on prévient que cela ne fonctionne pas
 */
public class GameS extends concreteobserver{
	
	public GameS(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compare(new Event(game,"GameScreen", true, "S"))) {
			for(int i=0; i<game.getTiledModel().getPnjs().size();i++) {
				if(game.getTiledModel().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX()) {
					if(game.getTiledModel().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY() +1 |
							game.getTiledModel().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY() -1) {
						if(game.getTiledModel().getPnjs().get(i).getRace().equals("Elfe")) {
							game.getTiledModel().getPnjs().get(i).Soin(game.getGamescreen().getPlayer());
							valid=false;
						}	
					}
				}
				else if(game.getTiledModel().getPnjs().get(i).getY() == game.getGamescreen().getPlayer().getY()) {
					if(game.getTiledModel().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX() +1 |
							game.getTiledModel().getPnjs().get(i).getX() == game.getGamescreen().getPlayer().getX() -1) {
						if(game.getTiledModel().getPnjs().get(i).getRace().equals("Elfe")) {
							game.getTiledModel().getPnjs().get(i).Soin(game.getGamescreen().getPlayer());
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

