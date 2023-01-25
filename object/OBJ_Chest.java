package object;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
    
    public OBJ_Chest()
    {
        name = "Treasure Chest"; 

        try 
        {
            image = ImageIO.read(getClass().getResourceAsStream("/Res/objects/chest (OLD).png")); 
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
