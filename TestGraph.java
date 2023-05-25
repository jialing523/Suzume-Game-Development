
import java.io.*;
import java.util.*;
 
class TestGraph
{
    public static void main (String [] args) throws IOException
    {
        File f1 = new File("D:\\DOWNLOAD\\image 1.png");
        File f2 = new File("D:\\DOWNLOAD\\image 2.png");
        File f3 = new File("D:\\DOWNLOAD\\image 3.png");
        File f4 = new File("D:\\DOWNLOAD\\image 4.png");
    
        MapPieces mp1 = new MapPieces (f1,1);
        MapPieces mp2 = new MapPieces (f2,2);
        MapPieces mp3 = new MapPieces (f3,3);
        MapPieces mp4 = new MapPieces (f4,4);
        
        System.out.println("Number of possible path in map piece 1: "+mp1.findPossiblePaths());
        System.out.println("Number of possible path in map piece 2: "+mp2.findPossiblePaths());
        System.out.println("Number of possible path in map piece 3: "+mp3.findPossiblePaths());
        System.out.println("Number of possible path in map piece 4: "+mp4.findPossiblePaths());
        
        //left top - mapPiece1
        //right top - mapPiece2
        //left bottom - mapPiece3
        //right bottom - mapPiece4
        
        WholeMap wm = new WholeMap();
        System.out.println("\nWhole Map:");
        wm.wholeMap.printEdges();

        
        
        
    }

}
