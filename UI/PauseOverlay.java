package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import NewGame.Game;
import Utils.LoadSave;


public class PauseOverlay{

    private BufferedImage backgroundImage;
    private int bgX;
    private int bgY;
    private int bgH;
    private int bgW;

    public PauseOverlay(){
        loadBackground();
    }

    private void loadBackground() {
        backgroundImage = LoadSave.getImage(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImage.getWidth()  * Game.SCALE);
        bgH = (int) (backgroundImage.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = 100;
    }

    public void update(){

    }
    public void render(Graphics g){
        g.drawImage(backgroundImage, bgX, bgY, bgW, bgH, null);
    }
   

    
    public void mousePressed(MouseEvent e) {
        
    }
    
    public void mouseClicked(MouseEvent e) {
        
    }
    
    public void mouseReleased(MouseEvent e) {
       
    }
    
    public void mouseMoved(MouseEvent e) {
        
    }
}
