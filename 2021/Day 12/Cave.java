import java.util.ArrayList;

public class Cave
{
    private ArrayList<Cave> adjacentCaves;
    private String caveName;
    private boolean isSmall;
    private int timesVisited;
    private static Cave littleCave;
    private static boolean littleTwice;

    public Cave(String s) 
    {
        caveName = s;
        isSmall = !caveName.toUpperCase().equals(caveName);
        adjacentCaves = new ArrayList<>();
        littleCave = null;
        littleTwice = false;
      }

    public ArrayList<Cave> getAdjCaves()
    {
        return adjacentCaves;
    }

    public void addAdjCave(Cave c)
    {
        adjacentCaves.add(c);
    }

    public String getCaveName()
    {
        return caveName;
    }

    public boolean equals(Cave c)
    {
        if (caveName.equals(c.getCaveName()))
        {
            return true;
        }
        return false;
    }

    public boolean isSmall()
    {
        return isSmall;
    }

    public boolean canContinue()
    {
        for (Cave c : adjacentCaves)
        {
            if (c.canAccess())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean canAccess()
    {
        if (isSmall)
        {
            //Part B
            if (caveName.equals("start") || caveName.equals("end"))
            {
                return timesVisited < 1;
            }
            else if (!littleTwice)
            {
                return timesVisited < 2;
            }
            else
            {
                return timesVisited < 1;
            }
            //Part A
            // return timesVisited < 1;
        }
        return true;
    }

    public int timesVisited()
    {
        return timesVisited;
    }

    public void visit()
    {
        timesVisited++;
        //Part B
        if (isSmall && timesVisited == 2 && !littleTwice)
        {
            littleCave = this;
            littleTwice = true;
        }
    }

    public void unVisit()
    {
        timesVisited--;
        //Part B
        if (this == littleCave)
        {
            littleTwice = false;
        }
    }
}