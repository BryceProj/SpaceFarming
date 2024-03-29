package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel gp;
    Lighting lighting;

    int viewMode = 0; //0: normal, 1: scent

    public  EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setUp() {
        lighting = new Lighting(gp, 1000);
    }

    public void update() { lighting = new Lighting(gp, 1000); }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }

    public int getViewMode() {
        return viewMode;
    }
    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
        if(viewMode < 0) {
            this.viewMode = 0;
        }
        switch(viewMode) {
            case 0:
                gp.tileM.greyScale = false;
                break;
            case 1:
                gp.tileM.greyScale = true;
                break;
        }
    }
}
