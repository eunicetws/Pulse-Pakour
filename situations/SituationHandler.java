package situations;

import main.GamePanel;
import utilz.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SituationHandler {

    private GamePanel gp;
    private BufferedImage[] tile;
    private Situation S1;

    public SituationHandler(GamePanel gp){
        this.gp = gp;
        importTileSet();
        S1 = new Situation(LoadSave.getSData(LoadSave.S1));
    }

    private void importTileSet(){
        BufferedImage img = LoadSave.GetSprite(LoadSave.TILE_SPRITE);
        tile = new BufferedImage[50];
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 7; i++) {
                int index = j * 7 + i;
                tile[index] = img.getSubimage(i * 512, j * 512, 512, 512);  
            }
        }
        tile[49]= null;
    }

    public void draw(Graphics g){

        // int z=0;
        // for(int j=0; j<=6; j++){
        //     for(int i=0; i<=6; i++){
        //         g.drawImage(tile[z], gp.TILE_SIZE * i, gp.TILE_SIZE * j, gp.TILE_SIZE, gp.TILE_SIZE, null);
        //         z++;
        //         if (z>49){
        //             break;
        //         }
        //     }
        // }
        
        

        for(int j = 0; j * gp.TILE_SIZE < gp.SCREEN_HEIGHT - gp.TILE_SIZE; j++){
            for(int i = 0; i * gp.TILE_SIZE < gp.SCREEN_WIDTH ; i++){
                int index = S1.getTilesIndex(i,j);
                g.drawImage(tile[index], gp.TILE_SIZE * i, gp.TILE_SIZE * j, gp.TILE_SIZE, gp.TILE_SIZE, null);
            }
        }
        
    }

    public void update(){

    }
}
