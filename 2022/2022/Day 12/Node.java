public class Node 
{
    public int[] location;
    public int[][] validMoves;
    public long weight;

    public Node(int x, int y, long w, int moveSize)
    {
        validMoves = new int[moveSize][2];
        location = new int[2];
        location[0] = x;
        location[1] = y;
        weight = w;
    }

    public String toString()
    {
        return "X: " + location[0] + "\nY: " + location[1] +"\nWeight: " + weight;
    }

}
