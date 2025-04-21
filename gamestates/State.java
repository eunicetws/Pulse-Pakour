package gamestates;

import java.awt.event.MouseEvent;

import main.GamePanel;
import ui.Buttons;
import ui.ButtonsSettings;

public class State {

	protected GamePanel gp;

	public State(GamePanel gp) {
		this.gp = gp;
	}

	public boolean isIn(MouseEvent e, Buttons mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public boolean isIn2(MouseEvent e, ButtonsSettings mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public GamePanel getGame() {
		return gp;
	}
}