package gamestates;

import java.awt.event.MouseEvent;

import main.GamePanel;
import ui.Buttons;

public class State {

	protected GamePanel gp;

	public State(GamePanel gp) {
		this.gp = gp;
	}

	public boolean isIn(MouseEvent e, Buttons mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public GamePanel getGame() {
		return gp;
	}
}