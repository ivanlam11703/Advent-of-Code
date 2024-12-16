import java.util.*;
import java.io.*;

public class day8 {
    public static Map<String, List<String>> inputs = new HashMap<>();
    public static String directions = "";

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            directions = br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String start = st.nextToken();
                st.nextToken();
                String left = st.nextToken();
                String right = st.nextToken();
                List<String> temp = new ArrayList<>();
                temp.add(left.substring(1, left.length()-1));
                temp.add(right.substring(0, right.length()-1));
                inputs.put(start,temp);
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
        int steps = 0;
        String current = "AAA";

        while (!current.equals("ZZZ")) {
            for (Character c : directions.toCharArray()) {
                steps++;
                if (c == 'L') {
                    current = inputs.get(current).get(0);
                } else {
                    current = inputs.get(current).get(1);
                }
                if (current == "ZZZ") {
                    break;
                }
            }
        }
        
        return steps;
    }

    public static long pt2() {
        int steps = 0;
        List<String> currents = new ArrayList<>();
        for (String key : inputs.keySet()) {
            if (key.charAt(2) == 'A') {
                currents.add(key);
            }
        }
        
        List<Integer> temp = new ArrayList<>();
        for (String current : currents) {
            steps = 0;
            while (current.charAt(2) != 'Z') {
                for (Character c : directions.toCharArray()) {
                    steps++;
                    if (c == 'L') {
                        current = inputs.get(current).get(0);
                    } else {
                        current = inputs.get(current).get(1);
                    }
                    if (current.charAt(2) == 'Z') {
                        temp.add(steps);
                        break;
                    }
                }
            }
        }

        System.out.println(temp.toString());
        long lcm_of_array_elements = 1;
        int divisor = 2;
         
        while (true) {
            int counter = 0;
            boolean divisible = false;
             
            for (int i = 0; i < temp.size(); i++) {
 
                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.
 
                if (temp.get(i) == 0) {
                    return 0;
                }
                else if (temp.get(i) < 0) {
                    temp.set(i, temp.get(i) * -1);
                }
                if (temp.get(i) == 1) {
                    counter++;
                }
 
                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (temp.get(i) % divisor == 0) {
                    divisible = true;
                    temp.set(i, temp.get(i) / divisor);
                }
            }
 
            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
 
            // Check if all element_array is 1 indicate 
            // we found all factors and terminate while loop.
            if (counter == temp.size()) {
                break;
            }
        }

        return lcm_of_array_elements;
    }
}