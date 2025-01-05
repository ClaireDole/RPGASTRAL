package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MsgScreen;

/**
 * input A sur un écran de type GameScreen
 * correspond à une attaquemg du player
 */
public class GameA extends concreteobserver {

	public GameA(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"GameScreen", true, "A", null))==0) {
			//cas de l'abscence d'arme
			if(game.getPlayer().getMg() == null) {
				game.setScreen(new MsgScreen(game,"Il n'y a pas d'arme dans la main gauche."));
			}
			else {
				//Monstre
				Boolean valid = false;
				for(int i=1; i<=game.getPlayer().getMg().getPortee();i++) {
					if (event.getEcran().getTiledmodel().getMonstres()!=null) {
						for (int j = 0; j < event.getEcran().getTiledmodel().getMonstres().size(); j++) {
							int x = event.getEcran().getTiledmodel().getMonstres().get(j).getX();
							int y = event.getEcran().getTiledmodel().getMonstres().get(j).getY();
							if (x == game.getPlayer().getX() && y == game.getPlayer().getY() - i) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getMonstres().get(j));
								valid = true;
							} else if (x == game.getPlayer().getX() && y == game.getPlayer().getY() + i) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getMonstres().get(j));
								valid = true;
							} else if (x == game.getPlayer().getX() - i && y == game.getPlayer().getY()) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getMonstres().get(j));
								valid = true;
							} else if (x == game.getPlayer().getX() + i && y == game.getPlayer().getY()) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getMonstres().get(j));
								valid = true;
							}
							if (valid) {
								Music sound = Gdx.audio.newMusic(Gdx.files.internal("Son/attack.wav"));
								sound.setVolume(2.25f);
								sound.play();
								break;
							}
						} 
					}
					if(valid) {
						break;
					}
					if (event.getEcran().getTiledmodel().getEhumans()!=null) {
						//EnemyHuman
						for (int j = 0; j < event.getEcran().getTiledmodel().getEhumans().size(); j++) {
							int x = event.getEcran().getTiledmodel().getEhumans().get(j).getX();
							int y = event.getEcran().getTiledmodel().getEhumans().get(j).getY();
							if (x == game.getPlayer().getX() && y == game.getPlayer().getY() - i) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getEhumans().get(j));
								valid = true;
							} else if (x == game.getPlayer().getX() && y == game.getPlayer().getY() + i) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getEhumans().get(j));
								valid = true;
							} else if (x == game.getPlayer().getX() - i && y == game.getPlayer().getY()) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getEhumans().get(j));
								valid = true;
							} else if (x == game.getPlayer().getX() + i && y == game.getPlayer().getY()) {
								game.getPlayer().attaquemg(event.getEcran().getTiledmodel().getEhumans().get(j));
								valid = true;
							}
							if (valid) {
								Music sound = Gdx.audio.newMusic(Gdx.files.internal("Son/attack.wav"));
								sound.setVolume(2.25f);
								sound.play();
								break;
							}
							if (valid) {
								break;
							}
						} 
					}
				}
				if(!valid) {
					game.setScreen(new MsgScreen(game,"Pas d'ennemis à portée"));
				}
			}
			for(int i=0;i<game.getTiledModels().size();i++) {
				if(game.getTiledModels().get(i).getMap().getProperties().get("name",String.class).equals(event.getEcran().getMap().getProperties().get("name",String.class))) {
					game.getTiledModels().remove(game.getTiledModels().get(i));
					game.getTiledModels().add(event.getEcran().getTiledmodel());
				}
			}
		}
	}
}
