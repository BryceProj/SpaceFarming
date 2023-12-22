package Animations;

import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlaceHolderAnimation extends SuperAnimation {
    public PlaceHolderAnimation() {
        images = new BufferedImage[1][1];
        offsetSize = new int[1][1][4];
        timer = new Timer(new int[]{0}, true);
        movingHitBox = new Rectangle(0, 0, 16 ,8);

        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/objects/PBW-IdleUnderHole0.png"));
            offsetSize[0][0][0] = -8;
            offsetSize[0][0][1] = -1;
            offsetSize[0][0][2] = 16;
            offsetSize[0][0][3] = 8;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
