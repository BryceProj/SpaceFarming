package main;

import entity.Entity;
import entity.FlyGuy;
import entity.ParasiteBrainWorm;
import main.Assets.KeyHandler;
import object.OBJ_Soul;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    public GamePanel gp;
    public double screenX, screenY;
    public double worldX, worldY;
    public int cameraIndex = 0;

    KeyHandler keyH;
    public ArrayList<Entity> ent = new ArrayList<Entity>();
    public OBJ_Soul soul;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        this.keyH = keyH;

        setDefaultValues();
    }

    public void addEntity(String lifeform) {
        switch (lifeform) {
            case "FlyGuy":
                ent.add(new FlyGuy(gp));
                break;
            case "Brain Worm":
                ent.add(new ParasiteBrainWorm(gp));
        }
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 5;
    }

    public void setSoul(OBJ_Soul soul) {
        this.soul = soul;
    }

    public void update() {
//        if(keyH.spacePressed) {
//            cameraIndex = 1;
//        } else if(keyH.enterPressed) {
//            cameraIndex = 0;
//        }

        for(int i = 0; i < ent.size(); i++) {
            if(ent.get(i) != null) {
                if(i == cameraIndex) {
                    ent.get(i).playerUpdate(keyH);
                }
                else {
                    ent.get(i).update();
                }
            }
        }
        //do this somewhere between to stop the moving
        worldX = ent.get(cameraIndex).worldX;
        worldY = ent.get(cameraIndex).worldY;
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < ent.size(); i++) {
            if(ent.get(i) != null) {
                ent.get(i).draw(g2);
            }
        }
        //g2.drawImage(image, (int)screenX + (imgOffX * gp.scale), (int)screenY + (imgOffY * gp.scale), imgSizeX * gp.scale, imgSizeY * gp.scale, null);

        g2.drawOval((int) screenX, (int)screenY, 5, 5);
    }
}
