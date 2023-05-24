public class UnweightedEdge <T extends Comparable<T>>
{
    UnweightedVertex <T> toVertex;
    UnweightedEdge <T> nextEdge;
    
    public UnweightedEdge()
    {
        this.toVertex=null;
        this.nextEdge=null;
    }
    
    public UnweightedEdge(UnweightedVertex<T> destination, UnweightedEdge <T> a)
    {
        this.toVertex=destination;
        this.nextEdge=a;
    }
}
