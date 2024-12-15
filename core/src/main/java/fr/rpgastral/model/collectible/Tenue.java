package fr.rpgastral.model.collectible;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.entity.Player;

public class Tenue extends Collectible{
	//Bénédiction de Susanoo --> marche sur l'eau et augmente PV
	//Apparat du kitsune --> convainct à 100% de réussite
	//Protection d'Amaterasu --> protège des malus
	//Armure de Bishamonten --> augmente l'attaque
	//Eclat de Tsukuyomi --> augmente le mana
	//Broche d'Izanami --> annule les effets des terrains
	
	public Tenue(int x, int y, String s, RpgMain g) {
		super(x,y,s,g);
		this.setTexture(g.getAtlas().findRegion("Game/collectible/tenue"));
		this.setSprite(new Sprite(this.getTexture()));
		if(this.getName().equals("Bénédiction de Susanoo")) {
			setDescription("Permet de marcher sur l'eau et augment les PV");
		}
		else if(this.getName().equals("Protection d'Amaterasu")) {
			setDescription("Protège des potions malus");
		}
		else if(this.getName().equals("Broche d'Izanami")) {
			setDescription("Annule les effets de terrain (perte de PV)");
		}
		else if(this.getName().equals("Apparat du kistune")) {
			setDescription("Permet de convaincre à 100% les ennemis humains");
		}
		else if(this.getName().equals("Armure de Bishamonten")) {
			setDescription("Donne un bonus d'attaque");
		}
		else if(this.getName().equals("Eclat de Tsukuyomi")) {
			setDescription("Bonus de mana");
		}
	}
	public Tenue(Player player, String string) {
		super(player,string);
	}
	public void effect(Player p) {
		if (this.getName().equals("Bénédiction de Susanoo")) {
			p.SetPV(p.GetPV()+2);
		}
		else if (this.getName().equals("Eclat de Tsukuyomi")) {
			p.SetMana(p.GetMana()+2);
		}
		else if (this.getName().equals("Armure de Bishamonten")) {
			p.SetBonusAttaque(2);
		}
	}
	@Override
	public void dispawn() {
		ArrayList<Tenue> list = this.getG().getTiledModel().getTenue();
		list.remove(this);
		this.getG().getTiledModel().setTenue(list);
	}
}
