package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;

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
    		if(p.Gettenue().getName()=="Bénédiction de Susanoo") {
    			p.SetPV(5);
    		}
    		else if(p.Gettenue().getName()=="Eclat de Tsukuyomi") {
    			p.SetMana(6);
    		}
    	}
    	else {
    		p.SetPV(3);
    		p.SetMana(3);
    	}
    }
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}