package utilz;

import main.GamePanel;
import java.awt.geom.Rectangle2D;

public class Collision {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] situationData) {
		if (!IsSolid(x, y, situationData))
			if (!IsSolid(x + width, y + height, situationData))
				if (!IsSolid(x + width, y, situationData))
					if (!IsSolid(x, y + height, situationData))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] situationData) {
		if (x < 0 || x >= GamePanel.SCREEN_WIDTH)
			return true;
		if (y < 0 || y >= GamePanel.SCREEN_HEIGHT)
			return true;

		float xIndex = x / GamePanel.TILE_SIZE; // index of tiles by the x axis
		float yIndex = y / GamePanel.TILE_SIZE; // index of tiles by the y axis

		int value = situationData[(int) yIndex][(int) xIndex];

		if (value >= 48 || value < 0 || value == 49)
			return false;
		return true;
	} 

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		// Check the pixel below bottomleft and bottomright
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
				return false;
		return true;
	}

	public static boolean EntityHitsRoof(Rectangle2D.Float hitbox, int[][] lvlData) {
		// Check the pixel below bottomleft and bottomright
		if (!IsSolid(hitbox.x, hitbox.y+1 + hitbox.height, lvlData))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y+1 + hitbox.height, lvlData))
				return false;
		return true;
	}
	
}
