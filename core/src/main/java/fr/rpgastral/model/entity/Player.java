package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.carte.Terrain;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Potion;
import fr.rpgastral.model.collectible.Tenue;

/**
 * classe décrivant le joueur
 */
public class Player extends Entity{
	private Armes mg;
    private Armes md;
    private Tenue tenue;
    private float Mana;
    private float BonusAttaque;
    private Sprite sprite;

    public Player(int x, int y, RpgMain g) {
		super(x, y, g);
		this.PV = 3;
		this.Mana = 3;
		this.md = this.mg = null;
		this.tenue = null;
		this.BonusAttaque = 0;
		this.texture = g.getAtlas().findRegion("Game/player/player");
		this.sprite = new Sprite(this.texture);
	}
    
    public void move(int x, int y){
    	this.x = x;
    	this.y = y;
    	for(int i = 0; i< getG().getTiledModel().getObstacles().size(); i++ ) {
			int z = getG().getTiledModel().getObstacles().get(i).Getx();
			int w = getG().getTiledModel().getObstacles().get(i).Gety();
			if (x == z && y == w && (this.tenue == null) | (this.tenue.getName() != "Broche d'Izanami")) {
				this.takedamage(0.5f);
			}
		}
    }
    
    public void takedamage(float i){
        this.PV = PV-i;
        if (!this.isAlive()) {
        	//écran fin de jeu proposer retourner à l'écran d'accueil ou quitter le jeu
        }
    }
    public void attaquemg(Enemy e){
    	if((this.mg.getName()=="Arc") && ((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))) {
    		e.takedamage(this.mg.getDamage() + this.BonusAttaque);
    	}
    	else if (this.mg.getName()=="Spectre" &&(((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))
    			|((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1))|
    			((e.x == this.x +3 && e.y == this.y +3) | (e.x == this.x +3 && e.y == this.y -3) | (e.x == this.x -3 && e.y == this.y +3) | (e.x == this.x -3 && e.y == this.y -3)))) {
    		e.takedamage(this.mg.getDamage() + this.BonusAttaque);
    		this.Mana = this.Mana - this.mg.Getcout();
    	}
    	else if((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1)) {
        	e.takedamage(this.mg.getDamage()+ this.BonusAttaque);
    	}
    }
    public void attaquemd(Enemy e){
    	if((this.md.getName()=="Arc") && ((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))) {
    		e.takedamage(this.md.getDamage()+ this.BonusAttaque);
    	}
    	else if (this.md.getName()=="Spectre" &&(((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))
    			|((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1))|
    			((e.x == this.x +3 && e.y == this.y +3) | (e.x == this.x +3 && e.y == this.y -3) | (e.x == this.x -3 && e.y == this.y +3) | (e.x == this.x -3 && e.y == this.y -3)))) {
    		e.takedamage(this.md.getDamage()+this.BonusAttaque);
    		this.Mana = this.Mana - this.md.Getcout();
    	}
    	else if((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1)) {
        	e.takedamage(this.md.getDamage()+this.BonusAttaque);
    	}
    }
    
    public void Collect (Potion c){
        c.effect(this);
        c.dispawn();
    }
    public void Collect (Armes c) {
        if(this.x == c.getX() && this.y == c.getY()){
            if (this.md == null && !c.Getmaindouble()) { 
            	this.md = c;
            	c.dispawn();
            }
            else if (this.mg == null && !c.Getmaindouble()) { 
            	this.mg = c;
            	c.dispawn();
            }
            else if (this.mg == null && this.md == null && c.Getmaindouble()){
            	this.mg=this.md=c;
            	c.dispawn();
            }
            else {
            	//proposer choix
            	//dispawn l'arme si elle est choisie
            }
        }
    }
    
    public void Convaincre(EnemyHuman e) {
    	if (this.tenue.getName()=="Apparat du kitsune") {
    		//affiche l'ennemi est convaincu par vos arguments et prend la fuite
    		//e.dispose();
    	}
    	//nombre random qui permet de savoir si on peut convaincre ou pas
    	//si oui alors on dispawn l'ennemi
    }

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Armes getMg() {
		return mg;
	}

	public Armes getMd() {
		return md;
	}

	public Tenue getTenue() {
		return tenue;
	}

	public void setMg(Armes mg) {
		this.mg = mg;
	}

	public void setMd(Armes md) {
		this.md = md;
	}

	public void setTenue(Tenue tenue) {
		this.tenue = tenue;
	}
	public float GetMana() {
    	return this.Mana;
    }
    public Tenue Gettenue(){
    	return this.tenue;
    }
    public float GetBonusAttaque() {
    	return this.BonusAttaque;
    }
    public void SetBonusAttaque(float f) {
    	this.BonusAttaque = f;
    	if (this.BonusAttaque<0) {
    		this.BonusAttaque = 0;
    	}
    }
    public void SetMana(float n) {
    	if (n<=6) {
    		this.Mana = n;
    	}
    	else this.Mana = 6;
    	if(this.Mana<0) {
    		this.Mana = 0;
    	}
    }
    public void SetPV (float n) {
    	if(n<=5) {
    		this.PV=n;
    	}
    	else this.PV =5;
    }
}

