import java.io.*;
import java.util.*;

public class Day6
{
    public static void main(String[] args) 
    {
        //Part A - slower solution

        // ArrayList<Integer> fish = new ArrayList<Integer>();
        // try
        // {
        //     BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //     String line = br.readLine();
        //     StringTokenizer st = new StringTokenizer(line, ",");
        //     while (st.hasMoreTokens())
        //     {
        //         fish.add(Integer.parseInt(st.nextToken()));
        //     }
        //     br.close();
        // }
        // catch (Exception e)
        // {
        //     e.printStackTrace();
        //     System.exit(0);
        // }
        // for (int i = 0; i < 256; i++)
        // {
        //     for (int j = 0; j < fish.size(); j++)
        //     {
        //         if (fish.get(j) == 0)
        //         {
        //             fish.add(9);
        //             fish.set(j, 7);
        //         }
        //         fish.set(j, fish.get(j) - 1);
        //     }
        // }
        // System.out.println(fish.size());

        //Part B - faster solution
        long[] fish = new long[9];
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens())
            {
                fish[Integer.parseInt(st.nextToken())]++;
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        for (int i = 0; i < 256; i++)
        {
            long temp8 = fish[8];
            long temp7 = fish[7];
            long temp6 = fish[6];
            long temp5 = fish[5];
            long temp4 = fish[4];
            long temp3 = fish[3];
            long temp2 = fish[2];
            long temp1 = fish[1];
            long temp0 = fish[0];
            fish[8] = temp0;
            fish[7] = temp8;
            fish[6] = temp7 + temp0;
            fish[5] = temp6;
            fish[4] = temp5;
            fish[3] = temp4;
            fish[2] = temp3;
            fish[1] = temp2;
            fish[0] = temp1;
        }
        long answer = 0;
        for (int i = 0; i < fish.length; i++)
        {
            answer += fish[i];
        }
        System.out.println(answer);
    }
}