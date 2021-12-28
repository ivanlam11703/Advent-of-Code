import java.io.*;
import java.util.*;

public class Day8 
{
    public static void main(String[] args) 
    {
        //Part A

        // int count = 0;
        // try
        // {
        //     BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        //     String line = "";
        //     while ((line = br.readLine()) != null)
        //     {
        //         line = line.substring(line.indexOf("|") + 1);
        //         System.out.println(line);
        //         StringTokenizer st = new StringTokenizer(line);
        //         while (st.hasMoreTokens())
        //         {
        //             String digit = st.nextToken();
        //             if (digit.length() == 7 || digit.length() == 4 || digit.length() == 3 || digit.length() == 2)
        //             {
        //                 count++;
        //             }
        //         }
        //     }
        //     br.close();
        // }
        // catch(Exception e)
        // {
        //     e.printStackTrace();
        //     System.exit(0);
        // }
        // System.out.println(count);

        //Part B
        
        ArrayList<Integer> outputs = new ArrayList<>();
        ArrayDeque<String> toRemove = new ArrayDeque<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                ArrayList<String> potentialCombinations = new ArrayList<>();
                String output = line.substring(line.indexOf("|") + 2);
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens())
                {
                    String number = st.nextToken();
                    if (number.indexOf("|") != -1)
                    {
                        break;
                    }
                    potentialCombinations.add(number);
                }
                Map<Integer, String> key = new HashMap<>();
                String topLeftAndMiddle = "";
                String middle = "";
                String topLeft = "";
                while(potentialCombinations.size() > 0)
                {
                    for (String number : potentialCombinations)
                    {
                        if (number.length() == 2)
                        {
                            key.put(1, number);
                            toRemove.add(number);
                        }
                        else if (number.length() == 3)
                        {
                            key.put(7, number);
                            toRemove.add(number);
                        }
                        else if (number.length() == 4)
                        {
                            key.put(4, number);
                            toRemove.add(number);
                        }
                        else if (number.length() == 7)
                        {
                            key.put(8, number);
                            toRemove.add(number);
                        }
                    }
                    while (toRemove.size() > 0)
                    {
                        potentialCombinations.remove(toRemove.poll());
                    }
                    for (Character c : key.get(4).toCharArray())
                    {
                        String oneLetters = key.get(1);
                        if (oneLetters.indexOf(c) == -1)
                        {
                            topLeftAndMiddle += c;
                        }
                    }
                    for (String number : potentialCombinations)
                    {
                        if (number.length() == 5)
                        {
                            String requiredCharacters = key.get(1);
                            boolean hasRequiredCharacters = true;
                            for (Character c : requiredCharacters.toCharArray())
                            {
                                if (number.indexOf(c) == -1)
                                {
                                    hasRequiredCharacters = false;
                                }
                            }
                            if (hasRequiredCharacters)
                            {
                                key.put(3,number);
                                toRemove.add(number);
                                break;
                            }
                        }
                    }
                    potentialCombinations.remove(toRemove.poll());
                    ArrayList<String> potential = new ArrayList<>();
                    for (String number : potentialCombinations)
                    {
                        if (number.length() == 5)
                        {
                            potential.add(number);
                        }
                    }
                    for (Character c : topLeftAndMiddle.toCharArray())
                    {
                        if (potential.get(0).indexOf(c) != -1 && potential.get(1).indexOf(c) != -1)
                        {
                            middle = String.valueOf(c);
                        }
                        else
                        {
                            topLeft = String.valueOf(c);
                        }
                    }
                    for (String number : potentialCombinations)
                    {
                        if (number.length() == 5)
                        {
                            if (number.indexOf(topLeft) != -1)
                            {
                                key.put(5, number);
                                toRemove.add(number);
                            }
                            else
                            {
                                key.put(2, number);
                                toRemove.add(number);
                            }
                        }
                    }
                    while (toRemove.size() > 0)
                    {
                        potentialCombinations.remove(toRemove.poll());
                    }
                    for (String number : potentialCombinations)
                    {
                        if (number.length() == 6)
                        {
                            String requiredCharacters = key.get(3);
                            boolean hasRequiredCharacters = true;
                            {
                                for (Character c : requiredCharacters.toCharArray())
                                {
                                    if (number.indexOf(c) == -1)
                                    {
                                        hasRequiredCharacters = false;
                                        break;
                                    }
                                }
                                if (hasRequiredCharacters)
                                {
                                    key.put(9, number);
                                    toRemove.add(number);
                                    break;
                                }
                            }
                        }
                    }
                    potentialCombinations.remove(toRemove.poll());
                    for (String number : potentialCombinations)
                    {
                        if (number.length() == 6)
                        {
                            if (number.indexOf(middle) == -1)
                            {
                                key.put(0, number);
                                toRemove.add(number);
                            }
                            else
                            {
                                key.put(6, number);
                                toRemove.add(number);
                            }
                        }
                    }
                    while (toRemove.size() > 0)
                    {
                        potentialCombinations.remove(toRemove.poll());
                    }
                }
                StringTokenizer outputTokenizer = new StringTokenizer(output);
                String answer = "";
                while (outputTokenizer.hasMoreTokens())
                {
                    String number = outputTokenizer.nextToken();
                    if (number.length() == 2)
                    {
                        answer += 1;
                    }
                    else if (number.length() == 3)
                    {
                        answer += 7;
                    }
                    else if (number.length() == 4)
                    {
                        answer += 4;
                    }
                    else if (number.length() == 7)
                    {
                        answer += 8;
                    }
                    else if (number.length() == 5)
                    {
                        boolean hasRequiredCharacters = true;
                        for (Character c : key.get(2).toCharArray())
                        {
                            if (number.indexOf(c) == -1)
                            {
                                hasRequiredCharacters = false;
                                break;
                            }
                        }
                        if (hasRequiredCharacters)
                        {
                            answer += 2;
                            continue;
                        }
                        hasRequiredCharacters = true;
                        for (Character c : key.get(3).toCharArray())
                        {
                            if (number.indexOf(c) == -1)
                            {
                                hasRequiredCharacters = false;
                            }
                        }
                        if (hasRequiredCharacters)
                        {
                            answer += 3;
                            continue;
                        }
                        hasRequiredCharacters = true;
                        for (Character c : key.get(5).toCharArray())
                        {
                            if (number.indexOf(c) == -1)
                            {
                                hasRequiredCharacters = false;
                                break;
                            }
                        }
                        if (hasRequiredCharacters)
                        {
                            answer += 5;
                            continue;
                        }
                    }
                    else
                    {
                        boolean hasRequiredCharacters = true;
                        for (Character c : key.get(0).toCharArray())
                        {
                            if (number.indexOf(c) == -1)
                            {
                                hasRequiredCharacters = false;
                                break;
                            }
                        }
                        if (hasRequiredCharacters)
                        {
                            answer += 0;
                            continue;
                        }
                        hasRequiredCharacters = true;
                        for (Character c : key.get(6).toCharArray())
                        {
                            if (number.indexOf(c) == -1)
                            {
                                hasRequiredCharacters = false;
                                break;
                            }
                        }
                        if (hasRequiredCharacters)
                        {
                            answer += 6;
                            continue;
                        }
                        hasRequiredCharacters = true;
                        for (Character c : key.get(9).toCharArray())
                        {
                            if (number.indexOf(c) == -1)
                            {
                                hasRequiredCharacters = false;
                                break;
                            }
                        }
                        if (hasRequiredCharacters)
                        {
                            answer += 9;
                            continue;
                        }
                    }
                }
                outputs.add(Integer.parseInt(answer));
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        int sum = 0;
        for (int num : outputs)
        {
            sum += num;
        }
        System.out.println(sum);
    }    
}
