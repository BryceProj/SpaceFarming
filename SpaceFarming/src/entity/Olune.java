package entity;

import Abilities.SuperAbility;
import Animations.Olune.*;
import Animations.PlaceHolderAnimation;
import Animations.SuperAnimation;
import main.Assets.KeyHandler;
import main.Assets.MovementCollision;
import main.GamePanel;


import java.awt.*;

public class Olune extends Entity {
    public Olune(GamePanel gp) {
        super(gp);

        name = "Olune";
        abilities = new SuperAbility[2];
        abilities[0] = new SuperAbility("Idle", new String[]{"Idle"}, new SuperAnimation[]{new Olune_Idle()});
        abilities[1] = new SuperAbility("Walk", new String[]{"Walk"}, new SuperAnimation[]{new Olune_Walk()});

        imgOffX = -84;
        imgOffY = -67;
        imgSizeX = 140;
        imgSizeY = 134;

        mCollision = new MovementCollision(this, new String[]{"Land", "Water", "Water-Edge"}, new double[]{1.0, 0.0, 0.0}, new Rectangle(0, 84, 140, 50));
        solidArea = new Rectangle(0, 84, 140, 50);
        //solidAreaBase = new Rectangle(0, 84, 140, 50);
        setDefaultValues();
    }

    public void update() {
        super.update();
        //what the ai does

        super.updateImage();

    }

    public void updateAI() {
        if(status == "None") {

//            if (Math.random() * 100 < 30) {
//                status = "Start Walk";
//                //calculate rotation
//                //rads = Math.toRadians(Math.random() * 359);
//                speedUsedX = Math.sin(rads) * speed;
//                speedUsedY = -Math.cos(rads) * speed;
//            } else {
//                status = "Idle";
//            }
        }
    }

    public void setDefaultValues() {
        collisionOn = false;
        worldX = gp.tileSize * (int)(Math.random() * 8 + 11);
        worldY = gp.tileSize * (int)(Math.random() * 5 + 2);
        speed = 5.0;
        speed45 = (speed / Math.sqrt(2));
        speedUsedX = 0;
        speedUsedY = 0;
        direction = "Down";
        status = "Idle";
    }


    public void playerUpdate(KeyHandler keyH) {
        super.update();
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

        if(keyH.upPressed && keyH.leftPressed && !vertLock && !horLock) {
            direction = "Up Left";
            status = "Walk";
            speedUsedX = -speed45;
            speedUsedY = -speed45;
        } else if(keyH.upPressed && keyH.rightPressed && !vertLock && !horLock) {
            direction = "up right";
            status = "Walk";
            speedUsedX = speed45;
            speedUsedY = -speed45;
        } else if(keyH.downPressed && keyH.leftPressed && !vertLock && !horLock) {
            direction = "down left";
            status = "Walk";
            speedUsedX = -speed45;
            speedUsedY = speed45;
        } else if(keyH.downPressed && keyH.rightPressed && !vertLock && !horLock) {
            direction = "down right";
            status = "Walk";
            speedUsedX = speed45;
            speedUsedY = speed45;
        } else if (keyH.upPressed && !vertLock) {
            direction = "up";
            status = "Walk";
            speedUsedY = -speed;
            speedUsedX = 0;
        } else if (keyH.downPressed && !vertLock) {
            direction = "down";
            status = "Walk";
            speedUsedY = speed;
            speedUsedX = 0;
        } else if (keyH.rightPressed && !horLock) {
            direction = "right";
            status = "Walk";
            speedUsedX = speed;
            speedUsedY = 0;
        }else if (keyH.leftPressed && !horLock) {
            direction = "left";
            status = "Walk";
            speedUsedX = -speed;
            speedUsedY = 0;
        }

        if(keyAmt == 0) {
            if(status == "Walk") {
                status = "Idle";
            }
        } else if(keyAmt > 0 && !status.contains("Idle") && !status.contains("No_Move")) {
            //tile collision
            collisionOn = false;
            collisionAmount = 1.0;
            mCollision.genCheckTile();

            //check obj collision
            //int objIndex = gp.cMoveChecker.checkObject(this, true);
            //super.pickUpObject(objIndex);
            if(status == "Walk") {
                super.walk();
            }
        }

        super.updateImage();
    }

}
