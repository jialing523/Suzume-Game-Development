package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.ALLBITS;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.TicTacToe3;

public class Player extends Entity
{
    GamePanel gp;
    KeyHandler keyH;
    TicTacToe3 ttt3;
    int stationCounter;
   
    
    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp=gp;
        this.keyH=keyH;
        stationCounter=0;
        
        solidArea = new Rectangle();
        solidArea.x=0;
        solidArea.y=0;
        
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues()
    {
        x=0;
        y=19*gp.tileSize;
        speed = gp.tileSize;
        direction="right";
    }
    
    public void getPlayerImage()
    {
        try{
            up1=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_up_1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_up_2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_down_1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_down_2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_left_1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_left_2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_right_1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/player/Suzume_right_2.png"));
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void update() throws InterruptedException {
    int dx = 0;
    int dy = 0;

 
            
    if (keyH.upPressed) {
        direction = "up";
        dy = -speed;
    } else if (keyH.downPressed) {
        direction = "down";
        dy = speed;
    } else if (keyH.rightPressed) {
        direction = "right";
        dx = speed;
    } else if (keyH.leftPressed) {
        direction = "left";
        dx = -speed;
    }

    collisionOn = false;
    gp.cChecker.checkTile(this);
    gp.cChecker.checkStation(this);

    
    

    if (!collisionOn&&!reachStation) {
        x += dx;
        y += dy;
        
        int counter =0;
           while(keyH.isAnyKeyPressed())
        {
            
            if(counter ==0)
            {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
            }

            Thread.sleep(ALLBITS);
            counter=1;
        }

                
            

        
    }
}
    public void draw(Graphics2D g2)
    {
//        g2.setColor(Color.WHITE); 
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        
        BufferedImage image =null;
        
        switch(direction)
        {
            case("up"):
            {
                if(spriteNum ==1)
                    image=up1;
                if(spriteNum == 2)
                    image=up2;
                break;
            }
            case("down"):
            {
                if(spriteNum ==1)
                    image=down1;
                if(spriteNum == 2)
                    image=down2;
                break;
            }
            case("left"):
            {
                if(spriteNum ==1)
                    image=left1;
                if(spriteNum == 2)
                    image=left2;
                break;
            }
            case("right"):
            {
                if(spriteNum ==1)
                    image=right1;
                if(spriteNum == 2)
                    image=right2;
                break;
            }
        }
        
        g2.drawImage(image, x, y,gp.tileSize,gp.tileSize,null);
    }


}
