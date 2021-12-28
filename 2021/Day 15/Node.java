public class Node 
{
    private int[] location;
    private long weight;

    public Node(int x, int y, long w)
    {
        location = new int[2];
        location[0] = x;
        location[1] = y;
        weight = w;
    }

    public int[] getLocation()
    {
        return location;
    }

    public long getWeight()
    {
        return weight;
    }

    public String toString()
    {
        return "X: " + location[0] + "\nY: " + location[1] +"\nWeight: " + weight;
    }

    public void setWeight(long w)
    {
        weight = w;
    }
}
