package Animations.FlyGuy;

import Animations.SuperAnimation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlyGuyIdleAnimation extends SuperAnimation {

    public FlyGuyIdleAnimation() {

        images = new BufferedImage[4][2];
        offsetSize = new int[4][2][4];
        timing = new int[] {0, 15};
        animationTime = 30;
        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UI1.png"));
            images[0][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UI2.png"));
            images[1][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DI1.png"));
            images[1][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DI2.png"));

            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    offsetSize[i][j][0] = -11;
                    offsetSize[i][j][1] = -50;
                    offsetSize[i][j][2] = 24;
                    offsetSize[i][j][3] = 62;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
