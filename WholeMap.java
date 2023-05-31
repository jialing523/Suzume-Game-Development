import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class WholeMap
{
    File f1 = new File("D:\\DOWNLOAD\\image 1.png");
    File f2 = new File("D:\\DOWNLOAD\\image 2.png");
    File f3 = new File("D:\\DOWNLOAD\\image 3.png");
    File f4 = new File("D:\\DOWNLOAD\\image 4.png");
    
    UnweightedGraph<Integer> wholeMap,halfTopMap,halfBottomMap;
    MapPieces mp1,mp2,mp3,mp4;
    int [][] wholeMapDotsArray;
    
    public WholeMap() throws IOException
    {
        mp1=new MapPieces(f1,1);
        int [][] dotsArray1 = mp1.getDotsArray();
        this.changeDot(dotsArray1);
        
        mp2=new MapPieces(f2,2);
        int [][] dotsArray2 = mp2.getDotsArray();
        this.changeDot(dotsArray2);
        
        int [][] dotsArray1_2 = this.combineArrayHorizontally(dotsArray1, dotsArray2);
        
        mp3=new MapPieces(f3,3);
        int [][] dotsArray3 = mp3.getDotsArray();
        this.changeDot(dotsArray3);
        
        mp4=new MapPieces(f4,4);
        int [][] dotsArray4 = mp4.getDotsArray();

        int [][] dotsArray3_4 = this.combineArrayHorizontally(dotsArray3, dotsArray4);
        
        int [][] wholeMapDotsArray = this.combinateArrayVertically(dotsArray1_2, dotsArray3_4);
        
        MapPieces wholeMap = new MapPieces(wholeMapDotsArray);
        this.wholeMap=wholeMap.getGraph();
//        
//        for(int i=0; i<20; i++)
//        {
//            for(int j=0; j<20;j++ )
//            {
//                System.out.print(dotsArray3_4[i][j]+ " ");
//            }
//            System.out.println();
//        }
    }
    
    public static void main (String [] args) throws IOException
    {
        WholeMap wm = new WholeMap();
    }
    
    public int[][] combineArrayHorizontally(int[][]arr1,int[][]arr2){
            int rows=arr1.length;
            int cols1=arr1[0].length;
            int cols2=arr2[0].length;
             int[][] combinedArray=new int[rows][cols1+cols2];

             for(int i=0;i<rows;i++){
                 for(int j=0;j<cols1;j++){
                     combinedArray[i][j]=arr1[i][j];
                 }
             }

             for(int i=0;i<rows;i++){
                 for(int j=0;j<cols2;j++){
                     combinedArray[i][j+cols1]=arr2[i][j];
                 }
             }
            return combinedArray;
        }

        public int[][] combinateArrayVertically(int[][]arr1,int[][]arr2){
            int cols=arr1[0].length;
            int row1=arr1.length;
            int row2=arr2.length;
            
            int[][]combinedArray=new int[(row2+row1)][cols];
            
            for(int i=0;i<row1;i++){
                for(int j=0;j<cols;j++){
                    combinedArray[i][j]=arr1[i][j];
                }

            }

            for(int i=0;i<row2;i++){
                for(int j=0;j<cols;j++){
                    combinedArray[i+row1][j]=arr2[i][j];
                }
            }

            return combinedArray;
        }




        public int[][] changeDot(int[][]arr)
        {
                    arr[19][9]=1;
                            
                    return arr;
        }
        
           public int [][] getWholeMapDotsArray()
    {
        return this.wholeMapDotsArray;
    }
}
      
    

