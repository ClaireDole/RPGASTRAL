package fr.rpgastral.model.collectible;

import fr.rpgastral.controler.RpgMain;

public class Armes extends Collectible{
	private Boolean maindouble;
	private int cout;

	public Armes(int x, int y, int e, String s, RpgMain g) {
		super(x,y,e,s,g);
		this.maindouble = false;
		if (s =="Bâton") {
			setDamage(1); 
		}
		else if (s=="Epée") {
			setDamage(2);
		}
		else if (s=="Hâche") {
			setDamage(3);
			this.maindouble = true;
		}
		else if (s=="Spectre") {
			setDamage(3);
			this.cout = 1;
		}
		else if (s=="Arc"){
			setDamage(2);
		}
	}
	public Boolean Getmaindouble() {
		return this.maindouble;
	}
	public int Getcout() {
		return this.cout;
	}
}
