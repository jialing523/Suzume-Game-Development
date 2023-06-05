
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class ShortestPathMap
{
    public int [][] map1,map2,map3,map4,map5,map6;
    WholeMap wm;
    
    public ShortestPathMap() throws IOException
    {
        wm = new WholeMap();

        
        File f0 = new File("D:\\APP\\netbeans\\22004743 SEM1\\WIA1002_GROUPASSIGNMENT\\ShortestPaths.txt");
        Scanner sc = new Scanner (new FileInputStream (f0));
        
        int map=1;
        while(sc.hasNextLine())
        {
            String path = sc.nextLine();
            String [] vertex = path.split("] ");
            int [][] tempMap = new int [40][20];
            
            for (int i=0; i<wm.wholeMapDotsArray.length;i++)
            {
                for (int j=0; j<wm.wholeMapDotsArray[i].length;j++)
                {
                    tempMap[i][j] = wm.wholeMapDotsArray[i][j];
                    
                    if(wm.wholeMapDotsArray[i][j]==2)
                        tempMap[i][j] = 5;
                }
            }
//            System.out.println(map);
//            for(int a=0; a<40;a++)
//            {
//                for(int b=0; b<20; b++)
//                {
//                    System.out.print(tempMap[a][b] +" ");
//                }
//                System.out.println();
//            }
            
            
            for(int i=0; i<vertex.length; i++)
            {
                String [] coordinate = vertex[i].split(", ");
                
                int row = Integer.parseInt(coordinate[1]);          
                int col = Integer.parseInt(coordinate[2]);
      
                if(Integer.parseInt(coordinate[3])==0)
                {
                    tempMap[row][col]=4;
                }
                
                if(Integer.parseInt(coordinate[3])==2)
                {
                    tempMap[row][col]=2;
                }
                
                switch(map)
                {
                    case 1:
                        map1=tempMap;
                        break;
                    case 2:
                        map2=tempMap;
                        break;
                    case 3:
                        map3=tempMap;
                        break;
                    case 4:
                        map4=tempMap;
                        break;
                    case 5:
                        map5=tempMap;
                        break;
                    case 6:
                        map6=tempMap;
                        break;
                }
                
            }
            
            map++;
        }
        sc.close();
               
    }
    
    public static void main (String [] args) throws IOException
    {
        ShortestPathMap spm = new ShortestPathMap();
        
        for(int i=0; i<40;i++)
        {
            for(int j=0; j<20; j++)
            {
                System.out.print(spm.map1[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<40;i++)
        {
            for(int j=0; j<20; j++)
            {
                System.out.print(spm.map2[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<40;i++)
        {
            for(int j=0; j<20; j++)
            {
                System.out.print(spm.map3[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<40;i++)
        {
            for(int j=0; j<20; j++)
            {
                System.out.print(spm.map4[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<40;i++)
        {
            for(int j=0; j<20; j++)
            {
                System.out.print(spm.map5[i][j] +" ");
            }
            System.out.println();
        }
        
        System.out.println();
        for(int i=0; i<40;i++)
        {
            for(int j=0; j<20; j++)
            {
                System.out.print(spm.map6[i][j] +" ");
            }
            System.out.println();
        }
    }
}
