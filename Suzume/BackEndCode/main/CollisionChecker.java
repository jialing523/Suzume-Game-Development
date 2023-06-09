
import Entity.Entity;
import static java.awt.image.ImageObserver.ALLBITS;
import java.util.ArrayList;
import java.util.Random;

public class CollisionChecker
{
    GamePanel gp;
    public TicTacToe1 ttt1;
    public TicTacToe2 ttt2;
    public TicTacToe3 ttt3;
    
    ArrayList<Integer> previousStationCol,previousStationRow,previousPlayerX,previousPlayerY;
    public int winStation;//gameStatus;
    public boolean gameOver;//finished;
    

    public CollisionChecker(GamePanel gp)
    {
        this.gp=gp;
        previousStationCol=new ArrayList<>();
        previousStationRow=new ArrayList<>();
        previousPlayerX=new ArrayList<>();
        previousPlayerY=new ArrayList<>();
        winStation=0;
        gameOver=false;
        //finished = false;
        //gameStatus=-1;
        
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
                        this.callStation(entity, entityCol, entityRow);
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
                        this.callStation(entity, entityCol, entityRow);
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
                        this.callStation(entity, entityCol, entityRow);
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
                        this.callStation(entity, entityCol, entityRow);
                    }
                    
                }
                else
                    entity.collisionOn=true;
                break;
            }
        }
        
    }
    
    
    public void callStation(Entity entity, int col, int row) throws InterruptedException
    {
        Random rand = new Random();
        int game = rand.nextInt(3);
        
        //System.out.println("game"+game);
        //int game=2;

        
        switch (game)
        {
            case 0:
                entity.reachStation=true;
                
                ttt1 = new TicTacToe1();
                
                while(!ttt1.finish)
                {
                    Thread.sleep(ALLBITS);
                }

                entity.reachStation=false;

                if(ttt1.gameStatus==1)
                {
                    gp.playSE(1);
                    gp.tileM.mapTileNum[row][col]=4;

                    previousStationCol.add(col);
                    previousStationRow.add(row);


                    previousPlayerX.add(entity.x);
                    previousPlayerY.add(entity.y);
                    winStation++;


                }
                else if(winStation!=0)
                {
                    gp.playSE(2);
                    winStation--;
                    gp.tileM.mapTileNum[previousStationRow.get(winStation)][previousStationCol.get(winStation)]=2;
                    previousStationRow.remove(winStation);
                    previousStationCol.remove(winStation);

                    entity.x=previousPlayerX.get(winStation);
                    entity.y=previousPlayerY.get(winStation);
                    previousPlayerX.remove(winStation);
                    previousPlayerY.remove(winStation);
                }
                else
                {
                    gp.stopMusic();
                    gp.playSE(3);
                    System.out.println("Game Over!!!!!");
                    gameOver=true;
                    gp.gameState=gp.gameOver;

                }
                

                
                break;
            case 1:
                entity.reachStation=true;
                
                ttt2 = new TicTacToe2();
                
                while(!ttt2.finish)
                {
                    Thread.sleep(ALLBITS);
                }

                entity.reachStation=false;

                if(ttt2.gameStatus==1)
                {
                    gp.playSE(1);
                    gp.tileM.mapTileNum[row][col]=4;

                    previousStationCol.add(col);
                    previousStationRow.add(row);


                    previousPlayerX.add(entity.x);
                    previousPlayerY.add(entity.y);
                    winStation++;


                }
                else if(winStation!=0)
                {
                    gp.playSE(2);
                    winStation--;
                    gp.tileM.mapTileNum[previousStationRow.get(winStation)][previousStationCol.get(winStation)]=2;
                    previousStationRow.remove(winStation);
                    previousStationCol.remove(winStation);

                    entity.x=previousPlayerX.get(winStation);
                    entity.y=previousPlayerY.get(winStation);
                    previousPlayerX.remove(winStation);
                    previousPlayerY.remove(winStation);
                }
                else
                {
                    gp.stopMusic();
                    gp.playSE(3);
                    System.out.println("Game Over!!!!!");
                    gameOver=true;
                    gp.gameState=gp.gameOver;

                }
                
                
                
                break;
            case 2:
                entity.reachStation=true;
                ttt3 = new TicTacToe3();
                
                while(!ttt3.finish)
                {
                    Thread.sleep(ALLBITS);
                }

                entity.reachStation=false;

                if(ttt3.gameStatus==1)
                {
                    gp.playSE(1);
                    gp.tileM.mapTileNum[row][col]=4;

                    previousStationCol.add(col);
                    previousStationRow.add(row);


                    previousPlayerX.add(entity.x);
                    previousPlayerY.add(entity.y);
                    winStation++;


                }
                else if(winStation!=0)
                {
                    gp.playSE(2);
                    winStation--;
                    gp.tileM.mapTileNum[previousStationRow.get(winStation)][previousStationCol.get(winStation)]=2;
                    previousStationRow.remove(winStation);
                    previousStationCol.remove(winStation);

                    entity.x=previousPlayerX.get(winStation);
                    entity.y=previousPlayerY.get(winStation);
                    previousPlayerX.remove(winStation);
                    previousPlayerY.remove(winStation);
                }
                else
                {
                    gp.stopMusic();
                    gp.playSE(3);
                    System.out.println("Game Over!!!!!");
                    gameOver=true;
                    gp.gameState=gp.gameOver;
                    
                    

                }

                
                break;
      
        }
        
    }
    
    
    public void checkFinal(Entity entity)
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
                    if(gp.tileM.tile[tileNum1].finalStation)
                    {
                        entity.collisionOn=true;
                        if(winStation == 4)
                        {
                            entity.collisionOn=false;
                            gp.tileM.mapTileNum[entityRow][entityCol]=4;
                            gp.playSE(4);
                            
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
                    if(gp.tileM.tile[tileNum1].finalStation)
                    {
                        entity.collisionOn=true;
                        if(winStation == 4)
                        {
                            entity.collisionOn=false;
                            gp.tileM.mapTileNum[entityRow][entityCol]=4;
                            gp.playSE(4);
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
                    if(gp.tileM.tile[tileNum1].finalStation)
                    {
                        entity.collisionOn=true;
                        if(winStation == 4)
                        {
                            entity.collisionOn=false;
                            gp.tileM.mapTileNum[entityRow][entityCol]=4;
                            gp.playSE(4);
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
                    if(gp.tileM.tile[tileNum1].finalStation)
                    {
                        entity.collisionOn=true;
                        if(winStation == 4)
                        {
                            entity.collisionOn=false;
                            gp.tileM.mapTileNum[entityRow][entityCol]=4;
                            gp.playSE(4);
                         
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
