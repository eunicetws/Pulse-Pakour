package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    public UI() {
        setTitle("Pulse Parkour Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create Panel for Background
        JPanel panel = new JPanel() {
            private Image background = new ImageIcon("assets/background.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        // Load Buttons with Icons
        JButton playButton = createButton("assets/button_play.png", "assets/button_play_hover.png", 350, 120);
        JButton tutorialButton = createButton("assets/button_tutorial.png", "assets/button_tutorial_hover.png", 350, 180);
        JButton exitButton = createButton("assets/button_exit.png", "assets/button_exit_hover.png", 350, 240);

        // Button Actions
        playButton.addActionListener(e -> {
            dispose(); // Close menu
            Main.startGame(); // Start Game
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Add to Panel
        panel.add(playButton);
        panel.add(tutorialButton);
        panel.add(exitButton);
        add(panel);

        setVisible(true);
    }

    // Create Button with Hover Effects
    private JButton createButton(String normalImage, String hoverImage, int x, int y) {
        JButton button = new JButton(new ImageIcon(normalImage));
        button.setBounds(x, y, 150, 50);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setRolloverIcon(new ImageIcon(hoverImage)); // Hover effect
        return button;
    }
}
