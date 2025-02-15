import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;

public class UnweightedGraph <T extends Comparable<T>>
{
    UnweightedVertex<T> head;
    int size;
    int numOfPaths;
    int stationCount;
    Map<T[], Integer> distance;
    
    public UnweightedGraph()
    {
        head = null;
        size=0;
        stationCount=0;
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
    
    public UnweightedVertex<T> getVertex(T[] v)
    {
        if(!hasVertex(v))
            return null;
        if(head==null)
            return null;
        
        UnweightedVertex<T> temp = head;
        while(temp!=null)
        {
            if(Arrays.equals(temp.vertexInfo, v))
                    return temp;
            temp=temp.nextVertex;
        }
        return null;
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
                System.out.print("["+Arrays.toString(currentEdge.toVertex.vertexInfo)+"]  ");
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

    
    public boolean removeAllEdges(T[] v)
    {
        if(head == null)
            return false;
        if(!hasVertex(v))
            return false;
        if(this.getIndeg(v)==0&&this.getOutdeg(v)==0)
            return false;
        
        UnweightedVertex<T> temp = head;
        while(temp!=null)
        {
            if(Arrays.equals(temp.vertexInfo, v))
            {
                UnweightedEdge<T> currentEdge = temp.firstEdge;
                while(currentEdge!=null)
                {
                    T[] destination = currentEdge.toVertex.vertexInfo;
                    this.removeEdge(temp.vertexInfo, destination);
                    this.removeEdge(destination,temp.vertexInfo);
                    
                    currentEdge=currentEdge.nextEdge;
                }
                return true;
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
    
    // breadth first search
   public int findPaths(T[] source, T[] destination, T station,int requiredOcc)
    {
        List<List<T[]>> allPaths =new ArrayList<>();
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
            List<T[]> currentPath = queue.poll();

            //Get the last vertex visited
            T[] last = currentPath.get(currentPath.size() - 1);

            //Check if reach destination of r not
            if (Arrays.equals(last, destination)&&countOccurrences(currentPath)==requiredOcc)
            {
                allPaths.add(new ArrayList<>(currentPath));
                noOfPath++;}

                if(countOccurrences(currentPath)>requiredOcc) {
                continue;  // skip exploring this path further
                }
                //iterates the path to check the no of station visited


            //if haven't reach the destination yet

            //Get the neighbour vertex list
            ArrayList<T[]> ngList = this.getNeighbours(last);

            //Check along the neighbours
            for (int i = 0; i < ngList.size(); i++)
            {
                //step into one of its neighbours
                //only if the neighbours vertex is not visited
                if (isNotVisited(ngList.get(i), currentPath))
                {
                    //add the neighbours vertex into the path visited before
                    List<T[]> newPath = new ArrayList<>(currentPath);
                    newPath.add(ngList.get(i));

                    //Store the incompleted path into queue again
                    queue.offer(newPath);
                }
            }
        }
        //System.out.println(noOfPath);

        return noOfPath;
    }
    
    private int countOccurrences(List<T[]> path) {
        int count = 0;
        for (T[] vertex : path) {
            if ((Integer)vertex[3] == 2) {
                count++;
            }
        }
        return count;
    }
    
    // Depth first search for finding possible paths
       public int printAllPathsUntil(T[] current, T[] destination,List<T[]> localPathList)
    {               
                                                                                                                                                                            
        if(Arrays.equals(current,destination))
        {                                                                

            if(stationCount==4)
            numOfPaths++;                                                                                 

        } 
      

        if(stationCount<=4)
        {
            ArrayList<T[]> ngList = this.getNeighbours(current);                                                   
            for(int i=0;i<ngList.size();i++)
            {                                                                      
                if(isNotVisited(ngList.get(i),localPathList))
                {                                                     
                    localPathList.add(ngList.get(i));  
                    if((Integer)ngList.get(i)[3]==2)
                        stationCount++;
                    printAllPathsUntil(ngList.get(i),destination,localPathList);                                   
                    localPathList.remove(ngList.get(i));
                    if((Integer)ngList.get(i)[3]==2)
                        stationCount--;
                }                                                                                                  
            }
        }           
        return numOfPaths;                                                                         
    }
 
  public void printAllPaths(T[]source,T[]destination){                                                     
         List<T[]> paths=new ArrayList<>();                                                                         
         paths.add(source);                                                                                         
         System.out.println("All possible paths: "+printAllPathsUntil(source,destination,paths));                   
                                                                                                                    
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
    
    
    public int getNumOfVertex()
    {
        UnweightedVertex<T> temp = this.head;
        int size=0;
        while(temp!=null)
        {
            size++;
            temp=temp.nextVertex;
        }
        return size;
    }
    
    
    // SHORTEST PATH
        public List<List<T[]>> findShortestPaths(T[] source, T[] destination, T station,int requiredOcc)
    {
        List<List<T[]>> allPaths =new ArrayList<>();
        //Store visited path
        Queue<List<T[]>> queue = new LinkedList<>();
        distance=new HashMap<>();

        //Initialization
        List<T[]> initialPath = new ArrayList<>();
        initialPath.add(source);
        distance.put(source,0);        // start at source
        //Store source into queue
        queue.offer(initialPath);

        //
        int noOfPath=0,stationCounter=0;
         int shortestDistance=0;
        boolean foundRequiredOccurrences = true;

        while (!queue.isEmpty()) {
            //Access the path visited before
            List<T[]> currentPath = queue.poll();

            //Get the last vertex visited
            T[] last = currentPath.get(currentPath.size() - 1);

            //Check if reach destination of r not
            if (Arrays.equals(last, destination) && countOccurrences(currentPath) == requiredOcc) {
                if (foundRequiredOccurrences == true) {
                    allPaths.add(new ArrayList<>(currentPath));
                    noOfPath++;
                    shortestDistance = distance.get(last);
                    foundRequiredOccurrences = false;
                } else {
                    if (distance.get(last) <= shortestDistance) {
                        allPaths.add(new ArrayList<>(currentPath));
                        noOfPath++;
                    }


                }       }

                if (countOccurrences(currentPath) > requiredOcc) {
                    continue;  // skip exploring this path further
                }
                //iterates the path to check the no of station visited


                //if haven't reach the destination yet

                //Get the neighbour vertex list
                ArrayList<T[]> ngList = this.getNeighbours(last);

                //Check along the neighbours
                for (int i = 0; i < ngList.size(); i++) {
                    //step into one of its neighbours
                    //only if the neighbours vertex is not visited
                    if (isNotVisited(ngList.get(i), currentPath)) {
                        //add the neighbours vertex into the path visited before
                        List<T[]> newPath = new ArrayList<>(currentPath);
                        newPath.add(ngList.get(i));
                        distance.put(ngList.get(i), distance.get(last) + 1);

                        //Store the incompleted path into queue again
                        queue.offer(newPath);
                    }
                }

            //System.out.println(noOfPath);

        }return allPaths;
    }             
            
}
