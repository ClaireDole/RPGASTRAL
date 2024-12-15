package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.RpgMain;
import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.model.collectible.Tenue;

public class Msg2 extends concreteobserver{

	public Msg2(String name) {
		super(name);
	}
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"MsgScreen", true, "2"))) {
			if(event.getC() instanceof Tenue) {
				game.getTiledModel().getTenue().add(game.getGamescreen().getPlayer().getTenue());
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
			game.setScreen(game.getGamescreen());
		}
	}

}
