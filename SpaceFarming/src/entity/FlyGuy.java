package entity;

import Abilities.SuperAbility;
import Animations.FlyGuy.FlyGuyIdleAnimation;
import Animations.FlyGuy.FlyGuySprintingAnimation;
import Animations.FlyGuy.FlyGuyWalkingAnimation;
import Animations.SuperAnimation;
import main.GamePanel;
import main.Assets.KeyHandler;

import java.awt.*;

public class FlyGuy extends Entity {
    public FlyGuy(GamePanel gp) {
        super(gp);
        name = "FlyGuy";
        abilities = new SuperAbility[3];
        abilities[0] = new SuperAbility("Idle", new String[]{"Idle"}, new SuperAnimation[]{new FlyGuyIdleAnimation()});
        abilities[1] = new SuperAbility("Walk", new String[]{"Walk"}, new SuperAnimation[]{new FlyGuyWalkingAnimation()});
        abilities[2] = new SuperAbility("Sprint", new String[]{"Sprint"}, new SuperAnimation[]{new FlyGuySprintingAnimation()});

        imgOffX = -11;
        imgOffY = -50;
        imgSizeX = 24;
        imgSizeY = 62;

        solidArea = new Rectangle(3, 50, 17, 12);
        //solidAreaBase = new Rectangle(3, 50, 17, 12);
        setDefaultValues();
    }

    public void update() {
        super.update();

        //what the ai does

        if(status.contains("Sprint")) { super.sprint(); }
        else if (status.contains("Walk")) { super.walk(); }
        else { super.idle(); }


        //super.updateImage(); //makes two updates happen on player use for some reason.
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
        speed = 1.5;
        speed45 = (speed / Math.sqrt(2));
        speedUsedX = 0;
        speedUsedY = 0;
        direction = "Down";
        status = "Idle";
        maxStamina = 300;
    }

    public void playerUpdate(KeyHandler keyH) {
        super.playerUpdate(keyH);
    }
}
