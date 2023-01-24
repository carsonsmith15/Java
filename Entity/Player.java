package Entity; 

import Main.KeyHandler;
import object.OBJ_Fireball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Player extends Entity 
{
    GamePanel gamePanel;
    KeyHandler keyHandler;  
    Fireball fireball; 

    public final int screenX; 
    public final int screenY; 

    public Player(GamePanel gp, KeyHandler kh) 
    {       
        super(gp); 
        this.gamePanel = gp; 
        this.keyHandler = kh;

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2); 
        this.screenY = gp.screenHeight/2- (gp.tileSize/2); 

        setDefaultValues();
        getPlayerImage();

        fireball = new Fireball(gp); 
    }

    public void setDefaultValues() 
    {        
        worldX = gp.tileSize * 23; 
        worldY = gp.tileSize * 21; 
        speed = 4; 
        verticalJump = 10; 
        direction = "down"; 

        projectile = new OBJ_Fireball(gamePanel); 
    }

    public void getPlayerImage() 
    {
        try 
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_up_1.png")); 
            up2 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_up_2.png")); 
            down1 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_down_1.png")); 
            down2 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_down_2.png")); 
            right1 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_right_1.png")); 
            right2 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_right_2.png")); 
            left1 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_left_1.png")); 
            left2 = ImageIO.read(getClass().getResourceAsStream("/Res/player/boy_left_2.png")); 
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void update() 
    {
        if (keyHandler.upPressed || keyHandler.downPressed || 
        keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.spacePressed) 
        {                      
            if (keyHandler.spacePressed)
            {
                worldY -= verticalJump; 
            }
            if (keyHandler.upPressed) 
            {
                direction = "up";                
                worldY -= speed;         
            }
            else if (keyHandler.downPressed) 
            {
                direction = "down";
                worldY += speed; 
            }
            else if (keyHandler.leftPressed) 
            {
                direction = "left";              
                worldX -= speed; 
            }
            else if (keyHandler.rightPressed) 
            {
                direction = "right";               
                worldX += speed; 
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
        

        if (keyHandler.shootKeyPressed && !projectile.alive)
        {
            // SET DEFAULT COORDINATES, DIRECTION, AND USER 
            projectile.set(worldX, worldY, direction, true, this);

            gamePanel.projectileList.add(projectile);
        }
    }

    public void draw(Graphics2D g2) 
    {   
        BufferedImage image = null; 

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
        
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        fireball.draw(g2);
    }    
}
