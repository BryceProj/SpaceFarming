package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Skull extends SuperObject {
    public OBJ_Skull() {

        name = "Skull";
        solidAreaX = 0;
        solidAreaY = 0;
        try {
            //issues going on, figure out object animation
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Dskull01.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
        imgSizeX = 64;
        imgSizeY = 64;
        imgOffX = -32;
        imgOffY = -32;

        solidArea = new Rectangle(0, 0, imgSizeX, imgSizeY);
    }
}
