package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    GamePanel gp;
    public int[][] mapTileNum;
    public Tile[] tile;
    public int sizeX;
    public int sizeY;

    public Map(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        tile = new Tile[15]; //kinds of tiles

        loadMap();
        getTileImage();
    }

    public Map() {}

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e) {

        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].collision = true;
            tile[0].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
            tile[0].type = "Water";

            tile[1] = new Tile();
            tile[1].collision = true;
            tile[1].type = "Land";
            tile[1].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
            tile[2] = new Tile();
            tile[2].collision = true;
            tile[2].type = "Land";
            tile[2].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));

            tile[3] = new Tile();
            tile[3].collision = true;
            tile[3].type = "Water-Edge";
            tile[3].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-T-Border.png"));
            tile[4] = new Tile();
            tile[4].collision = true;
            tile[4].type = "Water-Edge";
            tile[4].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-B-Border.png"));
            tile[5] = new Tile();
            tile[5].collision = true;
            tile[5].type = "Water-Edge";
            tile[5].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-L-Border.png"));
            tile[6] = new Tile();
            tile[6].collision = true;
            tile[6].type = "Water-Edge";
            tile[6].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-R-Border.png"));
            tile[7] = new Tile();
            tile[7].collision = true;
            tile[7].type = "Water-Edge";
            tile[7].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-TL-Border.png"));
            tile[8] = new Tile();
            tile[8].collision = true;
            tile[8].type = "Water-Edge";
            tile[8].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-TR-Border.png"));
            tile[9] = new Tile();
            tile[9].collision = true;
            tile[9].type = "Water-Edge";
            tile[9].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-BL-Border.png"));
            tile[10] = new Tile();
            tile[10].collision = true;
            tile[10].type = "Water-Edge";
            tile[10].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-BR-Border.png"));
            tile[11] = new Tile();
            tile[11].collision = true;
            tile[11].type = "Water-Edge";
            tile[11].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-OTL-Border.png"));
            tile[12] = new Tile();
            tile[12].collision = true;
            tile[12].type = "Water-Edge";
            tile[12].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-OTR-Border.png"));
            tile[13] = new Tile();
            tile[13].collision = true;
            tile[13].type = "Water-Edge";
            tile[13].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-OBL-Border.png"));
            tile[14] = new Tile();
            tile[14].collision = true;
            tile[14].type = "Water-Edge";
            tile[14].image[0] = ImageIO.read(getClass().getResourceAsStream("/tiles/Water-OBR-Border.png"));



        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
