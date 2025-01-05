package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MsgScreen;

/**
 * input Z sur un écran de type GameScreen
 * tentative de convaincre un ennemi
 */
public class GameZ extends concreteobserver{

	public GameZ(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compareTo(new Event(game,"GameScreen",true,"Z",null))==0) {
			if (event.getEcran().getTiledmodel().getEhumans()!=null) {
				//présence d'ennemis à convaincre
				for (int i = 0; i < event.getEcran().getTiledmodel().getEhumans().size(); i++) {
					int x = event.getEcran().getTiledmodel().getEhumans().get(i).getX();
					int y =event.getEcran().getTiledmodel().getEhumans().get(i).getY();
					int p = event.getEcran().getTiledmodel().getEhumans().get(i).getPortee();
					if (x == game.getPlayer().getX() + (p + 1)
							&& y == game.getPlayer().getY()) {
						valid = false;
						game.getPlayer().Convaincre(event.getEcran().getTiledmodel().getEhumans().get(i));
					} else if (x == game.getPlayer().getX() - (p + 1)
							&& y == game.getPlayer().getY()) {
						valid = false;
						game.getPlayer().Convaincre(event.getEcran().getTiledmodel().getEhumans().get(i));
					} else if (x == game.getPlayer().getX()
							&& y == game.getPlayer().getY() + (p + 1)) {
						valid = false;
						game.getPlayer().Convaincre(event.getEcran().getTiledmodel().getEhumans().get(i));
					} else if (x == game.getPlayer().getX()
							&& y == game.getPlayer().getY() - (p + 1)) {
						valid = false;
						game.getPlayer().Convaincre(event.getEcran().getTiledmodel().getEhumans().get(i));
					}
				} 
			}
			if(valid) {
				game.setScreen(new MsgScreen(game,"Il n'y a pas d'ennemis à convaincre aux environs."));
			}
		}
	}
}
