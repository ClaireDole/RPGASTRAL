package fr.rpgastral.controler.observerpattern;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.collectible.Collectible;

/**
 * classe permettant l'aiguillage des input vers le concreteobserver correspondant
 * permet de mettre en place correctement l'organisation MVC du projet
 */
public class Event {
	private String screen;
	private Boolean keypressed;
	private String touche;
	private RpgMain game;
	private Collectible c;
	
	public Event(RpgMain game, String screen, Boolean keypressed, String touche) {
		this.game = game;
		this.screen = screen;
		this.keypressed = keypressed;
		this.touche = touche;
	}
	public Event(RpgMain game, String screen, Boolean keypressed, String touche, Collectible c) {
		this(game, screen, keypressed, touche);
		this.c = c;
	}
	
	public Boolean compare(Event e) {
		if(this.screen==e.screen && this.keypressed==e.keypressed && this.touche == e.touche) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public Boolean getKeypressed() {
		return keypressed;
	}

	public void setKeypressed(Boolean keypressed) {
		this.keypressed = keypressed;
	}

	public String getTouche() {
		return touche;
	}

	public void setTouche(String touche) {
		this.touche = touche;
	}

	public RpgMain getGame() {
		return game;
	}

	public void setGame(RpgMain game) {
		this.game = game;
	}
	public Collectible getC() {
		return c;
	}
	public void setC(Collectible c) {
		this.c = c;
	}

}
