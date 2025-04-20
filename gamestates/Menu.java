package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entity.Animation;
import main.GamePanel;
import ui.Buttons;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

	private Buttons[] buttons = new Buttons[3];
	private BufferedImage backgroundImg;
    Animation idle;

	public Menu(GamePanel gp) {
		super(gp);
		loadButtons();
		loadBackground();

	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSprite("/res/menu/MainMenu2.png");
        idle = new Animation(20, 19,"/res/player/player_idle/Chara - BlueIdle");
	}

	private void loadButtons() {
		buttons[0] = new Buttons("/res/menu/button1.png", "/res/menu/button1.1.png", 535, 210, 285, 70, Gamestate.PLAYING);
		buttons[1] = new Buttons("/res/menu/button2.png", "/res/menu/button2.1.png", 535, 300, 285, 70, Gamestate.SETTINGS);
		buttons[2] = new Buttons("/res/menu/button3.png", "/res/menu/button3.1.png", 535, 392, 285, 70, Gamestate.CLOSE);
	}

	@Override
	public void update() {
        idle.updateAnimationTick();
		for (Buttons mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(backgroundImg, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT-30, null);
        g.drawImage(idle.getCurrentFrame(), 120, 120, 448, 448, null);
		for (Buttons mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Buttons mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Buttons mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				break;
			}
		}

		resetButtons();

	}

	private void resetButtons() {
		for (Buttons mb : buttons)
			mb.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Buttons mb : buttons)
			mb.setMouseOver(false);

		for (Buttons mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			Gamestate.state = Gamestate.PLAYING;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}