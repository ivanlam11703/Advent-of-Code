import java.io.*;
import java.util.*;

public class Day11
{
    static Octopus[][] o = new Octopus[0][0];
    public static void main(String[] args) 
    {
        ArrayList<String> inputs = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                inputs.add(line);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        //Part A and B together

        o = new Octopus[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++)
        {
            for (int j = 0; j < inputs.get(i).length(); j++)
            {
                o[i][j] = new Octopus(Integer.parseInt(String.valueOf(inputs.get(i).charAt(j))));
            }
        }
        int[][] moves = new int[][]{{0,1}, {1,1}, {1,0}, {1,-1}, {-1, 1}, {-1,-1}, {0,-1}, {-1,0}};
        int count = 0;
        int steps = 0;
        boolean synced = false;
        while (!synced)
        {
            steps++;
            for (int i = 0; i < o.length; i++)
            {
                for (int j = 0; j < o[i].length; j++)
                {
                    // System.out.print(o[i][j].getEnergy() + " ");
                    o[i][j].resetFlash();
                    o[i][j].increaseEnergy();
                }
            }
            boolean flash = true;
            while (flash)
            {
                flash = false;
                for (int i = 0; i < o.length; i++)
                {
                    for (int j = 0; j < o[i].length; j++)
                    {
                        if ((o[i][j].getEnergy() > 9) && (!o[i][j].hasFlashed()))
                        {
                            // System.out.println(o[i][j].getEnergy());
                            o[i][j].flash();
                            flash = true;
                            count++;
                            for (int[] move : moves)
                            {
                                if (inRange(i + move[0], j + move[1]))
                                {
                                    o[i + move[0]][j + move[1]].increaseEnergy();
                                }
                            }
                        }
                    }
                }
            }
            synced = true;
            for (int i = 0; i < o.length; i++)
            {
                for (int j = 0; j < o[i].length; j++)
                {
                    if (!o[i][j].hasFlashed())
                    {
                        synced = false;
                    }
                }
            }
        }
        //Part A answer
        System.out.println(count);

        //Part B answer
        System.out.println(steps);
    }

    private static boolean inRange(int x, int y)
    {
        return x >= 0 && x < o[0].length && y >= 0 && y < o.length;
    }
}