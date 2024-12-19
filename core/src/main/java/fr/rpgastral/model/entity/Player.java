package fr.rpgastral.model.entity;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;
import fr.rpgastral.controler.observerpattern.sujet;
import fr.rpgastral.controler.observerpattern.concreteobserver.PlayerMove;
import fr.rpgastral.controler.observerpattern.concreteobserver.Victory;
import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Potion;
import fr.rpgastral.model.collectible.Tenue;
import fr.rpgastral.view.MsgScreen;

/**
 * classe décrivant le joueur
 */
public class Player extends Entity implements sujet{
	/**
	 * arme portée avec la main gauche
	 */
	private Armes mg;
	/**
	 * arme portée avec la main droite
	 */
    private Armes md;
    /**
     * tenue portée
     */
    private Tenue tenue;
    /**
     * energie magique, permet de lancer une attaque avec l'arme sceptre
     */
    private float Mana;
    private float BonusAttaque;
    private ArrayList<concreteobserver> observers;

    public Player(int x, int y, RpgMain g) {
		super(x, y, g);
		setPV(3);
		this.Mana = 3;
		this.md = this.mg = null;
		this.tenue = null;
		this.BonusAttaque = 0;
		this.observers = new ArrayList<concreteobserver>();
		attach(new PlayerMove("move"));
		attach(new Victory("victoire"));
		setTexture(g.getAtlas().findRegion("Game/player/player"));
		setSprite(new Sprite(this.getTexture()));
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
    	Event event = new Event(this.getG(), "Player",false,"move");
    	notify(event);
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
     * gestion de l'attaque du joueur avec l'arme en main droite
     * @param e ennemi à attaquer
     */
    public void attaquemd(Enemy e) {
    	if(e instanceof Monstre && e.getName().equals("Volant") && this.getMd().getName().equals("Arc")) {
    		e.takedamage(e.getPV());
    	}
    	else if(e instanceof Monstre && e.getName().equals("Volant") && this.getMd().getName()!="Arc") {
    		e.takedamage(0.05f);
    	}
    	else {
    		e.takedamage(this.BonusAttaque + this.getMd().getDamage());
    	}
    	this.SetMana(this.Mana - this.getMd().getCout());
    	Event event = new Event(this.getG(), "Player",false,"victory");
    	notify(event);
    }
    /**
     * gestion de l'attaque du joueur avec l'arme en main gauche
     * @param e ennemi à attaquer
     */
    public void attaquemg(Enemy e) {
    	if(e instanceof Monstre && e.getName().equals("Volant") && this.getMg().getName().equals("Arc")) {
    		e.takedamage(e.getPV());
    	}
    	else if(e instanceof Monstre && e.getName().equals("Volant") && this.getMg().getName()!="Arc") {
    		e.takedamage(0.05f);
    	}
    	else {
    		e.takedamage(this.BonusAttaque + this.getMg().getDamage());
    	}
    	this.SetMana(this.Mana - this.getMg().getCout());
    	Event event = new Event(this.getG(), "Player",false,"victory");
    	notify(event);
    }

    /**
     * la méthode Collect est surchargée pour les trois types de collectibles : potions, tenues et armes
     * @param c collectible à récupérer
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
     * la tenue apparat du kitsune permet de convaincre tout le temps
     * sinon cela fonctionne de façon aléatoire et dépend du type d'ennemis
     * Attention : une seule et unique tentative par ennemi distinct !
     * @param e ennemi surlequel on agit
     */
    public void Convaincre(EnemyHuman e) {
    	if(this.tenue!=null) {
    		if (this.tenue.getName()=="Apparat du kitsune") {
    			this.getG().setScreen(new MsgScreen(this.getG(),"L'ennemi "+e.getName()+" est convaincu par vos arguments et prend la fuite"));
    			e.dispawn();
    		}
    	}
    	else {
    		Random random = new Random();
    		int alea = random.nextInt(100);
    		if(alea < e.getTaux()) {
    			this.getG().setScreen(new MsgScreen(this.getG(),"L'ennemi "+e.getName()+" est convaincu par vos arguments et prend la fuite."));
        		e.dispawn();
    		}
    		else {
    			this.getG().setScreen(new MsgScreen(this.getG(), "L'ennemi "+e.getName()+" n/'est pas convaincu par vos arguments et préfère se battre."));
    			e.setTaux(0);
    		}
    	}
    }

	@Override
	public void attach(concreteobserver o) {
		this.observers.add(o);
	}

	@Override
	public void unattach(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notify(Event e) {
		this.observers.forEach(observer -> observer.update(e));	
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

