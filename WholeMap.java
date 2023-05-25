
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class WholeMap<T extends Comparable<T>>
{
    File f1 = new File("D:\\DOWNLOAD\\image 1.png");
    File f2 = new File("D:\\DOWNLOAD\\image 2.png");
    File f3 = new File("D:\\DOWNLOAD\\image 3.png");
    File f4 = new File("D:\\DOWNLOAD\\image 4.png");
    
    UnweightedGraph<Integer> wholeMap,halfTopMap,halfBottomMap;
    MapPieces mp1,mp2,mp3,mp4;
    
    public WholeMap() throws IOException
    {
        mp1=new MapPieces(f1,1);
        this.changeStation(mp1);
        mp2=new MapPieces(f2,2);
        this.changeStation(mp2);
        halfTopMap=this.connectGraphHorizontally(mp1, mp2);
        
        
        mp3=new MapPieces(f3,3);
        this.changeStation(mp3);
        mp4=new MapPieces(f4,4);
        halfBottomMap=this.connectGraphHorizontally(mp3, mp4);
        
        wholeMap=this.connectGraphVertically(this.halfTopMap, this.halfBottomMap);
        
    }
    
    public static void main (String [] args) throws IOException
    {
        WholeMap wm= new WholeMap();
        System.out.println(wm.halfTopMap.getNumOfVertex());
        //wm.halfTopMap.printEdges();
        System.out.println(wm.halfBottomMap.getNumOfVertex());
        //wm.halfBottomMap.printEdges();
        
        System.out.println(wm.wholeMap.getNumOfVertex());
        wm.wholeMap.printEdges();
    }
    
    
    public void changeStation(MapPieces mp)
    {
        UnweightedVertex<Integer> temp = mp.getGraph().head;
        Integer [] finalStation={mp.getMapNo(),19,9,3};
        while(temp!=null)
        {
            if(Arrays.equals(temp.vertexInfo, finalStation))
            {
                temp.vertexInfo[3]=1;
                mp.getGraph().removeAllEdges(finalStation);
            }
            temp=temp.nextVertex;
        }
        
        
        
    }
    
    public UnweightedGraph<Integer> connectGraphHorizontally(MapPieces mp1, MapPieces mp2)
    {
        UnweightedGraph<Integer> graph1 = mp1.getGraph();
        UnweightedGraph<Integer> graph2 = mp2.getGraph();

        UnweightedVertex<Integer> vertex1 = graph1.head;
        UnweightedVertex<Integer> vertex2 = graph2.head;
        UnweightedVertex<Integer> vertex2_next = null;

        while (vertex1 != null)
        {
            //iterates along graph1.vertex

            //stop at the most right side vertex of graph1
            if (vertex1.vertexInfo[2] == 9 && vertex1.vertexInfo[0] == mp1.getMapNo() && vertex2.vertexInfo[0] == mp2.getMapNo())
            {
                //break the link between the most right side vertex of graph1 and its next vertex
                //such that the most right side vertex can link to the most left side vertex of graph2
                UnweightedVertex<Integer> vertex1_next = vertex1.nextVertex;
                while (vertex2 != null)
                {
                    boolean statusCheck = true;

                    if (vertex2.vertexInfo[2] == 0 && vertex2.vertexInfo[1] == vertex1.vertexInfo[1])
                    {
                        vertex1.nextVertex = vertex2;

                        //add edge if it is not a obstacle
                        if (vertex2.vertexInfo[3] != 1 && vertex1.vertexInfo[3] != 1)
                            graph1.addUndirectedEdge(vertex1.vertexInfo, vertex2.vertexInfo);

                        //break the link between the most right side vertex of graph2 and its next vertex
                        //such that the most right side vertex can link to the most left side vertex of graph1
                        while (vertex2.vertexInfo[1] == vertex1.vertexInfo[1] && vertex2.nextVertex != null)
                        {
                            if (vertex2.vertexInfo[2] == 9)
                            {
                                vertex2_next = vertex2.nextVertex;
                                vertex2.nextVertex = vertex1_next;
                                vertex2 = vertex2_next;
                                statusCheck = false;
                                break;
                            }
                            vertex2 = vertex2.nextVertex;    
                        }
                    }
                    if (statusCheck)
                        vertex2 = vertex2.nextVertex;

                    // Break out of the loop if the desired condition is met
                    if (!statusCheck)
                        break;
                }
            }
            vertex1 = vertex1.nextVertex;
        }

        graph1.size += graph2.size;

        return graph1;      
    }

    public UnweightedGraph<Integer> connectGraphVertically (UnweightedGraph<Integer> graph1, UnweightedGraph<Integer> graph2)
    {
        UnweightedVertex<Integer> vertex1 = graph1.head;
        UnweightedVertex<Integer> vertex2 = graph2.head;

        while(vertex1.nextVertex!=null)
        {
            vertex1=vertex1.nextVertex;
        }

        vertex1.nextVertex=vertex2;
        
        graph1.size+=graph2.size;

        return graph1;
    }

}
