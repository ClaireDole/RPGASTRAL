package fr.rpgastral.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

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
			if(game.getTiledModelGame().getVolcaniques() != null) {
				for(int i = 0; i< game.getTiledModelGame().getVolcaniques().size(); i++ ) {
					int x = game.getTiledModelGame().getVolcaniques().get(i).Getx();
					int y = game.getTiledModelGame().getVolcaniques().get(i).Gety();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						if(!(game.getGamescreen().getPlayer().getPV() - 0.25f <=0) && (game.getGamescreen().getPlayer().Gettenue()==null ||
								game.getGamescreen().getPlayer().Gettenue().getName() != "Broche d'Izanami")) {
							game.getGamescreen().getPlayer().takedamage(0.25f);;
							Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/takedamage.wav"));
							sound.setVolume(10f);
							sound.play();
						}
					}
				}
			}
			//gestion potion
			if(game.getTiledModelGame().getPotions() != null) {
				for(int i = 0; i< game.getTiledModelGame().getPotions().size(); i++ ) {
					int x = game.getTiledModelGame().getPotions().get(i).getX();
					int y = game.getTiledModelGame().getPotions().get(i).getY();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						game.getGamescreen().getPlayer().Collect(game.getTiledModelGame().getPotions().get(i));
						if(game.getTiledModelGame().getPotions().get(i).getDamage()>0) {
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
			if(game.getTiledModelGame().getTenues() != null) {
				for(int i = 0; i< game.getTiledModelGame().getTenues().size(); i++ ) {
					int x = game.getTiledModelGame().getTenues().get(i).getX();
					int y = game.getTiledModelGame().getTenues().get(i).getY();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/collect.wav"));
						sound.play();
						game.getGamescreen().getPlayer().Collect(game.getTiledModelGame().getTenues().get(i));
					}
				}
			}
			//gestion armes
			if(game.getTiledModelGame().getArmes() != null) {
				for(int i = 0; i< game.getTiledModelGame().getArmes().size(); i++ ) {
					int x = game.getTiledModelGame().getArmes().get(i).getX();
					int y = game.getTiledModelGame().getArmes().get(i).getY();
					if (x == game.getGamescreen().getPlayer().getX() && y == game.getGamescreen().getPlayer().getY()) {
						Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/collect.wav"));
						sound.play();
						game.getGamescreen().getPlayer().Collect(game.getTiledModelGame().getArmes().get(i));
					}
				}
			}
			//gestion monstres
			if(game.getTiledModelGame().getMonstres()!=null) {
				for(int i = 0; i< game.getTiledModelGame().getMonstres().size(); i++ ) {
					//gestion du cycle de mouvement des monstres volant
					if(game.getTiledModelGame().getMonstres().get(i).getName().equals("Volant")) {
						Monstre volant = game.getTiledModelGame().getMonstres().get(i);
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
					if(game.getTiledModelGame().getMonstres().get(i).getName().equals("Orc") || game.getTiledModelGame().getMonstres().get(i).getName().equals("Gobelin")
							|| game.getTiledModelGame().getMonstres().get(i).getName().equals("Volant")) {
						int x = game.getTiledModelGame().getMonstres().get(i).getX();
						int y = game.getTiledModelGame().getMonstres().get(i).getY();
						for(int j=1; j<= game.getTiledModelGame().getMonstres().get(i).getPortee();j++) {
							if(x+j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x-j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x==game.getGamescreen().getPlayer().getX() && y-j==game.getGamescreen().getPlayer().getY()
									||x==game.getGamescreen().getPlayer().getX() && y+j==game.getGamescreen().getPlayer().getY()) {
								Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/takedamage.wav"));
								sound.setVolume(10f);
								sound.play();
								game.getTiledModelGame().getMonstres().get(i).attaque(game.getGamescreen().getPlayer());
							}
						}
					}
				}
			}
			//gestion des ennemis humains
			if(game.getTiledModelGame().getEhumans()!=null) {
				for(int i = 0; i< game.getTiledModelGame().getEhumans().size(); i++ ) {
					EnemyHuman enemy = game.getTiledModelGame().getEhumans().get(i);
					//gestion du cycle de mouvement des voleurs
					if(enemy.getName().equals("Voleur")) {
						if(enemy.getPosition()==1) {
							Boolean valid = true;
							for(int j = 0; j< game.getTiledModelGame().getObstacles().size(); j++ ) {
								int x = game.getTiledModelGame().getObstacles().get(j).Getx();
								int y = game.getTiledModelGame().getObstacles().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModelGame().getEau().size(); j++ ) {
								int x = game.getTiledModelGame().getEau().get(j).Getx();
								int y = game.getTiledModelGame().getEau().get(j).Gety();
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
							for(int j = 0; j< game.getTiledModelGame().getObstacles().size(); j++ ) {
								int x = game.getTiledModelGame().getObstacles().get(j).Getx();
								int y = game.getTiledModelGame().getObstacles().get(j).Gety();
								if (x == enemy.getX()+1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModelGame().getEau().size(); j++ ) {
								int x = game.getTiledModelGame().getEau().get(j).Getx();
								int y = game.getTiledModelGame().getEau().get(j).Gety();
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
							for(int j = 0; j< game.getTiledModelGame().getObstacles().size(); j++ ) {
								int x = game.getTiledModelGame().getObstacles().get(j).Getx();
								int y = game.getTiledModelGame().getObstacles().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()-1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModelGame().getEau().size(); j++ ) {
								int x = game.getTiledModelGame().getEau().get(j).Getx();
								int y = game.getTiledModelGame().getEau().get(j).Gety();
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
							for(int j = 0; j< game.getTiledModelGame().getObstacles().size(); j++ ) {
								int x = game.getTiledModelGame().getObstacles().get(j).Getx();
								int y = game.getTiledModelGame().getObstacles().get(j).Gety();
								if (x == enemy.getX()-1 && y == enemy.getY()+1) {
									valid = false;
								}
							}
							for(int j = 0; j< game.getTiledModelGame().getEau().size(); j++ ) {
								int x = game.getTiledModelGame().getEau().get(j).Getx();
								int y = game.getTiledModelGame().getEau().get(j).Gety();
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
					if(game.getTiledModelGame().getEhumans().get(i).getName().equals("Chef") || game.getTiledModelGame().getEhumans().get(i).getName().equals("Brigand")
							|| game.getTiledModelGame().getEhumans().get(i).getName().equals("Voleur")) {
						int x = game.getTiledModelGame().getEhumans().get(i).getX();
						int y = game.getTiledModelGame().getEhumans().get(i).getY();
						for(int j=1; j<= game.getTiledModelGame().getEhumans().get(i).getPortee();j++) {
							if(x+j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x-j==game.getGamescreen().getPlayer().getX() && y==game.getGamescreen().getPlayer().getY()
									|| x==game.getGamescreen().getPlayer().getX() && y-j==game.getGamescreen().getPlayer().getY()
									||x==game.getGamescreen().getPlayer().getX() && y+j==game.getGamescreen().getPlayer().getY()) {
								Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/takedamage.wav"));
								sound.setVolume(10f);
								sound.play();
								game.getTiledModelGame().getEhumans().get(i).attaque(game.getGamescreen().getPlayer());
							}
						}
					}
				}
			}
		}
	}
}
