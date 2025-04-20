package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gamestates.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int TILE_SIZE = 64;
    public static final int SCREEN_COL = 16, SCREEN_ROW = 9;
    public static final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROW + 30;

    private final int FPS = 60;
    private final int UPS = 200;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
	MouseHandler MouseH = new MouseHandler(this);
    private Playing playing = new Playing(this);
	private Menu menu = new Menu(this);
    

    public GamePanel() {
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
		this.addMouseListener(MouseH);
        this.setFocusable(true); //GamePanel focuse on receiving key input
    }

    public void startGameThread(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    public void run() {

		double timePerFrame = 1000000000 / FPS;
		double timePerUpdate = 1000000000 / UPS;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}

    public void update() {
		switch (Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;

		}
	}

    public void paintComponent(Graphics g) {
        switch (Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		case CLOSE:
            System.exit(0);
		default:
			break;
		}
    }

	public Playing getPlaying() {
		return playing;
	}

	public Menu getMenu() {
		return menu;
	}
}