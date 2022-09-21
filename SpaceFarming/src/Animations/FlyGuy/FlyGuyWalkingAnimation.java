package Animations.FlyGuy;

import Animations.SuperAnimation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlyGuyWalkingAnimation extends SuperAnimation {

    public FlyGuyWalkingAnimation() {

        images = new BufferedImage[4][2];
        offsetSize = new int[4][2][4];
        timing = new int[] {0, 22};
        animationTime = 44;
        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/up1.png"));
            images[0][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/up2.png"));
            images[1][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/down1.png"));
            images[1][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/down2.png"));
            images[2][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left1.png"));
            images[2][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left2.png"));
            images[3][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right1.png"));
            images[3][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right2.png"));

            for(int i = 0; i < 4; i++) {
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
