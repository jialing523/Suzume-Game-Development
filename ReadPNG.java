import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ReadPNG
{
    public static void main (String [] args) throws IOException 
    {
        // Load the PNG file
        File file1 = new File("D:\\DOWNLOAD\\image 1.png");
        File file2 = new File("D:\\DOWNLOAD\\image 2.png");
        File file3 = new File("D:\\DOWNLOAD\\image 3.png");
        File file4 = new File("D:\\DOWNLOAD\\image 4.png");
        
        System.out.println("Pixels of image 1");
        int [][] image1_pixels = scanImage(file1);
        System.out.println();
        int [][] image1_dots = converting(image1_pixels);
        System.out.println();
        
        System.out.println("Pixels of image 2");
        int [][] image2_pixels = scanImage(file2);
        System.out.println();
        int [][] image2_dots = converting(image2_pixels);
        System.out.println();
        
        System.out.println("Pixels of image 3");
        int [][] image3_pixels = scanImage(file3);
        System.out.println();
        int [][] image3_dots = converting(image3_pixels);
        System.out.println();
        
        System.out.println("Pixels of image 4");
        int [][] image4_pixels = scanImage(file4);
        System.out.println();
        int [][] image4_dots = converting(image4_pixels);
        System.out.println();
        
        
        
        
    }
    
    
    public static int [][] scanImage (File f) throws IOException
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
                
                //Display
                System.out.print(pixels[y][x] + " ");
            }
            System.out.println();
        }
        return pixels;
    }
    
    public static int[][] converting(int[][] pixels) 
    {
        int [][] dots = new int [pixels.length][];
        
        for (int i=0; i<pixels.length; i++)
        {
            dots[i]=new int[pixels[i].length];
            
            for (int j=0; j<pixels[i].length;j++)
            {
                //converting
                if (pixels[i][j]==0)
                    dots[i][j]=0;
                else if (pixels[i][j]==64)
                    dots[i][j]=1;
                else if (pixels[i][j]==128)
                    dots[i][j]=2;
                else if (pixels[i][j]==192)
                    dots[i][j]=3;
                
                System.out.print(dots[i][j]+" ");
            }
            System.out.println();
        }
        
        return dots;
    }
}
