package main;

import javax.swing.*;

import main.GamePanel;

public class Main {
    public static int WIDTH, HEIGHT;
    public static int WIDTH_RATIO = 16, HEIGHT_RATIO = 9;
    GamePanel gp;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UI()); // UI is now in the same package
    }

    public static void startGame() {
        JFrame window = new JFrame();
        GamePanel gp = new GamePanel();
        
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setMinimumSize(new Dimension( gp.SCRREN_WIDTH, gp.SCRREN_HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pulse Pakour");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
