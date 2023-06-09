
package main;



import Entity.Player;
import Tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable
{
    final int originalTileSize =16;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;

    public final int maxScreenCol=40;
    public final int maxScreenRow=22;
    public UI ui= new UI(this);
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    final int FPS = 60;

    public TileManager tileM = new TileManager(this,-1);
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    public CollisionChecker cChecker= new CollisionChecker(this);
    Thread gameThread;

    public Player player =new Player(this, keyH);
//game state
    public int gameState;

    public final int titleState=0;
    public final int pauseState=1;
    public final int playState=2;
    public final int storyIntroState=3;

    public final int gameOver=4;
    
    public final int displayPath=6;



    public GamePanel()
    {
        this.setPreferredSize(new Dimension (screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);




    }


        public void setupGame(){
        gameState=titleState;
}

    public void startGameThread()
    {

        gameThread = new Thread(this);
        gameThread.start();
        //playMusic(0);

    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;//0.01666s
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null)
        {

            try {
                //Updates info: Suzume position
                this.update();

            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Draw the screen with the updated info
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000000;

                if(remainingTime <0)
                    remainingTime=0;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

    }

    public void update() throws InterruptedException
    {
        if(gameState==playState){
            player.update();
        }
        if(gameState==pauseState){
            //nothing

        }


    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //title state

        if(gameState==titleState||gameState==this.storyIntroState){//
          ui.draw(g2);
          
        }
        else {

            try {
                tileM.draw(g2);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            player.draw(g2);
            ui.draw(g2);
            g2.dispose();
        }

    }

    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();

    }

    public void stopMusic()
    {
        sound.stop();
    }

    public void playSE(int i)
    {
        sound.setFile(i);
        sound.play();
    }
    
    public void retry(){
        player.setDefaultValues();
        playSE(0);

}

    public void restart(){
        player.setDefaultValues();
        ui.commandNum=0;

    }





}

