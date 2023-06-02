import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MapPieces
{
    private UnweightedGraph<Integer> graph;
    private int mapNo;
    private ReadPNG pieces;
    private int [][] dotsArray;
    private ArrayList<Integer[]> vertexArray;
    
    public MapPieces(File f,int mapNo) throws IOException // for mapPieces used
    {
        this.mapNo=mapNo;
        this.pieces = new ReadPNG(f);
        this.dotsArray = pieces.getArrayDots();
        this.graph = new UnweightedGraph();
        this.vertexArray=new ArrayList<>();
        this.addVertex(this.dotsArray);
        this.addEdges(this.dotsArray);
    }
    
    public MapPieces(int [][] dotsArray)//for combination of whole Map used
    {
        this.dotsArray=dotsArray;
        this.graph = new UnweightedGraph();
        this.vertexArray=new ArrayList<>();
        this.addVertex(dotsArray);
        this.addEdges(dotsArray);
    }
    
    public void addVertex(int [][] dotsArray)
    {
        for(int i=0; i<dotsArray.length;i++)
        {
            for(int j=0; j<dotsArray[i].length;j++)
            {
                Integer [] holder1 ={this.mapNo,i,j,dotsArray[i][j]};
                graph.addVertex(holder1);
                vertexArray.add(holder1);
            }
        }
    }
    
    public void addEdges(int [][] dotsArray)
    {
        for(int i=0; i<dotsArray.length;i++)
        {
            for(int j=0; j<dotsArray[i].length;j++)
            {
                Integer [] holder1 ={this.mapNo,i,j,dotsArray[i][j]};
                if(dotsArray[i][j]!=1)
                {
                    if(i-1>=0)
                    {
                        if(dotsArray[i-1][j]!=1)
                        {
                            Integer [] holder2 ={this.mapNo,i-1,j,dotsArray[i-1][j]};
                            if(!graph.hasEdge(holder1, holder2))
                                graph.addUndirectedEdge(holder1, holder2);
                        }
                    }

                    if(i+1<dotsArray.length)
                    {
                        if(dotsArray[i+1][j]!=1)
                        {
                            Integer [] holder2 ={this.mapNo,i+1,j,dotsArray[i+1][j]};
                            if(!graph.hasEdge(holder1, holder2))
                                graph.addUndirectedEdge(holder1, holder2);
                        }
                    }

                    if(j-1>=0)
                    {
                        if(dotsArray[i][j-1]!=1)
                        {
                            Integer [] holder2 ={this.mapNo,i,j-1,dotsArray[i][j-1]};
                            if(!graph.hasEdge(holder1, holder2))
                                graph.addUndirectedEdge(holder1, holder2);
                        }
                    }

                    if(j+1<dotsArray[i].length)
                    {
                        if(dotsArray[i][j+1]!=1)
                        {
                            Integer [] holder2 ={this.mapNo,i,j+1,dotsArray[i][j+1]};
                            if(!graph.hasEdge(holder1, holder2))
                                graph.addUndirectedEdge(holder1, holder2);
                        }
                    }
                }
                    
            }
        }
    }
    
    public UnweightedGraph<Integer> getGraph()
    {
        return this.graph;
    }
    
    public ArrayList<Integer[]> getVertexArray()
    {
        return this.vertexArray;
    }
    
    public int [][] getDotsArray()
    {
        return this.dotsArray;
    }
    
    public int getMapNo()
    {
        return this.mapNo;
    }
    
    public  int findPossiblePaths() throws IOException
    {

        Integer [] source = {mapNo,0,0,0};
        Integer [] destination = {mapNo,19,9,3};
        
        return this.getGraph().findPaths(source,destination,2,3);
    }
   
}
