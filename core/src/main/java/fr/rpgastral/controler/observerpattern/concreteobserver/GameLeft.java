package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;

public class GameLeft implements Observer{

	private RpgMain g;
	
	public GameLeft(RpgMain game) {
		this.g = game;
	}
	
	@Override
	public void update(Event event) {
		if(event.compare(new Event("GameScreen", true, "LEFT"))) {
			Boolean valid = true;
			for(int i = 0; i< g.getTiledModel().getObstacles().size(); i++ ) {
				int x = g.getTiledModel().getObstacles().get(i).Getx();
				int y = g.getTiledModel().getObstacles().get(i).Gety();
				if (x == g.getGamescreen().getPlayer().Getx()-1 && y == g.getGamescreen().getPlayer().Gety()) {
					valid = false;
				}
			}
			if(g.getGamescreen().getPlayer().getTenue() != null) {
				if(g.getGamescreen().getPlayer().getTenue().Getname() != "Bénédiction de Susanoo") {
					for(int i = 0; i< g.getTiledModel().getEau().size(); i++ ) {
						int x = g.getTiledModel().getEau().get(i).Getx();
						int y = g.getTiledModel().getEau().get(i).Gety();
						if (x == g.getGamescreen().getPlayer().Getx()-1 && y == g.getGamescreen().getPlayer().Gety()) {
							valid = false;
						}
					}
				}
			}
			else if (g.getGamescreen().getPlayer().getTenue() == null) {
				for(int i = 0; i< g.getTiledModel().getEau().size(); i++ ) {
					int x = g.getTiledModel().getEau().get(i).Getx();
					int y = g.getTiledModel().getEau().get(i).Gety();
					if (x == g.getGamescreen().getPlayer().Getx()-1 && y == g.getGamescreen().getPlayer().Gety()) {
						valid = false;
					}
				}
			}
			if(g.getGamescreen().getPlayer().Getx() -1 >= 0 && valid) {
				g.getGamescreen().getPlayer().move(g.getGamescreen().getPlayer().Getx()-1,g.getGamescreen().getPlayer().Gety());
			}
		}
		
	}

}
