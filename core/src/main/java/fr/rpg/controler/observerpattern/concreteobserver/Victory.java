package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.VictoryScreen;

/**
 * à chaque attaque de la part du player
 * vérification de si le joueur à gagner en éliminant tous les monstres obligatoires
 */
public class Victory extends concreteobserver {

	public Victory(String name) {
		super(name);
	}

	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compareTo(new Event(game,"Player",false,"victory",null))==0) {
			if(test(game)) {
				game.getGameScreens().forEach(screen -> screen.getBackground().stop());
				game.setScreen(new VictoryScreen(game));
			}
		}
	}
	private Boolean test(RpgMain game) {
		for(int i=0; i<game.getTiledModels().size(); i++) {
			if(!(game.getTiledModels().get(i).getEnemys().isEmpty())) {
				return false;
			}
		}
		return true;
	}
}
