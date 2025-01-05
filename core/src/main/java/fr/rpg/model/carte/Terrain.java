package fr.rpg.model.carte;

/**
 * description des terrains franchissables ou non et ceux avec des effets
 * eau peut être franchie uniquement si le joueue a une tenue spécifique, la bénédiction de Susanoo
 * volcanique cause des dégâts au joueur chaque fois qu'il se déplace mais cela ne le tuera pas
 * on peut annuler les effets des terrains volcaniques avec la tenue broche d'Izanami
 */
public class Terrain{
	private int x;
	private int y;
	private Boolean franchissement;
	private String name;

	public Terrain(int x, int y, String n) {
		this.x = x;
		this.y = y;
		this.name = n;
		if(this.name =="plaine" | this.name =="chemin" | this.name =="volcanique") {
			this.franchissement = true;
		}
		else {
			this.franchissement = false;
		}
	}

	public int Getx() {
		return this.x;
	}
	public int Gety() {
		return this.y;
	}
	public Boolean Getfranchissement() {
		return this.franchissement;
	}
	public String Getname() {
		return this.name;
	}
}