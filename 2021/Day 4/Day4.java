import java.io.*;
import java.util.*;

public class Day4 
{
    public static void main(String[] args) 
    {
        List<Integer> numbersCalled = new ArrayList<>();
        List<Integer[][]> boards = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens())
            {
                numbersCalled.add(Integer.parseInt(st.nextToken()));
            }
            while ((line = br.readLine()) != null)
            {
                Integer[][] board = new Integer[5][5];
                if (line.isEmpty())
                {
                    continue;
                }
                for (int i = 0; i < 5; i++)
                {
                    if (i == 0)
                    {
                        st = new StringTokenizer(line);
                    }
                    else
                    {
                        line = br.readLine();
                        st = new StringTokenizer(line);
                    }
                    for (int j = 0; j < 5; j++)
                    {
                        board[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                boards.add(board);
            }
            br.close();
        }
        catch(Exception e)
        {
            System.exit(0);
        }
        for (int number : numbersCalled)
        {
            for (Integer[][] board : boards)
            {
                for (int i = 0; i < 5; i++)
                {
                    for (int j = 0; j < 5; j++)
                    {
                        if (board[i][j] == number)
                        {
                            board[i][j] = -1;
                        }
                    }
                }
            }
            
            //Part A

            // Integer[][] wonBoard = checkForWin((ArrayList<Integer[][]>) boards);

            // if (wonBoard != null)
            // {
            //     int answer = 0;
            //     for (int i = 0; i < 5; i++)
            //     {
            //         for (int j = 0; j < 5; j++)
            //         {
            //             if (wonBoard[i][j] != -1)
            //             {
            //                 answer += wonBoard[i][j];
            //             }
            //         }
            //     }
            //     System.out.println(answer * number);
            //     break;
            // }

            //Part B

            if (boards.size() == 1)
            {
                Integer[][] finalAnswer = checkForWin((ArrayList<Integer[][]>)boards);
                if (finalAnswer != null)
                {
                    int answer = 0;
                    for (int i = 0; i < 5; i++)
                    {
                        for (int j = 0; j < 5; j++)
                        {
                            if (finalAnswer[i][j] != -1)
                            {
                                answer += finalAnswer[i][j];
                            }
                        }
                    }
                    System.out.println(answer * number);
                    break;
                }
            }
            removeWonBoards((ArrayList<Integer[][]>) boards);
        }
    }
    
    private static Integer[][] checkForWin(ArrayList<Integer[][]> boards)
    {
        for (Integer[][] board : boards)
        {
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    if (board[i][j] != -1)
                    {
                        break;
                    }
                    if (j == 4)
                    {
                        return board;
                    }
                }
            }
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    if (board[j][i] != - 1)
                    {
                        break;
                    }
                    if (j == 4)
                    {
                        return board;
                    }
                }
            }
        }
        return null;
    }

    private static void removeWonBoards(ArrayList<Integer[][]> boards)
    {
        for (int i = 0; i < 100; i++)
        {
            Integer[][] wonBoard = checkForWin(boards);
            if (wonBoard != null)
            {
                boards.remove(wonBoard);
            }
        }
    }
}
