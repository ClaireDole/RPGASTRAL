package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.model.entity.EnemyHuman;
import fr.rpgastral.model.entity.Monstre;

public class PlayerMove extends concreteobserver{

	public PlayerMove(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game, "Player", false, "move"))) {
			//gestion effet terrain volcanique
			if(game.getTiledModel().getVolcaniques() != null) {
				for(int i = 0; i< game.getTiledModel().getVolcaniques().size(); i++ ) {
					int x = game.getTiledModel().getVolcaniques().get(i).Getx();
					int y = game.getTiledModel().getVolcaniques().get(i).Gety();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						if(!(game.getGamescreen().getPlayer().getPV() - 0.25f <=0) && (game.getGamescreen().getPlayer().Gettenue()==null ||
								game.getGamescreen().getPlayer().Gettenue().getName() != "Broche d'Izanami")) {
							game.getGamescreen().getPlayer().takedamage(0.25f);;
						}
					}
				}
			}
			//gestion potion
			if(game.getTiledModel().getPotions() != null) {
				for(int i = 0; i< game.getTiledModel().getPotions().size(); i++ ) {
					int x = game.getTiledModel().getPotions().get(i).getX();
					int y = game.getTiledModel().getPotions().get(i).getY();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						game.getGamescreen().getPlayer().Collect(game.getTiledModel().getPotions().get(i));
					}
				}
			}
			//gestion tenues
			if(game.getTiledModel().getTenues() != null) {
				for(int i = 0; i< game.getTiledModel().getTenues().size(); i++ ) {
					int x = game.getTiledModel().getTenues().get(i).getX();
					int y = game.getTiledModel().getTenues().get(i).getY();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						game.getGamescreen().getPlayer().Collect(game.getTiledModel().getTenues().get(i));
					}
				}
			}
			//gestion armes
			if(game.getTiledModel().getArmes() != null) {
				for(int i = 0; i< game.getTiledModel().getArmes().size(); i++ ) {
					int x = game.getTiledModel().getArmes().get(i).getX();
					int y = game.getTiledModel().getArmes().get(i).getY();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						game.getGamescreen().getPlayer().Collect(game.getTiledModel().getArmes().get(i));
					}
				}
			}
			//gestion monstres
			if(game.getTiledModel().getMonstres()!=null) {
				for(int i = 0; i< game.getTiledModel().getMonstres().size(); i++ ) {
					//gestion du cycle de mouvement des monstres volant
					if(game.getTiledModel().getMonstres().get(i).getName().equals("Volant")) {
						Monstre volant = game.getTiledModel().getMonstres().get(i);
						if(volant.getPosition()==1) {
							volant.setY(volant.getY()+1);
							volant.setPosition(2);
						}
						else if(volant.getPosition()==2) {
							volant.setX(volant.getX()+1);
							volant.setPosition(3);
						}
						else if(volant.getPosition()==3) {
							volant.setY(volant.getY()-1);
							volant.setPosition(4);
						}
						else if(volant.getPosition()==4) {
							volant.setX(volant.getX()-1);
							volant.setPosition(1);
						}
					}
					//gestion de l'attaque automatique du player par les orcs, gobelins et volants
					if(game.getTiledModel().getMonstres().get(i).getName().equals("Orc") || game.getTiledModel().getMonstres().get(i).getName().equals("Gobelin")
							|| game.getTiledModel().getMonstres().get(i).getName().equals("Volant")) {
						int x = game.getTiledModel().getMonstres().get(i).getX();
						int y = game.getTiledModel().getMonstres().get(i).getY();
						for(int j=1; j<= game.getTiledModel().getMonstres().get(i).getPortee();j++) {
							if(x+j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x-j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x==game.getGamescreen().getPlayer().getX() && y-j==game.getGamescreen().getPlayer().getY()
									||x==game.getGamescreen().getPlayer().getX() && y+j==game.getGamescreen().getPlayer().getY()) {
								game.getTiledModel().getMonstres().get(i).attaque(game.getGamescreen().getPlayer());
							}
						}
					}
				}
			}
			//gestion des ennemis humains
			if(game.getTiledModel().getEhumans()!=null) {
				for(int i = 0; i< game.getTiledModel().getEhumans().size(); i++ ) {
					EnemyHuman enemy = game.getTiledModel().getEhumans().get(i);
					//gestion du cycle de mouvement des voleurs
					if(enemy.getName().equals("Voleur")) {
						if(enemy.getPosition()==1) {
							Boolean valid = true;
							for(int j = 0; j< game.getTiledModel().getObstacles().size(); j++ ) {
								int x = game.getTiledModel().getObstacles().get(j).Getx();
								int y = game.getTiledModel().getObstacles().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModel().getEau().size(); j++ ) {
								int x = game.getTiledModel().getEau().get(j).Getx();
								int y = game.getTiledModel().getEau().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							if(valid) {
								enemy.setY(enemy.getY()+1);
								enemy.setX(enemy.getX()+1);
							}
							enemy.setPosition(2);
						}
						else if(enemy.getPosition()==2) {
							Boolean valid = true;
							for(int j = 0; j< game.getTiledModel().getObstacles().size(); j++ ) {
								int x = game.getTiledModel().getObstacles().get(j).Getx();
								int y = game.getTiledModel().getObstacles().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModel().getEau().size(); j++ ) {
								int x = game.getTiledModel().getEau().get(j).Getx();
								int y = game.getTiledModel().getEau().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							if(valid) {
								enemy.setY(enemy.getY()-1);
								enemy.setX(enemy.getX()+1);
							}
							enemy.setPosition(3);
						}
						else if(enemy.getPosition()==3) {
							Boolean valid = true;
							for(int j = 0; j< game.getTiledModel().getObstacles().size(); j++ ) {
								int x = game.getTiledModel().getObstacles().get(j).Getx();
								int y = game.getTiledModel().getObstacles().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModel().getEau().size(); j++ ) {
								int x = game.getTiledModel().getEau().get(j).Getx();
								int y = game.getTiledModel().getEau().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							if(valid) {
								enemy.setY(enemy.getY()-1);
								enemy.setX(enemy.getX()-1);
							}
							enemy.setPosition(4);
						}
						else if(enemy.getPosition()==4) {
							Boolean valid = true;
							for(int j = 0; j< game.getTiledModel().getObstacles().size(); j++ ) {
								int x = game.getTiledModel().getObstacles().get(j).Getx();
								int y = game.getTiledModel().getObstacles().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModel().getEau().size(); j++ ) {
								int x = game.getTiledModel().getEau().get(j).Getx();
								int y = game.getTiledModel().getEau().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							if(valid) {
								enemy.setY(enemy.getY()+1);
								enemy.setX(enemy.getX()-1);
							}
							enemy.setPosition(1);
						}
					}
					//gestion de l'attaque automatique du player
					if(game.getTiledModel().getEhumans().get(i).getName().equals("Chef") || game.getTiledModel().getEhumans().get(i).getName().equals("Brigand")
							|| game.getTiledModel().getEhumans().get(i).getName().equals("Voleur")) {
						int x = game.getTiledModel().getEhumans().get(i).getX();
						int y = game.getTiledModel().getEhumans().get(i).getY();
						for(int j=1; j<= game.getTiledModel().getEhumans().get(i).getPortee();j++) {
							if(x+j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x-j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x==game.getGamescreen().getPlayer().getX() && y-j==game.getGamescreen().getPlayer().getY()
									||x==game.getGamescreen().getPlayer().getX() && y+j==game.getGamescreen().getPlayer().getY()) {
								game.getTiledModel().getEhumans().get(i).attaque(game.getGamescreen().getPlayer());
							}
						}
					}
				}
			}
		}
	}
}
