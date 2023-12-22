package Animations.ParasiteBrainWorm;

import Animations.SuperAnimation;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class A_PBW_IdleAG extends SuperAnimation {
    public A_PBW_IdleAG() {
        //(xoff, yoff, xsize, ysize)
        images = new BufferedImage[1][10];
        offsetSize = new int[1][10][4];
        timer = new Timer(new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, true);
        movingHitBox = new Rectangle(0, 0, 15, 5);
        try {
                images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle0.png"));
                offsetSize[0][0][0] = -8;
                offsetSize[0][0][1] = -13;
                offsetSize[0][0][2] = 16;
                offsetSize[0][0][3] = 20;

                images[0][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle1.png"));
                offsetSize[0][1][0] = -8;
                offsetSize[0][1][1] = -16;
                offsetSize[0][1][2] = 17;
                offsetSize[0][1][3] = 23;

                images[0][2] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle2.png"));
                offsetSize[0][2][0] = -8;
                offsetSize[0][2][1] = -14;
                offsetSize[0][2][2] = 17;
                offsetSize[0][2][3] = 21;

                images[0][3] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle1.png"));
                offsetSize[0][3][0] = -8;
                offsetSize[0][3][1] = -16;
                offsetSize[0][3][2] = 17;
                offsetSize[0][3][3] = 23;

                images[0][4] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle0.png"));
                offsetSize[0][4][0] = -8;
                offsetSize[0][4][1] = -13;
                offsetSize[0][4][2] = 16;
                offsetSize[0][4][3] = 20;

                images[0][5] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle3.png"));
                offsetSize[0][5][0] = -8;
                offsetSize[0][5][1] = -13;
                offsetSize[0][5][2] = 16;
                offsetSize[0][5][3] = 20;

                images[0][6] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle4.png"));
                offsetSize[0][6][0] = -9;
                offsetSize[0][6][1] = -16;
                offsetSize[0][6][2] = 17;
                offsetSize[0][6][3] = 23;

                images[0][7] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle5.png"));
                offsetSize[0][7][0] = -9;
                offsetSize[0][7][1] = -14;
                offsetSize[0][7][2] = 17;
                offsetSize[0][7][3] = 21;

                images[0][8] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle4.png"));
                offsetSize[0][8][0] = -9;
                offsetSize[0][8][1] = -16;
                offsetSize[0][8][2] = 17;
                offsetSize[0][8][3] = 23;

                images[0][9] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/ParasiteBrainWorm/PBW-Idle3.png"));
                offsetSize[0][9][0] = -8;
                offsetSize[0][9][1] = -13;
                offsetSize[0][9][2] = 16;
                offsetSize[0][9][3] = 20;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
