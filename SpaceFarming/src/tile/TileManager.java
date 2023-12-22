package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.*;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    public Map map;
    public int tileAnimationCounter;

    public boolean greyScale = false;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        map = new Map(gp);
        tileAnimationCounter = 0;
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < gp.maxScreenCol; i++) {
            for(int j = 0; j < gp.maxScreenRow; j++) {
                int tileNum = map.mapTileNum[i][j];
                int worldX = i * (int)gp.originalTileSize;
                int worldY = j * (int)gp.originalTileSize;
                double screenX = (gp.scale * (worldX - gp.player.worldX)) + gp.player.screenX;
                double screenY = (gp.scale * (worldY - gp.player.worldY)) + gp.player.screenY;
                //worldX *= gp.scale;
                //worldY *= gp.scale;

                //change here to factor for scale
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.translate((int)screenX, (int)screenY);
                    if(!greyScale) {
                        g2.drawImage(map.tile[tileNum].image[map.tile[tileNum].currentFrame], 0, 0, gp.tileSize, gp.tileSize, null);
                    } else {
                        Image colorImage = map.tile[tileNum].image[map.tile[tileNum].currentFrame];
                        ImageFilter iFilter = new GrayFilter(false, 0);
                        ImageProducer imageProducer = new FilteredImageSource(colorImage.getSource(), iFilter);
                        Image greyImage = Toolkit.getDefaultToolkit().createImage(imageProducer);
                        g2.drawImage(greyImage, 0, 0, gp.tileSize, gp.tileSize, null);
                    }
                    g2.translate(-(int)screenX, -(int)screenY);
                }
            }
        }
    }

    public void update() {
        if(tileAnimationCounter > 90 / map.tile.length) {
            for(int i = 0; i < map.tile.length; i++) {
                if(map.tile[i].currentFrame == map.tile[i].frames - 1) {
                    tileAnimationCounter = 0;
                    map.tile[i].currentFrame = 0;
                } else {
                    map.tile[i].currentFrame++;
                }
            }
        }
        tileAnimationCounter++;
    }
}
