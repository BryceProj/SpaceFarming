package entity;

import Abilities.SuperAbility;
import main.Assets.MovementCollision;
import main.GamePanel;
import main.Assets.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    public GamePanel gp;
    public double screenX, screenY;

    public double worldX ,worldY;
    public double speed, speed45, speedUsedX, speedUsedY;

    public String name;

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
    public Rectangle solidArea, solidAreaBase; //change animation to alter hitbox like currentOffSize
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
        for(int i = 0; i < abilities.length; i++) {
            for(int j = 0; j < abilities[i].status.length; j++) {
                if(status == abilities[i].status[j]) {
                    abilities[i].animation[j].update(continuation);
                    solidArea = new Rectangle(abilities[i].animation[j].movingHitBox);
                    solidAreaBase = new Rectangle(solidArea);
                    if(abilities[i].animation[j].oneTimeRaised) {
                        status = abilities[i].animation[j].nextStatus;
                        abilities[i].animation[j].updateTimes();
                        abilities[i].animation[j].oneTimeRaised = false;
                        updateImage();
                        return;
                    }
//                    String usedDirection = direction;
//                    if(direction == "up left" || direction == "up right") {
//                        usedDirection = "up";
//                    } else if(direction == "down left" || direction == "down right") {
//                        usedDirection = "down";
//                    }
                    image = abilities[i].animation[j].draw(direction);
                    imgOffX = abilities[i].animation[j].currentOffSize[0];
                    imgOffY = abilities[i].animation[j].currentOffSize[1];
                    imgSizeX = abilities[i].animation[j].currentOffSize[2];
                    imgSizeY = abilities[i].animation[j].currentOffSize[3];
                }
            }
            if(status == abilities[i].name) {

            }
        }
    }

    public void setDefaultValues() {
        collisionOn = false;
        direction = "down";
        status = "Idle";
    }

    public void playerUpdate(KeyHandler keyH) {}

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
        if(collisionOn == false || collisionAmount > 0.0) {
            if(status == "Walk") {
                worldX += speedUsedX * collisionAmount;
                worldY += speedUsedY * collisionAmount;
//                switch (direction) {
//                    case "up left":
//                        worldY -= speedUsedY * collisionAmount;
//                        worldX -= speedUsedX * collisionAmount;
//                        break;
//                    case "up right":
//                        worldY -= speedUsedY * collisionAmount;
//                        worldX += speedUsedX * collisionAmount;
//                        break;
//                    case "down left":
//                        worldY += speedUsedY * collisionAmount;
//                        worldX -= speedUsedX * collisionAmount;
//                        break;
//                    case "down right":
//                        worldY += speedUsedY * collisionAmount;
//                        worldX += speedUsedX * collisionAmount;
//                        break;
//
//                    case "up":
//                        worldY -= speedUsedY * collisionAmount;
//                        break;
//                    case "down":
//                        worldY += speedUsedY * collisionAmount;
//                        break;
//                    case "left":
//                        worldX -= speedUsedX * collisionAmount;
//                        break;
//                    case "right":
//                        worldX += speedUsedX * collisionAmount;
//                        break;
//                    case "forward":
//                        worldX += speedUsedX * collisionAmount;
//                        worldY += speedUsedY * collisionAmount;
//                        break;
//                }
            }
        }
    }
}
