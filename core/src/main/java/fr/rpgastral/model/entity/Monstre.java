package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;

public abstract class Monstre extends Enemy {
    private RpgMain game;

    public Monstre(int x, int y, final RpgMain game) {
		super(x, y, game);
	}

}
