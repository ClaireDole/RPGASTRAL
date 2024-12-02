package fr.rpgastral.controler.observerpattern;

public class Event {
	private String screen;
	private Boolean keypressed;
	private String touche;
	
	public Event(String m, Boolean k, String t) {
		this.screen = m;
		this.keypressed = k;
		this.touche = t;
	}
	
	public Boolean compare(Event e) {
		if(this.screen==e.screen && this.keypressed==e.keypressed && this.touche == e.touche) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public Boolean getKeypressed() {
		return keypressed;
	}

	public void setKeypressed(Boolean keypressed) {
		this.keypressed = keypressed;
	}

	public String getTouche() {
		return touche;
	}

	public void setTouche(String touche) {
		this.touche = touche;
	}
}
