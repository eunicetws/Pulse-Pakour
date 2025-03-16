package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {
    private BufferedImage[] animate;
    private String source;
    private int size;
    private int aniTick, aniIndex, aniSpeed; 

    public Animation(int aniSpeed, int size, String source) {
        this.aniSpeed = aniSpeed;
        this.size = size;
        this.source = source;
        loadAnimation(this.size);  // Load images when the object is created
    }
    
    private void loadAnimation(int size){
        animate = new BufferedImage[size];

        for(int i = 0; i < animate.length; i++){
            try{
                animate[i] = ImageIO.read(getClass().getResourceAsStream(source + i + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public int updateAnimationTick(){
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= animate.length){
                aniIndex = 0;
            }
        }
        return aniIndex;
    }

    public BufferedImage getCurrentFrame() {
        return animate[aniIndex];  
    }

}
