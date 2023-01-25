package Main;

import Entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter 
{
    GamePanel gp; 
    KeyHandler kh; 

    public AssetSetter(GamePanel gamePanel, KeyHandler kh)
    {
        this.gp = gamePanel; 
        this.kh = kh; 
    }

    public void setObject()
    {
        gp.obj[0] = new OBJ_Key(); 
        gp.obj[0].worldX = 23 * gp.tileSize; 
        gp.obj[0].worldY = 7 * gp.tileSize; 

        gp.obj[1] = new OBJ_Key(); 
        gp.obj[1].worldX = 23 * gp.tileSize; 
        gp.obj[1].worldY = 40 * gp.tileSize; 

        gp.obj[2] = new OBJ_Key(); 
        gp.obj[2].worldX = 40 * gp.tileSize; 
        gp.obj[2].worldY = 7 * gp.tileSize; 
        
        gp.obj[3] = new OBJ_Door(); 
        gp.obj[3].worldX = 10 * gp.tileSize; 
        gp.obj[3].worldY = 11 * gp.tileSize;  

        gp.obj[4] = new OBJ_Door(); 
        gp.obj[4].worldX = 12 * gp.tileSize; 
        gp.obj[4].worldY = 21 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(); 
        gp.obj[5].worldX = 23 * gp.tileSize; 
        gp.obj[5].worldY = 35 * gp.tileSize;

        gp.obj[6] = new OBJ_Boots(); 
        gp.obj[6].worldX = 36 * gp.tileSize; 
        gp.obj[6].worldY = 43 * gp.tileSize;

        gp.obj[7] = new OBJ_Chest(); 
        gp.obj[7].worldX = 10 * gp.tileSize; 
        gp.obj[7].worldY = 7 * gp.tileSize;        
    }
    
    public void setNPC()
    {
        gp.npc[0] = new NPC_OldMan(gp, kh); 
        gp.npc[0].worldX = gp.tileSize*21; 
        gp.npc[0].worldY = gp.tileSize*21; 
    }
}
