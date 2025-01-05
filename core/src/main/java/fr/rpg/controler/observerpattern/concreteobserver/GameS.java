package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MsgScreen;

/**
 * input S sur un écran de type GameScreen
 * tentative de soin du player 
 * valide uniquement si un ONJ de type elfe est juste à côté du player
 */
public class GameS extends concreteobserver{

	public GameS(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		Boolean valid = true;
		if(event.compareTo(new Event(game,"GameScreen", true, "S",null))==0) {
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
			if(event.compareTo(new Event(game,"GameScreen", true, "S",null))==0 && valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
		}
	}
}

