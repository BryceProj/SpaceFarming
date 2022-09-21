package Animations.ParasiteBrainWorm;

import Animations.SuperAnimation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class A_PBW_MovementUG extends SuperAnimation {
    public A_PBW_MovementUG() {
        images = new BufferedImage[1][1];
        offsetSize = new int[1][1][4];
        timing = new int[]{0};
        animationTime = 30;
        movingHitBox = new Rectangle(0, 0, 8, 4);

        //try {
            images[0][0] = null;
            offsetSize[0][0][0] = -2;
            offsetSize[0][0][1] = 1;
            offsetSize[0][0][2] = 4;
            offsetSize[0][0][3] = 4;

        //}
//         catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
