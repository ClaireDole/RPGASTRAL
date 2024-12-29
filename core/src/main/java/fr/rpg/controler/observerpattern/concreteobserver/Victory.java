package fr.rpg.controler.observerpattern.concreteobserver;

import java.util.ArrayList;

import fr.rpg.controler.RpgMain;
import fr.rpg.controler.observerpattern.Event;
import fr.rpg.view.VictoryScreen;

/**
 * vérification de si le joueur à gagner en éliminant tous les monstres obligatoires
 */
public class Victory extends concreteobserver {
	
	public Victory(String name) {
		super(name);
	}
	
	@Override
	public void update(Event event) {
		RpgMain game = event.getGame();
		if(event.compare(new Event(game,"Player",false,"victory",null))) {
			ArrayList<Integer> valid = new ArrayList<Integer>();
			game.getTiledModels().forEach(model -> {if(model.getEnemys() != null)
				if(!(model.getEnemys().isEmpty())){
					valid.add(1);
				}
			});
			if(valid.isEmpty()) {
				game.getGameScreens().forEach(screen -> screen.getBackground().stop());
				game.setScreen(new VictoryScreen(game));
			}
		}
	}
}
