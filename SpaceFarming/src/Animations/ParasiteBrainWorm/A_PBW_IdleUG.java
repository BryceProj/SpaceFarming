package Animations.ParasiteBrainWorm;

import Animations.SuperAnimation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class A_PBW_IdleUG extends SuperAnimation {
    public A_PBW_IdleUG() {
        images = new BufferedImage[1][1];
        offsetSize = new int[1][1][4];
        timing = new int[]{0};
        animationTime = 10;
        movingHitBox = new Rectangle(0, 0, 4, 4);

        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-IdleUnderground0.png"));
            offsetSize[0][0][0] = -2;
            offsetSize[0][0][1] = 1;
            offsetSize[0][0][2] = 4;
            offsetSize[0][0][3] = 4;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
