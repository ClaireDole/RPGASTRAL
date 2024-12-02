package fr.rpgastral.controler.observerpattern;

public interface sujet {
	void attach(Observer o);
	void unattach(Observer o);
	void notify (Event e);
}
