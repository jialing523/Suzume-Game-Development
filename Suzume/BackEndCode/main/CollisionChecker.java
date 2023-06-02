package main;

import Entity.Entity;
import static java.awt.image.ImageObserver.ALLBITS;

public class CollisionChecker
{
    GamePanel gp;
    public TicTacToe3 ttt3;
    int previousStationCol,previousStationRow,previousPlayerX,previousPlayerY,winStation;

    public CollisionChecker(GamePanel gp)
    {
        this.gp=gp;
        previousStationCol=previousPlayerX=0;
        previousStationRow=previousPlayerY=19*gp.tileSize;
        winStation=0;
        
    }
    
    public void checkTile(Entity entity)
    {
        int entityCol = entity.x/gp.tileSize;
        int entityRow = entity.y/gp.tileSize;
        
                    
        int tileNum1;
        switch (entity.direction)
        {
            case "up":
            {
                entityRow = (entity.y-entity.speed)/gp.tileSize;
                if(entityRow>=0)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];
                    if(gp.tileM.tile[tileNum1].collision )
                        entity.collisionOn=true;
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
            case "down":
            {
                entityRow = (entity.y+entity.speed)/gp.tileSize;
                if(entityRow<20)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];
                    if(gp.tileM.tile[tileNum1].collision )
                        entity.collisionOn=true;
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
            case "left":
            {
                entityCol = (entity.x-entity.speed)/gp.tileSize;
                if(entityCol>=0)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];
                    if(gp.tileM.tile[tileNum1].collision)
                        entity.collisionOn=true;
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
            case "right":
            {
                entityCol = (entity.x+entity.speed)/gp.tileSize;
                if(entityCol<40)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];                 
                    if(gp.tileM.tile[tileNum1].collision )
                        entity.collisionOn=true;
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
        }
        
    }
    
    
    public void checkStation(Entity entity) throws InterruptedException
    {
        int entityCol = entity.x/gp.tileSize;
        int entityRow = entity.y/gp.tileSize;
       
        int tileNum1;
        switch (entity.direction)
        {
            case "up":
            {
                entityRow = (entity.y-entity.speed)/gp.tileSize;
                if(entityRow>=0)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];
                    if(gp.tileM.tile[tileNum1].station)
                    {
                        entity.reachStation=true;
   
                        ttt3 = new TicTacToe3 ();
                        while(!ttt3.finish)
                        {
                            Thread.sleep(ALLBITS);
                        }
                        entity.reachStation=false;
                        
                        if(ttt3.gameStatus==1)
                        {
                            gp.tileM.mapTileNum[entityRow][entityCol]=0;
                            
                            previousStationCol=entityCol;
                            previousStationRow=entityRow;
                            
                            previousPlayerX=entity.x;
                            previousPlayerY=entity.y;
                        }
                        else if(winStation!=0)
                        {
                            winStation--;
                            gp.tileM.mapTileNum[previousStationRow][previousStationCol]=2;
                            
                            entity.x=previousPlayerX;
                            entity.y=previousPlayerY;
                        }
                        else
                        {
                            System.out.println("Game Over!!!!!");
                        }
                    }
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
            case "down":
            {
                entityRow = (entity.y+entity.speed)/gp.tileSize;
                if(entityRow<20)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];
                    if(gp.tileM.tile[tileNum1].station)
                    {
                        entity.reachStation=true;
               
                        ttt3 = new TicTacToe3 ();
                        while(!ttt3.finish)
                        {
                            Thread.sleep(ALLBITS);
                        }
                        entity.reachStation=false;
                        
                        if(ttt3.gameStatus==1)
                        {
                            gp.tileM.mapTileNum[entityRow][entityCol]=0;
                            previousStationCol=entityCol;
                            previousStationRow=entityRow;
                            
                            previousPlayerX=entity.x;
                            previousPlayerY=entity.y;
                        }
                        else if(winStation!=0)
                        {
                            winStation--;
                            gp.tileM.mapTileNum[previousStationRow][previousStationCol]=2;
                            
                            entity.x=previousPlayerX;
                            entity.y=previousPlayerY;
                        }
                        else
                        {
                            System.out.println("Game Over!!!!!");
                        }
                    }
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
            case "left":
            {
                entityCol = (entity.x-entity.speed)/gp.tileSize;
                if(entityCol>=0)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];
                    if(gp.tileM.tile[tileNum1].station)
                    {
                        entity.reachStation=true;
     
                        
                        ttt3 = new TicTacToe3 ();
                        while(!ttt3.finish)
                        {
                            Thread.sleep(ALLBITS);
                        }
                        entity.reachStation=false;
                        if(ttt3.gameStatus==1)
                        {
                            gp.tileM.mapTileNum[entityRow][entityCol]=0;
                            previousStationCol=entityCol;
                            previousStationRow=entityRow;
                            
                            previousPlayerX=entity.x;
                            previousPlayerY=entity.y;
                        }
                        else if(winStation!=0)
                        {
                            winStation--;
                            gp.tileM.mapTileNum[previousStationRow][previousStationCol]=2;
                            
                            entity.x=previousPlayerX;
                            entity.y=previousPlayerY;
                        }
                        else
                        {
                            System.out.println("Game Over!!!!!");
                        }
                    }
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
            case "right":
            {
                entityCol = (entity.x+entity.speed)/gp.tileSize;
                if(entityCol<40)
                {
                    tileNum1=gp.tileM.mapTileNum[entityRow][entityCol];                 
                    if(gp.tileM.tile[tileNum1].station)
                    {
                        entity.reachStation=true;
          
                        
                        ttt3 = new TicTacToe3 ();
                        while(!ttt3.finish)
                        {
                            Thread.sleep(ALLBITS);
                        }
                        entity.reachStation=false;
                        if(ttt3.gameStatus==1)
                        {
                            gp.tileM.mapTileNum[entityRow][entityCol]=0;
                            previousStationCol=entityCol;
                            previousStationRow=entityRow;
                            
                            previousPlayerX=entity.x;
                            previousPlayerY=entity.y;
                        }
                        else if(winStation!=0)
                        {
                            winStation--;
                            gp.tileM.mapTileNum[previousStationRow][previousStationCol]=2;
                            
                            entity.x=previousPlayerX;
                            entity.y=previousPlayerY;
                        }
                        else
                        {
                            System.out.println("Game Over!!!!!");
                        }
                    }
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
        }
        
    }

}
