package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.model.collectible.Armes;
import fr.rpg.model.collectible.Tenue;

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
		if(event.compare(new Event(game,"MsgScreen", true, "2",null))) {
			//dans le cas d'une tenue
			if(event.getC() instanceof Tenue) {
				event.getEcran().getTiledmodel().getTenues().add(game.getPlayer().getTenue());
				if(game.getPlayer().getTenue().getName().equals("Bénédiction de Susanoo")) {
					if(game.getPlayer().getPV()-2>=0.25) {
						game.getPlayer().SetPV(game.getPlayer().getPV()-2);
					}
					else {
						game.getPlayer().SetPV(0.25f);
					}
				}
				else if(game.getPlayer().getTenue().getName().equals("Eclat de Tsukuyomi")) {
					game.getPlayer().setMana(game.getPlayer().getMana() -2);
				}
				else if(game.getPlayer().getTenue().getName().equals("Armure de Bishamonten")) {
					game.getPlayer().setBonusAttaque(game.getPlayer().getBonusAttaque() -2);
				}
				game.getPlayer().setTenue((Tenue) event.getC());
				event.getC().dispawn(event.getEcran().getTiledmodel());
				((Tenue)event.getC()).effect(game.getPlayer());
			}
			//dans le cas d'une arme
			if(event.getC() instanceof Armes) {
				//cas d'une arme à deux mains
				if(((Armes) event.getC()).getMaindouble()) {
					if(game.getPlayer().getMd()!= null) {
						event.getEcran().getTiledmodel().getArmes().add(game.getPlayer().getMd());
					}
					if(game.getPlayer().getMg()!= null) {
						//il faut vérifier qu'on mette mg sur une case atteignable par le joueur...
						verificationdrop(game,event);
					}
					game.getPlayer().setMd((Armes) event.getC());
					game.getPlayer().setMg((Armes) event.getC());
					event.getC().dispawn(event.getEcran().getTiledmodel());
				}
				//cas d'une arme à une main
				else {
					event.getEcran().getTiledmodel().getArmes().add(game.getPlayer().getMd());
					game.getPlayer().setMd((Armes) event.getC());
					event.getC().dispawn(event.getEcran().getTiledmodel());
				}
			}
			game.setScreen(game.getPlayer().getGamescreen());
		}
	}
	
	/**
	 * sert à vérifier qu'on drop l'arme de la main gauche à un endroit atteignable par le joueur
	 * on autorise les cases de terrain eau car avec la bonne tenue elles sont atteignables
	 * @param game
	 */
	private void verificationdrop(RpgMain game,Event event) {
		Boolean valid = false;
		Integer k = 1;
		while(!valid) {
			Boolean existx1 = false;
			Boolean existx2 = false;
			Boolean existy1 = false;
			Boolean existy2 = false;
			for(int i = 0; i< event.getEcran().getTiledmodel().getObstacles().size(); i++ ) {
				int x = event.getEcran().getTiledmodel().getObstacles().get(i).Getx();
				int y = event.getEcran().getTiledmodel().getObstacles().get(i).Gety();
				if (x == game.getPlayer().getX()+k && y == game.getPlayer().getY()) {
					existx1 = true;
				}
				if (x == game.getPlayer().getX()-k && y == game.getPlayer().getY()) {
					existx2 = true;
				}
				if (x == game.getPlayer().getX() && y == game.getPlayer().getY()+k) {
					existy1 = true;
				}
				if (x == game.getPlayer().getX() && y == game.getPlayer().getY()-k) {
					existy2 = true;
				}
			}
			if(!existx1) {
				Armes mg = game.getPlayer().getMg();
				mg.setX(game.getPlayer().getX()+k);
				event.getEcran().getTiledmodel().getArmes().add(mg);
				valid=true;
			}
			else if(!existx2) {
				Armes mg = game.getPlayer().getMg();
				mg.setX(game.getPlayer().getX()-k);
				event.getEcran().getTiledmodel().getArmes().add(mg);
				valid=true;
			}
			else if(!existy1) {
				Armes mg = game.getPlayer().getMg();
				mg.setY(game.getPlayer().getY()+k);
				event.getEcran().getTiledmodel().getArmes().add(mg);
				valid=true;
			}
			else if(!existy2) {
				Armes mg = game.getPlayer().getMg();
				mg.setY(game.getPlayer().getY()-k);
				event.getEcran().getTiledmodel().getArmes().add(mg);
				valid = true;
			}
			k = k+1;
		}
	}

}
