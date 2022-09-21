package main.Assets;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class CollisionChecker {
    //check entity size when checking tiles to see how many
    //add diagonal logic possibly

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int)((entity.worldX + entity.solidArea.x + entity.imgOffX) * gp.scale);
        int entityRightWorldX = (int)((entity.worldX + entity.solidArea.x + entity.solidArea.width + entity.imgOffX) * gp.scale);
        int entityTopWorldY = (int)((entity.worldY + entity.solidArea.y + entity.imgOffY) * gp.scale);
        int entityBottomWorldY = (int)((entity.worldY + entity.solidArea.y + entity.solidArea.height + entity.imgOffY) * gp.scale);

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        if(!entity.status.contains("Idle") && !entity.status.contains("No_Move")) {

            if(entity.direction.contains("up")) {
                entityTopRow = (int)((entityTopWorldY - (entity.speedUsedY * gp.scale))/gp.tileSize);
                tileNum1 = gp.tileM.map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.map.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.map.tile[tileNum1].collision == true || gp.tileM.map.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            } else if(entity.direction.contains("down")) {
                entityBottomRow = (int)((entityBottomWorldY + (entity.speedUsedY * gp.scale))/gp.tileSize);
                tileNum1 = gp.tileM.map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.map.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.map.tile[tileNum1].collision == true || gp.tileM.map.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            } else if(entity.direction.contains("left")) {
                entityLeftCol = (int)((entityLeftWorldX - (entity.speedUsedX * gp.scale))/gp.tileSize);
                tileNum1 = gp.tileM.map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.map.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.map.tile[tileNum1].collision == true || gp.tileM.map.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            } else if(entity.direction.contains("right")) {
                entityRightCol = (int)((entityRightWorldX + (entity.speedUsedX * gp.scale))/gp.tileSize);
                tileNum1 = gp.tileM.map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.map.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.map.tile[tileNum1].collision == true || gp.tileM.map.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.obj.size(); i++) {
            if(gp.obj.get(i) != null) {
                //get entity solid area pos
                entity.solidArea.x = (int)(entity.worldX + ((entity.solidArea.x + entity.imgOffX) * gp.scale));
                entity.solidArea.y = (int)(entity.worldY + ((entity.solidArea.y + entity.imgOffY) * gp.scale));
                entity.solidArea.width = (int)(entity.solidArea.width * gp.scale);
                entity.solidArea.height = (int)(entity.solidArea.height * gp.scale);

                //get object solid area pos
                gp.obj.get(i).solidArea.x = (int)(gp.obj.get(i).worldX + ((gp.obj.get(i).solidArea.x + gp.obj.get(i).imgOffX) * gp.scale));
                gp.obj.get(i).solidArea.y = (int)(gp.obj.get(i).worldY + ((gp.obj.get(i).solidArea.y + gp.obj.get(i).imgOffY) * gp.scale));
                gp.obj.get(i).solidArea.width = (int)((gp.obj.get(i).imgSizeX) * gp.scale);
                gp.obj.get(i).solidArea.height = (int)((gp.obj.get(i).imgSizeY) * gp.scale);

                //offset needs to be accounted for
                if(!entity.direction.contains("Idle") && !entity.direction.contains("No_Move")) {
                    if(entity.direction.contains("up")) {
                        entity.solidArea.y -= (entity.speedUsedY * gp.scale);
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)) {
                            if(gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } else if(entity.direction.contains("down")) {
                        entity.solidArea.y += (entity.speedUsedY * gp.scale);
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)) {
                            if(gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } else if(entity.direction.contains("left")) {
                        entity.solidArea.x -= (entity.speedUsedX * gp.scale);
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)) {
                            if(gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } else if(entity.direction.contains("right")) {
                        entity.solidArea.x += (entity.speedUsedX * gp.scale);
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)) {
                            if(gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                }

                entity.solidArea = new Rectangle(entity.solidAreaBase);
                gp.obj.get(i).solidArea = new Rectangle(0, 0, gp.obj.get(i).imgSizeX, gp.obj.get(i).imgSizeY);
            }
        }

        return index;
    }

//    public int checkEntity(Entity checking, Entity checked) {
//
//    }
}
