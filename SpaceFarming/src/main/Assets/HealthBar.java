package main.Assets;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealthBar {
    public Entity entity;
    double maxSize = 32.0;
    double scaling;
    public BufferedImage barImage, orbImage, circleImage;

    public HealthBar(Entity entity) {
        this.entity = entity;
        try {
            orbImage = ImageIO.read(getClass().getResourceAsStream("/objects/SoulOrb.png"));
            barImage = ImageIO.read(getClass().getResourceAsStream("/HealthBar/Bar.png"));
            circleImage = ImageIO.read(getClass().getResourceAsStream("/HealthBar/CircleBack.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        setScaling();
    }

    public void draw(Graphics2D g2) {

        //entity.image
        g2.translate(10, 10);
        g2.drawImage(barImage, 30, 0, 640, 64, null);
        //health and stamina
        g2.drawRect(30, 8, 100, 24);
        g2.drawImage(circleImage, 0, 0, 64, 64, null);

        g2.translate(entity.imgOffX * scaling + 32, entity.imgOffY * scaling + 32);
        g2.rotate(entity.rotation, entity.imgSizeX * scaling / 2, entity.imgSizeY * scaling / 2);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, entity.opacity));
        g2.drawImage(entity.image, 0, 0, (int)(entity.imgSizeX * scaling), (int)(entity.imgSizeY * scaling), null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2.rotate(-entity.rotation, entity.imgSizeX * scaling / 2, entity.imgSizeY * scaling / 2);
        g2.translate(-entity.imgOffX * scaling - 32, -entity.imgOffY * scaling - 32);
        g2.drawImage(orbImage, 640, 0, 64, 64, null);
        g2.translate(-10, -10);
    }

    public void setScaling() {
        if(entity.imgSizeX > entity.imgSizeY) {
            scaling = maxSize / entity.imgSizeX;
        } else {
            scaling = maxSize / entity.imgSizeY;
        }
    }
}
