package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.view.MsgScreen;

public class GameE extends concreteobserver {

	public GameE(String name) {
		super(name);
	}
	
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "E"))) {
			//cas de l'abscence d'arme
			if(game.getGamescreen().getPlayer().getMd() == null) {
				game.setScreen(new MsgScreen(game,"Il n'y a pas d'arme dans la main droite."));
			}
			//presence d'un ennemi
			else {
				Boolean valid = false;
				for(int i=1; i<=game.getGamescreen().getPlayer().getMd().getPortee();i++) {
					//Monstres
					for(int j = 0; j< game.getTiledModel().getMonstres().size(); j++ ) {
						int x = game.getTiledModel().getMonstres().get(j).getX();
						int y = game.getTiledModel().getMonstres().get(j).getY();
						if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()-i) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getMonstres().get(j));
							valid = true;
						}
						else if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()+i) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getMonstres().get(j));
							valid = true;
						}
						else if (x == game.getGamescreen().getPlayer().getX()-i && y == game.getGamescreen().getPlayer().getY()) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getMonstres().get(j));
							valid=true;
						}
						else if (x == game.getGamescreen().getPlayer().getX()+i && y == game.getGamescreen().getPlayer().getY()) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getMonstres().get(j));
							valid=true;
						}
						if(valid) {
							break;
						}
					if(valid) {
						break;
					}
					}
					//EnemyHuman
					for(int j = 0; j< game.getTiledModel().getEhumans().size(); j++ ) {
						int x = game.getTiledModel().getEhumans().get(j).getX();
						int y = game.getTiledModel().getEhumans().get(j).getY();
						if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()-i) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getEhumans().get(j));
							valid = true;
						}
						else if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()+i) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getEhumans().get(j));
							valid = true;
						}
						else if (x == game.getGamescreen().getPlayer().getX()-i && y == game.getGamescreen().getPlayer().getY()) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getEhumans().get(j));
							valid=true;
						}
						else if (x == game.getGamescreen().getPlayer().getX()+i && y == game.getGamescreen().getPlayer().getY()) {
							game.getGamescreen().getPlayer().attaquemd(game.getTiledModel().getEhumans().get(j));
							valid=true;
						}
						if(valid) {
							break;
						}
					if(valid) {
						break;
					}
					}
				}
				if(!valid) {
					game.setScreen(new MsgScreen(game,"Pas d'ennemis à portée"));
				}
			}
		}
	}
}
