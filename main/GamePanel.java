package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
    public final int TILESET = 40, SCALE = 3;
    public final int TILE_SIZE = TILESET * SCALE;

    public final int SCREEN_COL = 16, SCREEN_ROW = 12;
    public final int SCRREN_WIDTH = TILE_SIZE * SCREEN_COL;
    public final int SCRREN_HEIGHT = TILE_SIZE * SCREEN_ROW;

    //FPS
    final int FPS = 60;
    final int UPS = 200;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); //GamePanel focuse on receiving key input
    }

    public void startGameThread(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    public void run(){
        double timePerFrame = 1_000_000_000/FPS;
        double timePerUpdate = 1_000_000_000/UPS;
        
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
    }
    
    private void drawGrid(Graphics2D g2) {
        for (int row = 0; row < SCREEN_ROW; row++) {
            for (int col = 0; col < SCREEN_COL; col++) {
                // Alternate between grey and white squares
                if ((row + col) % 2 == 0) {
                    g2.setColor(Color.GRAY); // Grey color for even sum of row+col
                } else {
                    g2.setColor(Color.WHITE); // White color for odd sum of row+col
                }

                // Draw each square in the grid
                g2.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawGrid(g2);
        player.draw(g2);
        
        g2.dispose();   //used to save memory by removing g2
    }
}
