package fr.rpg.model.entity;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.rpg.controler.RpgMain;
import fr.rpg.model.carte.TiledModel;

/**
 * description des ennemis de type monstre
 * on retrouve les slimes, les gobelins, les volants et les orcs
 */
public class Monstre extends Enemy {

	public Monstre(int x, int y,String name, RpgMain g) {
		super(x, y,name,g);
		if(this.getName().equals("Slime")) {
			this.setDamage(0.25f);
			this.setPV(1.5f);
			this.setPortee(1);
			this.setTexture(g.getAtlas().findRegion("Game/enemy/slime"));
			this.setSprite(new Sprite(this.getTexture()));
		}
		else if(this.getName().equals("Gobelin")) {
			this.setDamage(0.5f);
			this.setPV(2.5f);
			this.setPortee(1);
			this.setTexture(g.getAtlas().findRegion("Game/enemy/gobelin"));
			this.setSprite(new Sprite(this.getTexture()));
		}
		else if(this.getName().equals("Orc")) {
			this.setDamage(0.75f);
			this.setPV(3f);
			this.setPortee(2);
			this.setTexture(g.getAtlas().findRegion("Game/enemy/orc"));
			this.setSprite(new Sprite(this.getTexture()));
		}
		else if(this.getName().equals("Volant")) {
			this.setDamage(0.5f);
			this.setPV(3f);
			this.setPortee(1);
			this.setPosition(1);
			this.setTexture(g.getAtlas().findRegion("Game/enemy/volant"));
			this.setSprite(new Sprite(this.getTexture()));
		}
	}

	/**
	 * prise de dégâts des monstres
	 * dans le cas d'un slime, on lance une attaque en réponse, quel que soit la portée nécessaire
	 */
	@Override
	public void takedamage(float i, TiledModel tiledmodel){
		setPV(this.getPV() - i);
		if(!isAlive()) {
			this.dispawn(tiledmodel);
		}
		else if(this.getName().equals("Slime")) {
			this.attaque(this.getG().getPlayer());
		}
	}

	/**
	 * gestion de l'attaque du monstre sur le joueur
	 * on utilise un cooldown pour les orcs et les gobelins qui attaquent en fonction de la position du joueur
	 * cela évite que ces monstres attaquent en presque permanence le joueur lorsque ce dernier est à portée
	 */
	@Override
	public void attaque(Player p) {
		if(this.getName().equals("Slime")) {
			p.takedamage(this.getDamage());
		}
		else {
			if(cooldownover()) {
				p.takedamage(this.getDamage());
				cooldownstart();
			}
		}
	}

	/**
	 * supprime l'instance monstre du jeu
	 * si le monstre fait partie des ennemis obligatoires, on le retire aussi de la liste correspondante
	 */
	@Override
	public void dispawn(TiledModel tiledmodel) {
		ArrayList<Monstre> list = tiledmodel.getMonstres();
		list.remove(this);
		tiledmodel.setMonstres(list);
		ArrayList<Enemy> e = tiledmodel.getEnemys();
		if(!(tiledmodel.getMonstres().contains(this)) && e.contains(this)) {
			e.remove(this);
			Sound sound=Gdx.audio.newSound(Gdx.files.internal("Son/success.wav"));
			sound.play();
		}
		tiledmodel.setEnemys(e);
	}
}
