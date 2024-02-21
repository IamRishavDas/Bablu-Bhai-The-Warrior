package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;;

public abstract class Entity {
    // only extending class can use it
    protected float x, y;
    protected int width, height;

    // bound the player for collision check
    protected Rectangle hitBox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox();
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) x, (int) y, width, height);
    }

    // for checking the hitbox
    protected void drawHitBox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    protected void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
