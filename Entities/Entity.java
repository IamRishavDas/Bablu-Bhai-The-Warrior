package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;;

public abstract class Entity {
    // only extending class can use it
    protected float x, y;
    protected int width, height;

    // bound the player for collision check
    protected Rectangle2D.Float hitBox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    // for checking the hitbox
    protected void drawHitBox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
    }

    // protected void updateHitBox() {
    //     hitBox.x = (int) x;
    //     hitBox.y = (int) y;
    // }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
