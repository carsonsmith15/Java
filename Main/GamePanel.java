package Main; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import Tile.TileManager;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable 
{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; 

    public final int tileSize =  originalTileSize * scale; // 48X48 tile
    public final int maxScreenCol = 16; 
    public final int maxScreenRow = 12; 
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 

    // WORLD SETTINGS
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50; 
    public final int worldWidth = tileSize * maxWorldCol; 
    public final int worldHeight = tileSize * maxWorldRow; 

    // GAME STATE
    public int gameState;
    public final int playState = 1; 
    public final int pauseState = 2; 

    // FPS 
    int FPS = 60; 

    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; 

    TileManager tileManager = new TileManager(this); 
    public Player player = new Player(this, keyH); 
    
    public CollisionChecker collisionChecker = new CollisionChecker(this); 

    AssetSetter assetSetter = new AssetSetter(this, keyH); 

    public SuperObject obj[] = new SuperObject[10]; 
    public Entity npc[] = new Entity[10];
    
    public ArrayList<Entity> projectileList = new ArrayList<>(); 
    public ArrayList<Entity> entityList = new ArrayList<>(); 

    // SOUND 
    Sound music = new Sound(); 
    Sound soundEffects = new Sound(); 

    // UI
    public UI ui = new UI(this); 

    public void setUpGame()
    {
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
    }

    public GamePanel() 
    { 
        // Sets the size of the JPanel 
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        // Sets the background color to black 
        this.setBackground(Color.black);

        // improves rendering performance 
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() 
    {
        gameThread = new Thread(this); 
        gameThread.start();
    }

    @Override
    public void run() 
    {   
        // Sleep method        
        double drawInterval = 1000000000/FPS; // 0.0166 seconds 
        double nextDrawTime = System.nanoTime() + drawInterval; 

        while (gameThread != null) 
        {
            update(); 
            repaint(); 

            try 
            {
                double remainingTime = nextDrawTime - System.nanoTime(); 
                remainingTime = remainingTime/1000000; 

                if (remainingTime < 0)
                {
                    remainingTime = 0; 
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval; 
            } 
            catch (InterruptedException e) 
            {               
                e.printStackTrace();
            } 
        }        
    }

    public void update() 
    {
        player.update();   

         // NPC
         for(int i = 0; i < npc.length; i++)
         {
             if(npc[i] != null)
             {
                 npc[i].update();
             }
         }
        
        for (int i = 0; i < projectileList.size(); i++ ) 
        {
            if (projectileList.get(i) != null)
            {
                if (projectileList.get(i).alive)
                {
                    projectileList.get(i).update();
                }
                if (!projectileList.get(i).alive)
                {
                    projectileList.remove(i); 
                }
            }
        }
    }
   
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; 

        // Background TILES
        tileManager.draw(g2);

        // OBJECT
        for(int i = 0; i < obj.length; i++)
        {
            if(obj[i] != null)
            {
                obj[i].draw(g2, this);
            }
        }

        // NPC
        for(int i = 0; i < npc.length; i++)
        {
            if(npc[i] != null)
            {
                npc[i].draw(g2);
            }
        }

        // PLAYER 
        player.draw(g2); 

        ui.draw(g2);

        g2.dispose(); 
    }   
    
    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {        
        music.stop();
    }

    public void playSE(int i)
    {
        soundEffects.setFile(i);
        soundEffects.play();
    }

    public void stopSE(int i)
    {
        soundEffects.setFile(i);
        soundEffects.stop();
    }
}
