package main.Assets;

import entity.Entity;

import java.awt.*;

public abstract class Collision {
    public Entity entity; //walking or attacking entity
    //type = Tile Type: Water, Land, etc
    public String[] type; // tile type or attack type
    public double[] amount; //collision amount or damage amount
    public Rectangle hitbox; //walking or attack

    public Collision() {}

    public Collision(Entity entity, String[] type, double[] amount, Rectangle hitbox) {
        this.entity = entity;
        this.type = type;
        this.amount = amount;
        this.hitbox = hitbox;
    }
}
