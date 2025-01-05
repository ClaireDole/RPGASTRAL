package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;

/**
 * Input flèche bas sur un écran de type GameScreen
 * gestion du mouvement du joueur lorsqu'il veut aller vers le bas
 * on vérifie s'il peut (sortie de la carte, obstacles)
 * puis on regarde s'il existe des collectibles à l'endroit où le joueur se déplace
 * si oui on gère la collecte du collectible
 * on déplace le joueur
 */
public class GameDown extends concreteobserver{

	public GameDown(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"GameScreen", true, "DOWN",null))==0) {
			Boolean valid = true;
			if (event.getEcran().getTiledmodel().getObstacles()!=null) {
				//obstacles
				for (int i = 0; i < event.getEcran().getTiledmodel().getObstacles().size(); i++) {
					int x = event.getEcran().getTiledmodel().getObstacles().get(i).Getx();
					int y = event.getEcran().getTiledmodel().getObstacles().get(i).Gety();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY() - 1) {
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
						if (x == game.getPlayer().getX() && y == game.getPlayer().getY()-1) {
							valid = false;
						}
					}
				}
			}
			//eau
			else if (game.getPlayer().getTenue() == null && event.getEcran().getTiledmodel().getEau()!=null) {
				for(int i = 0; i< event.getEcran().getTiledmodel().getEau().size(); i++ ) {
					int x = event.getEcran().getTiledmodel().getEau().get(i).Getx();
					int y = event.getEcran().getTiledmodel().getEau().get(i).Gety();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY()-1) {
						valid = false;
					}
				}
			}
			if (event.getEcran().getTiledmodel().getMonstres()!=null) {
				//présence de monstres
				for (int i = 0; i < event.getEcran().getTiledmodel().getMonstres().size(); i++) {
					int x = event.getEcran().getTiledmodel().getMonstres().get(i).getX();
					int y = event.getEcran().getTiledmodel().getMonstres().get(i).getY();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY() - 1) {
						valid = false;
					}
				} 
			}
			if (event.getEcran().getTiledmodel().getEhumans()!=null) {
				//présence d'ennemis humains
				for (int i = 0; i < event.getEcran().getTiledmodel().getEhumans().size(); i++) {
					int x = event.getEcran().getTiledmodel().getEhumans().get(i).getX();
					int y = event.getEcran().getTiledmodel().getEhumans().get(i).getY();
					if (x == game.getPlayer().getX() && y == game.getPlayer().getY() - 1) {
						valid = false;
					}
				} 
			}
			//déplacement validé, gestion des collectibles puis déplacement du joueur
			if(game.getPlayer().getY()-1 >= 0
					&& valid) {
				game.getPlayer().move(game.getPlayer().getX(),game.getPlayer().getY()-1);
			}
			if(!valid) {
				Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/bump.mp3"));
				sound.play();
			}
		}
	}

}

