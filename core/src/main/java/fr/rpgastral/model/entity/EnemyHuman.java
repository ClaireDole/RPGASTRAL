package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;

public class EnemyHuman extends Enemy{
	private String name;
	private RpgMain game;
	
	public EnemyHuman(int x, int y, String n, RpgMain game) {
		super(x, y,game);
		this.name = n;
		if(this.name =="Brigand") {
			this.Setdamage(1);
		}
		else if (this.name=="Moyen") {
			this.Setdamage(1.25f);
		}
	}

}
