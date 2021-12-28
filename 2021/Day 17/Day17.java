import java.util.*;
import java.io.*;

public class Day17
{
    private static int targetX1, targetX2, targetY1, targetY2, currentX, currentY, maxY, localMaxY;
    public static void main(String[] args) 
    {
        // Part A and Part B run with the same code

        targetX1 = targetX2 = targetY1 = targetY2 = currentX = currentY = 0;
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = br.readLine();
            br.close();
            line = line.substring(13);
            StringTokenizer st = new StringTokenizer(line, "..");
            targetX1 = Integer.parseInt(st.nextToken().substring(2));
            String x2y1 = st.nextToken();
            StringTokenizer stok = new StringTokenizer(x2y1, ",");
            targetX2 = Integer.parseInt(stok.nextToken());
            targetY1 = Integer.parseInt(stok.nextToken().substring(3));
            targetY2 = Integer.parseInt(st.nextToken());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // Part A is maxY (highest possible Y value that lands inside the target area)
        // Part B i count (total number of solutions that land inside the target area)
        int count = 0;
        maxY = localMaxY = -1;
        for (int i = -1000; i < 1000; i++)
        {
            for (int j = -1000; j < 1000; j++)
            {
                if (simulate(i,j))
                {
                    maxY = Math.max(localMaxY,maxY);
                    count++;
                }
                reset();
            }
        }
        System.out.println(maxY);
        System.out.println(count);
    }

    private static boolean simulate(int initialX, int initialY)
    {
        while (currentX < targetX2 && currentY > targetY1)
        {
            currentX += initialX;
            currentY += initialY;
            localMaxY = Math.max(localMaxY, currentY);
            if (currentX >= targetX1 && currentX <= targetX2 && currentY >= targetY1 && currentY <= targetY2)
            {
                return true;
            }
            if (initialX > 0)
            {
                initialX--;
                initialY--;
            }
            else
            {
                initialY--;
            }
        }
        return false;
    }

    private static void reset()
    {
        currentX = currentY = localMaxY = 0;
    }
}