package Animations.FlyGuy;

import Animations.SuperAnimation;
import main.Assets.Timer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlyGuyWalkingAnimation extends SuperAnimation {

    public FlyGuyWalkingAnimation() {

        images = new BufferedImage[4][8];
        offsetSize = new int[4][8][4];
        int eachtime = 6;
        timer = new Timer(new int[]{eachtime, eachtime, eachtime, eachtime, eachtime, eachtime, eachtime, eachtime}, true);
        nextStatus = "Walk";
        try {
            images[0][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW1.png"));
            images[0][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW2.png"));
            images[0][2] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW3.png"));
            images[0][3] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW4.png"));
            images[0][4] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW5.png"));
            images[0][5] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW6.png"));
            images[0][6] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW7.png"));
            images[0][7] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-UW8.png"));
            images[1][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW1.png"));
            images[1][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW2.png"));
            images[1][2] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW3.png"));
            images[1][3] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW4.png"));
            images[1][4] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW5.png"));
            images[1][5] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW6.png"));
            images[1][6] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW7.png"));
            images[1][7] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/BugMan/Bug Man-DW8.png"));
            images[2][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left1.png"));
            images[2][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left2.png"));
            images[2][2] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left1.png"));
            images[2][3] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left2.png"));
            images[2][4] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left1.png"));
            images[2][5] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left2.png"));
            images[2][6] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left1.png"));
            images[2][7] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/left2.png"));
            images[3][0] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right1.png"));
            images[3][1] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right2.png"));
            images[3][2] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right1.png"));
            images[3][3] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right2.png"));
            images[3][4] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right1.png"));
            images[3][5] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right2.png"));
            images[3][6] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right1.png"));
            images[3][7] = ImageIO.read(getClass().getResourceAsStream("/EntitySprites/right2.png"));

            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 8; j++) {
                    offsetSize[i][j][0] = -12;
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
