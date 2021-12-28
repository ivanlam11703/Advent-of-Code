import java.io.*;
import java.util.*;

public class Day10
{
    static ArrayList<String> inputLines = new ArrayList<>();
    //Part A and B solutions are together
    public static void main(String[] args) 
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
                inputLines.add(line);
            }
            br.close();
        }    
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        removeAdjacents();
        removePairs();

        int parenCount = 0;
        int carCount = 0;
        int bracCount = 0;
        int curlCount = 0;

        ArrayList<String> corruptedStrings = new ArrayList<>();
        for (String s : inputLines)
        {
            for (int i = 0; i < s.length(); i++)
            {
                if (s.charAt(i) == ')')
                {
                    parenCount++;
                    corruptedStrings.add(s);
                    break;
                }
                else if (s.charAt(i) == '}')
                {
                    curlCount++;
                    corruptedStrings.add(s);
                    break;
                }
                else if (s.charAt(i) == '>')
                {
                    carCount++;
                    corruptedStrings.add(s);
                    break;
                }
                else if (s.charAt(i) == ']')
                {
                    bracCount++;
                    corruptedStrings.add(s);
                    break;
                }
            }
        }

        // Part A answer
        System.out.println(parenCount * 3 + bracCount * 57 + curlCount * 1197 + carCount * 25137);

        for (String s : corruptedStrings)
        {
            inputLines.remove(s);
        }
        
        ArrayList<Long> scores = new ArrayList<>();
        for (String s : inputLines)
        {
            String added = "";
            for (int i = s.length() - 1; i >= 0; i--)
            {
                if (s.charAt(i) == '{')
                {
                    added += "}";
                }
                else if (s.charAt(i) == '(')
                {
                    added += ")";
                }
                else if (s.charAt(i) == '<')
                {
                    added += ">";
                }
                else if (s.charAt(i) == '[')
                {
                    added += "]";
                }
            }
            long score = 0;
            for (Character c : added.toCharArray())
            {
                score *= 5;
                if (c == ')')
                {
                    score++;
                }
                else if (c == ']')
                {
                    score += 2;
                }
                else if (c == '}')
                {
                    score += 3;
                }
                else if (c == '>')
                {
                    score += 4;
                }
            }
            scores.add(score);
        }
        Collections.sort(scores);

        //Part B answer
        System.out.println(scores.get(scores.size() / 2));
    }

    private static void removeAdjacents()
    {
        for (int j = 0; j < inputLines.size(); j++)
        {
            String s = inputLines.get(j);
            boolean removedSomething = true;
            while (removedSomething)
            {
                removedSomething = false;
                for (int i = 0; i < s.length() - 1; i++)
                {
                    if (s.charAt(i) == '(')
                    {
                        if (inputLines.get(j).charAt(i + 1) == ')')
                        {
                            if (i == s.length() - 1)
                            {
                                s = s.substring(0, i);
                                inputLines.set(j, s);
                                removedSomething = true;
                            }
                            s = s.substring(0, i) + s.substring(i + 2, s.length());
                            inputLines.set(j, s);
                            removedSomething = true;
                        }
                    }
                    else if (s.charAt(i) == '{')
                    {
                        if (s.charAt(i + 1) == '}')
                        {
                            if (i == s.length() - 1)
                            {
                                s = s.substring(0, i);
                                inputLines.set(j, s);
                                removedSomething = true;
                            }
                            s = s.substring(0, i) + s.substring(i + 2, s.length());
                            inputLines.set(j, s);
                            removedSomething = true;
                        }
                    }
                    else if (s.charAt(i) == '<')
                    {
                        if (s.charAt(i + 1) == '>')
                        {
                            if (i == s.length() - 1)
                            {
                                s = s.substring(0, i);
                                inputLines.set(j, s);
                                removedSomething = true;
                            }
                            s = s.substring(0, i) + s.substring(i + 2, s.length());
                            inputLines.set(j, s);
                            removedSomething = true;
                        }
                    }
                    else if (s.charAt(i) == '[')
                    {
                        if (s.charAt(i + 1) == ']')
                        {
                            if (i == s.length() - 1)
                            {
                                s = s.substring(0, i);
                                inputLines.set(j, s);
                                removedSomething = true;
                            }
                            s = s.substring(0, i) + s.substring(i + 2, s.length());
                            inputLines.set(j, s);
                            removedSomething = true;
                        }
                    }
                }
            }
        }
    }

    private static void removePairs()
    {
        for (String s : inputLines)
        {
            boolean somethingRemoved = true;
            while (somethingRemoved)
            {
                somethingRemoved = false;
                for (int i = 0; i < s.length() - 1; i++)
                {
                    for (int j = i + 1; j < s.length(); j++)
                    {
                        if (s.charAt(i) == '(')
                        {
                            if (s.charAt(j) == ')')
                            {
                                if (checkOpenClose(s.substring(i, j)))
                                {
                                    s = s.substring(0, i) + s.substring(i + 1, j) + s.substring(j + 1);
                                    somethingRemoved = true;
                                }
                            }
                        }
                        else if (s.charAt(j) == s.charAt(i) + 2)
                        {
                            if (checkOpenClose(s.substring(i, j)))
                            {
                                s = s.substring(0, i) + s.substring(i + 1, j) + s.substring(j + 1);
                                somethingRemoved = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean checkOpenClose(String s)
    {
        int openParen = 0;
        int closeParen = 0;
        int openBrac = 0;
        int closeBrac = 0;
        int openCurl = 0;
        int closeCurl = 0;
        int openCar = 0;
        int closeCar = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
            {
                openParen++;
            }
            else if (s.charAt(i) == ')')
            {
                closeParen++;
            }
            else if (s.charAt(i) == '[')
            {
                openBrac++;
            }
            else if (s.charAt(i) == ']')
            {
                closeBrac++;
            }
            else if (s.charAt(i) == '{')
            {
                openCurl++;
            }
            else if (s.charAt(i) == '}')
            {
                closeCurl++;
            }
            else if (s.charAt(i) == '<')
            {
                openCar++;
            }
            else if (s.charAt(i) == '>')
            {
                closeCar++;
            }
        }
        if (openCar == closeCar && openBrac == closeBrac && openParen == closeParen && openCurl == closeCurl)
        {
            return true;
        }
        return false;
    }
}
