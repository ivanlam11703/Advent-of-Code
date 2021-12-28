import java.io.*;
import java.util.*;

public class Day25
{
    private static char[][] grid;
    private static boolean cucumberMoved;
    public static void main(String[] args) 
    {
        grid = new char[0][0];
        cucumberMoved = true;
        ArrayList<String> input = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                input.add(line);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        grid = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++)
        {
            for (int j = 0; j < input.get(i).length(); j++)
            {
                grid[i][j] = input.get(i).charAt(j);
            }
        }
        
        int counter = 0;
        while (cucumberMoved)
        {
            cucumberMoved = false;
            stepEast();
            stepSouth();
            counter++;
            // for (int i = 0; i < grid.length; i++)
            // {
            //     for (int j = 0; j < grid[i].length; j++)
            //     {
            //         System.out.print(grid[i][j]);
            //     }
            //     System.out.println();
            // }
        }
        System.out.println(counter);
    }

    private static boolean inRange(int i, int j)
    {
        return i >=0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

    private static void stepEast()
    {
        char[][] temp = new char[grid.length][grid[0].length];
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[i].length; j++)
            {
                temp[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[i].length; j++)
            {
                if (grid[i][j] == '>')
                {
                    if (inRange(i, j + 1))
                    {
                        if (grid[i][j + 1] == '.')
                        {
                            cucumberMoved = true;
                            temp[i][j] = '.';
                            temp[i][j + 1] = '>';
                        }
                    }
                    else
                    {
                        if (grid[i][0] == '.')
                        {
                            cucumberMoved = true;
                            temp[i][j] = '.';
                            temp[i][0] = '>';
                        }
                    }
                }
            }
        }
        grid = temp;
    }

    private static void stepSouth()
    {
        char[][] temp = new char[grid.length][grid[0].length];
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[i].length; j++)
            {
                temp[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[i].length; j++)
            {
                if (grid[i][j] == 'v')
                {
                    if (inRange(i + 1, j))
                    {
                        if (grid[i + 1][j] == '.')
                        {
                            cucumberMoved = true;
                            temp[i][j] = '.';
                            temp[i + 1][j] = 'v';
                        }
                    }
                    else
                    {
                        if (grid[0][j] == '.')
                        {
                            cucumberMoved = true;
                            temp[i][j] = '.';
                            temp[0][j] = 'v';
                        }
                    }
                }
            }
        }
        grid = temp;
    }
}