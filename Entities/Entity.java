package Entities;

public abstract class Entity {
    // only extending class can use it
    protected float x, y;
    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }
}
