package fr.rpgastral.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font {
	private BitmapFont font1;
	private BitmapFont font2;
	
	public BitmapFont Getfont1() {
		this.font1 = new BitmapFont();
		this.font1.setColor(Color.BLACK);
		return this.font1;
	}
	
	public BitmapFont Getfont2() {
		this.font2 = new BitmapFont();
		this.font2.setColor(Color.ROYAL);
		return this.font2;
	}
	
}
