package Animations.Fish;

import Animations.SuperAnimation;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FishSwimAnimation extends SuperAnimation {
    public FishSwimAnimation() {
        //(xoff, yoff, xsize, ysize)
        images = new BufferedImage[1][8];
        offsetSize = new int[1][8][4];
        timer = new Timer(new int[8], true);
        for(int i = 0; i < 8; i++) {
            if(i == 0) {
                timer.maxTime[i] = 0;
            } else {
                timer.maxTime[i] = timer.maxTime[i - 1] + 7 + (int)(Math.random() * 6);
            }
        }
        movingHitBox = new Rectangle(0, 0, 22, 66);
        oneTime = true;
        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/Fish/Atolea/FinDown.png"));
            offsetSize[0][0][0] = -11;
            offsetSize[0][0][1] = -34;
            offsetSize[0][0][2] = 22;
            offsetSize[0][0][3] = 68;

            images[0][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/Fish/Atolea/FinRightish.png"));
            offsetSize[0][1][0] = -11;
            offsetSize[0][1][1] = -34;
            offsetSize[0][1][2] = 22;
            offsetSize[0][1][3] = 68;

            images[0][2] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/Fish/Atolea/FinRight.png"));
            offsetSize[0][2][0] = -11;
            offsetSize[0][2][1] = -32;
            offsetSize[0][2][2] = 22;
            offsetSize[0][2][3] = 64;

            images[0][3] = images[0][1];
            offsetSize[0][3][0] = -11;
            offsetSize[0][3][1] = -34;
            offsetSize[0][3][2] = 22;
            offsetSize[0][3][3] = 68;

            images[0][4] = images[0][0];
            offsetSize[0][4][0] = -11;
            offsetSize[0][4][1] = -34;
            offsetSize[0][4][2] = 22;
            offsetSize[0][4][3] = 68;

            images[0][5] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/Fish/Atolea/FinLeftish.png"));
            offsetSize[0][5][0] = -11;
            offsetSize[0][5][1] = -34;
            offsetSize[0][5][2] = 22;
            offsetSize[0][5][3] = 68;

            images[0][6] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/Fish/Atolea/FinLeft.png"));
            offsetSize[0][6][0] = -11;
            offsetSize[0][6][1] = -33;
            offsetSize[0][6][2] = 22;
            offsetSize[0][6][3] = 64;

            images[0][7] = images[0][5];
            offsetSize[0][7][0] = -11;
            offsetSize[0][7][1] = -34;
            offsetSize[0][7][2] = 22;
            offsetSize[0][7][3] = 68;

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTimes() {
        timer.reset();
        for(int i = 0; i < 8; i++) {
            if(i == 0) {
                timer.maxTime[i] = 0;
            } else {
                timer.maxTime[i] = timer.maxTime[i - 1] + 7 + (int)(Math.random() * 6);
            }
        }
    }
}
