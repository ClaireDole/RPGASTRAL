package fr.rpgastral.model.entity;

import fr.rpgastral.controler.RpgMain;

public class PNJ extends Entity{
	public String race;
    String message;
    private RpgMain game;

    public PNJ(int x, int y, final RpgMain game) {
		super(x, y, game);
	}
    public void Dialogue(){
        System.out.println(this.message);
        if (this.race =="elfe"){
            // demander si on veut lancer un soin sur le player
        }
    }

    public void Soin(Player p){
    	if(p.Gettenue().Getname()=="Bénédiction de Susanoo") {
    		p.SetPV(5);
    	}
    	else if(p.Gettenue().Getname()=="Eclat de Tsukuyomi") {
    		p.SetMana(6);
    	}
    	else if(p.Gettenue().Getname()=="Broche d'Izanami") {
    		p.SetDéplacement(5);
    	}
    	else {
    		p.SetPV(3);
    		p.SetMana(3);
    		p.SetDéplacement(3);
    	}
    }
}