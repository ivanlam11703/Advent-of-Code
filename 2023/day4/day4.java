import java.util.*;
import java.io.*;

public class day4 {
    public static List<String> inputs = new ArrayList<>();
    public static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(pt1());
        System.out.println(pt2());
    }

    public static int pt1() {
        int sum = 0;
        for (String str : inputs) {
            int power = 0;
            String temp = str.substring(str.indexOf(":") + 1);

            String winnerStr = temp.substring(0, temp.indexOf("|"));
            String numberStr = temp.substring(temp.indexOf("|") + 1);

            StringTokenizer winnerSt = new StringTokenizer(winnerStr);
            StringTokenizer st = new StringTokenizer(numberStr);

            ArrayList<Integer> winners = new ArrayList<>();
            while(winnerSt.hasMoreTokens()) {
                winners.add(Integer.parseInt(winnerSt.nextToken()));
            }

            while (st.hasMoreTokens()) {
                if (winners.contains(Integer.parseInt(st.nextToken()))) {
                    power++;
                }
            }
            
            if (power > 0) {
                sum += Math.pow(2, power-1);
            }
        }
        return sum;
    }

    public static void playCards() {
        for (int i = 0; i < inputs.size(); i++) {
            String str = inputs.get(i);
            int power = 0;
            String temp = str.substring(str.indexOf(":") + 1);

            String winnerStr = temp.substring(0, temp.indexOf("|"));
            String numberStr = temp.substring(temp.indexOf("|") + 1);

            StringTokenizer winnerSt = new StringTokenizer(winnerStr);
            StringTokenizer st = new StringTokenizer(numberStr);

            ArrayList<Integer> winners = new ArrayList<>();
            while(winnerSt.hasMoreTokens()) {
                winners.add(Integer.parseInt(winnerSt.nextToken()));
            }

            while (st.hasMoreTokens()) {
                if (winners.contains(Integer.parseInt(st.nextToken()))) {
                    power++;
                }
            }
            
            map.put(i, power);
        }
    }

    public static int pt2helper(int i) {
        int sum = 0;
        sum++;

        if (map.get(i) != 0) {
            for (int j = 1; j <= map.get(i); j++) {
                sum += pt2helper(i+j);
            }
        }

        return sum;
    }

    public static int pt2() {
        int sum = 0;
        playCards();
        for (int i = 0; i < map.size(); i++) {
            sum++;
            if (map.get(i) != 0) {
                for (int j = 1; j <= map.get(i); j++) {
                    sum += pt2helper(i+j);
                }
            }
        }
        return sum;
    }
}