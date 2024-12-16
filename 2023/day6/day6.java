import java.util.*;
import java.io.*;

public class day6 {
    public static List<Integer> distances = new ArrayList<>();
    public static List<Integer> times = new ArrayList<>();

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            while (st.hasMoreTokens()) {
                times.add(Integer.parseInt(st.nextToken()));
            }

            line = br.readLine();
            st = new StringTokenizer(line);
            st.nextToken();
            while (st.hasMoreTokens()) {
                distances.add(Integer.parseInt(st.nextToken()));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(pt1());
        System.out.println(pt2());
    }

    public static boolean simulate(long time, long hold, long distance) {
        return (time - hold) * hold > distance;
    }

    public static int pt1() {
        int answer = 1;
        for (int i = 0; i < distances.size(); i++) {
            int record = distances.get(i);
            int numWins = 0;
            for (int j = 0; j < times.get(i); j++) {
                if (simulate(times.get(i), j, record)) {
                    numWins++;
                }
            }
            answer *= numWins;
        }
        return answer;
    }

    public static long pt2() {
        int answer = 0;
        String distanceStr = "";
        String timeStr = "";
        for (Integer str : distances) {
            distanceStr += str.toString();
        }

        for (Integer str : times) {
            timeStr += str.toString();
        }

        long distance = Long.parseLong(distanceStr);
        long time = Long.parseLong(timeStr);

        for (int i = 0; i < time; i++) {
            if (simulate(time, i, distance)) {
                answer++;
            }
        }
        return answer;
    }
}