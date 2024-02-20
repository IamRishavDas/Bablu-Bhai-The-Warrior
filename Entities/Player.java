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
    
    private boolean left, right, up, down;

    private boolean moving = false;
    private final int playerSpeed = 1;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        setAnimation();
        updateAnimation();
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

    public void setAnimation(){
        if(moving){
            playerAction = Constants.PlayerConstants.RUNNING;
        }else{
            playerAction = Constants.PlayerConstants.IDLE;
        }
    }

    public void updatePos(){

        moving = false; //default

        if(left && !right){ // left
            x -= playerSpeed;
            moving = true;
        }else if(!left && right){ //right
            x += playerSpeed;
            moving = true;
        }

        if(up && !down){ //up
            y -= playerSpeed;
            moving = true;
        }else if(!up && down){ //down
            y += playerSpeed;
            moving = true;
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

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    
    
}
