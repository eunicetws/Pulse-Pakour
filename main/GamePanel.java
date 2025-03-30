package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import situations.SituationHandler;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{
    public final int TILE_SIZE = 64;
    public final int SCREEN_COL = 16, SCREEN_ROW = 9;
    public final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COL;
    public final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROW + 30;

    private final int FPS = 60;
    private final int UPS = 200;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    SituationHandler sh = new SituationHandler(this);

    public GamePanel() {
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
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

    public void update(){
        player.update();
        sh.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        drawGrid(g);
        sh.draw(g);
        player.draw(g);
        
        g.dispose();   //used to save memory by removing g2
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row < SCREEN_ROW; row++) {
            for (int col = 0; col < SCREEN_COL; col++) {
                // Alternating colors: light gray and dark gray
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                
                // Fill the tile with the selected color
                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
    }

}