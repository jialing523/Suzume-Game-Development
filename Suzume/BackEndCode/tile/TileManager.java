package Tile;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import BackEnd.WholeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileManager
{
    GamePanel gp;
    public Tile [] tile;
    WholeMap wm;
    public int [][] mapTileNum;
        
    
    public TileManager(GamePanel gp)
    {
        try {
            this.wm= new WholeMap();
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gp=gp;
        //10 kinds of tile
        tile= new Tile[10];
        
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
            
            tile[1]= new Tile();
            tile[1].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tile[1].collision=true;
            
            tile[2]= new Tile();
            tile[2].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/station_hut.png"));
            tile[2].collision=true;
            tile[2].station=true;
            
            tile[3]= new Tile();
            tile[3].image =ImageIO.read(getClass().getResourceAsStream("/Tiles/final_station.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();;
        }
    }
    
    public void loadMap()
    {

            
            for(int i=0; i<wm.getWholeMapDotsArray().length;i++)
            {
                for(int j=0; j<wm.getWholeMapDotsArray()[i].length;j++)
                {
                    mapTileNum[19-j][i] = wm.getWholeMapDotsArray()[i][j];
                }
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

                }
                x += gp.tileSize;
            }
            y+=gp.tileSize;
            x=0;
        }
 
    }
}
