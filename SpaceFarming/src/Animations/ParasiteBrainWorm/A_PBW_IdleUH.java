package Animations.ParasiteBrainWorm;

import Animations.SuperAnimation;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class A_PBW_IdleUH extends SuperAnimation {
    public A_PBW_IdleUH() {
        images = new BufferedImage[1][3];
        offsetSize = new int[1][3][4];
        timer = new Timer(new int[]{10, 10, 10}, true);

        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/objects/PBW-IdleUnderHole0.png"));
            offsetSize[0][0][0] = -8;
            offsetSize[0][0][1] = -13;
            offsetSize[0][0][2] = 16;
            offsetSize[0][0][3] = 20;

            images[0][1] = ImageIO.read(getClass().getResourceAsStream("/objects/PBW-IdleUnderHole0.png"));
            offsetSize[0][1][0] = -8;
            offsetSize[0][1][1] = -16;
            offsetSize[0][1][2] = 17;
            offsetSize[0][1][3] = 23;

            images[0][2] = ImageIO.read(getClass().getResourceAsStream("/objects/PBW-IdleUnderHole0.png"));
            offsetSize[0][2][0] = -8;
            offsetSize[0][2][1] = -14;
            offsetSize[0][2][2] = 17;
            offsetSize[0][2][3] = 21;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
