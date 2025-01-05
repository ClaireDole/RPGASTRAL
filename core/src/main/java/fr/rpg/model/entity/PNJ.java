package fr.rpg.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpg.controler.RpgMain;

/**
 * Personnages Non Joueurs
 */
public class PNJ extends Entity{
	private String race;
	private String message;

	/**
	 * constructeur classique
	 * @param x abscisse
	 * @param y ordonnée
	 * @param race humain ou elfe
	 * @param g instance du jeu
	 */
	public PNJ(int x, int y, String race, RpgMain g) {
		super(x, y, g);
		this.race = race;
		if(race=="Elfe") {
			setTexture(g.getAtlas().findRegion("Game/elf"));
			setSprite(new Sprite(this.getTexture()));
		}
		else if(race=="Humain") {
			setTexture(g.getAtlas().findRegion("Game/PNJ"));
			setSprite(new Sprite(this.getTexture()));
		}
	}

	/**
	 * constructeur si le PNJ a un message
	 * @param x abscisse
	 * @param y ordonnée
	 * @param race humain ou elfe
	 * @param g instance du jeu
	 * @param msg dialogue du pnj
	 */
	public PNJ(int x, int y, String race, RpgMain g, String msg) {
		this(x, y, race, g);
		this.message = msg;
	}

	/**
	 * les elfes peuvent soigner le joueur s'il a besoin
	 * @param p joueur
	 */
	public void Soin(Player p){
		if(p.getTenue()!=null) {
			if(p.getTenue().getName()=="Bénédiction de Susanoo") {
				p.SetPV(5);
			}
			else if(p.getTenue().getName()=="Eclat de Tsukuyomi") {
				p.setMana(6);
			}
		}
		else {
			p.SetPV(3);
			p.setMana(3);
		}
	}

	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}