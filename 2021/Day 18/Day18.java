import java.util.*;
import java.io.*;

public class Day18 
{
    public static void main(String[] args) 
    {
        ArrayList<String> input = new ArrayList<>();
        // Part A
        // ArrayDeque<SnailNumber> numbers = new ArrayDeque<>();

        // Part B
        ArrayList<SnailNumber> numbers = new ArrayList<>();
        
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
        for (String line : input)
        {
            SnailNumber sn = parseSnailNumber(line);
            numbers.add(sn);
        }

        // Part A

        // while (numbers.size() > 1)
        // {
        //     numbers.push(numbers.poll().add(numbers.poll()));
        // }
        // System.out.println(numbers.poll().getMagnitude());

        // Part B

        int maxMagnitude = -1;
        for (int i = 0; i < numbers.size(); i++)
        {
            for (int j = 0; j < numbers.size(); j++)
            {
                if (i == j) 
                {
                    continue;
                }
                SnailNumber firstLeft = numbers.get(i).clone();
                SnailNumber firstRight = numbers.get(j).clone();
                SnailNumber secondLeft = numbers.get(i).clone();
                SnailNumber secondRight = numbers.get(j).clone();
                int one = firstLeft.add(firstRight).getMagnitude();
                int two = secondLeft.add(secondRight).getMagnitude();
                int max = Math.max(one,two);
                maxMagnitude = Math.max(max, maxMagnitude);
            }
        }
        System.out.println(maxMagnitude);
    }

    private static SnailNumber parseSnailNumber(String s)
    {
        if (s.length() == 1)
        {
            return new SnailNumber(Integer.parseInt(s));
        }
        int level = 0;
        int splitAt = -1;
        for (int i = 0; i < s.length() && splitAt == -1; i++)
        {
            if (s.charAt(i) == '[')
            {
                level ++;
            }
            else if (s.charAt(i) == ']')
            {
                level--;
            }
            else if (s.charAt(i) == ',')
            {
                if (level == 1)
                {
                    splitAt = i;
                }
            }
        }
        return new SnailNumber(parseSnailNumber(s.substring(1,splitAt)), parseSnailNumber(s.substring(splitAt + 1, s.length() - 1)));
    }
}
