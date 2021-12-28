import java.util.*;
import java.io.*;

public class Day20
{
    // Part A and B run with the same code
    // The only difference is the number of times that the enhance function is run

    private static String key = "";
    private static char[][] image = new char[100][100];
    private static int enhanceCounter;
    public static void main(String[] args) 
    {
        for (char[] c : image)
        {
            Arrays.fill(c,'.');
        }
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            key = br.readLine();
            br.readLine();
            String line = "";
            int i = 0;
            while ((line = br.readLine()) != null)
            {
                for (int j = 0; j < line.length(); j++)
                {
                    image[i][j] = line.charAt(j);
                }
                i++;
            }
            br.close();
        }   
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // Part A is running the enhance function twice
        // Part B is running the enhance function 50 times
        int counter = 0;
        for (int i = 0; i < 50; i++)
        {
            enhance();
        }
        for (int i = 0; i < image.length; i++)
        {
            for (int j = 0; j < image[i].length; j++)
            {
                if (image[i][j] == '#')
                {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    private static void enhance()
    {
        int[][] moves = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};
        char[][] temp = new char[image.length + 2][image[0].length + 2];
        for (char[] c : temp)
        {
            if (enhanceCounter % 2 == 0)
            {
                Arrays.fill(c, '.');
            }
            else
            {
                Arrays.fill(c, '#');
            }
        }
        for (int i = 0; i < image.length; i++)
        {
            for (int j = 0; j < image[i].length; j++)
            {
                temp[i + 1][j + 1] = image[i][j];
            }
        }
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[0].length; j++)
            {
                String tempCharNumber = "";
                for (int[] move : moves)
                {
                    tempCharNumber += inRange(i - 1 + move[0],j - 1 + move[1],image);
                }
                String tempBinaryNumber = "";
                for (Character c : tempCharNumber.toCharArray())
                {
                    if (c == '.')
                    {
                        tempBinaryNumber += '0';
                    }
                    else
                    {
                        tempBinaryNumber += '1';
                    }
                }
                temp[i][j] = key.charAt(Integer.parseInt(tempBinaryNumber,2));
            }
        }
        image = temp;
        enhanceCounter++;
    }

    private static char inRange(int i, int j, char[][] arr)
    {
        if (i >= 0 && i < arr.length && j >= 0 && j < arr[0].length)
        {
            return arr[i][j];
        }
        else
        {
            if (enhanceCounter % 2 == 0)
            {
                return '.';
            }
            else
            {
                return '#';
            }
        }
    }
} 