public class NodeComparator implements java.util.Comparator<Node>
{
    public int compare(Node o1, Node o2)
    {
        if( o1.getWeight() < o2.getWeight() )
		{
			return -1;
		}
		
		if( o1.getWeight() > o2.getWeight() )
		{
			return 1;
		}
        
        return 0;
    }
}
