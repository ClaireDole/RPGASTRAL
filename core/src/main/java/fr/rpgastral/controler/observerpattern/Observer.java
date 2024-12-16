package fr.rpgastral.controler.observerpattern;

/**
 * interface des concreteobservers
 * on utilise nos propres interfaces et classes car celles proposées par java pour implémenter l'observerpattern sont obsolètes
 */
public interface Observer {
	void update (Event event);
}
