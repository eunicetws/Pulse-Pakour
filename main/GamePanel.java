package main;

import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final static int TILESET = 20, SCALE = 3;
    final static int TILE_SIZE = TILESET * SCALE;

    final static int SCREEN_COL = 16, SCREEN_ROW = 12;
    final static int SCRREN_WIDTH = TILE_SIZE * SCREEN_COL;
    final static int SCRREN_HEIGHT = TILE_SIZE * SCREEN_ROW;

    Thread gameThread;

    public GamePanel() {
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){

    }
}
