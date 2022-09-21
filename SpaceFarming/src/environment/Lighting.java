package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp, int circleSize) {
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        double centerX = gp.player.screenX;
        double centerY = gp.player.screenY;
        double x = centerX - (circleSize / 2);
        double y = centerY - (circleSize / 2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
        Area lightArea = new Area(circleShape);

        screenArea.subtract(lightArea);

        Color color[] = new Color[8];
        float fraction[] = new float[8];

        for(int i = 0; i < color.length; i++) {
            float f = i != color.length - 1 ? (float) (1f * ((float)i / color.length)) : 0.95f;
            color[i] = new Color(0, 0, 0, f);
            fraction[i] = 1f * (float)((float)i / color.length);
        }

        RadialGradientPaint gPaint = new RadialGradientPaint((int)centerX, (int)centerY, (int)(gp.scale * circleSize / 2), fraction, color);

        g2.setPaint(gPaint);
        g2.fill(lightArea);
        g2.fill(screenArea);
        g2.dispose();;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
