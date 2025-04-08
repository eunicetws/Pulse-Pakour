package utilz;

import main.GamePanel;

public class Collision {
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsSolid(x, y, lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		if (x < 0 || x >= GamePanel.SCREEN_WIDTH)
			return true;
		if (y < 0 || y >= GamePanel.SCREEN_HEIGHT)
			return true;

		float xIndex = x / GamePanel.TILE_SIZE; // index of tiles by the x axis
		float yIndex = y / GamePanel.TILE_SIZE; // index of tiles by the y axis

		int value = lvlData[(int) yIndex][(int) xIndex];

		if (value >= 48 || value < 0 || value == 49)
			return false;
		return true;
	} 
}
