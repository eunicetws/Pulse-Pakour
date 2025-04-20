package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
    public static int WIDTH, HEIGHT;
    public static int WIDTH_RATIO = 16, HEIGHT_RATIO = 9;
    GamePanel gp;
    
    public static void main(String[] args) {
        JFrame window = new JFrame();
        
        window.setMinimumSize(new Dimension( GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
        window.pack();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pulse Pakour");

        GamePanel gamePanel = new GamePanel();  
        window.add(gamePanel);

        window.setLocationRelativeTo(null); // screen will appear in the middle
        window.setVisible(true);            // allow us to see the window

        gamePanel.startGameThread(); // start game
    }

}
