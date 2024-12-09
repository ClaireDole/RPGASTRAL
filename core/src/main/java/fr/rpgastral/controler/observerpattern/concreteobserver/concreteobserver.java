package fr.rpgastral.controler.observerpattern.concreteobserver;

import fr.rpgastral.controler.observerpattern.Event;
import fr.rpgastral.controler.observerpattern.Observer;

/**
 * classe correspondant aux observers, chaque concreteobserver ne fonctionne que pour un seul event en particulier
 * ils implementent tous l'interface observer et sont tous en permanence a l'ecoute de nouveaux event
 */

public class concreteobserver implements Observer {
	private String name;
	 public concreteobserver(String name) {
		 this.name=name;
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void update(Event event) {	
	}
}
