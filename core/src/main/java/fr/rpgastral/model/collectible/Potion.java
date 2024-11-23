package fr.rpgastral.model.collectible;

import fr.rpgastral.model.entity.Player;

public class Potion extends Collectible{
	
	public Potion(int x, int y, int e, String s) {
		super(x,y,e,s);
	}
	public void effect(Player p) {
		if (p.Gettenue().Getname()=="Protection d'Amaterasu" && this.damage < 0) {
			this.damage = 0;
		}
		else if(this.name == "PV") {
			p.SetPV(p.GetPV() + this.damage);
		}
		else if (this.name =="Mana") {
			p.SetMana(p.GetMana() + this.damage);
		}
		else if (this.name=="Déplacement"){
				p.SetDéplacement(p.GetDéplacement()+this.damage);
		}
	}
}
