package object;

import main.GamePanel;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_SoulParticle extends SuperObject {
    double newX, newY, oX, oY;
    double sX, sY;
    OBJ_Soul soul;

    public OBJ_SoulParticle(GamePanel gp, OBJ_Soul soul) {
        this.gp = gp;
        this.worldX = soul.worldX;
        this.worldY = soul.worldY;
        timer = new Timer(new int[]{1000}, false);
        name = "Soul Particle";

        oX = 0;
        oY = 0;
        newX = 0;
        newY = 0;
        sX = 0;
        sY = 0;

        solidAreaX = 0;
        solidAreaY = 0 + (int)gp.scale;
        try {
            //issues going on, figure out object animation
            int r = (int)(Math.random() * 3);
            if(r == 0) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/particle1.png"));
            } else if(r == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/particle2.png"));
            } else if(r == 2) {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/particle3.png"));
            } else {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/particle4.png"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
        imgSizeX = 6;
        imgSizeY = 6;
        imgOffX = -3;
        imgOffY = -3;

        solidArea = new Rectangle(0, 0, imgSizeX, imgSizeY);
    }
    public void update() {
//        if(timer.update() > timer.size()) {
//            //end of update
//            newX = (Math.random() * 2.0) - 1.0;
//            newY = 2.0; //arbitrary
//            while(Math.sqrt((newX * newX) + (newY * newY)) > 1.0) {
//                newY = (Math.random() * 2.0) - 1.0;
//            }
//            sX = (newX - oX) / 1000;
//            sY = (newY - oY) / 1000;
//
//            oX = newX;
//            oY = newY;
//        } else {
//            worldX += sX * soul.imgSizeX;
//            worldY += sY * soul.imgSizeY;
//        }
    }
}