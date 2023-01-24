package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager 
{
    GamePanel gamePanel;
    Tile[] tile; 
    int mapTileNum[][]; 
    
    public TileManager(GamePanel gamePanel) 
    {
        this.gamePanel = gamePanel;
        tile = new Tile[10]; 

        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow]; 
        getTileImage();
        loadMap();
    }

    public void loadMap() 
    {
        try 
        {
            InputStream is = getClass().getResourceAsStream("/Res/maps/world02.txt"); 
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); 

            int col = 0; 
            int row = 0; 

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow)
            {
                String line = br.readLine(); 

                while(col < gamePanel.maxWorldCol)
                {
                    String numbers[] = line.split(" "); 

                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num; 
                    col++; 
                }

                if(col == gamePanel.maxWorldCol)
                {
                    col = 0; 
                    row++; 
                }
            }
            br.close();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
    }

    public void getTileImage() 
    {
        try 
        {
            tile[0] = new Tile(); 
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Res/tiles/grass.png")); 

            tile[1] = new Tile(); 
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Res/tiles/wall.png")); 

            tile[2] = new Tile(); 
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Res/tiles/water.png")); 

            tile[3] = new Tile(); 
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Res/tiles/sand.png")); 

            tile[4] = new Tile(); 
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Res/tiles/tree.png"));
            
            tile[5] = new Tile(); 
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Res/tiles/earth.png")); 
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) 
    {
        int worldCol = 0; 
        int worldRow = 0; 
      

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow]; 

            int worldX = worldCol * gamePanel.tileSize; 
            int worldY = worldRow * gamePanel.tileSize; 
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX; 
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY; 

            if( worldX + gamePanel.tileSize  > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize  < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize  > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize  < gamePanel.player.worldY + gamePanel.player.screenY
            )
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);                
            }

            worldCol++;             

            if(worldCol == gamePanel.maxWorldCol)
            {
                worldCol = 0;                
                worldRow++;               
            }
        }   
    }
}
