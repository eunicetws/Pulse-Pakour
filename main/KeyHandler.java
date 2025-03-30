package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener {
    Player player;
    GamePanel gp;
    public boolean jump, crouch;

    private void KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) { // Spacebar
            
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
