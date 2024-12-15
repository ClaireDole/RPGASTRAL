package fr.rpgastral.model.collectible;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.entity.Player;

public class Tenue extends Collectible{
	//Bénédiction de Susanoo --> marche sur l'eau et augmente PV
	//Apparat du kitsune --> convainct à 100% de réussite
	//Protection d'Amaterasu --> protège des malus
	//Armure de Bishamonten --> augmente l'attaque réduit de 1 le déplacement
	//Eclat de Tsukuyomi --> augmente le mana
	//Broche d'Izanami --> augmente le déplacement et annule les effets des terrains
	
	public Tenue(int x, int y, int e, String s, RpgMain g) {
		super(x,y,e,s,g);
	}
	public Tenue(Player player, String string) {
		super(player,string);
	}
	public void effect(Player p) {
		if (this.getName() =="Bénédiction de Susanoo") {
			p.SetPV(p.GetPV()+2);
		}
		else if (this.getName()=="Eclat de Tsukuyomi") {
			p.SetMana(p.GetMana()+2);
		}
		else if (this.getName()=="Armure de Bishamonten") {
			p.SetBonusAttaque(2);
		}
	}
}
