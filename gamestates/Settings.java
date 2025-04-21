package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import utilz.LoadSave;
import java.awt.Font;
import java.awt.Color;

import entity.Animation;
import main.GamePanel;
import ui.*;
import utilz.SaveDataHandler;

public class Settings extends State implements Statemethods {

	private Buttons[] buttons = new Buttons[1];
	private ButtonsSettings[] buttons2 = new ButtonsSettings[3];
	private BufferedImage backgroundImg;
	String buttonType = "null";
    Animation idle;
	boolean startTime;
	int jump, crouch, pause;

	public Settings(GamePanel gp) {
		super(gp);
		loadButtons();
		loadBackground();
		setControls();
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSprite("/res/settings/settingsBg.png");
	}

	private void loadButtons() {
		buttons[0] = new Buttons("/res/settings/buttons1.png", "/res/settings/buttons1.1.png", 437, 447, 160, 50, Gamestate.MENU);
		buttons2[0] = new ButtonsSettings("/res/settings/buttons4.png", "/res/settings/buttons2.2.png", 363, 185, 305, 70, "jump");
		buttons2[1] = new ButtonsSettings("/res/settings/buttons3.png", "/res/settings/buttons3.2.png", 363, 275, 305, 70, "crouch");
		buttons2[2] = new ButtonsSettings("/res/settings/buttons2.png", "/res/settings/buttons4.2.png", 363, 360, 305, 70, "pause");
	}

	@Override
	public void update() {
		startTime = true;
		for (Buttons mb : buttons)
			mb.update();
		for (ButtonsSettings mb : buttons2)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(backgroundImg, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT-30, null);
		for (Buttons mb : buttons)
			mb.draw(g);
		for (ButtonsSettings mb : buttons2)
			mb.draw(g);
		g.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		g.setColor(Color.black);
		g.drawString(KeyEvent.getKeyText(jump), 533, 235);
		g.drawString(KeyEvent.getKeyText(crouch), 533, 322);
		g.drawString(KeyEvent.getKeyText(pause), 533, 408);
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
		for (ButtonsSettings mb : buttons2) {
			if (isIn2(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Buttons mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed()){
					mb.applyGamestate();
				}
				break;
			}
		}
		for (ButtonsSettings mb : buttons2){
			if (isIn2(e, mb)) {
				if (mb.isMousePressed()){
					buttonType = mb.setButton();
				}
				break;
			}
		}

		resetButtons();

	}

	private void resetButtons() {
		for (Buttons mb : buttons)
			mb.resetBools();
		for (ButtonsSettings mb : buttons2)
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

		for (ButtonsSettings mb : buttons2)
			mb.setMouseOver(false);

		for (ButtonsSettings mb : buttons2)
			if (isIn2(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (buttonType) {
			case "jump":
				jump = e.getKeyCode();
				try {
					SaveDataHandler.overWriteFile(jump, 0);
				} catch (Exception ex) {
					
				}
				buttonType = "null";
				break;
			case "crouch":
				crouch = e.getKeyCode();
				try {
					SaveDataHandler.overWriteFile(crouch, 1);
				} catch (Exception ex) {
					
				}
				buttonType = "null";
				break;
			case "pause":
				pause = e.getKeyCode();
				try {
					SaveDataHandler.overWriteFile(pause, 2);
				} catch (Exception ex) {
					
				}
				buttonType = "null";
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	public void setControls(){
		try{
			jump = SaveDataHandler.ReadFile(0);
			crouch = SaveDataHandler.ReadFile(1);
			pause = SaveDataHandler.ReadFile(2);
		} catch(Exception e){

		}
	}

	public int getJump(){
		return jump;
	}

	public int getCrouch(){
		return crouch;
	}

	public int getPause(){
		return pause;
	}


}