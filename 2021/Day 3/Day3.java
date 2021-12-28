import java.io.*;
import java.util.*;

public class Day3
{
    public static void main(String[] args) 
    {
        List<String> inputs = new ArrayList<String>();
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

        // Part A

        String gammaAnswer, epsilonAnswer;
        gammaAnswer = epsilonAnswer = "";
        int zeroCount, oneCount;
        zeroCount = oneCount = 0;
        for (int i = 0; i < inputs.get(1).length(); i++)
        {
            for (int j = 0; j < inputs.size(); j++)
            {
                if (inputs.get(j).charAt(i) == '0')
                {
                    zeroCount++;
                }
                else
                {
                    oneCount++;
                }
            }
        
            if (zeroCount > oneCount)
            {
                gammaAnswer += "0";
                epsilonAnswer += "1";
            }
            else
            {
                gammaAnswer += "1";
                epsilonAnswer += "0";
            }
            zeroCount = oneCount = 0;
        }
        System.out.println(gammaAnswer + "\n" + epsilonAnswer);
        System.out.println(Integer.parseInt(gammaAnswer, 2) * Integer.parseInt(epsilonAnswer, 2));

        //Part B

        // List<String> oxygenInput = new ArrayList<String>();
        // List<String> carbonInput = new ArrayList<String>();

        // for (String s : inputs)
        // {
        //     oxygenInput.add(s);
        //     carbonInput.add(s);
        // }

        // int count = 0;
        // while (oxygenInput.size() != 1)
        // {
        //     int zeroCount = 0, oneCount = 0, index = count;
        //     for (int i = 0; i < oxygenInput.size(); i++)
        //     {
        //         if (oxygenInput.get(i).charAt(index) == '0')
        //         {
        //             zeroCount++;
        //         }
        //         else
        //         {
        //             oneCount++;
        //         }
        //     }
        //     if (zeroCount > oneCount)
        //     {
        //         oxygenInput.removeIf(s -> (s.charAt(index) != '0'));
        //     }
        //     else if (oneCount > zeroCount)
        //     {
        //         oxygenInput.removeIf(s -> (s.charAt(index) != '1'));
        //     }
        //     else
        //     {
        //         oxygenInput.removeIf(s -> (s.charAt(index) != '1'));
        //     }
        //     count++;
        // }
        // count = 0;
        // while (carbonInput.size() != 1)
        // {
        //     int zeroCount = 0, oneCount = 0, index = count;
        //     for (int i = 0; i < carbonInput.size(); i++)
        //     {
        //         if (carbonInput.get(i).charAt(index) == '0')
        //         {
        //             zeroCount++;
        //         }
        //         else
        //         {
        //             oneCount++;
        //         }
        //     }
        //     if (zeroCount > oneCount)
        //     {
        //         carbonInput.removeIf(s -> (s.charAt(index) != '1'));
        //     }
        //     else if (oneCount > zeroCount)
        //     {
        //         carbonInput.removeIf(s -> (s.charAt(index) != '0'));
        //     }
        //     else
        //     {
        //         carbonInput.removeIf(s -> (s.charAt(index) != '0'));
        //     }
        //     count++;
        // }    
        // System.out.println(oxygenInput.get(0));
        // System.out.println(carbonInput.get(0));
        // System.out.println(Integer.parseInt(oxygenInput.get(0), 2) * Integer.parseInt(carbonInput.get(0), 2));
    }
}