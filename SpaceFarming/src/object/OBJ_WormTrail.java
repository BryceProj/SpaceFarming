package object;

import main.GamePanel;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_WormTrail extends SuperObject {
    public OBJ_WormTrail(double worldX, double worldY, GamePanel gp) {
        this.gp = gp;
        this.worldX = worldX + (Math.random() * 10 + (-5));
        this.worldY = worldY + (Math.random() * 10 + (-5));
        timer = new Timer(new int[]{100, 100}, false);
        name = "WormTrail";
        solidAreaX = 0;
        solidAreaY = 0 + (int)gp.scale;
        try {
            //issues going on, figure out object animation
            int t = (int)(Math.random() * 4);
            String name = "/EntitySprites/ParasiteBrainWorm/PBW-IdleUnderGround" + t + ".png";
            image = ImageIO.read(getClass().getResourceAsStream(name));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
        imgSizeX = 6;
        imgSizeY = 6;
        imgOffX = -3;
        imgOffY = 1;

        solidArea = new Rectangle(0, 0, imgSizeX, imgSizeY);
    }
    public void update() {
        if(timer.update() > timer.size()) {
            gp.obj.remove(this);
        } else if(timer.current == 1) { //start image fade
            opacity -= (1.0f / timer.maxTime[1]);
            opacity = opacity < 0.0f ? 0.0f : opacity;
        }
    }
}
