package entity;

import Abilities.SuperAbility;
import Animations.FlyGuy.FlyGuyIdleAnimation;
import Animations.FlyGuy.FlyGuyWalkingAnimation;
import Animations.SuperAnimation;
import main.GamePanel;
import main.Assets.KeyHandler;

import java.awt.*;

public class FlyGuy extends Entity {
    public FlyGuy(GamePanel gp) {
        super(gp);
        name = "FlyGuy";
        abilities = new SuperAbility[2];
        abilities[0] = new SuperAbility("Idle", new String[]{"Idle"}, new SuperAnimation[]{new FlyGuyIdleAnimation()});
        abilities[1] = new SuperAbility("Walk", new String[]{"Walk"}, new SuperAnimation[]{new FlyGuyWalkingAnimation()});

        imgOffX = -11;
        imgOffY = -50;
        imgSizeX = 24;
        imgSizeY = 62;

        solidArea = new Rectangle(3, 50, 17, 12);
        solidAreaBase = new Rectangle(3, 50, 17, 12);
        setDefaultValues();
    }

    public void update() {
        super.update();

        //what the ai does

        super.walk();


        super.updateImage();
    }

    public void updateAI() {
        if(status == "None") {

            if (Math.random() * 100 < 30) {
                status = "Start Walk";
                //calculate rotation
                double rads = Math.toRadians(Math.random() * 359);
                speedUsedX = Math.sin(rads) * speed;
                speedUsedY = -Math.cos(rads) * speed;
            } else {
                status = "Idle";
            }
        }
    }

    public void setDefaultValues() {
        collisionOn = false;
        worldX = gp.tileSize * (int)(Math.random() * 10 + 5);
        worldY = gp.tileSize * (int)(Math.random() * 10 + 5);
        speed = 2;
        speed45 = (speed / Math.sqrt(2));
        speedUsedX = 0;
        speedUsedY = 0;
        direction = "down";
        status = "Idle";
    }

    public void playerUpdate(KeyHandler keyH) {
        boolean vertLock = false;
        boolean horLock = false;
        int keyAmt = 0;

        if(keyH.upPressed) { keyAmt++; }
        if(keyH.leftPressed) { keyAmt++; }
        if(keyH.downPressed) { keyAmt++; }
        if(keyH.rightPressed) { keyAmt++; }
        if(keyH.upPressed && keyH.downPressed) {
            vertLock = true;
            keyAmt -= 2;
        }
        if(keyH.rightPressed && keyH.leftPressed) {
            horLock = true;
            keyAmt -= 2;
        }

        if(keyAmt > 1) {
            speedUsedX = speed45;
            speedUsedY = speed45;
        }

        if (keyH.upPressed && !vertLock) {
            direction = "up";
            status = "Walk";
            speedUsedY = speed;
            speedUsedX = 0;
        }
        if(keyH.downPressed && !vertLock) {
            direction = "down";
            status = "Walk";
            speedUsedY = speed;
            speedUsedX = 0;
        }
        if(keyH.rightPressed && !horLock) {
            direction = "right";
            status = "Walk";
            speedUsedX = speed;
            speedUsedY = 0;
        }
        if(keyH.leftPressed && !horLock) {
            direction = "left";
            status = "Walk";
            speedUsedX = speed;
            speedUsedY = 0;
        }
        if(keyAmt == 0) {
            if(status == "Walk") {
                status = "Idle";
            }
        } else if(keyAmt > 0) {
            //tile collision
            collisionOn = false;
            //gp.cMoveChecker.checkTile(this);

            //check obj collision
            //int objIndex = gp.cMoveChecker.checkObject(this, true);
            //super.pickUpObject(objIndex);

            super.walk();
        }
        super.updateImage();
    }
}
