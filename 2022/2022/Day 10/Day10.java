import java.io.*;
import java.util.*;

public class Day10 {
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

        // Part B
        int currentCycle = 0, registerValue = 1;
        String currentLine = "";
        for (String s : inputs) {
            // System.out.println(s);
            StringTokenizer st = new StringTokenizer(s);
            Set<Integer> sprite = new HashSet<>(Arrays.asList(registerValue-1, registerValue, registerValue + 1));
            if (st.nextToken().equals("noop")) {
                if (currentCycle % 40 == 0 && currentCycle != 0) {
                    System.out.println(currentLine);
                    currentLine = "";
                    currentCycle -= 40;
                }
                if (sprite.contains(currentCycle)) {
                    currentLine += "#";
                } else {
                    currentLine += " ";
                }
                currentCycle++;
            } else {
                for (int i = 0; i < 2; i++) {
                    if (currentCycle % 40 == 0 && currentCycle != 0) {
                        System.out.println(currentLine);
                        currentLine = "";
                        currentCycle -= 40;
                    }
                    if (sprite.contains(currentCycle)) {
                        currentLine += "#";
                    } else {
                        currentLine += " ";
                    }
                    currentCycle++;
                }
                registerValue += Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(currentLine);

        // Part A
        // int currentCycle = 0, sum = 0, registerValue = 1;
        // for (String s : inputs) {
        //     StringTokenizer st = new StringTokenizer(s);
        //     if (st.nextToken().equals("noop")) {
        //         currentCycle++;
        //         if (importantCycle(currentCycle)) {
        //             sum += (currentCycle * registerValue);
        //             // System.out.println(currentCycle + " " + registerValue);
        //         }
        //     } else {
        //         for (int i = 0; i < 2; i++) {
        //             currentCycle++;
        //             if (importantCycle(currentCycle)) {
        //                 sum += (currentCycle * registerValue);
        //                 // System.out.println(currentCycle + " " + registerValue);
        //             }
        //         }
        //         registerValue += Integer.parseInt(st.nextToken());
        //     }
        // }
        // System.out.println(sum);
    }

    public static boolean importantCycle(int currentCycle) {
        return (currentCycle == 20 || currentCycle == 60 ||
                currentCycle == 100 || currentCycle == 140 || currentCycle == 180 || currentCycle == 220);
    }
}