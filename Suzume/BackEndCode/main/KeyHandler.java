import Tile.TileManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, anyPressed;
    GamePanel gp;

    public boolean isAnyKeyPressed()
    {
        return upPressed ||downPressed ||leftPressed|| rightPressed;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
            //title state
        if(gp.gameState==gp.titleState){
            if(code==KeyEvent.VK_W||code==KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=2;
                }
            }
            if(code==KeyEvent.VK_S||code==KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum>2){
                    gp.ui.commandNum=0;
                }
            }

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.commandNum==0){
                   // gp.gameState=gp.playState;
                    gp.gameState=gp.storyIntroState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum==1){
                    //add later load game
                }
                if(gp.ui.commandNum==2){
                    System.exit(0);
                }
            }
        }
           else if(gp.gameState==gp.storyIntroState){
                if(code==KeyEvent.VK_ENTER){
                    gp.gameState=gp.displayPath;
                }
            }

       else if(gp.gameState==gp.playState){
        switch (code)
        {
            case (KeyEvent.VK_W):
            case (KeyEvent.VK_UP):
            {
                upPressed =true;
                break;
            }
            case (KeyEvent.VK_S):
            case (KeyEvent.VK_DOWN):
            {
                downPressed =true;
                break;
            }
            case (KeyEvent.VK_A):
            case (KeyEvent.VK_LEFT):
            {
                leftPressed = true;
                break;
            }
            case (KeyEvent.VK_D):
            case (KeyEvent.VK_RIGHT):
            {
                rightPressed=true;
                break;
            }}
            // pause
            if(code==KeyEvent.VK_SPACE){
               gp.gameState=gp.pauseState;
                }

                }

        else if(gp.gameState== gp.pauseState){
           if(code==KeyEvent.VK_SPACE){
               gp.gameState=gp.playState;
           }
        }
        else if(gp.gameState==gp.gameOver){
            gameOverState(code);
        }
        

        else if(gp.gameState==gp.displayPath){
        switch (code)
        {
            case (KeyEvent.VK_A):
            case (KeyEvent.VK_LEFT):
            {
                leftPressed = true;
                gp.ui.commandNum--;
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=0;
                }
                break;
            }
            case (KeyEvent.VK_D):
            case (KeyEvent.VK_RIGHT):
            {
                rightPressed=true;
                gp.ui.commandNum++;
                if(gp.ui.commandNum>=6){
                    gp.ui.commandNum=6;}
                break;
            }
            case(KeyEvent.VK_ENTER):
                gp.tileM= new TileManager (this.gp,-1);
                gp.gameState=gp.playState;
                break;
        
        }
        }
    }
 
        
        

        public void gameOverState(int code){
        if(code==KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum=1;
            }
        }
            if(code==KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum>1){
                    gp.ui.commandNum=0;
                }
            }

            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.commandNum==0){
                    gp.gameState=gp.playState;
                    gp.retry();
                }
                else if(gp.ui.commandNum==1){
                    gp.gameState=gp.titleState;
                    gp.restart();
                }
            }

        }
        




    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        switch (code)
        {
            case (KeyEvent.VK_W):
            case (KeyEvent.VK_UP):
            {
                upPressed =false;
                break;
            }
            case (KeyEvent.VK_S):
            case (KeyEvent.VK_DOWN):
            {
                downPressed =false;
                break;
            }
            case (KeyEvent.VK_A):
            case (KeyEvent.VK_LEFT):
            {
                leftPressed = false;
                break;
            }
            case (KeyEvent.VK_D):
            case (KeyEvent.VK_RIGHT):
            {
                rightPressed = false;
                break;
            }


        }


    }

}
