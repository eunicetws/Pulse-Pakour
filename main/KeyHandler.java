package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;

public class KeyHandler implements KeyListener {
    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
    
    @Override
	public void keyReleased(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU:
			break;
		case PLAYING:
			gp.getPlaying().keyReleased(e);
			break;
		default:
			break;

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (Gamestate.state) {
		case MENU:
			break;
		case PLAYING:
			gp.getPlaying().keyPressed(e);
			break;
		default:
			break;
		}
	}

    @Override
    public void keyTyped(KeyEvent e) {
        // No need to implement keyTyped for key states
    }
}
