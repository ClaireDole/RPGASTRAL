package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;

/**
 * gestion du mouvement du joueur lorsqu'il veut aller vers le haut
 * on vérifie s'il peut (sortie de la carte, obstacles)
 * puis on regarde s'il existe des collectibles à l'endroit où le joueur se déplace
 * si oui on gère la collecte du collectible
 * on déplace le joueur
 */
public class GameUp extends concreteobserver{
	
	public GameUp(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "UP"))) {
			Boolean valid = true;
			for(int i = 0; i< game.getTiledModel().getObstacles().size(); i++ ) {
				int x = game.getTiledModel().getObstacles().get(i).Getx();
				int y = game.getTiledModel().getObstacles().get(i).Gety();
				if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
					valid = false;
				}
			}
			if(game.getGamescreen().getPlayer().getTenue() != null) {
				if(game.getGamescreen().getPlayer().getTenue().getName() != "Bénédiction de Susanoo") {
					for(int i = 0; i< game.getTiledModel().getEau().size(); i++ ) {
						int x = game.getTiledModel().getEau().get(i).Getx();
						int y = game.getTiledModel().getEau().get(i).Gety();
						if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
							valid = false;
						}
					}
				}
			}
			else if (game.getGamescreen().getPlayer().getTenue() == null) {
				for(int i = 0; i< game.getTiledModel().getEau().size(); i++ ) {
					int x = game.getTiledModel().getEau().get(i).Getx();
					int y = game.getTiledModel().getEau().get(i).Gety();
					if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
						valid = false;
					}
				}
			}
			if(game.getGamescreen().getPlayer().Gety()+1 < game.getTiledModel().getHeight() && valid) {
				//gestion des effets terrein volcanique
				if(game.getTiledModel().getVolcanique() != null) {
					for(int i = 0; i< game.getTiledModel().getVolcanique().size(); i++ ) {
						int x = game.getTiledModel().getVolcanique().get(i).Getx();
						int y = game.getTiledModel().getVolcanique().get(i).Gety();
						if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
							if(!(game.getGamescreen().getPlayer().GetPV() - 0.25f <=0) && (game.getGamescreen().getPlayer().Gettenue()==null ||
									game.getGamescreen().getPlayer().Gettenue().getName() != "Broche d'Izanami")) {
								game.getGamescreen().getPlayer().takedamage(0.25f);;
							}
						}
					}
				}
				//gestion des potions
				if(game.getTiledModel().getPotions() != null) {
					for(int i = 0; i< game.getTiledModel().getPotions().size(); i++ ) {
						int x = game.getTiledModel().getPotions().get(i).getX();
						int y = game.getTiledModel().getPotions().get(i).getY();
						if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
							game.getGamescreen().getPlayer().Collect(game.getTiledModel().getPotions().get(i));
						}
					}
				}
				//gestion des tenues
				if(game.getTiledModel().getTenues() != null) {
					for(int i = 0; i< game.getTiledModel().getTenues().size(); i++ ) {
						int x = game.getTiledModel().getTenues().get(i).getX();
						int y = game.getTiledModel().getTenues().get(i).getY();
						if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
							game.getGamescreen().getPlayer().Collect(game.getTiledModel().getTenues().get(i));
						}
					}
				}
				//gestion armes
				if(game.getTiledModel().getArmes() != null) {
					for(int i = 0; i< game.getTiledModel().getArmes().size(); i++ ) {
						int x = game.getTiledModel().getArmes().get(i).getX();
						int y = game.getTiledModel().getArmes().get(i).getY();
						if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+1) {
							game.getGamescreen().getPlayer().Collect(game.getTiledModel().getArmes().get(i));
						}
					}
				}
				game.getGamescreen().getPlayer().move(game.getGamescreen().getPlayer().Getx(),game.getGamescreen().getPlayer().Gety()+1);
			}
		}
		
	}

}
