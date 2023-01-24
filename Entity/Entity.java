package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class Entity 
{
    GamePanel gp; 
    public int worldX, worldY; 
    public int speed; 
    public int verticalJump; 

    public String name; 

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; 
    public String direction; 

    public int spriteCounter = 0; 
    public int spriteNumber = 1;  
    
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
    
    public Entity (GamePanel gp)
    {
        this.gp = gp; 
    }

    public void update()
    {

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
