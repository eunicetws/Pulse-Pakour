package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
    public final static int TILESET = 20, SCALE = 3;
    public final static int TILE_SIZE = TILESET * SCALE;

    public final static int SCREEN_COL = 16, SCREEN_ROW = 12;
    public final static int SCRREN_WIDTH = TILE_SIZE * SCREEN_COL;
    public final static int SCRREN_HEIGHT = TILE_SIZE * SCREEN_ROW;

    //default position
    int playerX = 100, playerY = 100;

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); //GamePanel focuse on receiving jey input
    }

    public void startGameThread(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int FPS = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                FPS++;
            }

            if (timer >= 1_000_000_000){
                timer = 0;
                System.out.println("FPS" + FPS);
                FPS = 0;
                timer = 0;
            }
        }

    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        player.draw(g2);
        
        g2.dispose();   //used to save memory by removing g2
    }
}
