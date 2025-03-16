package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean jump, crouch;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) { // Spacebar
            jump = true;
        }
        if (code == KeyEvent.VK_CONTROL) { // Ctrl key
            crouch = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            jump = false;
        }
        if (code == KeyEvent.VK_CONTROL) {
            crouch = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No need to implement keyTyped for key states
    }
}
