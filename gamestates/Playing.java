package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.Font;

import entity.Player;
import main.GamePanel;
import situations.SituationHandler;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
    private BufferedImage backgroundImg, pausedImg, deathImg;
    SituationHandler sh;
    Player player;
    private boolean paused, death, startTimeB, stop, calcutalePause;
    private long currentTime, startTime, lastPauseStart = 0, totalPausedTime = 0; 
    private String totalRunTime;
    int jump, crouch, pauseEvent;
    private static Clip backgroundMusic;
    private static Clip gameOverMusic; // Added for game over music

    public Playing(GamePanel gp) {
        super(gp);
        reset();
        backgroundImg = LoadSave.GetSprite("/res/situation_data/background.png");
        pausedImg = LoadSave.GetSprite("/res/situation_data/paused.png");
        deathImg = LoadSave.GetSprite("/res/situation_data/death.png");
        if (backgroundMusic == null || !backgroundMusic.isRunning()) {
            playBackgroundMusic("/res/sounds/background_music.wav"); // Ensure correct path
        }
    }

    private boolean gameOverMusicPlayed = false; // Add a flag to track if game over music has been played

public void update() {
    if (startTimeB) {
        startTime = System.currentTimeMillis();
        startTimeB = false;
    }
    if (!paused) {
        player.loadSituationData1(sh.getCurrentSituation1().getSData());
        player.loadSituationData2(sh.getCurrentSituation2().getSData());
        sh.getStop(player.isStop());
        sh.setCollide(player.getCollide());
        stop = player.isStop();
        player.setShOffset(sh.getSOffset());
        player.update();
        sh.update();
        death = player.getDeath();

        if (death) {
            if (!gameOverMusicPlayed) { // Only play game over music once
                if (backgroundMusic != null && backgroundMusic.isRunning()) {
                    backgroundMusic.stop(); // Stop background music
                }
                playGameOverMusic("/res/sounds/gameover.wav"); // Play game over music
                gameOverMusicPlayed = true; // Set the flag to true
            }
        } else {
            if (gameOverMusicPlayed) {
                gameOverMusicPlayed = false; // Reset the flag when not in death condition
            }
            currentTime = System.currentTimeMillis();
        }
        long totalTime = currentTime - startTime - calculatePause();
        totalRunTime = getFormattedTime(totalTime);
    }
    if (paused) {
        calculatePause();
    }
}

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public void draw(Graphics g){
        if(!paused && !death){
            g.drawImage(backgroundImg, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT-30, null);
            //drawGrid(g);
            sh.draw(g);
            player.draw(g);
            g.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
            g.setColor(Color.black);
            g.drawString(totalRunTime, 50, 75);
            g.dispose();  //used to save memory by removing g2

        } else if (paused){
            g.drawImage(pausedImg, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT-30, null);
            
        } if (death){
            long deathStartTime = System.currentTimeMillis();
            long timeSinceDeath = 0;
            while (timeSinceDeath <= 50){
                timeSinceDeath = System.currentTimeMillis() - deathStartTime;
            }
            
            g.drawImage(deathImg, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT-30, null);
            g.setFont(new Font("Bookman Old Style", Font.BOLD, 80));
            g.setColor(Color.black);
            g.drawString(totalRunTime, 330, 320);
        }
    }

    public String getFormattedTime(long totalRunTime) {
        long activeTime = totalRunTime / 1000; // in seconds
    
        long hours = activeTime / 3600;
        long minutes = (activeTime % 3600) / 60;
        long seconds = activeTime % 60;
    
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public long calculatePause(){
        if ((paused || stop) &&!death ) {
            if(calcutalePause == false){
                lastPauseStart = System.currentTimeMillis();
            }
            calcutalePause = true;
            return totalPausedTime + (System.currentTimeMillis() - lastPauseStart);

        } else if ((!paused && !stop )&& lastPauseStart != 0) {
            totalPausedTime += (System.currentTimeMillis() - lastPauseStart); // Accumulate paused time
            lastPauseStart = 0;
            calcutalePause = false;
            return totalPausedTime;
        } 
        
        return totalPausedTime;
    }

    public void reset() {
        sh = new SituationHandler();
        player = new Player(GamePanel.TILE_SIZE * 6, GamePanel.TILE_SIZE * 5 - 10, 36, 83);
        startTime = System.currentTimeMillis();
        lastPauseStart = 0;
        totalPausedTime = 0;
        calcutalePause = false;
        paused = false;
        death = false;
        totalRunTime = "00:00:00";

        if (gameOverMusic != null && gameOverMusic.isRunning()) {
            gameOverMusic.stop(); // Stop game over music
            gameOverMusic.close();
        }
        if (backgroundMusic == null || !backgroundMusic.isRunning()) {
            playBackgroundMusic("/res/sounds/background_music.wav"); // Restart background music
        }
    }

     private void playBackgroundMusic(String filePath) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(filePath);
            if (audioSrc == null) {
                System.out.println("Audio file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
            backgroundMusic.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playGameOverMusic(String filePath) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(filePath);
            if (audioSrc == null) {
                System.out.println("Game over music file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            gameOverMusic = AudioSystem.getClip();
            gameOverMusic.open(audioStream);
            gameOverMusic.start(); // Play the game over music
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }

    private void playSoundEffect(String filePath) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(filePath);
            if (audioSrc == null) {
                System.out.println("Sound effect file not found: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip soundEffect = AudioSystem.getClip();
            soundEffect.open(audioStream);
            soundEffect.start(); // Play the sound effect
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if(e.getKeyCode() == jump){
            getPlayer().setUp(false);
            
        } /*else if(e.getKeyCode() == crouch){
            getPlayer().setDown(false);
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == jump){
            getPlayer().setUp(true);
            playSoundEffect("/res/sounds/jump.wav"); // Path to your jump sound effect
        // } else if(e.getKeyCode() == crouch){
        // 	getPlayer().setDown(true);

        }else if(e.getKeyCode() == pauseEvent || e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if (!death)
                paused = !paused;

        }else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if (paused || death) {
                reset();
                Gamestate.state = Gamestate.MENU;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getStartTime(){
        return startTimeB;
    }

    public void setStartTime(boolean startTime){
        this.startTimeB = startTime;
    }

    public void setControls(int jump, /*int crouch,*/ int pauseEvent){
        this.jump = jump;
        //this.crouch = crouch;
        this.pauseEvent = pauseEvent;
    }


}