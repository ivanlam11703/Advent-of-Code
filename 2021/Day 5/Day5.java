import java.io.*;
import java.util.*;

public class Day5
{
    public static void main(String[] args) 
    {
        //Part A and Part B are together in this 

        ArrayList<Integer> x1 = new ArrayList<>();
        ArrayList<Integer> x2 = new ArrayList<>();
        ArrayList<Integer> y1 = new ArrayList<>();
        ArrayList<Integer> y2 = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                line = line.replace(" -> ", ",");
                StringTokenizer st = new StringTokenizer(line, ",");
                while (st.hasMoreTokens())
                {
                    int firstx = Integer.parseInt(st.nextToken());
                    int firsty = Integer.parseInt(st.nextToken());
                    int secondx = Integer.parseInt(st.nextToken());
                    int secondy = Integer.parseInt(st.nextToken());

                    x1.add(firstx);
                    x2.add(secondx);
                    y1.add(firsty);
                    y2.add(secondy);
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        int[][] grid = new int[1000][1000];
        for (int i = 0; i < x1.size(); i++)
        {
            if (x1.get(i).intValue() == x2.get(i).intValue())
            {
                int larger = Math.max(y2.get(i).intValue(),y1.get(i).intValue());
                int smaller = Math.min(y2.get(i).intValue(), y1.get(i).intValue());
                for (int y = smaller; y <= larger; y++)
                {
                    grid[x1.get(i)][y]++;
                }
            }
            else if (y2.get(i).intValue() == y1.get(i).intValue())
            {
                int larger = Math.max(x1.get(i).intValue(), x2.get(i).intValue());
                int smaller = Math.min(x1.get(i).intValue(), x2.get(i).intValue());
                for (int x = smaller; x <= larger; x++)
                {
                    grid[x][y2.get(i)]++;
                }

            }
            else
            {
                int slope = (y2.get(i) - y1.get(i)) / (x2.get(i) - x1.get(i));
                if (slope == 1)
                {
                    if (x1.get(i).intValue() < x2.get(i).intValue())
                    {
                        int y = y1.get(i).intValue();
                        for (int x = x1.get(i).intValue(); x <= x2.get(i).intValue(); x++)
                        {
                            grid[x][y]++;
                            y++;
                        }
                    }
                    else if (x2.get(i).intValue() < x1.get(i).intValue())
                    {
                        int y = y2.get(i).intValue();
                        for (int x = x2.get(i).intValue(); x <= x1.get(i).intValue(); x++)
                        {
                            grid[x][y]++;
                            y++;
                        }
                    }
                }
                else if (slope == -1)
                {
                    if (x1.get(i).intValue() < x2.get(i).intValue())
                    {
                        int y = y1.get(i).intValue();
                        for (int x = x1.get(i).intValue(); x <= x2.get(i).intValue(); x++)
                        {
                            grid[x][y]++;
                            y--;
                        }
                    }
                    else if (x2.get(i).intValue() < x1.get(i).intValue())
                    {
                        int y = y2.get(i).intValue();
                        for (int x = x2.get(i).intValue(); x <= x1.get(i).intValue(); x++)
                        {
                            grid[x][y]++;
                            y--;
                        }
                    }
                }
            }
        }
        int answer = 0;
        for (int y = 0; y < grid.length; y++)
        {
            for (int x = 0; x < grid[0].length; x++)
            {
                if (grid[y][x] >= 2)
                {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}