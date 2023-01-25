package Main;

import javax.lang.model.util.ElementScanner6;

import Entity.Entity;

public class CollisionChecker {
    
    GamePanel gp; 

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp; 
    }

    public void checkTile(Entity entity, KeyHandler kh)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; 
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; 
        int entityTopWorldY = entity.worldY + entity.solidArea.y; 
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; 

        int entityLeftCol = entityLeftWorldX/gp.tileSize; 
        int entityRightCol = entityRightWorldX/gp.tileSize; 
        int entityTopRow = entityTopWorldY/gp.tileSize; 
        int entityBottomRow = entityBottomWorldY/gp.tileSize; 

        int tileNum1, tileNum2; 

        switch (entity.direction)
        {
            case "up": 
                if (kh.getSpacePressed())
                {
                    entityTopRow = (entityTopWorldY - entity.powerUpSpeed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow]; 
                    if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    }
                }
                else 
                {
                    entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow]; 
                    if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    }
                }                
                break; 
            case "down": 
                if (kh.getSpacePressed())
                {
                    entityBottomRow = (entityBottomWorldY + entity.powerUpSpeed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow]; 
                    if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    }
                }
                else 
                {
                    entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow]; 
                    if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    }
                }               
                break;
            case "left":
                if(kh.getSpacePressed())
                {
                    entityLeftCol = (entityLeftWorldX - entity.powerUpSpeed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; 
                    if(gp.tileManager.tile[tileNum1].collision|| gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    } 
                }
                else 
                {
                    entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow]; 
                    if(gp.tileManager.tile[tileNum1].collision|| gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    } 
                }
                
                break; 
            case "right": 
                if(kh.getSpacePressed())
                {
                    entityRightCol = (entityRightWorldX + entity.powerUpSpeed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow]; 
                    if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    }
                    break; 
                }
                else 
                {
                    entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize; 
                    tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow]; 
                    tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow]; 
                    if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    {
                        entity.collisionOn = true; 
                    }
                    break; 
                }
              
            

        }
    }

    public int checkObject(Entity entity, boolean player)
    {
        int index = 999; 

        for(int i = 0; i < gp.obj.length; i++)
        {
            if(gp.obj[i] != null)
            {
                // get entitys solid area position 
                entity.solidArea.x = entity.worldX + entity.solidArea.x; 
                entity.solidArea.y = entity.worldY + entity.solidArea.y; 

                // get the objects solid area position 
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;           
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;          
                
                switch(entity.direction)
                {
                    case "up": 
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            System.out.println("up collision");

                            if (gp.obj[i].collision)
                            {
                                entity.collisionOn = true; 
                            }
                            if (player == true)
                            {
                                index = i; 
                            }
                        }
                        break;
                    case "down": 
                        entity.solidArea.y += entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            System.out.println("down collision");
                            if (gp.obj[i].collision)
                            {
                                entity.collisionOn = true; 
                            }
                            if (player == true)
                            {
                                index = i; 
                            }
                        }
                        break;
                    case "left": 
                        entity.solidArea.x -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            System.out.println("left collision");
                            if (gp.obj[i].collision)
                            {
                                entity.collisionOn = true; 
                            }
                            if (player == true)
                            {
                                index = i; 
                            }
                        }
                        break;
                    case "right": 
                        entity.solidArea.x += entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea))
                        {
                            System.out.println("right collision");
                            if (gp.obj[i].collision)
                            {
                                entity.collisionOn = true; 
                            }
                            if (player == true)
                            {
                                index = i; 
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX; 
                entity.solidArea.y = entity.solidAreaDefaultY; 
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            
            }
        }

        return index; 
    }

    public int checkEntity(Entity entity, Entity[] target)     
    {
        int index = 999; 

        for(int i = 0; i < target.length; i++)
        {
            if(target[i] != null)
            {
                // get entitys solid area position 
                entity.solidArea.x = entity.worldX + entity.solidArea.x; 
                entity.solidArea.y = entity.worldY + entity.solidArea.y; 

                // get the objects solid area position 
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;           
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;          
                
                switch(entity.direction)
                {
                    case "up": 
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            System.out.println("up collision");
                            entity.collisionOn = true;                            
                            index = i;                             
                        }
                        break;
                    case "down": 
                        entity.solidArea.y += entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            System.out.println("down collision");                            
                            entity.collisionOn = true; 
                            index = i;                             
                        }
                        break;
                    case "left": 
                        entity.solidArea.x -= entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            System.out.println("left collision");                            
                            entity.collisionOn = true;                             
                            index = i;
                        }
                        break;
                    case "right": 
                        entity.solidArea.x += entity.speed; 
                        if(entity.solidArea.intersects(target[i].solidArea))
                        {
                            System.out.println("right collision");
                            entity.collisionOn = true;
                            index = i;                             
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX; 
                entity.solidArea.y = entity.solidAreaDefaultY; 
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            
            }
        }

        return index; 
    }

    public void checkPlayer(Entity entity)
    {
        // get entitys solid area position 
        entity.solidArea.x = entity.worldX + entity.solidArea.x; 
        entity.solidArea.y = entity.worldY + entity.solidArea.y; 

        // get the objects solid area position 
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;           
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;          
        
        switch(entity.direction)
        {
            case "up": 
                entity.solidArea.y -= entity.speed; 
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    System.out.println("up collision");
                    entity.collisionOn = true;                           
                                                
                }
                break;
            case "down": 
                entity.solidArea.y += entity.speed; 
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    System.out.println("down collision");                            
                    entity.collisionOn = true;                                                 
                }
                break;
            case "left": 
                entity.solidArea.x -= entity.speed; 
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    System.out.println("left collision");                            
                    entity.collisionOn = true;
                }
                break;
            case "right": 
                entity.solidArea.x += entity.speed; 
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    System.out.println("right collision");
                    entity.collisionOn = true;                                             
                }
                break;
        }

        entity.solidArea.x = entity.solidAreaDefaultX; 
        entity.solidArea.y = entity.solidAreaDefaultY; 
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}
