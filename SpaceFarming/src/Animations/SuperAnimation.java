package Animations;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperAnimation {

    //public int imageFrames;
    public int[] timing;
    public BufferedImage[][] images; //0:up 1:down 2:left 3:right
    public int[] currentOffSize = new int[4];
    public int[][][] offsetSize; //[][] certain image, [] offsets and size (xoff, yoff, xsize, ysize)
    public BufferedImage image = null;
    public int counter = 0;
    public int currentFrame = 0;
    public int animationTime = 0;
    public boolean oneTime = false;
    public boolean oneTimeRaised = false;
    public String nextStatus = "None";
    public Rectangle movingHitBox = new Rectangle();

    public SuperAnimation() {}

    public BufferedImage draw() { return null; }

    public void updateTimes() {}

    public void update(boolean continuation) {
        if(counter > animationTime) {
            counter = 0;
            currentFrame = 0;
            if(oneTime) { oneTimeRaised = true; }
        } else if(!continuation) {
            counter = 0;
            currentFrame = 0;
        } else {
            for(int i = timing.length - 1; i >= 0; i--) {
                if(counter >= timing[i]) {
                    currentFrame = i;
                    break;
                }
            }
            counter++;
        }
    }

    public BufferedImage draw(String direction) {
        int dir = -1;
        if(images.length > 1) {
            if (direction.contains("up")) {
                image = images[0][currentFrame];
                dir = 0;
            } else if (direction.contains("down")) {
                image = images[1][currentFrame];
                dir = 1;
            } else if (direction.contains("left")) {
                image = images[2][currentFrame];
                dir = 2;
            } else if (direction.contains("right")) {
                image = images[3][currentFrame];
                dir = 3;
            }
        } else {
            dir = 0;
            image = images[dir][currentFrame];
        }

        //walking collision change (shouldn't matter with underground movement for BW)
        if(dir >= 0) {
            for(int i = 0; i < 4; i++) {
                currentOffSize[i] = offsetSize[dir][currentFrame][i];
            }
        }


        return image;
    }
}
