package entity;

import java.awt.Graphics;


import main.GamePanel;
import static utilz.Collision.*;

public class Player extends Entity {
	private int TILE_SIZE = GamePanel.TILE_SIZE;

    private boolean up, down, stop;
	private boolean inAir = false;
	
	private float airSpeed = 0f;
	private float gravity = 0.1f;
	private float fallSpeedAfterCollision = 1f;
	private float jumpSpeed = -4.5f;

	private int offset_y = 54;
	private int offset_x = TILE_SIZE + 12;
    Animation running, jump;

	private int sh_offset;

    private int[][] situation1, situation2;

    public Player(int x, int y, int width, int height){
        super(x, y, width, height);
        running = new Animation(5, 13,"/res/player/player_walk/Chara_BlueWalk");
		initHitbox(x + offset_x, y+offset_y, width, height);
    }

    public void loadSituationData1(int[][] situation1){
        this.situation1 = situation1;
    }
	public void loadSituationData2(int[][] situation2){
        this.situation2 = situation2;
    }

    public void update(){
        updatePos();
        running.updateAnimationTick();
    }

    public void draw(Graphics g){
        g.drawImage(running.getCurrentFrame(), (int) hitbox.x-offset_x, (int) hitbox.y-offset_y, TILE_SIZE*3, TILE_SIZE*3, null);
        drawHitbox(g);
    }

    private void updatePos() {

	 	if (up)
			jump();

		 if (!inAir)
			if (!IsEntityOnFloor(hitbox, situation1, situation2))
				inAir = true;
		
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, situation1, situation2)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
			} else {
				if (IsEntityOnFloor(hitbox, situation1, situation2)){
					inAir = false;
					airSpeed = 0;
				}
				else{
					airSpeed = fallSpeedAfterCollision;
				}
			}
		}
		if (!CanMoveHere(hitbox.x + sh_offset+(1*sh_offset), hitbox.y, hitbox.width, hitbox.height, situation1, situation2))
			stop = true;
		else
			stop = false;
		
	}

	public void resetDirBooleans() {
		up = false;
		down = false;
		stop = true;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isStop() {
		return stop;
	}

	public void setShOffset(int sh_offset){
		this.sh_offset = sh_offset;
	}
    
}