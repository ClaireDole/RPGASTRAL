package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.model.carte.Teleport;
import fr.rpg.model.entity.EnemyHuman;
import fr.rpg.model.entity.Monstre;

public class PlayerMove extends concreteobserver{

	public PlayerMove(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game, "Player", false, "move",null))) {
			//gestion effet terrain volcanique
			if(event.getEcran().getTiledmodel().getVolcaniques() != null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getVolcaniques().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getVolcaniques().get(i).Getx();
					int y = event.getEcran().getTiledmodel().getVolcaniques().get(i).Gety();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY()) {
						if(!(game.getPlayer().getPV() - 0.25f <=0) && (game.getPlayer().getTenue()==null ||
								game.getPlayer().getTenue().getName() != "Broche d'Izanami")) {
							game.getPlayer().takedamage(0.25f);;
							Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/takedamage.wav"));
							sound.setVolume(10f);
							sound.play();
						}
					}
				}
			}
			//gestion potion
			if(event.getEcran().getTiledmodel().getPotions() != null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getPotions().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getPotions().get(i).getX();
					int y = event.getEcran().getTiledmodel().getPotions().get(i).getY();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY()) {
						game.getPlayer().Collect(event.getEcran().getTiledmodel().getPotions().get(i));
						if(event.getEcran().getTiledmodel().getPotions().get(i).getDamage()>0) {
							Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/bonus.wav"));
							sound.play();
						}
						else {
							Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/malus.mp3"));
							sound.play();
						}
					}
				}
			}
			//gestion tenues
			if(event.getEcran().getTiledmodel().getTenues() != null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getTenues().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getTenues().get(i).getX();
					int y = event.getEcran().getTiledmodel().getTenues().get(i).getY();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY()) {
						Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/collect.wav"));
						sound.play();
						game.getPlayer().Collect(event.getEcran().getTiledmodel().getTenues().get(i));
					}
				}
			}
			//gestion armes
			if(event.getEcran().getTiledmodel().getArmes() != null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getArmes().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getArmes().get(i).getX();
					int y = event.getEcran().getTiledmodel().getArmes().get(i).getY();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY()) {
						Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/collect.wav"));
						sound.play();
						game.getPlayer().Collect(event.getEcran().getTiledmodel().getArmes().get(i));
					}
				}
			}
			//gestion monstres
			if(event.getEcran().getTiledmodel().getMonstres()!=null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getMonstres().size(); i++ ) {
					//gestion du cycle de mouvement des monstres volant
					if(event.getEcran().getTiledmodel().getMonstres().get(i).getName().equals("Volant")) {
						Monstre volant = event.getEcran().getTiledmodel().getMonstres().get(i);
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
					if(event.getEcran().getTiledmodel().getMonstres().get(i).getName().equals("Orc") || event.getEcran().getTiledmodel().getMonstres().get(i).getName().equals("Gobelin")
							|| event.getEcran().getTiledmodel().getMonstres().get(i).getName().equals("Volant")) {
						int x = event.getEcran().getTiledmodel().getMonstres().get(i).getX();
						int y = event.getEcran().getTiledmodel().getMonstres().get(i).getY();
						for(int j=1; j<= event.getEcran().getTiledmodel().getMonstres().get(i).getPortee();j++) {
							if(x+j==game.getPlayer().getX() && y==game.getPlayer().getY()
									|| x-j==game.getPlayer().getX() && y==game.getPlayer().getY()
									|| x==game.getPlayer().getX() && y-j==game.getPlayer().getY()
									||x==game.getPlayer().getX() && y+j==game.getPlayer().getY()) {
								Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/takedamage.wav"));
								sound.setVolume(10f);
								sound.play();
								event.getEcran().getTiledmodel().getMonstres().get(i).attaque(game.getPlayer());
							}
						}
					}
				}
			}
			//gestion des ennemis humains
			if(event.getEcran().getTiledmodel().getEhumans()!=null) {
				for(int i = 0; i<event.getEcran().getTiledmodel().getEhumans().size(); i++ ) {
					EnemyHuman enemy =event.getEcran().getTiledmodel().getEhumans().get(i);
					//gestion du cycle de mouvement des voleurs
					if(enemy.getName().equals("Voleur")) {
						if(enemy.getPosition()==1) {
							Boolean valid = true;
							for(int j = 0; j< event.getEcran().getTiledmodel().getObstacles().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getObstacles().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getObstacles().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							for(int j = 0; j< event.getEcran().getTiledmodel().getEau().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getEau().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getEau().get(j).Gety();
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
							for(int j = 0; j< event.getEcran().getTiledmodel().getObstacles().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getObstacles().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getObstacles().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							for(int j = 0; j< event.getEcran().getTiledmodel().getEau().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getEau().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getEau().get(j).Gety();
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
							for(int j = 0; j<event.getEcran().getTiledmodel().getObstacles().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getObstacles().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getObstacles().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							for(int j = 0; j< event.getEcran().getTiledmodel().getEau().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getEau().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getEau().get(j).Gety();
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
							for(int j = 0; j< event.getEcran().getTiledmodel().getObstacles().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getObstacles().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getObstacles().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							for(int j = 0; j< event.getEcran().getTiledmodel().getEau().size(); j++ ) {
								int x = event.getEcran().getTiledmodel().getEau().get(j).Getx();
								int y = event.getEcran().getTiledmodel().getEau().get(j).Gety();
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
					if(event.getEcran().getTiledmodel().getEhumans().get(i).getName().equals("Chef") ||event.getEcran().getTiledmodel().getEhumans().get(i).getName().equals("Brigand")
							||event.getEcran().getTiledmodel().getEhumans().get(i).getName().equals("Voleur")) {
						int x = event.getEcran().getTiledmodel().getEhumans().get(i).getX();
						int y = event.getEcran().getTiledmodel().getEhumans().get(i).getY();
						for(int j=1; j<= event.getEcran().getTiledmodel().getEhumans().get(i).getPortee();j++) {
							if(x+j==game.getPlayer().getX() && y==game.getPlayer().getY()
									|| x-j==game.getPlayer().getX() && y==game.getPlayer().getY()
									|| x==game.getPlayer().getX() && y-j==game.getPlayer().getY()
									||x==game.getPlayer().getX() && y+j==game.getPlayer().getY()) {
								Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/takedamage.wav"));
								sound.setVolume(10f);
								sound.play();
								event.getEcran().getTiledmodel().getEhumans().get(i).attaque(game.getPlayer());
							}
						}
					}
				}
			}
			//gestion des téléportations
			if(event.getEcran().getTiledmodel().getTeleports() != null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getTeleports().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getTeleports().get(i).getX();
					int y = event.getEcran().getTiledmodel().getTeleports().get(i).getY();
					Teleport t = event.getEcran().getTiledmodel().getTeleports().get(i);
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY() && !(t.getName().equals("Player"))) {
						game.getGameScreens().forEach(screen -> {
							if(screen.getMap().getProperties().get("name", String.class).equals(t.getName())) {
								game.getPlayer().setGamescreen(screen);
								game.setScreen(screen);
							}
						});
						game.getPlayer().setX(t.getDestX());
						game.getPlayer().setY(t.getDestY());
					}
				}
			}
		}
	}
}
