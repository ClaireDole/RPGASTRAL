package fr.rpgastral.controler.observerpattern;

import fr.rpgastral.controler.observerpattern.concreteobserver.concreteobserver;

public interface sujet {
	void attach(concreteobserver o);
	void unattach(Observer o);
	void notify (Event e);
}
