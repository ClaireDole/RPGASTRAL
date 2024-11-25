package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.carte.Terrain;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Potion;
import fr.rpgastral.model.collectible.Tenue;

public class Player extends Entity{
	private Armes mg;
    private Armes md;
    private Tenue tenue;
    private float Mana;
    private float Déplacement;
    private int BonusAttaque;

    public Player(int x, int y, final RpgMain game) {
		super(x, y, game);
		this.PV = 3;
		this.Mana = 3;
		this.Déplacement = 3;
		this.md = this.mg = null;
		this.tenue = null;
		this.BonusAttaque = 0;
		this.texture = game.getAtlas().findRegion("Game/player/player");
	}
    public float GetMana() {
    	return this.Mana;
    }
    public float GetDéplacement() {
    	return this.Déplacement;
    }
    public Tenue Gettenue(){
    	return this.tenue;
    }
    public int GetBonusAttaque() {
    	return this.BonusAttaque;
    }
    public void SetBonusAttaque(int i) {
    	this.BonusAttaque = i;
    }
    public void SetMana(float n) {
    	if (n<=6) {
    		this.Mana = n;
    	}
    	else this.Mana = 6;
    }
    public void SetDéplacement(float n) {
    	if (n<=4) {
    		this.Mana = n;
    	}
    	else this.Mana=4;
    }
    public void SetPV (float n) {
    	if(n<=5) {
    		this.PV=n;
    	}
    	else this.PV =5;
    }
 
    public void move(int i){
    	
        //regarder le déplacement du player
    	//proposer les cases possibles où se déplacer en les faisant apparaître d'une couleur différente
    	//regarder le clic souris
    	//si clic sur une case possible, set x et y du player à la position de la case
    }
    
    public void takedamage(float i){
        this.PV = PV-i;
        if (!this.isAlive()) {
        	//écran fin de jeu proposer retourner à l'écran d'accueil ou quitter le jeu
        }
    }
    public void attaquemg(Enemy e){
    	if((this.mg.Getname()=="Arc") && ((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))) {
    		e.takedamage(this.mg.Getdamage() + this.BonusAttaque);
    	}
    	else if (this.mg.Getname()=="Spectre" &&(((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))
    			|((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1))|
    			((e.x == this.x +3 && e.y == this.y +3) | (e.x == this.x +3 && e.y == this.y -3) | (e.x == this.x -3 && e.y == this.y +3) | (e.x == this.x -3 && e.y == this.y -3)))) {
    		e.takedamage(this.mg.Getdamage() + this.BonusAttaque);
    		this.Mana = this.Mana - this.mg.Getcout();
    	}
    	else if((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1)) {
        	e.takedamage(this.mg.Getdamage()+ this.BonusAttaque);
    	}
    }
    public void attaquemg(Terrain t){
//            t.takedamage();
    }
    public void attaquemd(Terrain t){
//        t.takedamage();
}
    public void attaquemd(Enemy e){
    	if((this.md.Getname()=="Arc") && ((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))) {
    		e.takedamage(this.md.Getdamage()+ this.BonusAttaque);
    	}
    	else if (this.md.Getname()=="Spectre" &&(((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))
    			|((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1))|
    			((e.x == this.x +3 && e.y == this.y +3) | (e.x == this.x +3 && e.y == this.y -3) | (e.x == this.x -3 && e.y == this.y +3) | (e.x == this.x -3 && e.y == this.y -3)))) {
    		e.takedamage(this.md.Getdamage()+this.BonusAttaque);
    		this.Mana = this.Mana - this.md.Getcout();
    	}
    	else if((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1)) {
        	e.takedamage(this.md.Getdamage()+this.BonusAttaque);
    	}
    }
    
    public void Collect (Potion c){
        if(this.x == c.Getx() && this.y == c.Gety()){
            c.effect(this);
            c.dispawn(c);
        }
    }
    public void Collect (Tenue c) {
       if(this.x==c.Getx() && this.y==c.Gety()) {
        	if (this.tenue == null) {
        		this.tenue = c;
        		c.effect(this);
        		c.dispawn(c);
        	}
        	else {
        		//proposer choix de tenue
        		//si c est choisie alors on affect c; on dispawn c; on met la tenue précédente dans une liste qui est affichée dans la maison permettant de changer de tenue
        	}
        }
    }
    public void Collect (Armes c) {
        if(this.x == c.Getx() && this.y == c.Gety()){
            if (this.md == null && !c.Getmaindouble()) { 
            	this.md = c;
            	c.dispawn(c);
            }
            else if (this.mg == null && !c.Getmaindouble()) { 
            	this.mg = c;
            	c.dispawn(c);
            }
            else if (this.mg == null && this.md == null && c.Getmaindouble()){
            	this.mg=this.md=c;
            	c.dispawn(c);
            }
            else {
            	//proposer choix
            	//dispawn l'arme si elle est choisie
            }
        }
    }
    public void Dialogue(PNJ p){
    	if((p.x == this.x +1 && p.y == this.y +1) | (p.x == this.x +1 && p.y == this.y -1) | (p.x == this.x -1 && p.y == this.y +1) | (p.x == this.x -1 && p.y == this.y -1)) {
    		p.Dialogue();
    	}
    	else {
    		//afficher ça n'a pas d'effet...
    	}
        // si elfe alors boite de dialogue avec réponse y/n pour soin du player
    }
    public void Convaincre(EnemyHuman e) {
    	if (this.tenue.Getname()=="Apparat du kitsune") {
    		//affiche l'ennemi est convaincu par vos arguments et prend la fuite
    		//e.dispose();
    	}
    	//nombre random qui permet de savoir si on peut convaincre ou pas
    	//si oui alors on dispawn l'ennemi
    }
}

