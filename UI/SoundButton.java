package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.Constants;
import Utils.LoadSave;

public class SoundButton extends PauseButton{

    private BufferedImage[][] soundImages;
    private int rowIndex, colIndex;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean muted;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadSoundImages();
    }

    private void loadSoundImages() {
        BufferedImage temp = LoadSave.getImage(LoadSave.SOUND_BUTTONS);
        soundImages = new BufferedImage[2][3];

        for(int i=0; i<soundImages.length; i++){
            for(int j=0; j<soundImages[i].length; j++){
                soundImages[i][j] = temp.getSubimage(j * Constants.Ui.PauseButtons.SOUND_SIZE_DEFAULT, i * Constants.Ui.PauseButtons.SOUND_SIZE_DEFAULT, Constants.Ui.PauseButtons.SOUND_SIZE_DEFAULT, Constants.Ui.PauseButtons.SOUND_SIZE_DEFAULT);
            }
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void update(){
        if(muted) rowIndex = 1;
        else rowIndex  = 0;

        colIndex = 0; 
        if(mouseOver) colIndex = 1;
        if(mousePressed) colIndex = 2;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

    public void render(Graphics g){
        g.drawImage(soundImages[rowIndex][colIndex], x, y, width, height, null);
    }
}
