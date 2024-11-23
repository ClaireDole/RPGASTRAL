package fr.rpgastral.model.entity;

public class EnemyHuman extends Enemy{
	private String name;
	
	public EnemyHuman(int x, int y, String n) {
		super(x, y);
		this.name = n;
		if(this.name =="Brigand") {
			this.Setdamage(1);
		}
		else if (this.name=="Moyen") {
			this.Setdamage(1.25f);
		}
	}

}
