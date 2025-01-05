package fr.rpg.controler.observerpattern.concreteobserver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.MsgScreen;

/**
 * input R sur un écran de type GameScreen
 * tentative de discussion avec un PNJ
 */
public class GameR extends concreteobserver{

	public GameR(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		Boolean valid = true;
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"GameScreen", true, "R",null))==0) {
			for(int i=0; i<event.getEcran().getTiledmodel().getPnjs().size();i++) {
				if(event.getEcran().getTiledmodel().getPnjs().get(i).getX() == game.getPlayer().getX()) {
					if(event.getEcran().getTiledmodel().getPnjs().get(i).getY() == game.getPlayer().getY() +1 |
							event.getEcran().getTiledmodel().getPnjs().get(i).getY() == game.getPlayer().getY() -1) {
						if(event.getEcran().getTiledmodel().getPnjs().get(i).getMessage() != null) {
							game.setScreen(new MsgScreen(game,event.getEcran().getTiledmodel().getPnjs().get(i).getMessage()));
							valid=false;
						}
					}
				}
				else if(event.getEcran().getTiledmodel().getPnjs().get(i).getY() == game.getPlayer().getY()) {
					if(event.getEcran().getTiledmodel().getPnjs().get(i).getX() == game.getPlayer().getX() +1 |
							event.getEcran().getTiledmodel().getPnjs().get(i).getX() == game.getPlayer().getX() -1) {
						if(event.getEcran().getTiledmodel().getPnjs().get(i).getMessage() != null) {
							game.setScreen(new MsgScreen(game,event.getEcran().getTiledmodel().getPnjs().get(i).getMessage()));
							valid=false;
						}
					}
				}
			}
			if(valid) {
				game.setScreen(new MsgScreen(game,"ça ne marche pas..."));
			}
			else {
				Music sound=Gdx.audio.newMusic(Gdx.files.internal("Son/talk.wav"));
				sound.play();
			}
		}
	}
}

