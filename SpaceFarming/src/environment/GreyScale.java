package environment;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

public class GreyScale {

    GamePanel gp;
    BufferedImage greyFilter;

    public GreyScale(GamePanel gp) {
        greyFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)greyFilter.getGraphics();



//        Image colorImage = g2.g;
//        ImageFilter iFilter = new GrayFilter(false, 0);
//        ImageProducer imageProducer = new FilteredImageSource(g2.getSource(), iFilter);
//        Image greyImage = Toolkit.getDefaultToolkit().createImage(imageProducer);
//        g2.drawImage(greyImage, 0, 0, gp.tileSize, gp.tileSize, null);
    }
}
