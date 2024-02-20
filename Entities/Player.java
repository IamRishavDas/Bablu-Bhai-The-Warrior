package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Utils.Constants;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniSpeed = 15, aniIndex; // the lower aniSpeed leads to increase the animation speed
    private int playerAction = Constants.PlayerConstants.IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    private final int move = 1;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updateAnimation();
        setAnimation();
        updatePos();
    }

    // render the player
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 64*3, 40*3, null);
    }

    private void loadAnimations() {

        String filePath = "/Resources/player_sprites.png";
        InputStream is = getClass().getResourceAsStream(filePath);
        try {
            BufferedImage image = ImageIO.read(is);

            animations = new BufferedImage[9][6];

            for (int i = 0; i < animations.length; i++) {
                for (int j = 0; j < animations[i].length; j++) {
                    animations[i][j] = image.getSubimage(j * 64, i * 40, 64, 40);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

        public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void setAnimation(){
        if(moving){
            playerAction = Constants.PlayerConstants.RUNNING;
        }else{
            playerAction = Constants.PlayerConstants.IDLE;
        }
    }

    public void updatePos(){
        if(moving){
            switch (playerDir) {
                case Constants.Directions.LEFT -> x -= move;
                case Constants.Directions.RIGHT -> x += move;
                case Constants.Directions.UP -> y -= move;
                case Constants.Directions.DOWN -> y += move;
            }
        }
    }

    private void updateAnimation(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= Constants.PlayerConstants.GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }
}
