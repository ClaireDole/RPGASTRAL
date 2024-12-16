package fr.rpgastral.model.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Potion;
import fr.rpgastral.model.collectible.Tenue;
import fr.rpgastral.view.MsgScreen;

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
		setPV(3);
		this.Mana = 3;
		this.md = this.mg = null;
		this.tenue = null;
		this.BonusAttaque = 0;
		setTexture(g.getAtlas().findRegion("Game/player/player"));
		this.sprite = new Sprite(this.getTexture());
	}
    
    /**
     * déplacement du joueur sur la carte
     * @param x abscisse
     * @param y ordonnée
     */
    public void move(int x, int y){
    	setX(x);
    	setY(y);
    	if(this.tenue!= null) {
    		this.tenue.setX(this.getX());
    		this.tenue.setY(this.getY());
    	}
    	if(this.getMd()!=null) {
    		this.getMd().setX(this.getX());
    		this.getMd().setY(this.getY());
    	}
    	if(this.getMg()!=null) {
    		this.getMg().setX(this.getX());
    		this.getMg().setY(this.getY());
    	}
    }
    
    /**
     * le joueur prend un dégât
     * on vérifie si le joueur est mort
     * @param i nombre de dégât subi
     */
    public void takedamage(float i){
        setPV(getPV()-i);
        if (!this.isAlive()) {
        	//écran fin de jeu proposer retourner à l'écran d'accueil ou quitter le jeu
        }
    }
    
    /**
     * gestion de l'attaque du joueur
     * @param e
     */
//    public void attaquemg(Enemy e){
//    	int x = this.getX();
//		int y = this.getY();
//		if((this.mg.getName()=="Arc") && ((e.getX() == x +2 && e.getY() == this.getY() +2) | (e.getX() == x +2 && e.y == y -2) | (e.x == x -2 && e.y == y +2) | (e.x == x -2 && e.y == y -2))) {
//    		e.takedamage(this.mg.getDamage() + this.BonusAttaque);
//    	}
//    	else if (this.mg.getName()=="Spectre" &&(((e.x == x +2 && e.y == y +2) | (e.x == x +2 && e.y == y -2) | (e.x == x -2 && e.y == y +2) | (e.x == x -2 && e.y == y -2))
//    			|((e.x == x +1 && e.y == y +1) | (e.x == x +1 && e.y == y -1) | (e.x == x -1 && e.y == y +1) | (e.x == x -1 && e.y == y -1))|
//    			((e.x == x +3 && e.y == y +3) | (e.x == x +3 && e.y == y -3) | (e.x == x -3 && e.y == y +3) | (e.x == x -3 && e.y == y -3)))) {
//    		e.takedamage(this.mg.getDamage() + this.BonusAttaque);
//    		this.Mana = this.Mana - this.mg.Getcout();
//    	}
//    	else if((e.x == x +1 && e.y == y +1) | (e.x == x +1 && e.y == y -1) | (e.x == x -1 && e.y == y +1) | (e.x == x -1 && e.y == y -1)) {
//        	e.takedamage(this.mg.getDamage()+ this.BonusAttaque);
//    	}
//    }
//    public void attaquemd(Enemy e){
//    	if((this.md.getName()=="Arc") && ((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))) {
//    		e.takedamage(this.md.getDamage()+ this.BonusAttaque);
//    	}
//    	else if (this.md.getName()=="Spectre" &&(((e.x == this.x +2 && e.y == this.y +2) | (e.x == this.x +2 && e.y == this.y -2) | (e.x == this.x -2 && e.y == this.y +2) | (e.x == this.x -2 && e.y == this.y -2))
//    			|((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1))|
//    			((e.x == this.x +3 && e.y == this.y +3) | (e.x == this.x +3 && e.y == this.y -3) | (e.x == this.x -3 && e.y == this.y +3) | (e.x == this.x -3 && e.y == this.y -3)))) {
//    		e.takedamage(this.md.getDamage()+this.BonusAttaque);
//    		this.Mana = this.Mana - this.md.Getcout();
//    	}
//    	else if((e.x == this.x +1 && e.y == this.y +1) | (e.x == this.x +1 && e.y == this.y -1) | (e.x == this.x -1 && e.y == this.y +1) | (e.x == this.x -1 && e.y == this.y -1)) {
//        	e.takedamage(this.md.getDamage()+this.BonusAttaque);
//    	}
//    }
    
    /**
     * la méthode Collect est surchargée pour les trois types de collectibles : potions, tenues et armes
     * @param c collectible
     */
    public void Collect (Potion c){
        c.effect(this);
        c.dispawn();
    }
    public void Collect(Tenue c) {
    	if (this.tenue ==  null) {
    		this.tenue = c;
    		c.effect(this);
    		c.dispawn();
    	}
    	else {
    		getG().setScreen(new MsgScreen(getG(),c));
    	}
    }
    public void Collect (Armes c) {
        if(this.getMd() == null && !(c.getMaindouble())) {
        	this.setMd(c);
        	c.dispawn();
        }
        else if (this.getMg() == null && !(c.getMaindouble())) {
        	this.setMg(c);
        	c.dispawn();
        }
        else if (this.getMd()==null && this.getMg()==null && c.getMaindouble()) {
        	this.setMd(c);
        	this.setMg(c);
        	c.dispawn();
        }
        else {
        	getG().setScreen(new MsgScreen(getG(),c));
        }
    }
    
    /**
     * convaincre un ennemi permet d'éviter le combat mais de se débarasser de lui
     * ne fonctionne qu'avec les ennemis humains
     * la tenue apparat du kitsune permet de convaincre à tous les coûts
     * sinon cela fonctionne de façon aléatoire et dépend du type d'ennemis
     * @param e ennemi surlequel on agit
     */
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
    		setPV(n);
    	}
    	else setPV(5);
    }
}

