package fr.rpg.controler.observerpattern;

import fr.rpg.controler.RpgMain;
import fr.rpg.model.collectible.Collectible;
import fr.rpg.view.GameScreen;

/**
 * classe permettant l'aiguillage des input vers le concreteobserver correspondant
 * permet de mettre en place correctement l'organisation MVC du projet
 */
public class Event implements Comparable<Event>{
	private GameScreen ecran;
	private String screen;
	private Boolean keypressed;
	private String touche;
	private RpgMain game;
	private Collectible c;

	public Event(RpgMain game, String screen, Boolean keypressed, String touche, GameScreen ecran) {
		this.game = game;
		this.screen = screen;
		this.keypressed = keypressed;
		this.touche = touche;
		this.ecran = ecran;
	}
	public Event(RpgMain game, String screen, Boolean keypressed, String touche,GameScreen ecran, Collectible c) {
		this(game, screen, keypressed, touche, ecran);
		this.c = c;
	}

	/**
	 * implémentation de la méthode compareTo
	 */
	@Override
	public int compareTo(Event e) {
		if(this.screen==e.screen && this.keypressed==e.keypressed && this.touche == e.touche) {
			return 0;
		}
		else {
			return 1;
		}
	}

	/**
	 * @return the ecran
	 */
	public GameScreen getEcran() {
		return ecran;
	}
	/**
	 * @param ecran the ecran to set
	 */
	public void setEcran(GameScreen ecran) {
		this.ecran = ecran;
	}
	/**
	 * @return the screen
	 */
	public String getScreen() {
		return screen;
	}
	/**
	 * @param screen the screen to set
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}
	/**
	 * @return the keypressed
	 */
	public Boolean getKeypressed() {
		return keypressed;
	}
	/**
	 * @param keypressed the keypressed to set
	 */
	public void setKeypressed(Boolean keypressed) {
		this.keypressed = keypressed;
	}
	/**
	 * @return the touche
	 */
	public String getTouche() {
		return touche;
	}
	/**
	 * @param touche the touche to set
	 */
	public void setTouche(String touche) {
		this.touche = touche;
	}
	/**
	 * @return the game
	 */
	public RpgMain getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(RpgMain game) {
		this.game = game;
	}
	/**
	 * @return the c
	 */
	public Collectible getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(Collectible c) {
		this.c = c;
	}
}
