import java.util.*;
import java.io.*;

public class Day13
{
    public static void main(String[] args) 
    {
        char[][] paper;
        ArrayList<String> instructions = new ArrayList<>();
        ArrayList<Integer> xCoords = new ArrayList<>();
        ArrayList<Integer> yCoords = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null && !line.startsWith("fold") && !line.isEmpty())
            {
                StringTokenizer st = new StringTokenizer(line, ",");
                xCoords.add(Integer.parseInt(st.nextToken()));
                yCoords.add(Integer.parseInt(st.nextToken()));
            }
            while ((line = br.readLine()) != null)
            {
                instructions.add(line.substring(11));
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        int xMaxVal, yMaxVal;
        xMaxVal = yMaxVal = 0;
        for (int val : xCoords)
        {
            xMaxVal = Math.max(xMaxVal, val);
        }
        for (int val : yCoords)
        {
            yMaxVal = Math.max(yMaxVal, val);
        }
        paper = new char[yMaxVal + 1][xMaxVal + 1];

        for (char[] c : paper)
        {
            Arrays.fill(c, '.');
        }
        for (int i = 0; i < xCoords.size(); i++)
        {
            paper[yCoords.get(i)][xCoords.get(i)] = '#';
        }

        for (String instruction : instructions)
        {
            int lineNumber = Integer.parseInt(instruction.substring(2));
            char[][] temp;
            if (instruction.startsWith("y"))
            {
                for (int i = lineNumber; i < paper.length; i++)
                {
                    for (int j = 0; j < paper[i].length; j++)
                    {
                        if (paper[i][j] == '#')
                        {
                            int distanceFromLine = i - lineNumber;
                            paper[i][j] = '.';
                            paper[lineNumber - distanceFromLine][j] = '#';
                        }
                    }
                }
                temp = new char[lineNumber][paper[0].length];
                for (int i = 0; i < temp.length; i++)
                {
                    for (int j = 0; j < temp[i].length; j++)
                    {
                        temp[i][j] = paper[i][j];
                    }
                }
                paper = temp;
            }
            else if (instruction.startsWith("x"))
            {
                for (int i = 0; i < paper.length; i++)
                {
                    for (int j = lineNumber; j < paper[i].length; j++)
                    {
                        if (paper[i][j] == '#')
                        {
                            int distanceFromLine = j - lineNumber;
                            paper[i][j] = '.';
                            paper[i][lineNumber - distanceFromLine] = '#';
                        }
                    }
                }
                temp = new char[paper.length][lineNumber];
                for (int i = 0; i < temp.length; i++)
                {
                    for (int j = 0; j < temp[i].length; j++)
                    {
                        temp[i][j] = paper[i][j];
                    }
                }
                paper = temp;
            }
        }
        // Part A is "count" after first instruction
        // Part B is reading the code (the code is the letters printed to the command line)
        // int count = 0;
        for (int i = 0; i < paper.length; i++)
        {
            for (int j = 0; j < paper[i].length; j++)
            {
                System.out.print(paper[i][j]);
                // if (paper[i][j] == '#')
                // {
                //     count++;
                // }
            }
            System.out.println();
        }
        // System.out.println(count);
    }
}