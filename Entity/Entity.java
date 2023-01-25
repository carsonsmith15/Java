package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

public class Entity 
{
    GamePanel gp; 
    KeyHandler kh; 
    public int worldX, worldY; 
    public int speed; 
    public int powerUpSpeed; 

    public String name; 

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; 
    public String direction; 
    public String powerUpSeedDirection; 

    public int spriteCounter = 0; 
    public int spriteNumber = 1; 

    public int actionLockCounter = 0; 
    
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); 
    public boolean collisionOn = false; 

    public int solidAreaDefaultX; 
    public int solidAreaDefaultY; 
    
    // CHARACTER ATTRIBUTES 
 
    public int maxLife; 
    public int life; 
    public int maxMana; 
    public int mana; 

    public boolean alive = true; 

    public int level; 
    public int strength; 
    public int dexterity; 
    public int attack; 
    public int defense;
    public int exp;

    public Projectile projectile;
    public int useCost; 
    
    public Entity (GamePanel gp, KeyHandler kh)
    {
        this.gp = gp; 
        this.kh = kh; 
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null; 

        int screenX = worldX - gp.player.worldX + gp.player.screenX; 
        int screenY = worldY - gp.player.worldY + gp.player.screenY; 

        if( worldX + gp.tileSize  > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize  < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY
        )
        {

            switch (direction) 
            {
                case "up": 
                    if (spriteNumber == 1) 
                    {
                        image = up1; 
                    }
                    if (spriteNumber == 2) 
                    {
                        image = up2; 
                    }                
                    break; 
                case "down": 
                    if (spriteNumber == 1)
                    {
                        image = down1; 
                    }
                    if (spriteNumber == 2) 
                    {
                        image = down2; 
                    }  
                    break; 
                case "left": 
                    if (spriteNumber == 1) 
                    {
                        image = left1; 
                    }
                    if (spriteNumber == 2) 
                    {
                        image = left2; 
                    } 
                    break; 
                case "right": 
                    if (spriteNumber == 1) 
                    {
                        image = right1; 
                    }
                    if (spriteNumber == 2) 
                    {
                        image = right2; 
                    }  
                    break; 
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);                
        }
    }

    public void setAction()
    {

    }

    public void update()
    {
        setAction();
        collisionOn = false; 
        gp.collisionChecker.checkTile(this, kh);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);

         // IF COLLISION IS FALSE NPC CAN MOVE 
         if (!collisionOn)
         {
             switch (direction) 
             {
                 case "up": 
                     worldY -= speed; 
                     break; 
                 case "down":               
                     worldY += speed; 
                     break;
                 case "left": 
                     worldX -= speed;  
                     break; 
                 case "right":  
                     worldX += speed; 
                     break;
             }
         }
 
         spriteCounter++; 
         if (spriteCounter > 10) 
         {
             if (spriteNumber == 1) 
             {
                 spriteNumber = 2; 
             }
             else if (spriteNumber == 2) 
             {
                 spriteNumber = 1; 
             }    
             spriteCounter = 0; 
         }
    }

    public BufferedImage setup(String imagePath)
    {
        UtilityTool utilityTool = new UtilityTool(); 
        BufferedImage image = null; 

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png")); 
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image; 

    }
}
