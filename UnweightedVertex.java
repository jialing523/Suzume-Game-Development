
public class UnweightedVertex <T extends Comparable<T>>
{
    T dots,x,y,mPieces;
    T [] vertexInfo;
    int indeg,outdeg;
    UnweightedVertex <T> nextVertex;
    UnweightedEdge <T> firstEdge;
    
    public UnweightedVertex()
    {
        this.dots=null;
        this.x=null;
        this.y=null;
        this.mPieces=null;
        this.vertexInfo=null;
        indeg=0;
        outdeg=0;
        nextVertex=null;
        firstEdge = null;
    }
    
    public UnweightedVertex(T [] vertexInfo, UnweightedVertex <T> nextVertex)
    {
        this.mPieces=vertexInfo[0];
        this.x=vertexInfo[1];
        this.y=vertexInfo[2];
        this.dots=vertexInfo[3];
        this.vertexInfo=vertexInfo;
        indeg=0;
        outdeg=0;
        firstEdge=null;
    }
}
