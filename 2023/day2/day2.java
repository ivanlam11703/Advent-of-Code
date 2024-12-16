import java.util.*;
import java.io.*;
import java.util.regex.*;

public class day2 {
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
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

        System.out.println(pt1(inputs));
        System.out.println(pt2(inputs));
    }

    public static int pt1(List<String> inputs) {
        Map<String, Integer> colorCount = new HashMap<>();
        colorCount.put("blue", 14);
        colorCount.put("green", 13);
        colorCount.put("red", 12);

        int sum = 0;
        for (String str : inputs) {
            boolean validGame = true;
            int gameNum = Integer.parseInt(str.substring(5, str.indexOf(":")));
            str = str.substring(str.indexOf(":") + 2);
            StringTokenizer st = new StringTokenizer(str, ";");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                token = token.replaceAll(",", " ");
                StringTokenizer strtok = new StringTokenizer(token);
                while (strtok.hasMoreTokens()) {
                    int number = Integer.parseInt(strtok.nextToken());
                    String color = strtok.nextToken();

                    if (number > colorCount.get(color)) {
                        validGame = false;
                        break;
                    }
                }
            }
            if (validGame) {
                sum += gameNum;
            }
        }
        return sum;
    }

    public static int pt2(List<String> inputs) {
        Map<String, Integer> colorCount = new HashMap<>();

        int sum = 0;
        for (String str : inputs) {
            colorCount.put("blue", 0);
            colorCount.put("green", 0);
            colorCount.put("red", 0);

            str = str.substring(str.indexOf(":") + 2);
            StringTokenizer st = new StringTokenizer(str, ";");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                token = token.replaceAll(",", " ");
                StringTokenizer strtok = new StringTokenizer(token);
                while (strtok.hasMoreTokens()) {
                    int number = Integer.parseInt(strtok.nextToken());
                    String color = strtok.nextToken();

                    colorCount.put(color, Math.max(number, colorCount.get(color)));
                }
            }
            int temp = 1;
            for (String color : colorCount.keySet()) {
                temp *= colorCount.get(color);
            }
            sum += temp;
        }
        return sum;
    }
}