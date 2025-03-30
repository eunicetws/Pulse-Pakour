package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class LoadSave {
    public static final String TILE_SPRITE = "/res/tiles/Mossy - TileSet.png";
    public static final String S1 = "/res/situation_data/grid_1.png";
    public static final String S2 = "/res/situation_data/grid_2.png";
    
    public static BufferedImage GetSprite(String fileName) {
		BufferedImage img = null;
        try {
            InputStream is = LoadSave.class.getResourceAsStream(fileName);
            if (is == null) {
                System.out.println("Resource not found: " + fileName);
                return null;
            }
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
	}

    public static int[][] getSData(String fileName){
        BufferedImage img = GetSprite(fileName);
        int[][] s_data = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j <img.getHeight(); j++)
            for(int i = 0; i < img.getWidth(); i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if(value >= 49)
                    value = 49;
                s_data[j][i]=value;
            }
        return s_data;
    }
}
