package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;

/**
 * Personnages Non Joueurs
 */
public class PNJ extends Entity{
	private String race;
    private String message;
    private Sprite sprite;

    public PNJ(int x, int y, String race, RpgMain g) {
		super(x, y, g);
		this.race = race;
		if(race=="Elfe") {
			setTexture(g.getAtlas().findRegion("Game/elf"));
			this.sprite = new Sprite(this.getTexture());
		}
		else if(race=="Humain") {
			setTexture(g.getAtlas().findRegion("Game/PNJ"));
			this.sprite = new Sprite(this.getTexture());
		}
	}
    
    /**
     * constructeur si le PNJ a un message
     * @param x
     * @param y
     * @param race
     * @param g
     * @param msg
     */
    public PNJ(int x, int y, String race, RpgMain g, String msg) {
    	this(x, y, race, g);
		this.message = msg;
    }
    
    /**
     * les elfes peuvent soigner le joueur s'il a besoin
     * @param p
     */
    public void Soin(Player p){
    	if(p.Gettenue().getName()=="Bénédiction de Susanoo") {
    		p.SetPV(5);
    	}
    	else if(p.Gettenue().getName()=="Eclat de Tsukuyomi") {
    		p.SetMana(6);
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
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}