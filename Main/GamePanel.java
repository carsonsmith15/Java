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

    // FPS 
    int FPS = 60; 

    KeyHandler keyH = new KeyHandler(); 
    Thread gameThread; 

    TileManager tileManager = new TileManager(this); 
    public Player player = new Player(this, keyH);  
    
    public ArrayList<Entity> projectileList = new ArrayList<>(); 
    public ArrayList<Entity> entityList = new ArrayList<>(); 

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

        tileManager.draw(g2);
        player.draw(g2); 

        g2.dispose(); 
    }    
}
