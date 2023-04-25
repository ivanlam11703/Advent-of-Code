import java.io.*;
import java.util.*;

public class Day15 {
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                String tempLine = line.substring(line.indexOf("x=") + 2, line.indexOf(":"));
                tempLine = tempLine.replace(" y=", "");
                inputs.add(tempLine);
                tempLine = line.substring(line.lastIndexOf("x=") +2);
                tempLine = tempLine.replace(" y=", "");
                inputs.add(tempLine);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        ArrayList<Sensor> sensors = new ArrayList<>();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int i = 0; i < inputs.size(); i+=2) {
            StringTokenizer st = new StringTokenizer(inputs.get(i),",");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            minX = Math.min(x,minX);
            minY = Math.min(y,minY);
            maxX = Math.max(x,maxX);
            maxY = Math.max(y,maxY);
            st = new StringTokenizer(inputs.get(i+1), ",");
            int bx = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());
            minX = Math.min(bx,minX);
            minY = Math.min(by,minY);
            maxX = Math.max(bx,maxX);
            maxY = Math.max(by,maxY);
            sensors.add(new Sensor(x, y, bx, by));
        }
        
        long count = 0, frequency = 20;
        Set<Long> seen = new HashSet<>();
        Set<Long> noBeacon = new HashSet<>();
        Map<Long,Long> potentialBeacon = new HashMap<>();
        for (Sensor s : sensors) {
            // Part B
            // for (long x = minX; x <= maxX; x++) {
            //     for (long y = minY; y <= maxY; y++) {
            //         long tempDist = Math.abs(x-s.x) + Math.abs(y-s.y);
            //         if (x > 0 && x <= 20 && y > 0 && y <= 20) {
            //             if (tempDist > s.manhattanDist) {
            //                 if (!potentialBeacon.containsKey(x)) {
            //                     potentialBeacon.put(x,y);
            //                 }
            //             }
            //         }
            //     }
            // }

            // Part A
            if (s.by == 2000000) {
                if (!seen.contains(s.bx)) {
                    seen.add(s.bx);
                    count--;
                }
            }
            for (long x = minX; x <= maxX + Math.abs(minX) * 2; x++) {
                long tempDist = Math.abs(x-s.x) + Math.abs(2000000-s.y);
                if (tempDist <= s.manhattanDist) {
                    if (!noBeacon.contains(x)) {
                        count++;
                        noBeacon.add(x);
                    }
                }
            }
        }
        for (long key : potentialBeacon.keySet()) {
            System.out.println(key + ": " + potentialBeacon.get(key));
        }
        System.out.println(count);
    }
}