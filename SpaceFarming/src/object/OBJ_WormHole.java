package object;

import main.GamePanel;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_WormHole extends SuperObject {
    public OBJ_WormHole(double worldX, double worldY, GamePanel gp) {
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;
        timer = new Timer(new int[]{1000, 300}, false);
        name = "WormHole";
        solidAreaX = 0;
        solidAreaY = 0 + (int)gp.scale;
        try {
            //issues going on, figure out object animation
            image = ImageIO.read(getClass().getResourceAsStream("/objects/PBW-IdleUnderHole0.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
        imgSizeX = 16;
        imgSizeY = 8;
        imgOffX = -8;
        imgOffY = -1;

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
