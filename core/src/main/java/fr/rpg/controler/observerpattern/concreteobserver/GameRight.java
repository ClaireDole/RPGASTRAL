package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * input flèche droite sur un écran de type GameScreen
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
		if(event.compareTo(new Event(game,"GameScreen", true, "RIGHT",null))==0) {
			Boolean valid = true;
			//obstacles
			if (event.getEcran().getTiledmodel().getObstacles()!=null) {
				for (int i = 0; i < event.getEcran().getTiledmodel().getObstacles().size(); i++) {
					int x = event.getEcran().getTiledmodel().getObstacles().get(i).Getx();
					int y = event.getEcran().getTiledmodel().getObstacles().get(i).Gety();
					if (x == game.getPlayer().getX() + 1 && y == game.getPlayer().getY()) {
						valid = false;
					}
				} 
			}
			//eau
			if(game.getPlayer().getTenue() != null && event.getEcran().getTiledmodel().getEau()!=null) {
				if(game.getPlayer().getTenue().getName() != "Bénédiction de Susanoo") {
					for(int i = 0; i< event.getEcran().getTiledmodel().getEau().size(); i++ ) {
						int x = event.getEcran().getTiledmodel().getEau().get(i).Getx();
						int y = event.getEcran().getTiledmodel().getEau().get(i).Gety();
						if (x == game.getPlayer().getX()+1 && y == game.getPlayer().getY()) {
							valid = false;
						}
					}
				}
			}
			else if (game.getPlayer().getTenue() == null && event.getEcran().getTiledmodel().getEau()!=null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getEau().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getEau().get(i).Getx();
					int y = event.getEcran().getTiledmodel().getEau().get(i).Gety();
					if (x == game.getPlayer().getX()+1 && y == game.getPlayer().getY()) {
						valid = false;
					}
				}
			}
			if (event.getEcran().getTiledmodel().getMonstres()!=null) {
				//Monstres
				for (int i = 0; i < event.getEcran().getTiledmodel().getMonstres().size(); i++) {
					int x = event.getEcran().getTiledmodel().getMonstres().get(i).getX();
					int y = event.getEcran().getTiledmodel().getMonstres().get(i).getY();
					if (x == game.getPlayer().getX() + 1 && y == game.getPlayer().getY()) {
						valid = false;
					}
				} 
			}
			if (event.getEcran().getTiledmodel().getEhumans()!=null) {
				//présence d'ennemis humains
				for (int i = 0; i < event.getEcran().getTiledmodel().getEhumans().size(); i++) {
					int x = event.getEcran().getTiledmodel().getEhumans().get(i).getX();
					int y = event.getEcran().getTiledmodel().getEhumans().get(i).getY();
					if (x == game.getPlayer().getX() + 1 && y == game.getPlayer().getY()) {
						valid = false;
					}
				} 
			}
			if(game.getPlayer().getX() +1 < event.getEcran().getTiledmodel().getWidth() && valid) {
				game.getPlayer().move(game.getPlayer().getX()+1,game.getPlayer().getY());
			}
			if(!valid) {
				Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/bump.mp3"));
				sound.play();
			}
		}

	}

}

