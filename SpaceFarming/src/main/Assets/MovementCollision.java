package main.Assets;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MovementCollision extends Collision {

    public MovementCollision(Entity entity, String[] type, double[] amount, Rectangle hitbox) {
        super(entity, type, amount, hitbox);
    }

    public void genCheckTile() {
        genCheckTile(entity.collisionAmount);
    }

    public void genCheckTile(double cAmount) {
//        AffineTransform af = new AffineTransform();
//        af.translate(entity.worldX, entity.worldY);
//        af.rotate(entity.rotation, -entity.imgOffX, -entity.imgOffY);
//        Rectangle rect = hitbox;
//        Shape rRect = af.createTransformedShape(rect);
//        Rectangle bounds = rRect.getBounds();
//        double entityLeftWorldX = bounds.x;
//        double entityRightWorldX = bounds.width + bounds.x;
//        double entityTopWorldY = bounds.y;
//        double entityBottomWorldY = bounds.y + bounds.height;

        double entityLeftWorldX = (entity.worldX + entity.solidArea.x + entity.imgOffX);
        double entityRightWorldX = (entity.worldX + entity.solidArea.x + entity.solidArea.width + entity.imgOffX);
        double entityTopWorldY = (entity.worldY + entity.solidArea.y + entity.imgOffY);
        double entityBottomWorldY = (entity.worldY + entity.solidArea.y + entity.solidArea.height + entity.imgOffY);

        int entityLeftCol = (int)(entityLeftWorldX/entity.gp.originalTileSize);
        int entityRightCol = (int)(entityRightWorldX/entity.gp.originalTileSize);
        int entityTopRow = (int)(entityTopWorldY/entity.gp.originalTileSize);
        int entityBottomRow = (int)(entityBottomWorldY/entity.gp.originalTileSize);

        int[] tileNum;

        if(!entity.status.contains("Idle") && !entity.status.contains("No_Move")) {
            //entity.collisionAmount = 10000000.0;

            double amtU = 10000000.0;
            if(entity.speedUsedY < 0) {
                entity.collisionAmount = 10000000.0;
                tileNum = new int[entityRightCol - entityLeftCol + 1];
                entityTopRow = (int)((entityTopWorldY + (entity.speedUsedY * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityLeftCol + i][entityTopRow];
                }
                for(int i = 0; i < tileNum.length; i++) {
                    for(int j = 0; j < type.length; j++) {
                        //rRect.intersects(new Rectangle((int)(entityLeftWorldX + (i * entity.gp.originalTileSize)), entityTopRow * entity.gp.originalTileSize, entity.gp.originalTileSize, entity.gp.originalTileSize))
                        if(entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtU = amtU > amount[j] ? amount[j] : amtU;
                        }
                    }
                }
                entityTopRow = (int)(entityTopWorldY/entity.gp.originalTileSize);
            } else { amtU = 1.0; }
            double amtD = 10000000.0;
            if(entity.speedUsedY > 0) {
                entity.collisionAmount = 10000000.0;
                tileNum = new int[entityRightCol - entityLeftCol + 1];
                entityBottomRow = (int)((entityBottomWorldY + (entity.speedUsedY * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityLeftCol + i][entityBottomRow];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        //rRect.intersects(new Rectangle((int)(entityLeftWorldX + (i * entity.gp.originalTileSize)), entityBottomRow * entity.gp.originalTileSize, entity.gp.originalTileSize, entity.gp.originalTileSize)) &&
                        if(entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtD = amtD > amount[j] ? amount[j] : amtD;
                        }
                    }
                }
                entityBottomRow = (int)(entityBottomWorldY/entity.gp.originalTileSize);
            } else { amtD = 1.0; }
            double amtL = 10000000.0;
            if(entity.speedUsedX < 0) {
                entity.collisionAmount = 10000000.0;
                tileNum = new int[entityBottomRow - entityTopRow + 1];
                entityLeftCol = (int)((entityLeftWorldX + (entity.speedUsedX * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityBottomRow - entityTopRow; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityLeftCol][entityTopRow + i];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        //rRect.intersects(new Rectangle(entityLeftCol * entity.gp.originalTileSize, (int)(entityTopWorldY + (i * entity.gp.originalTileSize)), entity.gp.originalTileSize, entity.gp.originalTileSize)) &&
                        if(entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtL = amtL > amount[j] ? amount[j] : amtL;
                        }
                    }
                }
                entityLeftCol = (int)(entityLeftWorldX/entity.gp.originalTileSize);
            } else { amtL = 1.0; }
            double amtR = 10000000.0;
            if(entity.speedUsedX > 0) {
                entity.collisionAmount = 10000000.0;
                tileNum = new int[entityBottomRow - entityTopRow + 1];
                entityRightCol = (int)((entityRightWorldX + (entity.speedUsedX * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityBottomRow - entityTopRow; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityRightCol][entityTopRow + i];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        //rRect.intersects(new Rectangle(entityRightCol * entity.gp.originalTileSize, (int)(entityTopWorldY + (i * entity.gp.originalTileSize)), entity.gp.originalTileSize, entity.gp.originalTileSize)) &&
                        if(entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtR = amtR > amount[j] ? amount[j] : amtR;
                        }
                    }
                }
                entityRightCol = (int)(entityRightWorldX/entity.gp.originalTileSize);
            } else { amtR = 1.0; }

            //checks that the right tiles will be checked based on how fast an entity moves on specific tiles
            if(cAmount < entity.collisionAmount) {
                genCheckTile(entity.collisionAmount);
            }

            if(amtU == 0.0 || amtD == 0.0) {
                entity.speedUsedY = 0.0;
                if(entity.speedUsedX < 0) {
                    entity.collisionAmount = amtL;
                }  else if(entity.speedUsedX > 0) {
                    entity.collisionAmount = amtR;
                }
            }
            if(amtL == 0.0 || amtR == 0.0) {
                entity.speedUsedX = 0.0;
                if(entity.speedUsedY < 0) {
                    entity.collisionAmount = amtU;
                }  else if(entity.speedUsedY > 0) {
                    entity.collisionAmount = amtD;
                }
            }
        }
//        af.translate(-entity.worldX, -entity.worldY);
//        af.rotate(-entity.rotation, entity.imgOffX, entity.imgOffY);
    }

    public void fishCheckTile() { fishCheckTile(entity.collisionAmount); }

    public void fishCheckTile(double cAmount) {
        //rotations
        //if fish hits wall, stop

        AffineTransform af = new AffineTransform();
        af.translate(entity.worldX, entity.worldY);
        af.rotate(entity.rotation, -entity.imgOffX, -entity.imgOffY);
        Rectangle rect = hitbox;
        Shape rRect = af.createTransformedShape(rect);

        Rectangle bounds = rRect.getBounds();
        int entityLeftWorldX = bounds.x;
        int entityRightWorldX = bounds.width + bounds.x;
        int entityTopWorldY = bounds.y;
        int entityBottomWorldY = bounds.y + bounds.height;

        int entityLeftCol = entityLeftWorldX / entity.gp.originalTileSize;
        int entityRightCol = entityRightWorldX / entity.gp.originalTileSize;
        int entityTopRow = entityTopWorldY / entity.gp.originalTileSize;
        int entityBottomRow = entityBottomWorldY / entity.gp.originalTileSize;
        //check when going in each direction using speed to check

        int[][] tileNum;

        if (!entity.status.contains("Idle") && !entity.status.contains("No_Move")) {
            entity.collisionAmount = 10000000000.0;
            entity.collisionOn = false;

            boolean[] dir = new boolean[]{false, false, false, false}; //udlr
            double[] amt = new double[]{10000000000.0, 10000000000.0}; // u/d, l/r

            System.out.println(cAmount);
//            if(entity.speedUsedX != 0 || entity.speedUsedY != 0) {
//                entity.collisionAmount = 10000000.0;
//            }
            if (entity.speedUsedX > 0) { //RIGHT----------------------------------------------------------------------------------------------------------------------------------
                entityRightCol = (int) ((entityRightWorldX + (entity.speedUsedX * cAmount)) / entity.gp.originalTileSize);
                dir[3] = true;

                tileNum = new int[(entityRightCol - entityLeftCol) + 1][(entityBottomRow - entityTopRow) + 1];

                for (int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    for (int j = 0; j <= entityBottomRow - entityTopRow; j++) {
                        tileNum[i][j] = entity.gp.tileM.map.mapTileNum[entityLeftCol + i][entityTopRow + j];
                    }
                }
                Rectangle tRect;
                for (int i = 0; i < tileNum.length; i++) {
                    for (int j = 0; j < tileNum[0].length; j++) {
                        tRect = new Rectangle(entityLeftCol + (i * entity.gp.originalTileSize), entityTopRow + (j * entity.gp.originalTileSize), entity.gp.originalTileSize, entity.gp.originalTileSize);
                        for (int k = 0; k < type.length; k++) {
                            if (rRect.intersects(tRect) && entity.gp.tileM.map.tile[tileNum[i][j]].type == type[k]) {
                                entity.collisionOn = true;
                                entity.collisionAmount = entity.collisionAmount > amount[k] ? amount[k] : entity.collisionAmount;
                                amt[1] = amt[1] > amount[k] ? amount[k] : amt[1];
                            }
                        }
                    }
                }
                entityRightCol = entityRightWorldX/entity.gp.originalTileSize;
            } else if (entity.speedUsedX < 0) { //LEFT----------------------------------------------------------------------------------------------------------------------------------
                entityLeftCol = (int)((entityLeftWorldX + (entity.speedUsedX * cAmount)) / entity.gp.originalTileSize);
                dir[2] = true;

                tileNum = new int[entityRightCol - entityLeftCol + 1][entityBottomRow - entityTopRow + 1];

                for (int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    for (int j = 0; j <= entityBottomRow - entityTopRow; j++) {
                        tileNum[i][j] = entity.gp.tileM.map.mapTileNum[entityLeftCol + i][entityTopRow + j];
                    }
                }
                Rectangle tRect;
                for (int i = 0; i < tileNum.length; i++) {
                    for (int j = 0; j < tileNum[0].length; j++) {
                        tRect = new Rectangle(entityLeftCol + (i * entity.gp.originalTileSize), entityTopRow + (j * entity.gp.originalTileSize), entity.gp.originalTileSize, entity.gp.originalTileSize);
                        for (int k = 0; k < type.length; k++) {
                            if (rRect.intersects(tRect) && entity.gp.tileM.map.tile[tileNum[i][j]].type == type[k]) {
                                entity.collisionOn = true;
                                entity.collisionAmount = entity.collisionAmount > amount[k] ? amount[k] : entity.collisionAmount;
                                amt[1] = amt[1] > amount[k] ? amount[k] : amt[1];
                            }
                        }
                    }
                }
                entityLeftCol = entityLeftWorldX/entity.gp.originalTileSize;
            }
            if (entity.speedUsedY > 0) { //DOWN----------------------------------------------------------------------------------------------------------------------------------
                entityBottomRow = (int) ((entityBottomWorldY + (entity.speedUsedY * cAmount)) / entity.gp.originalTileSize);
                dir[1] = true;

                tileNum = new int[entityRightCol - entityLeftCol + 1][entityBottomRow - entityTopRow + 1];

                for (int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    for (int j = 0; j <= entityBottomRow - entityTopRow; j++) {
                        tileNum[i][j] = entity.gp.tileM.map.mapTileNum[entityLeftCol + i][entityTopRow + j];
                    }
                }
                Rectangle tRect;
                for (int i = 0; i < tileNum.length; i++) {
                    for (int j = 0; j < tileNum[0].length; j++) {
                        tRect = new Rectangle(entityLeftCol + (i * entity.gp.originalTileSize), entityTopRow + (j * entity.gp.originalTileSize), entity.gp.originalTileSize, entity.gp.originalTileSize);
                        for (int k = 0; k < type.length; k++) {
                            if (rRect.intersects(tRect) && entity.gp.tileM.map.tile[tileNum[i][j]].type == type[k]) {
                                entity.collisionOn = true;
                                entity.collisionAmount = entity.collisionAmount > amount[k] ? amount[k] : entity.collisionAmount;
                                amt[0] = amt[0] > amount[k] ? amount[k] : amt[0];
                            }
                        }
                    }
                }
                entityBottomRow = entityBottomWorldY/entity.gp.originalTileSize;
            } else if (entity.speedUsedY < 0) { //UP----------------------------------------------------------------------------------------------------------------------------------
                entityTopRow = (int) ((entityTopWorldY + (entity.speedUsedY * cAmount)) / entity.gp.originalTileSize);
                dir[0] = true;

                tileNum = new int[entityRightCol - entityLeftCol + 1][entityBottomRow - entityTopRow + 1];

                for (int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    for (int j = 0; j <= entityBottomRow - entityTopRow; j++) {
                        tileNum[i][j] = entity.gp.tileM.map.mapTileNum[entityLeftCol + i][entityTopRow + j];
                    }
                }
                Rectangle tRect;
                for (int i = 0; i < tileNum.length; i++) {
                    for (int j = 0; j < tileNum[0].length; j++) {
                        tRect = new Rectangle(entityLeftCol + (i * entity.gp.originalTileSize), entityTopRow + (j * entity.gp.originalTileSize), entity.gp.originalTileSize, entity.gp.originalTileSize);
                        for (int k = 0; k < type.length; k++) {
                            if (rRect.intersects(tRect) && entity.gp.tileM.map.tile[tileNum[i][j]].type == type[k]) {
                                entity.collisionOn = true;
                                entity.collisionAmount = entity.collisionAmount > amount[k] ? amount[k] : entity.collisionAmount;
                                amt[0] = amt[0] > amount[k] ? amount[k] : amt[0];
                            }
                        }
                    }
                }
                entityTopRow = entityTopWorldY/entity.gp.originalTileSize;
            }

            //checks that the right tiles will be checked based on how fast an entity moves on specific tiles
            if (cAmount < entity.collisionAmount) {
                fishCheckTile(entity.collisionAmount);
            }

            if (amt[0] == 0.0) {
                entity.speedUsedY = 0.0;
                if (dir[2] || dir[3]) {
                    entity.collisionAmount = amt[1];
                }
            }
            if (amt[1] == 0.0) {
                entity.speedUsedX = 0.0;
                if (dir[0] || dir[1]) {
                    entity.collisionAmount = amt[0];
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player) { //needs a redo without scale as a factor
        int index = 999;

        for(int i = 0; i < entity.gp.obj.size(); i++) {
            if(entity.gp.obj.get(i) != null) {
                //get entity solid area pos
                entity.solidArea.x = (int)(entity.worldX + ((entity.solidArea.x + entity.imgOffX) * entity.gp.scale));
                entity.solidArea.y = (int)(entity.worldY + ((entity.solidArea.y + entity.imgOffY) * entity.gp.scale));
                entity.solidArea.width = (int)(entity.solidArea.width * entity.gp.scale);
                entity.solidArea.height = (int)(entity.solidArea.height * entity.gp.scale);

                //get object solid area pos
                Rectangle area = new Rectangle();
                entity.gp.obj.get(i).solidArea.x = (int)(entity.gp.obj.get(i).worldX + ((entity.gp.obj.get(i).solidArea.x + entity.gp.obj.get(i).imgOffX) * entity.gp.scale));
                entity.gp.obj.get(i).solidArea.y = (int)(entity.gp.obj.get(i).worldY + ((entity.gp.obj.get(i).solidArea.y + entity.gp.obj.get(i).imgOffY) * entity.gp.scale));
                entity.gp.obj.get(i).solidArea.width = (int)((entity.gp.obj.get(i).imgSizeX) * entity.gp.scale);
                entity.gp.obj.get(i).solidArea.height = (int)((entity.gp.obj.get(i).imgSizeY) * entity.gp.scale);

                //offset needs to be accounted for
                if(!entity.direction.contains("Idle") && !entity.direction.contains("No_Move")) {
                    if(entity.direction.contains("Up")) {
                        entity.solidArea.y -= (entity.speedUsedY * entity.gp.scale);
                        if(entity.solidArea.intersects(entity.gp.obj.get(i).solidArea)) {
                            if(entity.gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } else if(entity.direction.contains("Down")) {
                        entity.solidArea.y += (entity.speedUsedY * entity.gp.scale);
                        if(entity.solidArea.intersects(entity.gp.obj.get(i).solidArea)) {
                            if(entity.gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } else if(entity.direction.contains("Left")) {
                        entity.solidArea.x -= (entity.speedUsedX * entity.gp.scale);
                        if(entity.solidArea.intersects(entity.gp.obj.get(i).solidArea)) {
                            if(entity.gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } else if(entity.direction.contains("Right")) {
                        entity.solidArea.x += (entity.speedUsedX * entity.gp.scale);
                        if(entity.solidArea.intersects(entity.gp.obj.get(i).solidArea)) {
                            if(entity.gp.obj.get(i).collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                }

                //entity.solidArea = new Rectangle(entity.solidArea);
                entity.gp.obj.get(i).solidArea = new Rectangle(0, 0, entity.gp.obj.get(i).imgSizeX, entity.gp.obj.get(i).imgSizeY);
            }
        }

        return index;
    }
}


//OLD Version, unsure if still useful in any way
/*
    //slight changes may need to be made to account for movement while scrolling
    public void checkTile() {
        checkTile(entity.collisionAmount);
    }


    public void checkTile(double cAmount) {
        int entityLeftWorldX = (int)(entity.worldX + entity.solidArea.x + entity.imgOffX);
        int entityRightWorldX = (int)(entity.worldX + entity.solidArea.x + entity.solidArea.width + entity.imgOffX);
        int entityTopWorldY = (int)(entity.worldY + entity.solidArea.y + entity.imgOffY);
        int entityBottomWorldY = (int)(entity.worldY + entity.solidArea.y + entity.solidArea.height + entity.imgOffY);

        int entityLeftCol = entityLeftWorldX/entity.gp.originalTileSize;
        int entityRightCol = entityRightWorldX/entity.gp.originalTileSize;
        int entityTopRow = entityTopWorldY/entity.gp.originalTileSize;
        int entityBottomRow = entityBottomWorldY/entity.gp.originalTileSize;

        int[] tileNum;

        if(!entity.status.contains("Idle") && !entity.status.contains("No_Move")) {
            entity.collisionAmount = 10000000.0;
            entity.collisionOn = false;

            double amtU = 10000000.0;
            if(entity.direction.contains("up")) {
                tileNum = new int[entityRightCol - entityLeftCol + 1];
                entityTopRow = (int)((entityTopWorldY + (entity.speedUsedY * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityRightCol - i][entityTopRow];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        if(entity.gp.tileM.map.tile[tileNum[i]].collision && entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtU = amtU > amount[j] ? amount[j] : amtU;
                        }
                    }
                }
                entityTopRow = entityTopWorldY/entity.gp.originalTileSize;
            }
            double amtD = 10000000.0;
            if(entity.direction.contains("down")) {
                tileNum = new int[entityRightCol - entityLeftCol + 1];
                entityBottomRow = (int)((entityBottomWorldY + (entity.speedUsedY * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityRightCol - entityLeftCol; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityRightCol - i][entityBottomRow];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        if(entity.gp.tileM.map.tile[tileNum[i]].collision && entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtD = amtD > amount[j] ? amount[j] : amtD;
                        }
                    }
                }
                entityBottomRow = entityBottomWorldY/entity.gp.originalTileSize;
            }
            double amtL = 10000000.0;
            if(entity.direction.contains("left")) {
                tileNum = new int[entityBottomRow - entityTopRow + 1];
                entityLeftCol = (int)((entityLeftWorldX + (entity.speedUsedX * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityBottomRow - entityTopRow; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityLeftCol][entityBottomRow - i];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        if(entity.gp.tileM.map.tile[tileNum[i]].collision && entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtL = amtL > amount[j] ? amount[j] : amtL;
                        }
                    }
                }
                entityLeftCol = entityLeftWorldX/entity.gp.originalTileSize;
            }
            double amtR = 10000000.0;
            if(entity.direction.contains("right")) {
                tileNum = new int[entityBottomRow - entityTopRow + 1];
                entityRightCol = (int)((entityRightWorldX + (entity.speedUsedX * cAmount))/entity.gp.originalTileSize);
                for(int i = 0; i <= entityBottomRow - entityTopRow; i++) {
                    tileNum[i] = entity.gp.tileM.map.mapTileNum[entityRightCol][entityBottomRow - i];
                }
                for(int i = 0; i < tileNum.length; i ++) {
                    for(int j = 0; j < type.length; j++) {
                        if(entity.gp.tileM.map.tile[tileNum[i]].collision && entity.gp.tileM.map.tile[tileNum[i]].type == type[j]) {
                            entity.collisionOn = true;
                            entity.collisionAmount = entity.collisionAmount > amount[j] ? amount[j]: entity.collisionAmount;
                            amtR = amtR > amount[j] ? amount[j] : amtR;
                        }
                    }
                }
                entityRightCol = entityRightWorldX/entity.gp.originalTileSize;
            }

            //checks that the right tiles will be checked based on how fast an entity moves on specific tiles
            if(cAmount < entity.collisionAmount) {
                checkTile(entity.collisionAmount);
            }

            if(amtU == 0.0 || amtD == 0.0) {
                entity.speedUsedY = 0.0;
                if(entity.direction.contains("left")) {
                    entity.collisionAmount = amtL;
                }  else if(entity.direction.contains("right")) {
                    entity.collisionAmount = amtR;
                }
            }
            if(amtL == 0.0 || amtR == 0.0) {
                entity.speedUsedX = 0.0;
                if(entity.direction.contains("up")) {
                    entity.collisionAmount = amtU;
                }  else if(entity.direction.contains("down")) {
                    entity.collisionAmount = amtD;
                }
            }
        }
    }
     */