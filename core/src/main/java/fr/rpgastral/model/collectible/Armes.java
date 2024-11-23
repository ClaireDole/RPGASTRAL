package fr.rpgastral.model.collectible;

public class Armes extends Collectible{
	private Boolean maindouble;
	private int cout;

	public Armes(int x, int y, int e, String s) {
		super(x,y,e,s);
		this.maindouble = false;
		if (s =="Bâton") {
			this.damage = 1; 
		}
		else if (s=="Epée") {
			this.damage = 2;
		}
		else if (s=="Hâche") {
			this.damage = 3;
			this.maindouble = true;
		}
		else if (s=="Spectre") {
			this.damage = 3;
			this.cout = 1;
		}
		else if (s=="Arc"){
			this.damage = 2;
		}
	}
	public Boolean Getmaindouble() {
		return this.maindouble;
	}
	public int Getcout() {
		return this.cout;
	}
}
