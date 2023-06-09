
package Tile;

import BackEnd.ShortestPathMap;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import BackEnd.WholeMap;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.KeyHandler;

public class TileManager
{
    GamePanel gp;
    public Tile [] tile;
    //WholeMap wm;
    ShortestPathMap spm;
    public int [][] mapTileNum;
    KeyHandler kh = new KeyHandler(this.gp);
    public int mapNo;
        
    
    public TileManager(GamePanel gp,int mapNo)
    {
        try {
        spm= new ShortestPathMap();
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gp=gp;
        //10 kinds of tile
        tile= new Tile[10];
        this.mapNo=mapNo;
        mapTileNum = new int [20][40];
        this.getTileImage();
        this.loadMap();
    }
    
    public void getTileImage()
    {
        try
        {
            tile[0]= new Tile();
            tile[0].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));
            tile[0].collision=true;
            
            tile[1]= new Tile();
            tile[1].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tile[1].collision=true;
            
            tile[2]= new Tile();
            tile[2].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/station_hut.png"));
            tile[2].collision=true;
            tile[2].station=true;
            
            tile[3]= new Tile();
            tile[3].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/final_station.png"));
            tile[3].collision=true;
            tile[3].finalStation=true;
            
            tile[4]= new Tile();
            tile[4].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/road.png"));
            
            tile[5]= new Tile();
            tile[5].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
            tile[5].collision=true;
            
            tile[6]= new Tile();
            tile[6].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/red_flag.png"));
            
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }
    
    public void loadMap()
    {
        if(this.mapNo==-1)
        {
        Random rand = new Random();
        this.mapNo = rand.nextInt(6);
        }
        

        switch (mapNo)
        {
            case 0:
                for(int i=0; i<spm.map1.length;i++)
                {
                    for(int j=0; j<spm.map1[i].length;j++)
                    {
                        mapTileNum[19-j][i] = spm.map1[i][j];
                    }
                }
                break;
            case 1:
                for(int i=0; i<spm.map2.length;i++)
                {
                    for(int j=0; j<spm.map2[i].length;j++)
                    {
                        mapTileNum[19-j][i] = spm.map2[i][j];
                    }
                }
                break;
            case 2:
                for(int i=0; i<spm.map3.length;i++)
                {
                    for(int j=0; j<spm.map3[i].length;j++)
                    {
                        mapTileNum[19-j][i] = spm.map3[i][j];
                    }
                }
                break;
            case 3:
                for(int i=0; i<spm.map4.length;i++)
                {
                    for(int j=0; j<spm.map4[i].length;j++)
                    {
                        mapTileNum[19-j][i] = spm.map4[i][j];
                    }
                }
                break;
            case 4:
                for(int i=0; i<spm.map5.length;i++)
                {
                    for(int j=0; j<spm.map5[i].length;j++)
                    {
                        mapTileNum[19-j][i] = spm.map5[i][j];
                    }
                }
                break;
            case 5:
                for(int i=0; i<spm.map6.length;i++)
                {
                    for(int j=0; j<spm.map6[i].length;j++)
                    {
                        mapTileNum[19-j][i] = spm.map6[i][j];
                    }
                }
                break;   
        }
            
            
                     
        
        
    }
    public void draw(Graphics2D g2) throws IOException
    {
        int x=0;
        int y=0;

        
        for(int i=0; i<20; i++)
        {
            for(int j=0; j<40;j++)
            {
                switch(mapTileNum[i][j])
                {
                    
                    case 0:
                    {
                        g2.drawImage(tile[0].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                    case 1:
                    {
                        g2.drawImage(tile[1].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                    case 2:
                    {
                        g2.drawImage(tile[2].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                    case 3:
                    {
                        g2.drawImage(tile[3].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                    case 4:
                    {
                        g2.drawImage(tile[4].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                    case 5:
                    {
                        g2.drawImage(tile[5].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                    case 6:
                    {
                        g2.drawImage(tile[6].image, x, y,gp.tileSize,gp.tileSize,null);
                        break;
                    }
                        

                }
                x += gp.tileSize;
            }
            y+=gp.tileSize;
            x=0;
        }
    }
    
    
}
