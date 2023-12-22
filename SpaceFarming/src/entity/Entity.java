package entity;

import Abilities.SuperAbility;
import main.Assets.MovementCollision;
import main.Assets.Timer;
import main.GamePanel;
import main.Assets.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

public abstract class Entity {

    public GamePanel gp;
    public double screenX, screenY;

    public double worldX ,worldY;
    public double speed, speed45, speedUsedX, speedUsedY;

    public String name;

    public int maxStamina;
    public int stamina;
    Timer timerStaminaRegen1 = new Timer(new int[]{350}, false);
    Timer timerStaminaRegen2 = new Timer(new int[]{75}, false);
    public boolean staminaRegen1 = true;
    public boolean staminaRegen2 = true;

    public int imgOffX;
    public int imgOffY;
    public int imgSizeX = 0;
    public int imgSizeY = 0;

    public String direction, lastDirection;
    public String status;
    public SuperAbility[] abilities;
    public BufferedImage image = null;
    public float opacity = 1.0f;
    public double rotation = 0;

    //Collisions
    public MovementCollision mCollision;
    public Rectangle solidArea;
    public boolean collisionOn;
    public double collisionAmount = 1.0;

    public double maxHealth;
    public double currentHealth;

    public Entity() {}

    public Entity(GamePanel gp) {
        this.gp = gp;
        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;
    }

