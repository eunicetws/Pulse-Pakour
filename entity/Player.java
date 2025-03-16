package entity;

import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player {
    GamePanel gp;
    KeyHandler keyH;
    Animation walking;
    int x, y;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        walking = new Animation(10, 13,"/res/player_walk/Chara_BlueWalk");
        //speed = (FPS / animation per sec) :. 60/6
        
        setDefaultValues();
    }

    public void setDefaultValues(){
        x = 0;
        y = (gp.TILE_SIZE)*4;
    }

    public void update(){
        walking.updateAnimationTick();
        x += 0;
        y += 0;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(walking.getCurrentFrame(), x, y, gp.TILE_SIZE*2, gp.TILE_SIZE*2, null);
    }
}
