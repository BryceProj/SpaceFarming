package Animations;

import main.Assets.Timer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperAnimation {

    //public int imageFrames;
    public Timer timer;
    public BufferedImage[][] images; //0:up 1:down 2:left 3:right
    public int[] currentOffSize = new int[4];
    public int[][][] offsetSize; //[][] certain image, [] offsets and size (xoff, yoff, xsize, ysize)
    public BufferedImage image = null;
    public boolean oneTime = false;
    public boolean oneTimeRaised = false;
    public String nextStatus = "None";
    public Rectangle movingHitBox = new Rectangle();

    public SuperAnimation() {}

    public BufferedImage draw() { return null; }

    public void update(boolean continuation) {
        if(timer.update() > timer.size()) {
            oneTimeRaised = true;
            timer.reset();
        } else if(!continuation) {
            timer.reset();
        } else {
//            for(int i = timing.length - 1; i >= 0; i--) {
//                if(counter >= timing[i]) {
//                    currentFrame = i;
//                    break;
//                }
//            }
        }
    }

    public BufferedImage draw(String direction) {
        int dir = -1;
        if(images.length > 1) {
            if (direction.contains("Up")) {
                image = images[0][timer.current];
                dir = 0;
            } else if (direction.contains("Down")) {
                image = images[1][timer.current];
                dir = 1;
            } else if (direction.contains("Left")) {
                image = images[2][timer.current];
                dir = 2;
            } else if (direction.contains("Right")) {
                image = images[3][timer.current];
                dir = 3;
            }
        } else {
            dir = 0;
            image = images[dir][timer.current];
        }

        //walking collision change (shouldn't matter with underground movement for BW)
        if(dir >= 0) {
            for(int i = 0; i < 4; i++) {
                currentOffSize[i] = offsetSize[dir][timer.current][i];
            }
        }
        return image;
    }
}
