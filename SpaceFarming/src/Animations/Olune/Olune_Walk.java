package Animations.Olune;

import Animations.SuperAnimation;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Olune_Walk extends SuperAnimation {
    public Olune_Walk() {
        //(xoff, yoff, xsize, ysize)
        images = new BufferedImage[1][1];
        offsetSize = new int[1][1][4];
        timer = new Timer(new int[] {100}, true);
        movingHitBox = new Rectangle(0, 84, 140, 50);
        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/Olune/Olune_l_s.png"));
            offsetSize[0][0][0] = -70;
            offsetSize[0][0][1] = -75;
            offsetSize[0][0][2] = 140;
            offsetSize[0][0][3] = 134;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
