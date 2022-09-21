package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Path_New_Area extends SuperObject {
    //add some other map pointer

    public OBJ_Path_New_Area() {

        name = "Path New Area";
        collision = false;
        try {
            //issues going on, figure out object animation
            image = ImageIO.read(getClass().getResourceAsStream("/objects/soul1.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
        imgSizeX = 25;
        imgSizeY = 25;
        imgOffX = 0;
        imgOffY = 0;
        solidArea = new Rectangle(imgOffX, imgOffY, imgSizeX, imgSizeY);
    }
}
