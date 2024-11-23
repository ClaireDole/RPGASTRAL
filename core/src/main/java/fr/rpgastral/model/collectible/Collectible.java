package fr.rpgastral.model.collectible;

//passer sur du polymorphisme plutôt que des else if pour les potions
public abstract class Collectible{
 protected int x;
 protected int y;
 protected String name;
 protected float damage;
 
 public Collectible(int x, int y, float e, String s) {
	 this.x=x;
	 this.y=y;
	 this.damage=e;
	 this.name=s;
 }
 
 public int Getx() {
	 return this.x;
 }
 public int Gety() {
	 return this.y;
 }
 public String Getname() {
	 return this.name;
 }
 public float Getdamage() {
	 return this.damage;
 }
 public void dispawn(Collectible c) {
	 //enlever c de la collection d'objets présents sur la carte
 }
}
