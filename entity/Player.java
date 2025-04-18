package entity;

import java.awt.Graphics;


import main.GamePanel;
import static utilz.Collision.*;

public class Player extends Entity {
	private int TILE_SIZE = GamePanel.TILE_SIZE;

    private boolean left, up, right, down;
	private boolean inAir = false;
	
    private float playerSpeed = 1;
	private float airSpeed = 0f;
	private float gravity = 0.1f;
	private float fallSpeedAfterCollision = 1f;
	private float jumpSpeed = -4.5f;

	private int offset_y = 54;
	private int offset_x = TILE_SIZE + 12;
    Animation idle, running, jump;

    private int[][] situationData;

    public Player(int x, int y, int width, int height){
        super(x, y, width, height);
        running = new Animation(5, 13,"/res/player/player_walk/Chara_BlueWalk");
        idle = new Animation(20, 19,"/res/player/player_idle/Chara - BlueIdle");
		initHitbox(x + offset_x, y+offset_y, width, height);
    }

    public void loadSituationData(int[][] situationData){
        this.situationData = situationData;
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

		float xSpeed = 0;
		if (up)
			jump();
		if (!left && !right && !inAir)
			return;

		if (left)
			xSpeed = -playerSpeed;
		else if (right)
			xSpeed = playerSpeed;

		updateXPos(xSpeed);

		if (!inAir)
			if (!IsEntityOnFloor(hitbox, situationData))
				inAir = true;
		
		if (inAir) {
			if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, situationData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				if (IsEntityOnFloor(hitbox, situationData)){
					inAir = false;
					airSpeed = 0;
				}
				else{
					airSpeed = fallSpeedAfterCollision;
				}
			}

		} else
			updateXPos(xSpeed);
	}
	

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, situationData)) {
			hitbox.x += xSpeed;
		}
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

    public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
    
}