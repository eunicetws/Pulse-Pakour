package entity;

import java.awt.Graphics2D;
import java.awt.Color;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
    }

    public void update(){
        
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }
}
