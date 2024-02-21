package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import NewGame.Game;
import Utils.Constants;
import Utils.HelpMethods;
import Utils.LoadSave;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniSpeed = 15, aniIndex; // the lower aniSpeed leads to increase the animation speed
    private int playerAction = Constants.PlayerConstants.IDLE;

    private boolean left, right, up, down, jump;

    private boolean moving = false;
    private boolean attacking = false;

    private final int playerSpeed = 1;

    private int[][] levelData;

    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    // jumping speed / gravity
    private float airSpeed = 0.0f;
    private final float gravity = 0.05f * Game.SCALE;
    private float jumpSpeed = -3.2f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.05f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
    }

    public void update() {
        updatePos();
        setAnimation();
        updateAnimation();
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    // render the player
    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset),
                width, height, null);
        // drawHitBox(g);
    }

    private void loadAnimations() {

        BufferedImage image = LoadSave.getImage(LoadSave.PLAYER);

        animations = new BufferedImage[9][6];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = image.getSubimage(j * 64, i * 40, 64, 40);
            }
        }

    }

    public void setAnimation() {

        int startAni = playerAction;

        if (moving) {
            playerAction = Constants.PlayerConstants.RUNNING;
        } else {
            playerAction = Constants.PlayerConstants.IDLE;
        }

        if (attacking) {
            playerAction = Constants.PlayerConstants.ATTACK_1;
        }

        if (startAni != playerAction) {
            resetAnimation();
        }
    }

    private void resetAnimation() {
        aniTick = aniIndex = 0;
    }

    public void updatePos() {

        moving = false; // default

        if (jump) {
            jump();
        }

        // if anything is not placed
        if (!left && !right && !inAir)
            return;

        float xSpeed = 0;

        if (left) { // left
            xSpeed -= playerSpeed;
        }
        if (right) { // right
            xSpeed += playerSpeed;
        }

        if(!inAir) {
            if(!HelpMethods.IsEntityOnFloor(hitBox, levelData)){
                inAir = true;
            }
        }

        if (inAir) {
            if (HelpMethods.canMove(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXpos(xSpeed);
            } else {
                hitBox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXpos(xSpeed);
            }
        } else {
            updateXpos(xSpeed);
        }

        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXpos(float xSpeed) {
        if (HelpMethods.canMove(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = HelpMethods.GetEntityPosNextToWall(hitBox, xSpeed);
        }
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Constants.PlayerConstants.GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
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

    public void resetDirections() {
        left = right = up = down = false;
    }

    public void setJump(boolean value){
        jump = value;
    }

}
