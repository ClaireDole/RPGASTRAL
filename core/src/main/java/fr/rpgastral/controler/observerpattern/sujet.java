package fr.rpgastral.controler.observerpattern;

import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;

/**
 * interface des observables
 * on utilise nos propres interfaces et classes car celles proposées par java pour implémenter l'observerpattern sont obsolètes
 */
public interface sujet {
	void attach(concreteobserver o);
	void unattach(Observer o);
	void notify (Event e);
}
