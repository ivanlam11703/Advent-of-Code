import java.io.*;
import java.util.*;

public class Day21 {
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
        Map<String, String> instructions = new HashMap<>();
        Map<String, Long> values = new HashMap<>();
        for (String s : inputs) {
            String name = s.substring(0,4);
            String instruction = s.substring(6);
            try {
                values.put(name,Long.parseLong(instruction));
            } catch (NumberFormatException nfe) {
                instructions.put(name,instruction);
            }
        }

        // Part A and Part B run together!
        String toFind = "humn";
        LinkedList<String> includedNames = new LinkedList<>();
        while (!values.containsKey("root")) {
            for (Iterator<Map.Entry<String,String>> it = instructions.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String,String> entry = it.next();
                String key = entry.getKey();
                StringTokenizer st = new StringTokenizer(instructions.get(key));
                String name1 = st.nextToken();
                char op = st.nextToken().charAt(0);
                String name2 = st.nextToken();
                if (values.containsKey(name1) && values.containsKey(name2)) {
                    if (name1.equals(toFind)) {
                        includedNames.addFirst(name1);
                        toFind = key;
                    } else if (name2.equals(toFind)) {
                        includedNames.addFirst(name2);
                        toFind = key;
                    }
                    long answer = getValue(op, values.get(name1), values.get(name2));
                    values.put(key, answer);
                }
            }
        }
        System.out.println("Part A answer: " + values.get("root"));

        String rootString = instructions.get("root");
        StringTokenizer rTokenizer = new StringTokenizer(rootString);
        String tempName = rTokenizer.nextToken();
        rTokenizer.nextToken();
        String tempName1 = rTokenizer.nextToken();
        if (includedNames.contains(tempName)) {
            values.put(tempName, values.get(tempName1));
        } else {
            values.put(tempName1, values.get(tempName));
        }

        for (String s : includedNames) {
            if (s.equals("humn")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(instructions.get(s));
            String name1 = st.nextToken();
            char op = st.nextToken().charAt(0);
            String name2 = st.nextToken();
            if (includedNames.contains(name1)) {
                long answer = getInverseValue(op, values.get(s), values.get(name2), false);
                values.put(name1, answer);
            } else {
                long answer = getInverseValue(op, values.get(s), values.get(name1), true);
                values.put(name2, answer);
            }
        }
        System.out.println("Part B answer: " + values.get("humn"));
    }

    public static long getValue(char op, long val1, long val2) {
        if (op == '+') {
            return val1 + val2;
        } 
        if (op == '/') {
            return val1 / val2;
        } 
        if (op == '*') {
            return val1 * val2;
        }
        return val1 - val2;
    }

    public static long getInverseValue(char op, long val1, long val2, boolean second) {
        if (op == '+') {
            return val1 - val2;
        }
        if (op == '/') {
            if (second) {
                return val2 / val1;
            }
            return val1 * val2;
        }
        if (op == '*') {
            return val1 / val2;
        }
        if (op == '-') {
            if (second) {
                return val2-val1;
            }
        }
        return val1 + val2;
    }
}