package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MsgScreen;

public class GameE extends concreteobserver {

	public GameE(String name) {
		super(name);
	}
	
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"GameScreen", true, "E",null))) {
			//cas de l'abscence d'arme
			if(game.getPlayer().getMd() == null) {
				game.setScreen(new MsgScreen(game,"Il n'y a pas d'arme dans la main droite."));
			}
			//presence d'un ennemi
			else {
				Boolean valid = false;
				for(int i=1; i<=game.getPlayer().getMd().getPortee();i++) {
					//Monstres
					for(int j = 0; j< event.getEcran().getTiledmodel().getMonstres().size(); j++ ) {
						int x = event.getEcran().getTiledmodel().getMonstres().get(j).getX();
						int y = event.getEcran().getTiledmodel().getMonstres().get(j).getY();
						if (x == game.getPlayer().getX() && y == game.getPlayer().getY()-i) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getMonstres().get(j));
							valid = true;
						}
						else if (x == game.getPlayer().getX() && y == game.getPlayer().getY()+i) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getMonstres().get(j));
							valid = true;
						}
						else if (x == game.getPlayer().getX()-i && y == game.getPlayer().getY()) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getMonstres().get(j));
							valid=true;
						}
						else if (x == game.getPlayer().getX()+i && y == game.getPlayer().getY()) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getMonstres().get(j));
							valid=true;
						}
						if(valid) {
							Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/attack.wav"));
							sound.setVolume(2.25f);
							sound.play();
							break;
						}
					if(valid) {
						break;
					}
					}
					//EnemyHuman
					for(int j = 0; j< event.getEcran().getTiledmodel().getEhumans().size(); j++ ) {
						int x = event.getEcran().getTiledmodel().getEhumans().get(j).getX();
						int y = event.getEcran().getTiledmodel().getEhumans().get(j).getY();
						if (x == game.getPlayer().getX() && y == game.getPlayer().getY()-i) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getEhumans().get(j));
							valid = true;
						}
						else if (x == game.getPlayer().getX() && y == game.getPlayer().getY()+i) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getEhumans().get(j));
							valid = true;
						}
						else if (x == game.getPlayer().getX()-i && y == game.getPlayer().getY()) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getEhumans().get(j));
							valid=true;
						}
						else if (x == game.getPlayer().getX()+i && y == game.getPlayer().getY()) {
							game.getPlayer().attaquemd(event.getEcran().getTiledmodel().getEhumans().get(j));
							valid=true;
						}
						if(valid) {
							Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/attack.wav"));
							sound.setVolume(2.25f);
							sound.play();
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
