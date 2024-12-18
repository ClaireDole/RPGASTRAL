package fr.rpgastral.model.collectible;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;

/**
 * description des différentes armes du jeu et de leurs spécificités
 */
public class Armes extends Collectible{
	private Boolean maindouble;
	private int cout;
	private int portee;

	public Armes(int x, int y, String s, RpgMain g) {
		super(x,y,s,g);
		this.maindouble = false;
		this.cout = 0;
		if (s =="Bâton") {
			setDamage(1); 
			this.portee = 1;
			setTexture(getG().getAtlas().findRegion("Game/collectible/baton"));
			setDescription("Arme de base, dégâts : 1, portée : 1");
		}
		else if (s=="Epée") {
			setDamage(2);
			this.portee = 1;
			setTexture(getG().getAtlas().findRegion("Game/collectible/epee"));
			setDescription("Une épée de bonne qualitée, dégâts : 2, portée : 1");
		}
		else if (s=="Hâche") {
			setDamage(3);
			this.portee = 1;
			this.maindouble = true;
			setTexture(getG().getAtlas().findRegion("Game/collectible/hache"));
			setDescription("Une lourde hâche qu'ilf aut prendre à deux mains, dégâts : 3, portée : 1");
		}
		else if (s=="Sceptre") {
			setDamage(3);
			this.cout = 1;
			this.portee = 3;
			setTexture(getG().getAtlas().findRegion("Game/collectible/sceptre"));
			setDescription("L'arme d'un puissant mage, dégâts : 3, portée : 3");
		}
		else if (s=="Arc"){
			setDamage(2);
			this.portee = 2;
			setTexture(getG().getAtlas().findRegion("Game/collectible/arc"));
			setDescription("Une arme à distance, dégâts : 2, portée : 2");
		}
		setSprite(new Sprite(this.getTexture()));
	}
	
	/**
	 * suppression des armes dans le modèle de la carte
	 * pour plus d'informations @see TiledModel class
	 */
	@Override
	public void dispawn() {
		ArrayList<Armes> list = this.getG().getTiledModel().getArmes();
		list.remove(this);
		this.getG().getTiledModel().setArmes(list);
	}
	
	public Boolean getMaindouble() {
		return maindouble;
	}

	public void setMaindouble(Boolean maindouble) {
		this.maindouble = maindouble;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public int getPortee() {
		return portee;
	}

	public void setPortee(int portee) {
		this.portee = portee;
	}

}
