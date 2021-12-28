import java.util.*;
import java.io.*;

public class Day15
{
    private static int[][] grid = new int[0][0];

    // Part A and Part B run using the same code
    // All you need to do is adjust the "part2" boolean value on line 37
    public static void main(String[] args) 
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            ArrayList<String> inputs = new ArrayList<>();
            while ((line = br.readLine()) != null)
            {
                inputs.add(line);
            }
            grid = new int[inputs.size()][inputs.get(0).length()];
            for (int i = 0; i < inputs.size(); i++)
            {
                for (int j = 0; j < inputs.get(i).length(); j++)
                {
                    grid[i][j] = Integer.parseInt(String.valueOf(inputs.get(i).charAt(j)));
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        // CHANGE ME!!!
        boolean part2 = true;
        if (part2)
        {
            grid = makeBigGrid();
        }
        PriorityQueue<Node> weight = new PriorityQueue<>(new NodeComparator());
        Node[][] travelCost = new Node[grid.length][grid[0].length];
        int[][][] parent = new int[grid.length][grid[0].length][2];
        for (int i = 0; i < travelCost.length; i++)
        {
            for (int j = 0; j < travelCost[i].length; j++)
            {
                travelCost[i][j] = new Node(i, j , Long.MAX_VALUE);
            }
        }
        for (int i = 0; i < parent.length; i++)
        {
            for (int j = 0; j < parent[i].length; j++)
            {
                parent[i][j][0] = -1;
            }
        }
        parent[0][0][0] = 0;
        parent[0][0][1] = 0;
        travelCost[0][0] = new Node(0,0,0);
        for (int i = 0; i < travelCost.length; i++)
        {
            for (int j = 0; j < travelCost.length; j++)
            {
                weight.add(travelCost[i][j]);
            }
        }
        int[] toReach = new int[]{grid.length - 1, grid[0].length - 1};
        while (weight.size() > 0)
        {
            Node v = weight.poll();
            int[] vLocation = v.getLocation();
            if (parent[vLocation[0]][vLocation[1]][0] == -1) break;
            else if (Arrays.equals(vLocation, toReach)) break;
            int[][] moves = new int[][]{{0,1}, {1,0}, {-1,0}, {0, -1}};
            for (int[] move : moves)
            {
                int xCoord = vLocation[0];
                int yCoord = vLocation[1];
                if (inRange(xCoord + move[0], yCoord + move[1]))
                {
                    Node u = travelCost[xCoord + move[0]][yCoord + move[1]];
                    if (v.getWeight() + grid[xCoord + move[0]][yCoord + move[1]] < u.getWeight())
                    {
                        weight.remove(u);
                        u.setWeight(v.getWeight() + grid[xCoord + move[0]][yCoord + move[1]]);
                        weight.add(u);
                        travelCost[xCoord + move[0]][yCoord + move[1]] = u;
                        parent[xCoord + move[0]][yCoord + move[1]][0] = xCoord;
                        parent[xCoord + move[0]][yCoord + move[1]][1] = yCoord;
                    }
                }
            }
        }
        if (parent[grid.length - 1][grid[0].length - 1][0] != -1)
        {
            System.out.println(travelCost[grid.length - 1][grid[0].length - 1].getWeight());
        }
        else
        {
            System.out.println("seeing this message makes me sad");
        }
    }

    private static boolean inRange(int i, int j)
    {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

    private static int[][] makeBigGrid()
    {
        int[][] original = grid.clone();
        grid = new int[grid.length * 5][grid[0].length * 5];
        for (int i = 0; i < original.length; i++)
        {
            for (int j = 0; j < original[0].length; j++)
            {
                grid[i][j] = original[i][j];
            }
        }

        for (int x = 0; x < 5; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                for (int i = 0; i < original.length; i++)
                {
                    for (int j = 0; j < original[i].length; j++)
                    {
                        grid[i + (original.length * x)][j + (original[0].length * y)] = (original[i][j] + x + y) % 10;
                        if ((original[i][j] + x + y) >= 10)
                        {
                            grid[i + (original.length * x)][j + (original[0].length * y)]++;
                        }
                    }
                }
            }
        }
        return grid;
    }
}