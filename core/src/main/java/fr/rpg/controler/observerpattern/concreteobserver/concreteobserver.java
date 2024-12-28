package fr.rpg.controler.observerpattern.concreteobserver;

import fr.rpg.controler.observerpattern.Event;
import fr.rpg.controler.observerpattern.Observer;

/**
 * classe correspondant aux observers, chaque concreteobserver ne fonctionne que pour un seul event en particulier
 * ils implementent tous l'interface observer et sont tous en permanence a l'ecoute de nouveaux event
 */

public class concreteobserver implements Observer {
	private String name;
	
	/**
	 * constructeur
	 * @param name nom
	 */
	 public concreteobserver(String name) {
		 this.name=name;
	 }
	 /**
	  * getter
	  * @return nom
	  */
	public String getName() {
		return name;
	}
	/**
	 * setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void update(Event event) {	
	}
}