    public void draw(Graphics2D g2) {

        g2.translate((int)screenX + (imgOffX * gp.scale), (int)screenY + (imgOffY * gp.scale));
        g2.rotate(rotation, imgSizeX * gp.scale / 2, imgSizeY * gp.scale / 2);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.drawImage(image, 0, 0, (int)(imgSizeX * gp.scale), (int)(imgSizeY * gp.scale), null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.rotate(-rotation, imgSizeX * gp.scale / 2, imgSizeY * gp.scale / 2);
        g2.translate(-((int)screenX + (imgOffX * gp.scale)), -((int)screenY + (imgOffY * gp.scale)));

    }

    public void update() {
        lastDirection = direction;
    }

    public void updateImage() {
        screenX = (gp.scale * (worldX - gp.player.worldX)) + gp.player.screenX;
        screenY = (gp.scale * (worldY - gp.player.worldY)) + gp.player.screenY;

        boolean continuation = direction != lastDirection ? false : true;
        if(direction.contains("Up") && lastDirection.contains("Up")) {      //allows smooth diagonal animation
            continuation = true;
        } else if(direction.contains("Down") && lastDirection.contains("Down")) {
            continuation = true;
        }

        for(int i = 0; i < abilities.length; i++) {
            for(int j = 0; j < abilities[i].status.length; j++) { //list of statuses to check
                if(status == abilities[i].status[j]) {
                    abilities[i].animation[j].update(continuation);
                    solidArea = new Rectangle(abilities[i].animation[j].movingHitBox);
                    //solidAreaPrint = new Rectangle(solidArea);
                    if(abilities[i].animation[j].oneTimeRaised) {
                        continuation = false;
                        abilities[i].animation[j].oneTimeRaised = false;
                        //System.out.println(status);
                        return;
                    }
                    if(direction.contains("Up")) { image = abilities[i].animation[j].draw("Up"); }
                    else if(direction.contains("Down")) { image = abilities[i].animation[j].draw("Down"); }
                    else if(direction.contains("Left")) { image = abilities[i].animation[j].draw("Left"); }
                    else if(direction.contains("Right")) { image = abilities[i].animation[j].draw("Right"); }
                    imgOffX = abilities[i].animation[j].currentOffSize[0];
                    imgOffY = abilities[i].animation[j].currentOffSize[1];
                    imgSizeX = abilities[i].animation[j].currentOffSize[2];
                    imgSizeY = abilities[i].animation[j].currentOffSize[3];
                }
            }
        }
    }

    public void setDefaultValues() {
        collisionOn = false;
        direction = "Down";
        status = "Idle";
    }

    public void playerUpdate(KeyHandler keyH) {
        lastDirection = direction;
        direction = "";
        speedUsedX = 0;
        speedUsedY = 0;

        //checks what direction will be moved in based on keyH
        if(keyH.upPressed) {
            direction = "Up";
        }
        if(keyH.downPressed) {
            if(!direction.contains("Up ")) {
                direction = "Down";
            } else {
                direction = "";
            }
        }
        if(keyH.leftPressed) {
            direction = direction.length() > 1 ? direction.concat(" ") : direction;
            direction = direction.concat("Left");
        }
        if(keyH.rightPressed) {
            if(!direction.contains("Left")){
                direction = direction.length() > 1 ? direction.concat(" ") : direction;
                direction = direction.concat("Right");
            } else {
                direction = direction.substring(0, direction.indexOf("Left"));
            }
        }
        if(direction.length() > 1) {
            status = keyH.shiftPressed ? "Sprint" : "Walk";
        } else {
            direction = lastDirection;
            status = "Idle";
            collisionOn = false;
            //gp.cMoveChecker.checkTile(this);

            //check obj collision
            //int objIndex = gp.cMoveChecker.checkObject(this, true);
            //super.pickUpObject(objIndex);
        }
        if(direction.contains(" ")) {   //diagonal movement
            if(direction.contains("Up")) {
                speedUsedY = -speed45;
            }
            if(direction.contains("Down")) {
                speedUsedY = speed45;
            }
            if(direction.contains("Left")) {
                speedUsedX = -speed45;
            }
            if(direction.contains("Right")) {
                speedUsedX = speed45;
            }
        } else {
            if(direction.contains("Up")) {
                speedUsedY = -speed;
            }
            if(direction.contains("Down")) {
                speedUsedY = speed;
            }
            if(direction.contains("Left")) {
                speedUsedX = -speed;
            }
            if(direction.contains("Right")) {
                speedUsedX = speed;
            }
        }
        boolean sprinted = false;
        if(status.contains("Sprint")) {
            if(stamina > 0) {
                stamina = stamina - 2 <= 0 ? 0 : stamina - 2;
                sprint();
            } else {
                status = "Walk";
                walk();
                staminaRegen1 = false;
                timerStaminaRegen1.reset();
            }
            staminaRegen2 = false;
            timerStaminaRegen2.reset();
            sprinted = true;

        } else if( status.contains("Walk")) {
            walk();
        }
        if(!sprinted && stamina < maxStamina) {
            boolean canRegen = false;
            if(staminaRegen1) { canRegen = true; }  //this is for using all Stamina
            else if(timerStaminaRegen1.update() > timerStaminaRegen1.size()) {
                staminaRegen1 = true;
                timerStaminaRegen1.reset();
            }
            if(staminaRegen2) { canRegen = canRegen && true; } //this is for shortly after sprinting
            else if(timerStaminaRegen2.update() > timerStaminaRegen2.size()) {
                canRegen = canRegen && true;
                staminaRegen2 = true;
                timerStaminaRegen2.reset();

            } else{
                canRegen = false;
            }
            if(canRegen) { stamina++; }
        }
        updateImage();
    }

    public void pickUpObject(int i) {

        if(i != 999) {
            String objectName = gp.obj.get(i).name;

            switch(objectName) {
                case "Soul":
                    System.out.println("Soul");
                    //gp.obj[i] = null;
                    break;
                case "Path New Area":
                    System.out.println("Walking Away");
                    //gp.obj[i] = null;
                    break;
                case "Skull":
                    System.out.println("Skull");
                    //gp.obj[i] = null;
                    break;
            }
        }
    }

    //speeds are accurate to scale
    public void walk() {
        if(!collisionOn || collisionAmount > 0.0) {
            if(status == "Walk") {
                worldX += speedUsedX * collisionAmount;
                worldY += speedUsedY * collisionAmount;
            }
        }
    }
    public void sprint() {
        if (!collisionOn || collisionAmount > 0.0) {
            if (status == "Sprint") {
                worldX += speedUsedX * collisionAmount * 1.5;
                worldY += speedUsedY * collisionAmount * 1.5;
            }
        }
    }
    public void idle() {

    }
}
