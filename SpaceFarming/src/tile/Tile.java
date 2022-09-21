package tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage[] image;
    public boolean collision = false; //String[] // change here
    public String type;
    public double amount;
    public int frames = 1;
    public int currentFrame = 0;

    public Tile() {
        image = new BufferedImage[1];
    }

    public Tile(int frames) {
        this.frames = frames;
        image = new BufferedImage[frames];
    }
}
