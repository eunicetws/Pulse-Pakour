package utilz;

import main.GamePanel;
import situations.SituationHandler;
import java.awt.geom.Rectangle2D;

public class Collision {
    public static boolean CanMoveHere(float x, float y, float width, float height,  int[][] situation1, int[][] situation2) {
		if (!IsSolid(x, y, situation1, situation2))
			if (!IsSolid(x + width, y + height, situation1, situation2))
				if (!IsSolid(x + width, y, situation1, situation2))
					if (!IsSolid(x, y + height, situation1, situation2))
						return true;
		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] situation1, int[][] situation2) {
		if (x < 0 || x >= GamePanel.SCREEN_WIDTH)
			return true;
		if (y < 0 || y >= GamePanel.SCREEN_HEIGHT)
			return true;
		
		float xIndex = (x + SituationHandler.sOffset) / GamePanel.TILE_SIZE; // index of tiles by the x axis
		float yIndex = y / GamePanel.TILE_SIZE; // index of tiles by the y axis

		int value;

		if (yIndex > 9)
			return false;

		if (xIndex < 16) 
			value = situation1[(int) yIndex][(int) xIndex];
		else
			value = situation2[(int) yIndex][(int) xIndex - 15];

		if (value > 48 || value < 0 || value == 49)
			return false;
		return true;
	} 

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox,  int[][] situation1, int[][] situation2) {
		// Check the pixel below bottomleft and bottomright
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, situation1, situation2))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, situation1, situation2))
				return false;
		return true;
	}

	public static boolean EntityHitsRoof(Rectangle2D.Float hitbox,  int[][] situation1, int[][] situation2) {
		// Check the pixel below bottomleft and bottomright
		if (!IsSolid(hitbox.x, hitbox.y+1 + hitbox.height, situation1, situation2))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y+1 + hitbox.height, situation1, situation2))
				return false;
		return true;
	}
	
}
