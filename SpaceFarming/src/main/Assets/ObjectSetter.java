package main.Assets;

import main.GamePanel;
import object.OBJ_Skull;
import object.OBJ_Soul;
import object.OBJ_SoulParticle;

public class ObjectSetter {
    GamePanel gp;

    public ObjectSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj.add(new OBJ_Soul());
        gp.obj.get(0).worldX = 10 * gp.tileSize;
        gp.obj.get(0).worldY = 10 * gp.tileSize;

        gp.obj.add(new OBJ_SoulParticle(gp, (OBJ_Soul)gp.obj.get(0)));

    }
}
