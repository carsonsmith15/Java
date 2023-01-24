package object;

import Entity.Projectile;
import Main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Fireball extends Projectile 
{
    GamePanel gamePanel; 

    public OBJ_Fireball(GamePanel gp) 
    {
        super(gp);

        this.gamePanel = gp; 

        this.name = "Fireball"; 
        this.speed = 5; 
        this.maxLife = 80; 
        this.life = maxLife; 
        this.attack = 2; 
        this.useCost = 1; 
        this.alive = false; 

        getImage(); 
    }

    public void getImage()
    {
        try 
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_up_1.png")); 
            up2 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_up_2.png")); 
            down1 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_down_1.png")); 
            down2 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_down_2.png")); 
            right1 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_right_1.png")); 
            right2 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_right_2.png")); 
            left1 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_left_1.png")); 
            left2 = ImageIO.read(getClass().getResourceAsStream("/Res/projectiles/fireball_left_2.png")); 
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
}
