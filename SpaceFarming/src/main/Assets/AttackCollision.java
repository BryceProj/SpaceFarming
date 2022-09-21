package main.Assets;

import entity.Entity;

import java.awt.*;

public class AttackCollision extends Collision {

    public AttackCollision(Entity entity, String[] type, double[] amount, Rectangle hitbox) {
        super(entity, type, amount, hitbox);
    }

    public void checkAttack(Entity e) {
        //check within radius
    }

}
