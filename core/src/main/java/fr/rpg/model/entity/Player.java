package fr.rpg.model.entity;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observable;
import fr.rpg.controler.observerpattern.Observer;
import fr.rpg.controler.observerpattern.concreteobserver.GameOver;
import fr.rpg.controler.observerpattern.concreteobserver.PlayerMove;
import fr.rpg.controler.observerpattern.concreteobserver.Victory;
import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;
import fr.rpg.model.collectible.Armes;
import fr.rpg.model.collectible.Potion;
import fr.rpg.model.collectible.Tenue;
import fr.rpg.view.GameScreen;
import fr.rpg.view.MsgScreen;

/**
 * classe décrivant le joueur
 */
public class Player extends Entity implements Observable{
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
	/**
	 * nombre de dégâts bonus supplémentaires
	 */
	private float BonusAttaque;
	/**
	 * liste des concreteobserver en attente d'un évènement de la part du joueur
	 */
	private ArrayList<concreteobserver> observers;
	/**
	 * instance de l'écran avec la carte surlaquelle se trouve le joueur
	 */
	private GameScreen gamescreen;

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
		attach(new GameOver("gameover"));
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
		Event event = new Event(this.getG(), "Player",false,"move",gamescreen);
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
			Event event = new Event(this.getG(),"Player",false,"gameover",gamescreen);
			notify(event);
		}
	}

	/**
	 * gestion de l'attaque du joueur avec l'arme en main droite
	 * @param e ennemi à attaquer
	 */
	public void attaquemd(Enemy e) {
		if(e instanceof Monstre && e.getName().equals("Volant") && this.getMd().getName().equals("Arc")) {
			e.takedamage(e.getPV(),gamescreen.getTiledmodel());
		}
		else if(e instanceof Monstre && e.getName().equals("Volant") && this.getMd().getName()!="Arc") {
			e.takedamage(0.05f,gamescreen.getTiledmodel());
		}
		else {
			e.takedamage(this.BonusAttaque + this.getMd().getDamage(),gamescreen.getTiledmodel());
		}
		this.setMana(this.Mana - this.getMd().getCout());
		Event event = new Event(this.getG(), "Player",false,"victory", gamescreen);
		notify(event);
	}
	/**
	 * gestion de l'attaque du joueur avec l'arme en main gauche
	 * @param e ennemi à attaquer
	 */
	public void attaquemg(Enemy e) {
		if(e instanceof Monstre && e.getName().equals("Volant") && this.getMg().getName().equals("Arc")) {
			e.takedamage(e.getPV(), gamescreen.getTiledmodel());
		}
		else if(e instanceof Monstre && e.getName().equals("Volant") && this.getMg().getName()!="Arc") {
			e.takedamage(0.05f, gamescreen.getTiledmodel());
		}
		else {
			e.takedamage(this.BonusAttaque + this.getMg().getDamage(), gamescreen.getTiledmodel());
		}
		this.setMana(this.Mana - this.getMg().getCout());
		Event event = new Event(this.getG(), "Player",false,"victory", gamescreen);
		notify(event);
	}

	/**
	 * la méthode Collect est surchargée pour les trois types de collectibles : potions, tenues et armes
	 * @param c collectible à récupérer
	 */
	public void Collect (Potion c){
		c.effect(this);
		c.dispawn(gamescreen.getTiledmodel());
	}
	public void Collect(Tenue c) {
		if (this.tenue ==  null) {
			this.tenue = c;
			c.effect(this);
			c.dispawn(gamescreen.getTiledmodel());
		}
		else {
			getG().setScreen(new MsgScreen(getG(),c));
		}
	}
	public void Collect (Armes c) {
		if(this.getMd() == null && !(c.getMaindouble())) {
			this.setMd(c);
			c.dispawn(gamescreen.getTiledmodel());
		}
		else if (this.getMg() == null && !(c.getMaindouble())) {
			this.setMg(c);
			c.dispawn(gamescreen.getTiledmodel());
		}
		else if (this.getMd()==null && this.getMg()==null && c.getMaindouble()) {
			this.setMd(c);
			this.setMg(c);
			c.dispawn(gamescreen.getTiledmodel());
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
				Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/success.wav"));
				sound.play();
				this.getG().setScreen(new MsgScreen(this.getG(),"L'ennemi "+e.getName()+" est convaincu par vos arguments et prend la fuite"));
				e.dispawn(gamescreen.getTiledmodel());
			}
		}
		else {
			Random random = new Random();
			int alea = random.nextInt(100);
			if(alea < e.getTaux()) {
				Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/success.wav"));
				sound.play();
				this.getG().setScreen(new MsgScreen(this.getG(),"L'ennemi "+e.getName()+" est convaincu par vos arguments et prend la fuite."));
				e.dispawn(gamescreen.getTiledmodel());
			}
			else {
				this.getG().setScreen(new MsgScreen(this.getG(), "L'ennemi "+e.getName()+" n'est pas convaincu par vos arguments et préfère se battre."));
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

	/**
	 * @return the mg
	 */
	public Armes getMg() {
		return mg;
	}

	/**
	 * @param mg the mg to set
	 */
	public void setMg(Armes mg) {
		this.mg = mg;
	}

	/**
	 * @return the md
	 */
	public Armes getMd() {
		return md;
	}

	/**
	 * @param md the md to set
	 */
	public void setMd(Armes md) {
		this.md = md;
	}

	/**
	 * @return the tenue
	 */
	public Tenue getTenue() {
		return tenue;
	}

	/**
	 * @param tenue the tenue to set
	 */
	public void setTenue(Tenue tenue) {
		this.tenue = tenue;
	}

	/**
	 * @return the mana
	 */
	public float getMana() {
		return Mana;
	}

	/**
	 * @param mana the mana to set
	 */
	public void setMana(float mana) {
		if (mana<=6) {
			this.Mana = mana;
		}
		else this.Mana = 6;
		if(this.Mana<0) {
			this.Mana = 0;
		}
	}

	/**
	 * @return the bonusAttaque
	 */
	public float getBonusAttaque() {
		return BonusAttaque;
	}

	/**
	 * @param bonusAttaque the bonusAttaque to set
	 */
	public void setBonusAttaque(float bonusAttaque) {
		this.BonusAttaque = bonusAttaque;
		if (this.BonusAttaque<0) {
			this.BonusAttaque = 0;}
	}

	/**
	 * @return the observers
	 */
	public ArrayList<concreteobserver> getObservers() {
		return observers;
	}

	/**
	 * @param observers the observers to set
	 */
	public void setObservers(ArrayList<concreteobserver> observers) {
		this.observers = observers;
	}

	/**
	 * 
	 * @param Pv the Pv to set
	 */
	public void SetPV (float Pv) {
		if(Pv<=5) {
			setPV(Pv);
		}
		else setPV(5);
	}

	/**
	 * @return the gamescreen
	 */
	public GameScreen getGamescreen() {
		return gamescreen;
	}

	/**
	 * @param gamescreen the gamescreen to set
	 */
	public void setGamescreen(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}
}

