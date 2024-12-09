package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;

public class EnemyHuman extends Enemy{
	private String name;
	
	public EnemyHuman(int x, int y, String n, RpgMain g) {
		super(x, y,g);
		this.name = n;
		if(this.name =="Brigand") {
			this.Setdamage(1);
		}
		else if (this.name=="Moyen") {
			this.Setdamage(1.25f);
		}
	}

}
