package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

/**
 * gestion du mouvement du joueur lorsqu'il veut aller vers la droite
 * on vérifie s'il peut (sortie de la carte, obstacles)
 * puis on regarde s'il existe des collectibles à l'endroit où le joueur se déplace
 * si oui on gère la collecte du collectible
 * on déplace le joueur
 */
public class GameRight extends concreteobserver{
	
	public GameRight(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "RIGHT"))) {
			Boolean valid = true;
			for(int i = 0; i< game.getTiledModel().getObstacles().size(); i++ ) {
				int x = game.getTiledModel().getObstacles().get(i).Getx();
				int y = game.getTiledModel().getObstacles().get(i).Gety();
				if (x == game.getGamescreen().getPlayer().getX()+1 && y == game.getGamescreen().getPlayer().getY()) {
					valid = false;
				}
			}
			if(game.getGamescreen().getPlayer().getTenue() != null) {
				if(game.getGamescreen().getPlayer().getTenue().getName() != "Bénédiction de Susanoo") {
					for(int i = 0; i< game.getTiledModel().getEau().size(); i++ ) {
						int x = game.getTiledModel().getEau().get(i).Getx();
						int y = game.getTiledModel().getEau().get(i).Gety();
						if (x == game.getGamescreen().getPlayer().getX()+1 && y == game.getGamescreen().getPlayer().getY()) {
							valid = false;
						}
					}
				}
			}
			else if (game.getGamescreen().getPlayer().getTenue() == null) {
				for(int i = 0; i< game.getTiledModel().getEau().size(); i++ ) {
					int x = game.getTiledModel().getEau().get(i).Getx();
					int y = game.getTiledModel().getEau().get(i).Gety();
					if (x == game.getGamescreen().getPlayer().getX()+1 && y == game.getGamescreen().getPlayer().getY()) {
						valid = false;
					}
				}
			}
			for(int i = 0; i< game.getTiledModel().getMonstres().size(); i++ ) {
				int x = game.getTiledModel().getMonstres().get(i).getX();
				int y = game.getTiledModel().getMonstres().get(i).getY();
				if (x == game.getGamescreen().getPlayer().getX()+1 && y == game.getGamescreen().getPlayer().getY()) {
					valid = false;
				}
			}
			if(game.getGamescreen().getPlayer().getX() +1 <= game.getTiledModel().getWidth() && valid) {
				game.getGamescreen().getPlayer().move(game.getGamescreen().getPlayer().getX()+1,game.getGamescreen().getPlayer().getY());
			}
		}
		
	}

}

