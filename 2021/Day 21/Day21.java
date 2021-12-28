import java.util.*;
import java.io.*;

public class Day21
{
    private static long p1Start, p2Start, p1Score, p2Score, rollCount, dice;
    public static void main(String[] args) 
    {
        p1Start = p2Start = p1Score = p2Score = rollCount = dice = 0;
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = br.readLine();
            p1Start = Integer.parseInt(String.valueOf(line.charAt(line.length() - 1)));
            line = br.readLine();
            p2Start = Integer.parseInt(String.valueOf(line.charAt(line.length() - 1)));
            br.close();
        }    
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // Part A

        // playGame();
        // if (p1Score > p2Score)
        // {
        //     System.out.println(rollCount * p2Score);
        // }
        // else
        // {
        //     System.out.println(rollCount * p1Score);
        // }

        
        // Part B

        System.out.println(Math.max(playQuantumGame(1), playQuantumGame(2)));
    }

    // Part A methods

    // private static void playGame()
    // {
    //     while (p1Score < 1000 && p2Score < 1000)
    //     {
    //         for (int i = 0; i < 3; i++)
    //         {
    //             p1Start += rollDice();
    //         }
    //         if (p1Start >= 10)
    //         {
    //             p1Start = p1Start % 10;
    //             if (p1Start == 0)
    //             {
    //                 p1Start = 10;
    //             }
    //         }
    //         p1Score += p1Start;
    //         if (p1Score >= 1000) break;
    //         for (int i = 0; i < 3; i++)
    //         {
    //             p2Start += rollDice();
    //         }
    //         if (p2Start >= 10)
    //         {
    //             p2Start = p2Start % 10;
    //             if (p2Start == 0)
    //             {
    //                 p2Start = 10;
    //             }
    //         }
    //         p2Score += p2Start;
    //     }
    // }

    // private static long rollDice()
    // {
    //     dice++;
    //     rollCount++;
    //     return dice;
    // }

    // Part B methods

    private static long playQuantumGame(int player)
    {
        long localP1Start = p1Start;
        long localP2Start = p2Start;
        long localP1Score = p1Score;
        long localP2Score = p2Score;
        long wins = 0;
        boolean p1Turn = true;

        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 9, p1Turn, player);
        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 8, p1Turn, player) * 3;
        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 7, p1Turn, player) * 6;
        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 6, p1Turn, player) * 7;
        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 5, p1Turn, player) * 6;
        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 4, p1Turn, player) * 3;
        wins += rollQuantumDice(localP1Start, localP2Start, localP1Score, localP2Score, 3, p1Turn, player);

	    return wins;
    }

    private static long rollQuantumDice(long p1Start, long p2Start, long p1Score, long p2Score, int roll, boolean p1Turn, int player)
    {
        long wins = 0;
        if (p1Turn)
        {
            p1Start += roll;
            if (p1Start >= 10)
            {
                p1Start = p1Start % 10;
                if (p1Start == 0)
                {
                    p1Start = 10;
                }
            }
            p1Score += p1Start;
        }
        else
        {
            p2Start += roll;
            if (p2Start >= 10)
            {
                p2Start = p2Start % 10;
                if (p2Start == 0)
                {
                    p2Start = 10;
                }
            }
            p2Score += p2Start;
        }
        p1Turn = !p1Turn;
        if (p1Score >= 21 || p2Score >= 21) 
        {
            if (player == 1) 
            {
                if (p1Score >= 21) 
                {
                    return 1;
                } 
                else 
                {
                    return 0;
                }
            } 
            else 
            {
                if (p2Score >= 21) 
                {
                    return 1;
                } 
                else 
                {
                    return 0;
                }
            }
        } 
        else 
        {
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 9, p1Turn, player);
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 8, p1Turn, player) * 3;
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 7, p1Turn, player) * 6;
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 6, p1Turn, player) * 7;
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 5, p1Turn, player) * 6;
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 4, p1Turn, player) * 3;
            wins += rollQuantumDice(p1Start, p2Start, p1Score, p2Score, 3, p1Turn, player);
        }
        return wins;
    }
}