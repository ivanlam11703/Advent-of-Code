import java.util.*;
import java.io.*;
import java.util.regex.*;

public class day1 {
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
        int pt1 = 0;
        for (String str : inputs) {
            int first = 0;
            int last = 0;
            boolean found = false;

            for (char c : str.toCharArray()) {
                if (!found && Character.isDigit(c)) {
                    first = c - 48;
                    last = c - 48;
                    found = true;
                    continue;
                }

                if (Character.isDigit(c)) {
                    last = c - 48;
                }
            }
            pt1 += first * 10 + last;
        }
        return pt1;
    }

    public static int pt2(List<String> input) {
        Map<String, String> wordToNum = new HashMap<>();
        wordToNum.put("zero", "z0o");
        wordToNum.put("one", "o1e");
        wordToNum.put("two", "t2o");
        wordToNum.put("three", "t3e");
        wordToNum.put("four", "f4r");
        wordToNum.put("five", "f5e");
        wordToNum.put("six", "s6x");
        wordToNum.put("seven", "s7n");
        wordToNum.put("eight", "e8t");
        wordToNum.put("nine", "n9e");

        List<String> newList = new ArrayList<>();
        for (String str : input) {
            for (String s : wordToNum.keySet()) {
                str = str.replace(s, wordToNum.get(s));
            }
            newList.add(str);
        }

        return pt1(newList);
    }
}