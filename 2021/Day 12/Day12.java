import java.util.*;
import java.io.*;

public class Day12
{
    private static HashSet<String> paths = new HashSet<>();
    private static ArrayList<String> starts = new ArrayList<>();
    private static ArrayList<String> ends = new ArrayList<>();
    public static void main(String[] args) 
    {
        //Part A and B together
        //Check Cave class for changes between Part A and B
        ArrayList<Cave> caves = new ArrayList<>();
        try 
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null)
            {
              StringTokenizer st = new StringTokenizer(line, "-");
              starts.add(st.nextToken());
              ends.add(st.nextToken());
            }
            br.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
      
        for (int i = 0; i < starts.size(); i++)
        {
            Cave s = new Cave(starts.get(i));
            Cave e = new Cave(ends.get(i));
      
            for (int c = 0; c<caves.size(); c++) 
            {
                if (caves.get(c).getCaveName().equals(starts.get(i))) 
                {
                    s = caves.get(c);
                }
                if (caves.get(c).getCaveName().equals(ends.get(i))) 
                {
                    e = caves.get(c);
                }
            }
            s.addAdjCave(e);
            e.addAdjCave(s);
      
            if (!caves.contains(s)) 
            {
                caves.add(s);
            }
            if (!caves.contains(e)) 
            {
                caves.add(e);
            }
        }

        Cave startCave = null;
        for (int i = 0; i < caves.size(); i++)
        {
            if (caves.get(i).getCaveName().equals("start"))
            {
                startCave = caves.get(i);
            }
        }

        String currentPath = "";
        findPath(startCave, currentPath);
        
        System.out.println(paths.size());
    }

    private static void findPath(Cave c, String path)
    {       
        path += c.getCaveName() + " ";
        if (c.getCaveName().equals("end"))
        {
            paths.add(path);
        }
        else if (c.canContinue())
        {
            for (Cave adj : c.getAdjCaves())
            {
                c.visit();
                if (adj.canAccess())
                {
                    findPath(adj, path);
                }
                c.unVisit();
            }
        }
    }
}