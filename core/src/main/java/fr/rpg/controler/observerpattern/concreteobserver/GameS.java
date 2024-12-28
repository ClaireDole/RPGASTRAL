package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MsgScreen;

/**
 * si la touche R est pressée sur le GameScreen alors :
 * 		on vérifie s'il existe un PNJ de type elfe juste à côté de nous
 * 		si oui, on lance le soin du joueur
 * 		sinon, on prévient que cela ne fonctionne pas
 */
public class GameS extends concreteobserver{
	
	public GameS(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compare(new Event(game,"GameScreen", true, "S",null))) {
			for(int i=0; i<event.getEcran().getTiledmodel().getPnjs().size();i++) {
				if(event.getEcran().getTiledmodel().getPnjs().get(i).getX() == game.getPlayer().getX()) {
					if(event.getEcran().getTiledmodel().getPnjs().get(i).getY() == game.getPlayer().getY() +1 |
							event.getEcran().getTiledmodel().getPnjs().get(i).getY() == game.getPlayer().getY() -1) {
						if(event.getEcran().getTiledmodel().getPnjs().get(i).getRace().equals("Elfe")) {
							event.getEcran().getTiledmodel().getPnjs().get(i).Soin(game.getPlayer());
							valid=false;
							Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/heal.wav"));
							sound.play();
						}	
					}
				}
				else if(event.getEcran().getTiledmodel().getPnjs().get(i).getY() == game.getPlayer().getY()) {
					if(event.getEcran().getTiledmodel().getPnjs().get(i).getX() == game.getPlayer().getX() +1 |
							event.getEcran().getTiledmodel().getPnjs().get(i).getX() == game.getPlayer().getX() -1) {
						if(event.getEcran().getTiledmodel().getPnjs().get(i).getRace().equals("Elfe")) {
							event.getEcran().getTiledmodel().getPnjs().get(i).Soin(game.getPlayer());
							valid = false;
							Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/heal.wav"));
							sound.play();
					}
				}
			}
			}
			if(event.compare(new Event(game,"GameScreen", true, "S",null)) && valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
		}
	}
}

