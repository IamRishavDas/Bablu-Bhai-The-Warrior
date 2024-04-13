package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import NewGame.Game;
import Utils.Constants;
import Utils.LoadSave;


public class PauseOverlay{

    private BufferedImage backgroundImage;
    private int bgX;
    private int bgY;
    private int bgH;
    private int bgW;

    private SoundButton musicButton;
    private SoundButton sfxButton;

    public PauseOverlay(){
        loadBackground();
        createSoundButton();
    }

    private void createSoundButton() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY   = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, Constants.Ui.PauseButtons.SOUND_SIZE, Constants.Ui.PauseButtons.SOUND_SIZE);
        sfxButton   = new SoundButton(soundX, sfxY, Constants.Ui.PauseButtons.SOUND_SIZE, Constants.Ui.PauseButtons.SOUND_SIZE);
        
    }

    private void loadBackground() {
        backgroundImage = LoadSave.getImage(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImage.getWidth()  * Game.SCALE);
        bgH = (int) (backgroundImage.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update(){
        musicButton.update();
        sfxButton.update();
    }
    public void render(Graphics g){
        g.drawImage(backgroundImage, bgX, bgY, bgW, bgH, null); // background
        musicButton.render(g);
        sfxButton.render(g);
    }
   

    
    public void mousePressed(MouseEvent e) {
        if(isIn(e, musicButton)) musicButton.setMousePressed(true);
        else if(isIn(e, sfxButton)) sfxButton.setMousePressed(true);
    }
    
    public void mouseClicked(MouseEvent e) {
        
    }
    
    public void mouseReleased(MouseEvent e) {
        if(isIn(e, musicButton)){
            if(musicButton.isMousePressed()){   
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        else if(isIn(e, sfxButton)){
            if(sfxButton.isMousePressed()){
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }
        musicButton.resetBools();
        sfxButton.resetBools();
    }
    
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);

        if(isIn(e, musicButton)) musicButton.setMouseOver(true);
        else if(isIn(e, sfxButton)) sfxButton.setMouseOver(true);
    }

    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
