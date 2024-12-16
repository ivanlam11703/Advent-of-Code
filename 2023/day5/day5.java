import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class day5 {
    public static String seeds = "";
    public static List<String> seed2Soil = new ArrayList<>();
    public static List<String> soil2Fert = new ArrayList<>();
    public static List<String> fert2Water = new ArrayList<>();
    public static List<String> water2Light = new ArrayList<>();
    public static List<String> light2Temp = new ArrayList<>();
    public static List<String> temp2Humid = new ArrayList<>();
    public static List<String> humid2Loc = new ArrayList<>();
    public static List<List<String>> almanac = new ArrayList<>();
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            seeds = br.readLine();
            seeds = seeds.substring((seeds.indexOf(":") + 2));
            br.readLine();

            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                seed2Soil.add(line);
            }
            almanac.add(seed2Soil);

            line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                soil2Fert.add(line);
            }
            almanac.add(soil2Fert);

            line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                fert2Water.add(line);
            }
            almanac.add(fert2Water);

            line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                water2Light.add(line);
            }
            almanac.add(water2Light);

            line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                light2Temp.add(line);
            }
            almanac.add(light2Temp);

            line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                temp2Humid.add(line);
            }
            almanac.add(temp2Humid);

            line = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;
                humid2Loc.add(line);
            }
            almanac.add(humid2Loc);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(pt1());
        
        ExecutorService executor = Executors.newFixedThreadPool(10); 

        StringTokenizer st = new StringTokenizer(seeds);
        List<Long> results = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            long seed1 = Long.parseLong(st.nextToken());
            long seed2 = Long.parseLong(st.nextToken());

            executor.submit(() -> {
                long result = pt2(seed1, seed2); 
                System.out.println(result);
                results.add(result);
            });
        }

        executor.shutdown(); 

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
          } catch (InterruptedException e) {
        
          }

        System.out.println(Collections.min(results));
    }

    public static boolean inRange(long seed, long begin, long range) {
        return begin <= seed && begin + range - 1 >= seed;
    }

    public static long pt2(long seed1, long seed2) {
        long min = Long.MAX_VALUE;
        for (long seed = seed1; seed < seed1+seed2; seed++) {
            long temp = seed;
            for (List<String> list : almanac) {
                for (String str : list) {
                    StringTokenizer strtok = new StringTokenizer(str);
                    long end = Long.parseLong(strtok.nextToken());
                    long begin = Long.parseLong(strtok.nextToken());
                    long range = Long.parseLong(strtok.nextToken());
                    
                    if (inRange(temp, begin, range)) {
                        temp = end + temp - begin;
                        break;
                    }
                }
            }
            min = Math.min(min, temp);
        }
        return min;
    }

    public static long pt1() {
        long min = Long.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(seeds);
        while (st.hasMoreTokens()) {
            long seed = Long.parseLong(st.nextToken());

            for (List<String> list : almanac) {
                for (String str : list) {
                    StringTokenizer strtok = new StringTokenizer(str);
                    long end = Long.parseLong(strtok.nextToken());
                    long begin = Long.parseLong(strtok.nextToken());
                    long range = Long.parseLong(strtok.nextToken());
                    
                    if (inRange(seed, begin, range)) {
                        seed = end + seed - begin;
                        break;
                    }
                }
            }
            min = Math.min(min, seed);
        }
        return min;
    }
}