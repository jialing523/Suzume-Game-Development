import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UnweightedGraph <T extends Comparable<T>>
{
    UnweightedVertex<T> head;
    int size;
    
    public UnweightedGraph()
    {
        head = null;
        size=0;
    }
    
    
    public int getSize()
    {
        return this.size;
    }
    
    public boolean hasVertex(T[] v)
    {
        if (head == null)
            return false;
        UnweightedVertex<T> temp = head;
        while (temp!=null)
        {
            if(Arrays.equals(temp.vertexInfo,v))
                return true;
            temp=temp.nextVertex;
        }
        return false;
    }
    
    public int getIndeg(T[] v)
    {
        if (hasVertex(v))
        {
            UnweightedVertex<T> temp = head;
            while (temp!=null)
            {
                if(Arrays.equals(temp.vertexInfo,v))
                    return temp.indeg;
                temp=temp.nextVertex;
            }
        }
        return -1;
    }
    
        public int getOutdeg(T[] v)
    {
        if (hasVertex(v))
        {
            UnweightedVertex<T> temp = head;
            while (temp!=null)
            {
                if(Arrays.equals(temp.vertexInfo,v))
                    return temp.outdeg;
                temp=temp.nextVertex;
            }
        }
        return -1;
    }
    
    public boolean addVertex(T[] v)
    {
        if (!hasVertex(v))
        {
            UnweightedVertex<T> temp=head;
            UnweightedVertex<T> newVertex = new UnweightedVertex<T>(v,null);
            if(head == null)
                head=newVertex;
            else
            {
                UnweightedVertex<T> previous = head;
                while(temp!=null)
                {
                    previous=temp;
                    temp=temp.nextVertex;
                }
                previous.nextVertex=newVertex;
            }
            size++;
            return true;
        }
        else
            return false;
        
    }
    
    public int getIndex(T[] v)
    {
        UnweightedVertex<T> temp = head;
        int pos=0;
        while (temp!=null)
        {
            if (Arrays.equals(temp.vertexInfo,v))
                return pos;
            temp=temp.nextVertex;
            pos+=1;
        }
        return -1;
    }
    
    public ArrayList<T[]> getAllVertexObjects()
    {
        ArrayList<T[]> list = new ArrayList<>();
        UnweightedVertex<T> temp = head;
        while (temp!=null)
        {
            list.add(temp.vertexInfo);
            temp=temp.nextVertex;
        }
        return list;
    }
    
    public T[] getVertex(int pos)
    {
        if(pos>size-1 || pos<0)
            return null;
        UnweightedVertex<T> temp = head;
        for (int i=0; i<pos; i++)
            temp=temp.nextVertex;
        return temp.vertexInfo;
    }
    
    public boolean hasEdge(T[] source, T[] destination)
    {
        if (head == null)
            return false;
        if(!hasVertex(source)||!hasVertex(destination))
            return false;
        UnweightedVertex<T> sourceVertex = head;
        while(sourceVertex != null)
        {
            if (Arrays.equals(sourceVertex.vertexInfo,source))
            {
                UnweightedEdge <T> currentEdge = sourceVertex.firstEdge;
                while(currentEdge!=null)
                {
                    if(Arrays.equals(currentEdge.toVertex.vertexInfo,destination))
                        return true;
                    currentEdge=currentEdge.nextEdge;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;    
             
    }
    
    public boolean addEdge (T[] source, T[] destination)
    {
        if(head == null)
            return false;
        if(!hasVertex(source)||!hasVertex(destination))
            return false;
        
        UnweightedVertex<T> sourceVertex = head;
        while (sourceVertex!=null)
        {
            if(Arrays.equals(sourceVertex.vertexInfo,source))
            {
                UnweightedVertex<T> destinationVertex = head;
                while(destinationVertex!=null)
                {
                    if(Arrays.equals(destinationVertex.vertexInfo,destination))
                    {
                        UnweightedEdge <T> currentEdge =sourceVertex.firstEdge;
                        UnweightedEdge <T> newEdge = new UnweightedEdge <T> (destinationVertex,currentEdge);
                        sourceVertex.firstEdge=newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                                
                    }
                    destinationVertex=destinationVertex.nextVertex;
                }
            }
            sourceVertex=sourceVertex.nextVertex;
        }
        return false;
    }
    
    public ArrayList <T[]> getNeighbours(T[] v)
    {
        if(!hasVertex(v))
            return null;
        ArrayList<T[]> list = new ArrayList<>();
        
        UnweightedVertex<T> temp = head;
        while(temp!=null)
        {
            if(Arrays.equals(temp.vertexInfo,v))
            {
                UnweightedEdge <T> currentEdge = temp.firstEdge;
                while (currentEdge != null)
                {
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge=currentEdge.nextEdge;
                }
            }
            temp=temp.nextVertex;
        }
        return list;
    }
    
    public void printEdges()
    {
        UnweightedVertex<T> temp =head;
        
        while (temp!=null)
        {
            System.out.print("# " + Arrays.toString(temp.vertexInfo) + " : ");
            UnweightedEdge <T> currentEdge = temp.firstEdge;
            while(currentEdge!=null)
            {
                System.out.print("["+Arrays.toString(temp.vertexInfo) +","+Arrays.toString(currentEdge.toVertex.vertexInfo)+"]");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp=temp.nextVertex;
        }
    }
    
    public boolean addUndirectedEdge(T[] v1, T[] v2)
    {
        boolean add1 = this.addEdge(v1, v2);
        boolean add2= this.addEdge(v2, v1);
        
        return add1&&add2;
        
    }
    
    public boolean removeEdge(T[] source, T[] destination)
    {
        if(head == null)
            return false;
        if(!hasVertex(source)||!hasVertex(destination))
            return false;
        if(!hasEdge(source, destination))
            return false;
        UnweightedVertex<T> temp=head;
        while(temp!=null)
        {
            if(Arrays.equals(temp.vertexInfo,source))
            {
                UnweightedEdge <T> currentEdge = temp.firstEdge;
                UnweightedEdge <T> previousEdge = null;
                
                while(currentEdge!=null)
                {
                    if(Arrays.equals(currentEdge.toVertex.vertexInfo,destination))
                    {
                        if(previousEdge==null)
                            temp.firstEdge=currentEdge.nextEdge;
                        else
                            previousEdge.nextEdge=currentEdge.nextEdge;
                        
                        temp.outdeg--;
                        currentEdge.toVertex.indeg--;
                     
                        return true;
                    }
                    previousEdge=currentEdge;
                    currentEdge=currentEdge.nextEdge;
                }
            }
            temp=temp.nextVertex;
        }
        return false;
    }
    
    public boolean isEmpty()
    {
        if (head==null)
            return true;
        return false;
    }
    
    public int findPaths (T[] source, T[] destination)
    {
        //Store visited path 
        Queue<List<T[]>> queue = new LinkedList<>();
        
        //Initialization
        List<T[]> initialPath = new ArrayList<>();
        initialPath.add(source);
        
        //Store source into queue
        queue.offer(initialPath);
        
        //
        int noOfPath=0,stationCounter=0;
        
        while (!queue.isEmpty())
        {
            //Access the path visited before
            List<T[]> path = queue.poll();
            
            //Get the last vertex visited
            T[] last = path.get(path.size() - 1);

            //Check if reach destination of r not
            if (Arrays.equals(last, destination))
            {
                //iterates the path to check the no of station visited
                for(int i=0; i< path.size();i++)
                {
                    Integer [] temp =(Integer[]) path.get(i);
                    if(temp[3]==2)
                        stationCounter++;
                }
                
                if(stationCounter==3)
                {
                    //printPath(path);
                    noOfPath++;
                }

                //Reset the value for next path used
                stationCounter=0;
            }

            //if haven't reach the destination yet
            
            //Get the neighbour vertex list
            ArrayList<T[]> ngList = this.getNeighbours(last);
            
            //Check along the neighbours
            for (int i = 0; i < ngList.size(); i++)
            {
                //step into one of its neighbours
                //only if the neighbours vertex is not visited
                if (isNotVisited(ngList.get(i), path))
                {
                    //add the neighbours vertex into the path visited before
                    List<T[]> newPath = new ArrayList<>(path);
                    newPath.add(ngList.get(i));
                    
                    //Store the incompleted path into queue again
                    queue.offer(newPath);
                }
            }
        }
        
        return noOfPath;
    }
    
    public boolean isNotVisited (T[] v, List<T[]> path )
    {
        int size=path.size();
        
        for (int i=0; i<size; i++)
        {
            if(Arrays.equals(path.get(i),v))
                return false;       
        }
        return true;
    }
    
    public void printPath(List<T[]> path)
    {
        int size=path.size();
        
        for(T[] elements: path)
        {
            System.out.print(Arrays.toString(elements)+ " ");
        }
        System.out.println();
    }
            
            
}
