package main;

import entity.Entity;
import entity.FlyGuy;
import entity.Olune;
import entity.ParasiteBrainWorm;
import main.Assets.KeyHandler;
import main.Assets.Timer;
import object.OBJ_Soul;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    public GamePanel gp;
    public double screenX, screenY;
    public double worldX, worldY;
    public int cameraIndex = 0;
    public boolean sprint = false;
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
            case "Fly Guy":
                ent.add(new FlyGuy(gp));
                break;
            case "Brain Worm":
                ent.add(new ParasiteBrainWorm(gp));
                break;
            case "Olune":
                ent.add(new Olune(gp));
        }
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 5;
    }

    public void setSoul(OBJ_Soul soul) {
        this.soul = soul;
    }

    Timer timerE = new Timer(new int[]{3000000}, false);
    Timer timerS = new Timer(new int[]{3000000}, false);

    public void update() {
        if(keyH.enterPressed) { //conditions for switching entities
            if (timerE.counter == 0) {
                if (cameraIndex == ent.size() - 1) {
                    cameraIndex = 0;
                } else {
                    cameraIndex++;
                }
            }
            timerE.update();
        } else {
            timerE.reset();
        }
        if(keyH.xPressed) {
            if(timerS.counter == 0) {
                if(gp.eManager.getViewMode() == 0) {
                    gp.eManager.setViewMode(1);
                } else {
                    gp.eManager.setViewMode(0);
                }
                System.out.println(gp.eManager.getViewMode());
            }
            timerS.update();
        } else {
            timerS.reset();
        }

        for(int i = 0; i < ent.size(); i++) {
            if(ent.get(i) != null) {
                if(i == cameraIndex) {
                    //drawStamina(g);
                    ent.get(i).playerUpdate(keyH);
                    worldX = ent.get(cameraIndex).worldX;
                    worldY = ent.get(cameraIndex).worldY;
                    //System.out.println(ent.get(i).status + " " + ent.get(i).direction);
                    //System.out.println(ent.get(i).stamina + "/" + ent.get(i).maxStamina);
                }
                else {
                    ent.get(i).update();

                }
            }
        }
        //do this somewhere between to stop the moving

    }
    private void drawStamina(Graphics2D g2, int i) {

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
