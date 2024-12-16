import java.util.*;
import java.io.*;

public class day9 {
    public static List<List<Integer>> inputs = new ArrayList<>();
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                List<Integer> temp = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    temp.add(Integer.parseInt(st.nextToken()));
                }
                inputs.add(temp);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(pt1());
        System.out.println(pt2());
    }

    public static boolean oneValue(List<Integer> list) {
        return new HashSet<Integer>(list).size() == 1;
    }

    public static long pt1() {
        long sum = 0;

        for (List<Integer> list : inputs) {
            List<List<Integer>> temps = new ArrayList<>();
            List<Integer> current = list;
            temps.add(current);
            while (!oneValue(current)) {
                List<Integer> temp = new ArrayList<>();
                for (int i = 0; i < current.size() - 1; i++) {
                    temp.add(current.get(i+1) - current.get(i));
                }
                temps.add(temp);
                current = temp;
            }

            int prev = -1;
            for (int i = temps.size() - 1; i >= 0; i--) {
                List<Integer> temp = temps.get(i);
                if (oneValue(temp)) {
                    prev = temp.get(0);
                } else {
                    prev = temp.get(temp.size() - 1) + prev;
                }
            }
            sum += prev;
        }

        return sum;
    }

    public static long pt2() {
        long sum = 0;

        for (List<Integer> list : inputs) {
            List<List<Integer>> temps = new ArrayList<>();
            List<Integer> current = list;
            temps.add(current);
            while (!oneValue(current)) {
                List<Integer> temp = new ArrayList<>();
                for (int i = 0; i < current.size() - 1; i++) {
                    temp.add(current.get(i+1) - current.get(i));
                }
                temps.add(temp);
                current = temp;
            }

            int prev = -1;
            for (int i = temps.size() - 1; i >= 0; i--) {
                List<Integer> temp = temps.get(i);
                if (oneValue(temp)) {
                    prev = temp.get(0);
                } else {
                    prev = temp.get(0) - prev;
                }
            }
            sum += prev;
        }

        return sum;
    }
}