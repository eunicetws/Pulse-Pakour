package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entity.Player;
import main.GamePanel;
import situations.SituationHandler;

public class Playing extends State implements Statemethods {
    SituationHandler sh = new SituationHandler();
    Player player = new Player(GamePanel.TILE_SIZE*6, GamePanel.TILE_SIZE*5-10, 36, 83);

    public Playing(GamePanel gp) {
		super(gp);
        player.loadSituationData(sh.getCurrentSituation().getSData());
	}

    public void update(){
        player.update();
        sh.update();
    }

    public void windowFocusLost() {
		player.resetDirBooleans();
	}

    public void draw(Graphics g){
        
        drawGrid(g);
        sh.draw(g);
        player.draw(g);
        
        g.dispose();   //used to save memory by removing g2
    }

    private void drawGrid(Graphics g) {
        for (int row = 0; row < GamePanel.SCREEN_ROW; row++) {
            for (int col = 0; col < GamePanel.SCREEN_COL; col++) {
                // Alternating colors: light gray and dark gray
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.GRAY);
                }
                
                int x = col * GamePanel.TILE_SIZE;
                int y = row * GamePanel.TILE_SIZE;
                
                // Fill the tile with the selected color
                g.fillRect(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
            }
        }
    }

	public void mouseClicked(MouseEvent e){
        
    }

	public void mousePressed(MouseEvent e){
        
    }

	public void mouseReleased(MouseEvent e){
        
    }

	public void mouseMoved(MouseEvent e){
        
    }

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			getPlayer().setUp(false);
			break;
		case KeyEvent.VK_A:
			getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
			getPlayer().setDown(false);
			break;
		case KeyEvent.VK_D:
			getPlayer().setRight(false);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			getPlayer().setUp(true);
			break;
		case KeyEvent.VK_A:
			getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			getPlayer().setDown(true);
			break;
		case KeyEvent.VK_D:
			getPlayer().setRight(true);
			break;
		}
	}

    public Player getPlayer() {
		return player;
	}
}
