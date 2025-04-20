package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private GamePanel gp;

	public MouseHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gp.getMenu().mouseMoved(e);
			break;
		case PLAYING:
			gp.getPlaying().mouseMoved(e);
			break;
		default:
			break;

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gp.getMenu().mouseClicked(e);
				break;
			case PLAYING:
				gp.getPlaying().mouseClicked(e);;
				break;
			default:
				break;
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gp.getMenu().mousePressed(e);
				break;
			case PLAYING:
				gp.getPlaying().mousePressed(e);
				break;
			default:
				break;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gp.getMenu().mouseReleased(e);
				break;
			case PLAYING:
				gp.getPlaying().mouseReleased(e);
				break;
			default:
				break;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}