package main;

import entity.*;
import main.Assets.StatBar;
import main.Assets.ObjectSetter;
import main.Assets.KeyHandler;
import object.OBJ_Soul;
import object.OBJ_SoulParticle;
import object.SuperObject;
import tile.TileManager;
import environment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
    public final int originalTileSize = 64;
    public double scale = 2;
    public double minScale = 0.5;
    public double maxScale = 4;

    public int tileSize = (int)(originalTileSize * scale);
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 40;
    public final int screenWidth = 1920; //= 2560;
    public final int screenHeight = 1080; //= 1440;

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    MouseWheelListener mouseWheelH = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            tileSize = (int)(originalTileSize * scale);
            //if (e.getWheelRotation() < 0) {}
            //else if(e.getWheelRotation() > 0) {}
            scale += 0.05 * e.getWheelRotation();
            if(scale > maxScale) {
                scale = maxScale;
            } else if(scale < minScale) {
                scale = minScale;
            }
            tileSize = (int)(originalTileSize * scale) + 1;
            eManager.update(); //here
            //next time add tile based lighting too
            //make lighting rect bigger if circle is larger than it
            System.out.println(scale);
        }
    };

    EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;
    //public CollisionChecker cMoveChecker = new CollisionChecker(this);
    public StatBar statBar = new StatBar(new ParasiteBrainWorm(this));
    public Player player = new Player(this, keyH);


    public ObjectSetter aSetter = new ObjectSetter(this);
    int maxObj = 10;
    public ArrayList<SuperObject> obj = new ArrayList<>();
    int maxEnt = 15;
    public ArrayList<Entity> entities = new ArrayList<>();

    String[] orderedType = new String[player.ent.size() + entities.size() + obj.size()];
    double[] orderedPositions = new double[player.ent.size() + entities.size() + obj.size()]; //player, ents, objs
    int[] orderedIndex = new int[player.ent.size() + entities.size() + obj.size()];


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseWheelListener(mouseWheelH);
        this.setFocusable(true);
    }

    public void setUp() {
        //player.addEntity("Fly Guy"); //Brain Worm
        player.addEntity("Fly Guy"); //Brain Worm
        player.addEntity("Brain Worm");
        System.out.println(player.ent.get(0).worldX);
        statBar = new StatBar(player.ent.get(0));
        //player.addEntity("FlyGuy");
        aSetter.setObject();
        player.setSoul((OBJ_Soul) obj.get(0));
        player.soul.particles.add((OBJ_SoulParticle) obj.get(1));

        eManager.setUp();
        //entities.add(new ParasiteBrainWorm(this));
        entities.add(new ParasiteBrainWorm(this));
        //entities.add(new Olune(this));
        //entities.add(new FlyGuy(this));
        //entities.add(new FlyGuy(this));
        //entities.add(new FlyGuy(this));
        for(int i = 0; i < 10; i++) {
            entities.add(new Fish(this));
        }
        entities.add(new FlyGuy(this));

        orderedType = new String[]{""};
        orderedPositions = new double[player.ent.size() + entities.size() + obj.size()]; //player, ents, objs
        orderedIndex = new int[player.ent.size() + entities.size() + obj.size()];
    }


    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextInterval = System.nanoTime() + drawInterval;

        //game loop
        while(gameThread != null) {

            // 1 Updates
            update();

            // 2 Draw
            repaint();

            try {
                double remainingTime = nextInterval - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);
                nextInterval += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void update() {
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        player.update();
        tileM.update();
        for(int i = 0; i < obj.size(); i++) {
            obj.get(i).update();
        }
        setOrderToPrint();
    }




    public void setOrderToPrint() {
        //find y positions
        orderedType = new String[player.ent.size() + entities.size() + obj.size()];
        orderedPositions = new double[player.ent.size() + entities.size() + obj.size()]; //player, ents, objs
        orderedIndex = new int[player.ent.size() + entities.size() + obj.size()];
        int counter = 0;
        for(int i = 0; i < player.ent.size(); i++) {
            orderedType[counter] = "main.Player";
            orderedPositions[counter] = player.ent.get(i).worldY;
            orderedIndex[counter] = i;
            counter++;
        }
        for(int i = 0; i < entities.size(); i++) {
            orderedType[counter] = "Entity";
            orderedPositions[counter] = entities.get(i).worldY;
            orderedIndex[counter] = i;
            counter++;
        }
        for(int i = 0; i < obj.size(); i++) {
            orderedType[counter] = "Object";
            orderedPositions[counter] = obj.get(i).worldY;
            orderedIndex[counter] = i;
            counter++;
        }

        int tempI;
        double tempP;
        String tempT;
        int smallest = 0;
        for (int i = 0; i < orderedPositions.length; i++)
        {
            smallest = i;
            for(int j = orderedPositions.length - 1; j > i; j--) {
                if(orderedPositions[j] < orderedPositions[smallest]) {
                    smallest = j;
                }
            }

            tempP = orderedPositions[i];
            tempI = orderedIndex[i];
            tempT = orderedType[i];
            orderedPositions[i] = orderedPositions[smallest];
            orderedIndex[i] = orderedIndex[smallest];
            orderedType[i] = orderedType[smallest];
            orderedPositions[smallest] = tempP;
            orderedIndex[smallest] = tempI;
            orderedType[smallest] = tempT;

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //background

        //tiles
        tileM.draw(g2);

        for(int i = 0; i < player.ent.size() + entities.size() + obj.size(); i++) {
            if(orderedType[i] == "main.Player") {
                player.ent.get(orderedIndex[i]).draw(g2);
            } else if(orderedType[i] == "Entity") {
                entities.get(orderedIndex[i]).draw(g2);
            } else if(orderedType[i] == "Object") {
                obj.get(orderedIndex[i]).draw(g2, this);
            }
        }

        eManager.draw(g2);
        //entities
        //player.draw(g2);

        statBar.draw(g2);

        g2.dispose();
    }
}
