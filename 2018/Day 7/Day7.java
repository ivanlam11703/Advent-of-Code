import java.io.*;
import java.util.*;

public class Day7
{
    public static void main(String[] args) 
    {
        HashMap<Character, String> instructions = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++)
        {
            instructions.put(ch, "");
        }
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                String newInstruction = instructions.get(line.charAt(36)) + line.charAt(5);
                char[] chars = newInstruction.toCharArray();
                Arrays.sort(chars);
                newInstruction = new String(chars);
                instructions.replace(line.charAt(36), newInstruction);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        //======================================\\
        //               Part A                 \\
        //======================================\\

        // String answer = "";
        // while (!instructions.isEmpty())
        // {
        //     for (Character ch : instructions.keySet())
        //     {
        //         if (instructions.get(ch).isEmpty())
        //         {
        //             answer += ch;
        //             instructions.remove(ch);
        //             for (Character c : instructions.keySet())
        //             {
        //                 if (instructions.get(c).contains(String.valueOf(ch)))
        //                 {
        //                     String newInstruction = instructions.get(c).replace(String.valueOf(ch), "");
        //                     instructions.replace(c, newInstruction);
        //                 }
        //             }
        //             break;
        //         }
        //     }
        // }
        // System.out.println(answer);

        int workTime = 0;
        int[] timeUntilComplete = new int[5];
        char[] instructionsToRemove = new char[5];
        while (!instructions.isEmpty())
        {
            for (int i = 0; i < timeUntilComplete.length; i++)
            {
                if (timeUntilComplete[i] == 0)
                {
                    for (Character ch : instructions.keySet())
                    {
                        if (instructions.get(ch).isEmpty())
                        {
                            timeUntilComplete[i] = ch - 4;
                            instructionsToRemove[i] = ch;
                            instructions.remove(ch);
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < timeUntilComplete.length; i++)
            {
                if (timeUntilComplete[i] != 0)
                {
                    timeUntilComplete[i]--;
                    if (timeUntilComplete[i] == 0)
                    {
                        char ch = instructionsToRemove[i];
                        for (Character c : instructions.keySet())
                        {
                            if (instructions.get(c).contains(String.valueOf(ch)))
                            {
                                String newInstruction = instructions.get(c).replace(String.valueOf(ch), "");
                                instructions.replace(c, newInstruction);
                            }
                        }
                    }
                }
            }
            workTime++;
        }
        boolean completed = false;
        while (!completed)
        {
            completed = true;
            for (int i = 0; i < timeUntilComplete.length; i++)
            {
                if (timeUntilComplete[i] != 0)
                {
                    timeUntilComplete[i]--;
                    completed = false;
                    workTime++;
                    break;
                }
            }
        }
        System.out.println(workTime);
    }
}