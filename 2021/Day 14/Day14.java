import java.io.*;
import java.util.*;

public class Day14
{
    public static void main(String[] args) 
    {
        HashMap<String, Character> rules = new HashMap<>();
        String template = "";
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            template = br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                rules.put(line.substring(0,2), line.charAt(line.length() - 1));
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        HashMap<String,Long> vals = new HashMap<>();
        for(int i = 0; i < template.length() - 1; i++) 
        {
			vals.putIfAbsent(template.substring(i, i+2), (long) 0);
			vals.put(template.substring(i, i+2), vals.get(template.substring(i, i+2)) + 1);
		}
        for (int i = 0; i < 40 ; i++)
        {
            vals = iterate(vals, rules);
        }
        HashMap<String,Long> map = new HashMap<>();
        for(String key: vals.keySet()) 
        {
			map.merge(key.substring(0,1), vals.get(key), (a,b) -> a+b);
			map.merge(key.substring(1,2), vals.get(key), (a,b) -> a+b);
		}
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (String key : map.keySet())
        {
            min = Math.min(min, map.get(key));
            max = Math.max(max, map.get(key));
        }
        //Parts A and B work with the same code
        System.out.println((max - min) / 2);    
    }

    private static HashMap<String, Long> iterate(Map<String, Long> vals, HashMap<String, Character> rules)
    {
		HashMap<String, Long> newVals = new HashMap<>();
		for (String key : vals.keySet())
        {
            String first = key.substring(0, 1) + rules.get(key);
            String second = rules.get(key) + key.substring(1,2);
            newVals.merge(first, vals.get(key), (a,b) -> a + b);
            newVals.merge(second, vals.get(key), (a,b) -> a + b);
        }
		return newVals;
	}
}