package entity;

import Abilities.SuperAbility;
import Animations.Fish.FishIdleAnimation;
import Animations.Fish.FishSwimAnimation;
import Animations.FlyGuy.FlyGuyIdleAnimation;
import Animations.FlyGuy.FlyGuyWalkingAnimation;
import Animations.SuperAnimation;
import main.Assets.MovementCollision;
import main.GamePanel;

import java.awt.*;

public class Fish extends Entity {
    double rads = 0;
    double rSpeed;
    double difference = 0;
    int rotTimer = 0;

    public Fish(GamePanel gp) {
        super(gp);
        name = "Fish";
        abilities = new SuperAbility[2];
        abilities[0] = new SuperAbility("Idle", new String[]{"Idle"}, new SuperAnimation[]{new FishIdleAnimation()});
        abilities[1] = new SuperAbility("Walk", new String[]{"Walk"}, new SuperAnimation[]{new FishSwimAnimation()});

        imgOffX = -11;
        imgOffY = -34;
        imgSizeX = 22;
        imgSizeY = 68;
        opacity = 1.0f / 3;

        mCollision = new MovementCollision(this, new String[]{"Water", "Land", "Water-Edge"}, new double[]{1.0, 0.0, 0.0}, new Rectangle(0, 0, imgSizeX, imgSizeY));
        solidArea = new Rectangle(0, 0, imgSizeX, imgSizeY);
        solidAreaBase = new Rectangle(solidArea);
        setDefaultValues();
    }

    public void update() {
        super.update();
        //what the ai does
        if(status == "None") {

            if (Math.random() * 100 < 100) {
                status = "Start Walk";
                //calculate rotation
                //rads = Math.toRadians(Math.random() * 359);
                rads = Math.random() * 4 * (Math.PI / 2);
                speedUsedX = Math.sin(rads) * speed;
                speedUsedY = -Math.cos(rads) * speed;
            } else {
                status = "Idle";
            }
        }
        if(status == "Start Walk") {
            status = "Walk";
            rotTimer = 0;
            //Figure this shit out, change collision to types
            double rPI = rotation > Math.PI ? rotation - Math.PI : rotation + Math.PI;
            if(rPI > 2.0 * Math.PI) {
                rPI -= 2.0 * Math.PI;
            }
            if(rads < rotation) {
                if(rotation > rPI) {
                    if(rads > rPI) {
                        difference = -(rotation - rads);
                    } else {
                        difference = rads + ((2.0 * Math.PI) - rotation);
                    }
                } else {
                    difference = -(rotation - rads);
                }
            } else if(rads > rotation) {
                if(rotation < rPI) {
                    if(rPI > rads) {
                        difference = rads - rotation;
                    } else {
                        difference = -(rotation + (2.0 * Math.PI) - rads);
                    }
                } else {
                    difference = rads - rotation;
                }
            }
            rSpeed = difference / 40.0;
        }
        if(status == "Walk") {
            //mCollision.checkTile();
            if(rotTimer < 40) {
                if(rotation > 2 * Math.PI) {
                    rotation -= 2 * Math.PI;
                } else if(rotation < 0) {
                    rotation += 2 * Math.PI;
                }
                rotation += rSpeed;
                rotTimer++;
            }
            collisionOn = false;
            collisionAmount = 1.0;
            mCollision.genCheckTile();
            super.walk();
        }
        super.updateImage();

    }

    public void setDefaultValues() {
        collisionOn = false;
        worldX = gp.originalTileSize * (int)((Math.random() * 7) + 12);
        worldY = gp.originalTileSize * (int)((Math.random() * 4) + 11);
        speed = 0.5;
        speed45 = (speed / Math.sqrt(2));
        speedUsedX = 0;
        speedUsedY = 0;
        direction = "forward";
        status = "Idle";
    }


}
