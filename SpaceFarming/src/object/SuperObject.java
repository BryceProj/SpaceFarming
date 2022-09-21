package object;

import main.GamePanel;
import main.Assets.Timer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {

    public GamePanel gp;
    public BufferedImage image;
    public float opacity = 1.0f;
    public String name;
    public boolean collision = false;
    public double worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 50, 50);
    public int solidAreaX = 0;
    public int solidAreaY = 0;
    public int imgOffX, imgOffY;
    public int imgSizeX, imgSizeY;
    public Timer timer = new Timer(new int[]{0});

    public SuperObject() {}

    public void update() {

    }

    public void draw(Graphics2D g2, GamePanel gp) {
        double screenX = (gp.scale * (worldX - gp.player.worldX)) + gp.player.screenX;
        double screenY = (gp.scale * (worldY - gp.player.worldY)) + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2.drawImage(image, (int)(screenX + (imgOffX * gp.scale)), (int)(screenY + (imgOffY * gp.scale)), (int)(imgSizeX * gp.scale), (int)(imgSizeY * gp.scale), null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }
}
