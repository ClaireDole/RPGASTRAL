package fr.rpg.controler.observerpattern;

/**
 * interface des concreteobservers
 * on utilise nos propres interfaces et classes car celles proposées par java pour implémenter l'observerpattern sont obsolètes
 */
public interface Observer {
	/**
	 * fonctionnement à exécuter si l'évènement correspond bien à l'évènement attendu
	 * @param event évènement venant de se produire
	 */
	void update (Event event);
}
