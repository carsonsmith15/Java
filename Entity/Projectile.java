package Entity;

import Main.GamePanel;
import Main.KeyHandler;

public class Projectile extends Entity 
{
    Entity user; 

    public Projectile(GamePanel gp, KeyHandler kh) 
    {
        super(gp, kh); 
    }

    public void set(int x, int y, String direction, boolean alive, Entity user) 
    {
        this.worldX = x; 
        this.worldY = y; 
        this.direction = direction; 
        this.alive = alive; 
        this.user = user; 
        this.life = this.maxLife; 
    }

    public void update ()
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

        life--; 
        if (life <= 0)
        {
            alive = false; 
        }

        spriteCounter++; 
        if (spriteCounter > 12) 
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
}
