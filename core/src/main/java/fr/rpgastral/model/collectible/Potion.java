package fr.rpgastral.model.collectible;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.entity.Player;

public class Potion extends Collectible{
	
	public Potion(int x, int y, float e, String s, RpgMain g) {
		super(x,y,e,s,g);
		setTexture(g.getAtlas().findRegion("Game/collectible/potion"));
		setSprite(new Sprite(this.getTexture()));
	}
	public void effect(Player p) {
		if (p.Gettenue()!=null && p.Gettenue().getName().equals("Protection d'Amaterasu") && this.getDamage() < 0) {
			setDamage(0);;
		}
		if(this.getName().equals("PV")) {
			p.SetPV(p.GetPV() + this.getDamage());
			if(!p.isAlive()) {
				p.SetPV(0.25f);
			}
		}
		if (this.getName().equals("Mana")) {
			p.SetMana(p.GetMana() + this.getDamage());
		}
		if (this.getName().equals("Attaque")) {
			p.SetBonusAttaque(p.GetBonusAttaque() + this.getDamage());
		}
	}
	@Override
	public void dispawn() {
		ArrayList<Potion> list = this.getG().getTiledModel().getPotion();
		list.remove(this);
		this.getG().getTiledModel().setPotion(list);
	}
	
}
