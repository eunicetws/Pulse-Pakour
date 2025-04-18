package situations;

import main.GamePanel;
import utilz.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SituationHandler {
    private int rows = GamePanel.SCREEN_HEIGHT / GamePanel.TILE_SIZE;
    private int cols = GamePanel.SCREEN_WIDTH / GamePanel.TILE_SIZE;
    private int sTick = 0, sSpeed = 1;
    public static int sOffset = 0;
    private int drawnCols = 0;
    private BufferedImage[] tile;
    private Situation[] situations = new Situation[2];
    private Random rand = new Random();

    private Situation S1, S2;          
    private boolean stop;

    public SituationHandler(){
        importTileSet();
        situations[0] = new Situation(LoadSave.getSData(LoadSave.S1));
        situations[1] = new Situation(LoadSave.getSData(LoadSave.S2));
        S1 = situations[0];
        S2 = situations[1];
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

    public int updateAnimationTick(){
        sTick++;
        if (sTick >= sSpeed){
            sTick = 0;
            if (!stop) {
                sOffset += 1;
            }
            if (sOffset >= 15 * GamePanel.TILE_SIZE){
                sOffset = 0;
                int n = random();
                S1 = S2;
                S2 = situations[n];
            }
        }
        return sOffset;
    }

    public void draw(Graphics g){
        for(int j = 0; j  < rows; j++){
            for(int i = 0; i < cols; i++){
                int index = S1.getTilesIndex(i,j);
                drawnCols = (GamePanel.TILE_SIZE * i) - sOffset;
                g.drawImage(tile[index], drawnCols, GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
        }

        for(int j = 0; j < rows; j++){
            for(int i = 0; i < GamePanel.SCREEN_COL - drawnCols / GamePanel.TILE_SIZE ; i++){
                int index = S2.getTilesIndex(i, j);
                int drawnCols2 = drawnCols + (GamePanel.TILE_SIZE * i) ;
                g.drawImage(tile[index], drawnCols2, GamePanel.TILE_SIZE * j, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
        }
        
    }

    public void update(){
        updateAnimationTick();
    }

    private int random(){
        int n = rand.nextInt(2);
        return n;
    }

    public Situation getCurrentSituation1(){
        return S1;
    }

    public Situation getCurrentSituation2(){
        return S2;
    }

    public void getStop(boolean stop){
        this.stop = stop;
    }
}
