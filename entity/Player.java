package entity;

import java.awt.Graphics;


import main.GamePanel;
import main.KeyHandler;

public class Player {
    GamePanel gp;
    KeyHandler keyH;
    boolean up, down;
    Animation idle, running, jump;
    int x, y;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        running = new Animation(10, 13,"/res/player/player_walk/Chara_BlueWalk");
        idle = new Animation(15, 19,"/res/player/player_idle/Chara - BlueIdle");

        //speed = (FPS / animation per sec) :. 60/6

        setDefaultValues();
    }

    public void setDefaultValues(){
        x = (gp.TILE_SIZE)*6;
        y = (gp.TILE_SIZE)*5-10;
    }

    public void update(){
        updatePos();
        idle.updateAnimationTick();
        x += 0;
        y += 0;
    }

    private void updatePos() {
		if (up && !down) {
			
		} else if (!up && down) {
			
		}
        
	}

    public void draw(Graphics g){
        g.drawImage(idle.getCurrentFrame(), x, y, gp.TILE_SIZE*3, gp.TILE_SIZE*3, null);

    }

    private void isup(boolean up){
        this.up = up;
    }

    private void isdown(boolean down){
        this.down = down;
    }
}