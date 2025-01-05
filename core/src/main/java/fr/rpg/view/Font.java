package fr.rpg.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * classe regroupant les differentes font utilisées pour l'affichage du jeu
 **/
public class Font {
	private BitmapFont font1;
	private BitmapFont font2;
	private BitmapFont font3;

	public Font() {
		this.font1 = null;
		this.font2 = null;
		this.font3 = null;
	}

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

	public BitmapFont Getfont3() {
		this.font3 = new BitmapFont();
		this.font3.setColor(Color.NAVY);
		return this.font3;
	}

	public void dispose() {
		if(this.font1 != null) {
			this.font1.dispose();
		}
		if (this.font2 != null) {
			this.font2.dispose();
		}
		if (this.font3 != null) {
			this.font3.dispose();
		}
	}
}
