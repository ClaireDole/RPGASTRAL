package fr.rpgastral.controler.observerpattern.concreteobserver;

import java.util.ArrayList;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.model.collectible.Armes;
import fr.rpgastral.model.collectible.Tenue;

/**
 * correspond au choix 2 lors du choix entre différentes armes ou tenues
 */
public class Msg2 extends concreteobserver{

	public Msg2(String name) {
		super(name);
	}
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"MsgScreen", true, "2"))) {
			//dans le cas d'une tenue
			if(event.getC() instanceof Tenue) {
				game.getTiledModel().getTenues().add(game.getGamescreen().getPlayer().getTenue());
				if(game.getGamescreen().getPlayer().getTenue().getName().equals("Bénédiction de Susanoo")) {
					if(game.getGamescreen().getPlayer().GetPV()-2>=0.25) {
						game.getGamescreen().getPlayer().SetPV(game.getGamescreen().getPlayer().GetPV()-2);
					}
					else {
						game.getGamescreen().getPlayer().SetPV(0.25f);
					}
				}
				else if(game.getGamescreen().getPlayer().getTenue().getName().equals("Eclat de Tsukuyomi")) {
					game.getGamescreen().getPlayer().SetMana(game.getGamescreen().getPlayer().GetMana() -2);
				}
				else if(game.getGamescreen().getPlayer().getTenue().getName().equals("Armure de Bishamonten")) {
					game.getGamescreen().getPlayer().SetBonusAttaque(game.getGamescreen().getPlayer().GetBonusAttaque() -2);
				}
				game.getGamescreen().getPlayer().setTenue((Tenue) event.getC());
				event.getC().dispawn();
				((Tenue)event.getC()).effect(game.getGamescreen().getPlayer());
			}
			//dans le cas d'une arme
			if(event.getC() instanceof Armes) {
				//cas d'une arme à deux mains
				if(((Armes) event.getC()).getMaindouble()) {
					if(game.getGamescreen().getPlayer().getMd()!= null) {
						game.getTiledModel().getArmes().add(game.getGamescreen().getPlayer().getMd());
					}
					if(game.getGamescreen().getPlayer().getMg()!= null) {
						//il faut vérifier qu'on mette mg sur une case atteignable par le joueur...
						verificationdrop(game);
					}
					game.getGamescreen().getPlayer().setMd((Armes) event.getC());
					game.getGamescreen().getPlayer().setMg((Armes) event.getC());
					event.getC().dispawn();
				}
				//cas d'une arme à une main
				else {
					game.getTiledModel().getArmes().add(game.getGamescreen().getPlayer().getMd());
					game.getGamescreen().getPlayer().setMd((Armes) event.getC());
					event.getC().dispawn();
				}
			}
			game.setScreen(game.getGamescreen());
		}
	}
	
	/**
	 * sert à vérifier qu'on drop l'arme de la main gauche à un endroit atteignable par le joueur
	 * on autorise les cases de terrain eau car avec la bonne tenue elles sont atteignables
	 * @param game
	 */
	private void verificationdrop(RpgMain game) {
		Boolean valid = false;
		Integer k = 1;
		while(!valid) {
			Boolean existx1 = false;
			Boolean existx2 = false;
			Boolean existy1 = false;
			Boolean existy2 = false;
			for(int i = 0; i< game.getTiledModel().getObstacles().size(); i++ ) {
				int x = game.getTiledModel().getObstacles().get(i).Getx();
				int y = game.getTiledModel().getObstacles().get(i).Gety();
				if (x == game.getGamescreen().getPlayer().Getx()+k && y == game.getGamescreen().getPlayer().Gety()) {
					existx1 = true;
				}
				if (x == game.getGamescreen().getPlayer().Getx()-k && y == game.getGamescreen().getPlayer().Gety()) {
					existx2 = true;
				}
				if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()+k) {
					existy1 = true;
				}
				if (x == game.getGamescreen().getPlayer().Getx() && y == game.getGamescreen().getPlayer().Gety()-k) {
					existy2 = true;
				}
			}
			if(!existx1) {
				Armes mg = game.getGamescreen().getPlayer().getMg();
				mg.setX(game.getGamescreen().getPlayer().getX()+k);
				game.getTiledModel().getArmes().add(mg);
				valid=true;
			}
			else if(!existx2) {
				Armes mg = game.getGamescreen().getPlayer().getMg();
				mg.setX(game.getGamescreen().getPlayer().getX()-k);
				game.getTiledModel().getArmes().add(mg);
				valid=true;
			}
			else if(!existy1) {
				Armes mg = game.getGamescreen().getPlayer().getMg();
				mg.setY(game.getGamescreen().getPlayer().getY()+k);
				game.getTiledModel().getArmes().add(mg);
				valid=true;
			}
			else if(!existy2) {
				Armes mg = game.getGamescreen().getPlayer().getMg();
				mg.setY(game.getGamescreen().getPlayer().getY()-k);
				game.getTiledModel().getArmes().add(mg);
				valid = true;
			}
			k = k+1;
		}
	}

}
