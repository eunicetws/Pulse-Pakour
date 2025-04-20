
package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import main.GamePanel;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class Buttons {
	private int xPos, yPos, width, height, index;
	private Gamestate state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;

	public Buttons(String filename1, String filename2, int xPos, int yPos, int width, int height, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.state = state;
		loadImgs(filename1, filename2);
		initBounds();
	}

	private void initBounds() {
		bounds = new Rectangle(xPos, yPos, width, height);

	}

	private void loadImgs(String filename1, String filename2) {
		imgs = new BufferedImage[2];
        imgs[0] = LoadSave.GetSprite(filename1);
        imgs[1] = LoadSave.GetSprite(filename2);
	}

	public void draw(Graphics g) {
		g.drawImage(imgs[index], 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT-30, null);
		// g.drawRect(xPos, yPos, width, height);
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 1;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

}
