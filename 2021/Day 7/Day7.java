import java.io.*;
import java.util.*;

public class Day7
{
    public static void main(String[] args) 
    {
        String line = "";
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            line = br.readLine();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        StringTokenizer st = new StringTokenizer(line, ",");
        ArrayList<Integer> positions = new ArrayList<>();
        int largest = -1;
        while (st.hasMoreTokens())
        {
            int number = Integer.parseInt(st.nextToken());
            positions.add(number);
            if (number > largest)
            {
                largest = number;
            }
        }
        
        //Part A

        // int[] fuelNeeded = new int[largest + 1];
        // for (int i = 0; i < fuelNeeded.length; i++)
        // {
        //     for (int position : positions)
        //     {
        //         fuelNeeded[i] += Math.abs(position - i);
        //     }
        // }
        // int smallest = fuelNeeded[0];
        // for (int i = 0; i < fuelNeeded.length; i++)
        // {
        //     if (smallest > fuelNeeded[i])
        //     {
        //         smallest = fuelNeeded[i];
        //     }
        // }
        // System.out.println(smallest);

        //Part B

        int[] fuelNeeded = new int[largest + 1];
        for (int i = 0; i < fuelNeeded.length; i++)
        {
            for (int position : positions)
            {
                int distance = Math.abs(position - i);
                for (int j = 0; j <= distance; j++)
                {
                    fuelNeeded[i] += j;
                }
            }
        }
        int smallest = fuelNeeded[0];
        for (int i = 0; i < fuelNeeded.length; i++)
        {
            if (smallest > fuelNeeded[i])
            {
                smallest = fuelNeeded[i];
            }
        }
        System.out.println(smallest);
    }
}