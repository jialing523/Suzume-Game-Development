import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ReadPNG
{
    private File file;
    private int [][] image_dots;
    private int [][] image_pixels;
 
    public ReadPNG(File file) throws IOException
    {
        this.file=file;
        this.image_pixels=this.scanImage(file);
        this.image_dots=this.converting();
    }
    
   
    public int [][] scanImage (File f) throws IOException
    {
        BufferedImage image = ImageIO.read(f);
        
        // Get the width and height of the image
        int width = image.getWidth();
        int height = image.getHeight();

        int [][] pixels = new int[height][width];
        
        // Access the pixel data
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int pixel = image.getRGB(x, y);

                // Extract RGB values
                int green = (pixel >> 8) & 0xFF;

                // Store the pixel values
                pixels[y][x]=green;

                
            }
           
        }
        return pixels;
    }
    
    public int[][] converting() 
    {
        int [][] dots = new int [this.image_pixels.length][];
        
        for (int i=0; i<this.image_pixels.length; i++)
        {
            dots[i]=new int[this.image_pixels[i].length];
            
            for (int j=0; j<this.image_pixels[i].length;j++)
            {
                //converting
                if (this.image_pixels[i][j]==0)
                    dots[i][j]=0;
                else if (this.image_pixels[i][j]==64)
                    dots[i][j]=1;
                else if (this.image_pixels[i][j]==128)
                    dots[i][j]=2;
                else if (this.image_pixels[i][j]==192)
                    dots[i][j]=3;
                
                
            }
         
        }
        
        return dots;
    }
    
    public int[][] getArrayDots()
    { 
        return this.image_dots;
    }
    
    public int [][] getArrayPixels()
    {
        return this.image_pixels;
    }
}

