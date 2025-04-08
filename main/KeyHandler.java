package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener {
    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
    
    @Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gp.getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A:
			gp.getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
			gp.getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D:
			gp.getPlayer().setRight(false);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gp.getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A:
			gp.getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			gp.getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D:
			gp.getPlayer().setRight(true);
			break;
		}
	}

    @Override
    public void keyTyped(KeyEvent e) {
        // No need to implement keyTyped for key states
    }
}
