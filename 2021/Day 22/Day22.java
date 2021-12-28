import java.io.*;
import java.util.*;

public class Day22
{
    private static int[][][] grid = new int[101][101][101]; 
    private static ArrayList<String> x = new ArrayList<>();
    private static ArrayList<String> y = new ArrayList<>();
    private static ArrayList<String> z = new ArrayList<>();
    private static ArrayList<String> status = new ArrayList<>(); 
    public static void main(String[] args) 
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, " ");
                status.add(st.nextToken());
                StringTokenizer stok = new StringTokenizer(st.nextToken(), ",");    
                x.add(stok.nextToken());
                y.add(stok.nextToken());
                z.add(stok.nextToken());
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(solvePartA());
    }

    private static boolean inRange(int i, int j, int k)
    {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && k >=0 && k < grid[0][0].length;
    }

    private static long solvePartA()
    {
        for (int i = 0; i < status.size(); i++)
        {
            StringTokenizer stX = new StringTokenizer(x.get(i), "..");
            StringTokenizer stY = new StringTokenizer(y.get(i), "..");
            StringTokenizer stZ = new StringTokenizer(z.get(i), "..");
            int x1 = Integer.parseInt(stX.nextToken().substring(2));
            int x2 = Integer.parseInt(stX.nextToken());
            int xStart = Math.min(x1,x2);
            int xEnd = Math.max(x1,x2);
            int y1 = Integer.parseInt(stY.nextToken().substring(2));
            int y2 = Integer.parseInt(stY.nextToken());
            int yStart = Math.min(y1,y2);
            int yEnd = Math.max(y1,y2);
            int z1 = Integer.parseInt(stZ.nextToken().substring(2));
            int z2 = Integer.parseInt(stZ.nextToken());
            int zStart = Math.min(z1,z2);
            int zEnd = Math.max(z1,z2);
            if ((xStart < -50 && xEnd < -50) || (xStart > 50 && xEnd > 50) || (yStart < -50 && yEnd < -50) || (yStart > 50 && yEnd > 50) || 
            (zStart < -50 && zEnd < -50) || (zStart > 50 && zEnd > 50))
            {
                continue;
            }
            else if (xStart < -50 && xEnd > 50)
            {
                xStart = -50;
                xEnd = 50;
            }
            if (yStart < -50 && yEnd > 50)
            {
                yStart = -50;
                yEnd = 50;
            }
            if (zStart < -50 && zEnd > 50)
            {
                zStart = -50;
                zEnd = 50;
            }
            if (status.get(i).equals("on"))
            {
                for (int a = xStart; a <= xEnd; a++)
                {
                    for (int b = yStart; b <= yEnd; b++)
                    {
                        for (int c = zStart; c <= zEnd; c++)
                        {
                            if (inRange(a + 50, b + 50, c + 50))
                            {
                                grid[a + 50][b + 50][c + 50] = 1;
                            }
                        }
                    }
                }
            }
            else
            {
                for (int a = xStart; a <= xEnd; a++)
                {
                    for (int b = yStart; b <= yEnd; b++)
                    {
                        for (int c = zStart; c <= zEnd; c++)
                        {
                            if (inRange(a + 50, b + 50, c + 50))
                            {
                                grid[a + 50][b + 50][c + 50] = 0;
                            }
                        }
                    }
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                for (int k = 0; k < grid[i][j].length; k++)
                {
                    if (grid[i][j][k] == 1)
                    {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}