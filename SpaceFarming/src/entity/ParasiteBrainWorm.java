package entity;

import Abilities.SuperAbility;
import Animations.ParasiteBrainWorm.*;
import Animations.PlaceHolderAnimation;
import Animations.SuperAnimation;
import main.Assets.MovementCollision;
import main.GamePanel;
import main.Assets.KeyHandler;
import object.OBJ_WormHole;
import object.OBJ_WormTrail;

import java.awt.*;

public class ParasiteBrainWorm extends Entity {
    public ParasiteBrainWorm(GamePanel gp) {
        super(gp);

        name = "Brain Worm";
        abilities = new SuperAbility[3];
        abilities[0] = new SuperAbility("Idle", new String[]{"Idle Out", "Idle Underground"}, new SuperAnimation[]{ new A_PBW_IdleAG(), new A_PBW_IdleUG()});
        abilities[1] = new SuperAbility("Go Underground", new String[]{"Going Underground No_Move", "Going Out No_Move"}, new SuperAnimation[]{new PlaceHolderAnimation(), new PlaceHolderAnimation()});
        abilities[1].animation[0].oneTime = true;
        abilities[1].animation[1].oneTime = true;
        abilities[1].animation[0].nextStatus = "Idle Underground";
        abilities[1].animation[1].nextStatus = "Idle Out";
        abilities[2] = new SuperAbility("Walk", new String[]{"Walk"}, new SuperAnimation[]{new A_PBW_MovementUG()});

        imgOffX = -8;
        imgOffY = -13;
        imgSizeX = 16;
        imgSizeY = 20;

        mCollision = new MovementCollision(this, new String[]{"Water", "Land", "Water-Edge"}, new double[]{0.25, 5.0, 0.5}, new Rectangle(0, 14, 50, 10));
        solidArea = new Rectangle(0, 14, 50, 10);
        //solidAreaBase = new Rectangle(0, 14, 50, 10);
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
        speed = 1.0;
        speed45 = (speed / Math.sqrt(2));
        speedUsedX = 0;
        speedUsedY = 0;
        direction = "down";
        status = "Idle Out";

        //rotation = 0.5 * Math.PI;
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
        if(status == "Idle Underground" || status == "Walk") {
            if(keyH.upPressed && keyH.leftPressed && !vertLock && !horLock) {
                direction = "up left";
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
        }
        if(keyH.spacePressed && status == "Idle Out") {
            status = "Going Underground No_Move"; //here
            gp.obj.add(new OBJ_WormHole(worldX, worldY, gp));
        } else if(keyH.spacePressed && (status == "Idle Underground" || status == "Walk")) {
            status = "Going Out No_Move";
        }
        if(keyAmt == 0) {
            if(status == "Walk") {
                status = "Idle Underground";
            }
        } else if(keyAmt > 0 && !status.contains("Idle") && !status.contains("No_Move")) {
            //tile collision
            collisionOn = false;
            collisionAmount = 1.0;
            //gp.cMoveChecker.checkTile(this);
            mCollision.genCheckTile();

            //check obj collision
            //int objIndex = gp.cMoveChecker.checkObject(this, true);
            //super.pickUpObject(objIndex);
            if(status == "Walk") {
                if(Math.random() < 0.25) {
                    gp.obj.add(new OBJ_WormTrail(worldX, worldY, gp));
                }
               // System.out.println(mCollision.amount[1]);
                super.walk();
            }
        }
        //System.out.println(status);

        super.updateImage();
    }
}
