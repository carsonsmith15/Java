package Entity; 

import Main.KeyHandler;
import object.OBJ_Fireball;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class Player extends Entity 
{
    GamePanel gamePanel;
    KeyHandler keyHandler;  
    Fireball fireball; 

    public int keys = 0; 

    BufferedImage playersImage; 

    public final int screenX; 
    public final int screenY; 

    public Player(GamePanel gp, KeyHandler kh) 
    {       
        super(gp, kh); 
        this.gamePanel = gp; 
        this.keyHandler = kh;

        this.screenX = gp.screenWidth/2 - (gp.tileSize/2); 
        this.screenY = gp.screenHeight/2- (gp.tileSize/2); 

        solidArea = new Rectangle(8, 16, 30, 30); 

        solidAreaDefaultX = solidArea.x; 
        solidAreaDefaultY = solidArea.y; 

        setDefaultValues();
        getPlayerImage();

        fireball = new Fireball(gp, kh); 
    }

    public void setDefaultValues() 
    {        
        worldX = gp.tileSize * 23; 
        worldY = gp.tileSize * 21; 
        speed = 6; 
        powerUpSpeed = 20; 
        direction = "down"; 
        powerUpSeedDirection = ""; 

        projectile = new OBJ_Fireball(gamePanel, keyHandler); 
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
        if (keyHandler.getUpPressed() || keyHandler.getDownPressed() || 
        keyHandler.getLeftPressed() || keyHandler.getRightPressed() || keyHandler.getSpacePressed()) 
        {                      
            if (keyHandler.getSpacePressed())
            {
                powerUpSeedDirection = direction; 
                direction = direction; 
            }
            if (keyHandler.getUpPressed()) 
            {
                direction = "up";  
            }
            else if (keyHandler.getDownPressed()) 
            {
                direction = "down";                 
            }
            else if (keyHandler.getLeftPressed()) 
            {
                direction = "left"; 
            }
            else if (keyHandler.getRightPressed()) 
            {
                direction = "right"; 
            }

            // CHECK TILE COLLISION 
            collisionOn = false; 
            gp.collisionChecker.checkTile(this, keyHandler); 

            // CHECK OBJECT COLLISION 
            int objIndex = gp.collisionChecker.checkObject(this, true); 
            pickUpObject(objIndex);

            // CHECK NPC COLLISION 
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // IF COLLISION IS FALSE PLAYER CAN MOVE 
            if (collisionOn == false)
            {
                switch (direction) 
                {
                    case "up":      
                        if (powerUpSeedDirection.equalsIgnoreCase(direction) && keyHandler.getSpacePressed())
                        {
                            worldY -= powerUpSpeed; 
                        }
                        worldY -= speed; 
                        break; 
                    case "down":    
                        if (powerUpSeedDirection.equalsIgnoreCase(direction) && keyHandler.getSpacePressed())
                        {
                            worldY += powerUpSpeed; 
                        }                        
                        worldY += speed; 
                        break;
                    case "left":   
                        if (powerUpSeedDirection.equalsIgnoreCase(direction) && keyHandler.getSpacePressed())
                        {
                            worldX -= powerUpSpeed; 
                        }   
                        worldX -= speed;  
                        break; 
                    case "right":   
                        if (powerUpSeedDirection.equalsIgnoreCase(direction) && keyHandler.getSpacePressed())
                        {
                            worldX += powerUpSpeed; 
                        }   
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

        if (keyHandler.getSpacePressed() && !projectile.alive)
        {
            // SET DEFAULT COORDINATES, DIRECTION, AND USER 
            projectile.set(worldX, worldY, direction, true, this);

            gamePanel.projectileList.add(projectile);
        }
    }

    public void interactNPC(int index)
    {
        if(index != 999)
        {
            System.out.println("You are hitting the npc."); 
        }
    }

    public void pickUpObject(int index)
    {
        if(index != 999)
        {
            String objectName = gp.obj[index].name; 

            switch(objectName)
            {
                case "Key": 
                    gp.ui.showMessage("You got the key!");
                    gp.playSE(1);
                    keys++; 
                    gp.obj[index] = null;                     
                    break; 
                case "Door": 
                    if(keys > 0)
                    {
                        gp.ui.showMessage("You opened the door with your key!");
                        gp.playSE(3);
                        gp.obj[index] = null; 
                        keys--; 
                    }                    
                    break; 
                case "Boots": 
                    gp.ui.showMessage("Speed up! +1");
                    gp.playSE(2);
                    gp.obj[index] = null; 
                    speed += 1;                                        
                    break; 
                case "Treasure Chest": 
                    gp.ui.gameFinished = true; 
                    gp.stopMusic();
                    gp.playSE(4);     
                    gp.obj[index] = null;               
                                       
                    break; 
            }            
        }
    }

    public void draw(Graphics2D g2) 
    {   
        switch (direction) 
        {
            case "up": 
                if (spriteNumber == 1) 
                {
                    playersImage = up1; 
                }
                if (spriteNumber == 2) 
                {
                    playersImage = up2; 
                }                
                break; 
            case "down": 
                if (spriteNumber == 1)
                {
                    playersImage = down1; 
                }
                if (spriteNumber == 2) 
                {
                    playersImage = down2; 
                }  
                break; 
            case "left": 
                if (spriteNumber == 1) 
                {
                    playersImage = left1; 
                }
                if (spriteNumber == 2) 
                {
                    playersImage = left2; 
                } 
                break; 
            case "right": 
                if (spriteNumber == 1) 
                {
                    playersImage = right1; 
                }
                if (spriteNumber == 2) 
                {
                    playersImage = right2; 
                }  
                break; 
            case "verticalJump":
                if (playersImage == null)
                {
                    playersImage = up1; 
                }
                playersImage = playersImage; 
        }
        
        g2.drawImage(playersImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        fireball.draw(g2);
    }    
}
