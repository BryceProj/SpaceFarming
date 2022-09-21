package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class OBJ_Soul extends SuperObject {

    public ArrayList<OBJ_SoulParticle> particles = new ArrayList<>();
    public OBJ_Soul() {

        name = "Soul";
        solidAreaX = 0;
        solidAreaY = 0;
        try {
            //issues going on, figure out object animation
            image = ImageIO.read(getClass().getResourceAsStream("/objects/SoulOrb.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
        imgSizeX = 64;
        imgSizeY = 64;
        imgOffX = -32;
        imgOffY = -32;
        solidArea = new Rectangle(0, 0, imgSizeX, imgSizeY);
    }
}
