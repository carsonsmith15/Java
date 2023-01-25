package Entity;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Fireball extends Entity
{
    GamePanel gamePanel; 
    BufferedImage fireballImage; 
    boolean isVisible; 

    public Fireball(GamePanel gp, KeyHandler kh) 
    {
        super(gp, kh); 

        this.gamePanel = gp; 
        this.isVisible = false; 

        try 
        {
            fireballImage = ImageIO.read(getClass().getResourceAsStream("/Res/player/fireball.png"));
        } catch (IOException e) 
        {            
            e.printStackTrace();
        } 
    }

    public void update(int x, int y, boolean isVisible, String direction)
    {
        this.worldX = x + gamePanel.tileSize + 10;   
        this.worldY = y;     
        this.isVisible = isVisible; 
        this.direction = direction; 
    }

    public void updateDirection(String direction)
    {
        this.direction = direction; 
    }

    public void draw(Graphics2D g2) 
    {   
        if (isVisible)
        {
            if (this.direction.equalsIgnoreCase("up")) 
            {
                worldY -= 5; 
                g2.drawImage(fireballImage, worldX, worldY, 16, 16, null);
            }
            else if (this.direction.equalsIgnoreCase("down")) 
            {
                worldY += 5; 
                g2.drawImage(fireballImage, worldX, worldY, 16, 16, null);
            }
            else if (direction.equalsIgnoreCase("right"))
            {
                worldX += 5; 
                g2.drawImage(fireballImage, worldX, worldY, 16, 16, null);
            }
            else if (direction.equalsIgnoreCase("left"))
            {
                worldX -= 5; 
                g2.drawImage(fireballImage, worldX, worldY, 16, 16, null);
            }          
        }
        else 
        {
            g2.drawImage(fireballImage, worldX, worldY, 0, 0, null);            
        }
    }
}
