package fr.rpg.controler.observerpattern;

import fr.rpg.controler.observerpattern.concreteobserver.concreteobserver;

/**
 * interface des observables
 * on utilise nos propres interfaces et classes car celles proposées par java pour implémenter l'observerpattern sont obsolètes
 */
public interface Observable {
	/**
	 * ajout 
	 * @param o concreteobserver
	 */
	void attach(concreteobserver o);
	/**
	 * suppression
	 * @param o concreteobserver
	 */
	void unattach(Observer o);
	/**
	 * notification d'un évènement
	 * @param e évènement venant de se produire
	 */
	void notify (Event e);
}
