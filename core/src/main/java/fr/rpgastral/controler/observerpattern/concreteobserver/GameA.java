package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

public class GameA extends concreteobserver {

	public GameA(String name) {
		super(name);
	}
	
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "A"))) {
			//cas de l'abscence d'arme
			if(game.getGamescreen().getPlayer().getMg() == null) {
				game.setScreen(new MsgScreen(game,"Il n'y a pas d'arme dans la main gauche."));
			}
			else {
				//presence d'un ennemi
				Boolean valid = false;
				for(int i=1; i<=game.getGamescreen().getPlayer().getMg().getPortee();i++) {
					for(int j = 0; j< game.getTiledModel().getMonstres().size(); j++ ) {
						int x = game.getTiledModel().getMonstres().get(j).getX();
						int y = game.getTiledModel().getMonstres().get(j).getY();
						if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()-i) {
							game.getGamescreen().getPlayer().attaquemg(game.getTiledModel().getMonstres().get(j));
							valid = true;
						}
						else if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()+i) {
							game.getGamescreen().getPlayer().attaquemg(game.getTiledModel().getMonstres().get(j));
							valid = true;
						}
						else if (x == game.getGamescreen().getPlayer().getX()-i && y == game.getGamescreen().getPlayer().getY()) {
							game.getGamescreen().getPlayer().attaquemg(game.getTiledModel().getMonstres().get(j));
							valid=true;
						}
						else if (x == game.getGamescreen().getPlayer().getX()+i && y == game.getGamescreen().getPlayer().getY()) {
							game.getGamescreen().getPlayer().attaquemg(game.getTiledModel().getMonstres().get(j));
							valid=true;
						}
						if(valid) {
							break;
						}
					}
				if(valid) {
						break;
					}
				}
				if(!valid) {
					game.setScreen(new MsgScreen(game,"Pas d'ennemis à portée"));
				}
			}
		}
	}
}
