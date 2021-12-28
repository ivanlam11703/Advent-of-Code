import java.io.*;
import java.util.*;

public class Day22
{
    private static ArrayList<Integer> p1 = new ArrayList<>();
    private static ArrayList<Integer> p2 = new ArrayList<>();
    public static void main(String[] args) 
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            br.readLine();
            while (!(line = br.readLine()).isEmpty())
            {
                p1.add(Integer.parseInt(line));
            }
            line = br.readLine();
            while((line = br.readLine()) != null)
            {
                p2.add(Integer.parseInt(line));
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        // Part A

        // while (p1.size() != 0 && p2.size() != 0)
        // {
        //     play();
        // }
        // ArrayList<Integer> winner;
        // if (p1.size() == 0)
        // {
        //     winner = p2;
        // }
        // else
        // {
        //     winner = p1;
        // }
        // int score = 0;
        // int count = 1;
        // while (winner.size() > 0)
        // {
        //     score += winner.remove(winner.size() - 1) * count;
        //     count++;
        // }
        // System.out.println(score);

        //Part B
        
        boolean p1Wins = recursiveCombat(p1, p2);
        int count = 1;
		int score = 0;
		if (p1Wins) 
        {
			for (int i = p1.size() - 1; i >= 0; i--) 
            {
				score += p1.get(i) * count;
				count++;
			}
		} 
        else 
        {
			for (int i = p2.size() - 1; i >= 0; i--) 
            {
				score += p2.get(i) * count;
				count++;
			}
		}
        System.out.println(score);
    }

    // Method for Part A

    // private static void play()
    // {
    //     int p1Card = p1.remove(0);
    //     int p2Card = p2.remove(0);
    //     if (p1Card > p2Card)
    //     {
    //         p1.add(p1Card);
    //         p1.add(p2Card);
    //     }
    //     else
    //     {
    //         p2.add(p2Card);
    //         p2.add(p1Card);
    //     }
    // }

    private static boolean recursiveCombat(ArrayList<Integer> p1deck, ArrayList<Integer> p2deck) 
    {
		HashSet<String> history = new HashSet<String>();
		boolean p1Wins = false;

		while (!p1deck.isEmpty() && !p2deck.isEmpty()) 
        {
			if (!history.add(p1deck.toString())) 
            {
				return true;
			}
			int p1 = p1deck.remove(0);
			int p2 = p2deck.remove(0);
			if (p1deck.size() >= p1 && p2deck.size() >= p2)
            {
				ArrayList<Integer> p1subDeck = new ArrayList<Integer>();
				ArrayList<Integer> p2subDeck = new ArrayList<Integer>();
				for (int i = 0; i < p1; i++)
                {
					p1subDeck.add(p1deck.get(i));
                }
				for (int i = 0; i < p2; i++)
                {
					p2subDeck.add(p2deck.get(i));
                }
				p1Wins = recursiveCombat(p1subDeck, p2subDeck);
			} 
            else 
            {
				if (p1 > p2)
                {
					p1Wins = true;
                }
				else
                {
					p1Wins = false;
                }
			}

			if (p1Wins) 
            {
				p1deck.add(p1);
				p1deck.add(p2);
			} 
            else 
            {
				p2deck.add(p2);
				p2deck.add(p1);
			}
		}
		return p1Wins;
	}
}