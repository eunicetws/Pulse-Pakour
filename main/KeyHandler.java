package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    public boolean jump, crouch;

    @Override
    public void keyTyped(KeyEvent e){
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_SPACE){ //spacebar
            jump = true;
        }
        if(code == KeyEvent.VK_CONTROL){ //ctrl key
            crouch = true;
        }
    }

    public void keyPressed(KeyEvent e){
        
    }
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_SPACE){
            jump = false;
        }
        if(code == KeyEvent.VK_CONTROL){
            crouch = false;
        }
    }
}
