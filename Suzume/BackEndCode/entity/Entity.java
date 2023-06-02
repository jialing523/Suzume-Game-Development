package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity
{
    public int x,y;
    public int speed;
    
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    
    public int spriteNum = 1;
    public boolean collisionOn=false;
    public boolean reachStation=false;
}
