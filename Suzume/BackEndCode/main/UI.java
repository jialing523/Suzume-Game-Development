import Tile.TileManager;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    CollisionChecker ck;
    Font maruMonica,purisaB;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public boolean gameFinished = false;
    public String currentDialogue=" ";
    public int commandNum=0;
    //public int titleScreen=0;// 0; the first screen, 2=second screen

    public UI(GamePanel gp){
        this.gp=gp;

        try{
            InputStream is = getClass().getResourceAsStream("/Font/MaruMonica.ttf");
            maruMonica=Font.createFont(Font.TRUETYPE_FONT,is);
            is=getClass().getResourceAsStream("/Font/Purisa Bold.ttf");
            purisaB=Font.createFont(Font.TRUETYPE_FONT,is);
        }catch(FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        this.g2=g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //playState
        if(gp.gameState==gp.playState){

        }
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
        }
        // title state
        if(gp.gameState==gp.titleState){
            drawTitle();
        }

        if(gp.gameState==gp.gameOver){
        drawGameOver();
        }
        if(gp.gameState==gp.storyIntroState){
            drawStoryIntro();
        }
        
        if(gp.gameState==gp.displayPath)
        {
            drawAllPaths();
        }

    }


    public void drawGameOver(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text="Game Over";
        //shadow
        g2.setColor(Color.black);
        x=getXforCenteredText(text);
        y=gp.tileSize*7;
        g2.drawString(text,x,y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //retry
        g2.setFont(g2.getFont().deriveFont(60f));
        text="Retry";
        x=getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-40,y);
        }

        //back to title screen
        text="Quit";
        x=getXforCenteredText(text);
        y+=70;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-40,y);
        }

    }

    // pause
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text="PAUSED";
        int x=getXforCenteredText(text);
        int y=gp.screenHeight/2;
        g2.drawString(text,x,y);

    }

    // title state ui
    public void drawTitle(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text=" Suzume Plays Tic Tac Toe";
        int x= getXforCenteredText(text);
        int y= gp.tileSize*3;

        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        // suzume pic

        x= gp.screenWidth/2-((gp.tileSize*2)/2+30);
        y+=gp.tileSize*2;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*6,gp.tileSize*6,null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,52F));

        text=" NEW GAME";
        x= getXforCenteredText(text);
        y+= gp.tileSize*9;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text=" LOAD GAME";
        x= getXforCenteredText(text);
        y+= gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text="QUIT";
        x= getXforCenteredText(text);
        y+= gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum==2){
            g2.drawString(">",x-gp.tileSize,y);
        }

    }
    
    public void drawAllPaths()
    {
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30f));
        text="Next>";
        //shadow
        x=1824;
        y=995;
        
        System.out.println(x +"  "+y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30f));
        text="<Previous";
        //shadow
        x=5;
        y=995;
        
        System.out.println(x +"  "+y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        //retry
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30f));
        text="(Press Enter To Skip)";
        x=getXforCenteredText(text);
        y+=gp.tileSize*1;
        g2.drawString(text,x,y);
        
         
        
        switch(this.commandNum)
        {
            case 0:
                gp.tileM = new TileManager(this.gp,0);
                break;
            case 1:
                gp.tileM = new TileManager(this.gp,1);
                break;
            case 2:
                gp.tileM = new TileManager(this.gp,2);
                break;
            case 3:
                gp.tileM = new TileManager(this.gp,3);
                break;
            case 4:
                gp.tileM = new TileManager(this.gp,4);
                break;
            case 5:
                gp.tileM = new TileManager(this.gp,5);
                break;
        }
               
                
        }




    public void drawStoryIntro(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(42F));
        // add story intro here
        String text = " ";
        int x= getXforCenteredText(text);
        int  y=gp.tileSize*3;
        g2.drawString(text,x,y);
    }

    public int getXforCenteredText(String text){

        int length =(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        return x;

    }
}
