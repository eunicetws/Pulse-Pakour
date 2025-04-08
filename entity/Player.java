package entity;

import java.awt.Graphics;


import main.GamePanel;
import static utilz.Collision.CanMoveHere;

public class Player extends Entity {
    private boolean moving;
    private boolean left, up, right, down;
	private int TILE_SIZE = GamePanel.TILE_SIZE;
    private int playerSpeed;
	private int offset_y = 54;
	private int offset_x = TILE_SIZE + 12;
    Animation idle, running, jump;

    private int[][] situationData;

    public Player(int x, int y, int width, int height){
        super(x, y, width, height);
        running = new Animation(5, 13,"/res/player/player_walk/Chara_BlueWalk");
        idle = new Animation(20, 19,"/res/player/player_idle/Chara - BlueIdle");
		initHitbox(x + offset_x, y+offset_y, width / 2 - 28, height-45);
        playerSpeed = 1;
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
		moving = false;
		if (!left && !right && !up && !down)
			return;

		float xSpeed = 0, ySpeed = 0;

		if (left && !right)
			xSpeed = -playerSpeed;
		else if (right && !left)
			xSpeed = playerSpeed;

		if (up && !down)
			ySpeed = -playerSpeed;
		else if (down && !up)
			ySpeed = playerSpeed;

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, situationData)) {
			hitbox.x += xSpeed;
			hitbox.y += ySpeed;
			moving = true;
		}
    }

    public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
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