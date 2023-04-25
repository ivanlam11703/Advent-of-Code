import java.io.*;
import java.util.*;

public class Day11 {
    static Monkey[] monkeys = new Monkey[8];
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        for (int i = 0; i < inputs.size(); i+=6 ) {
            int currentMonkey = Integer.parseInt(String.valueOf(inputs.get(i).charAt(inputs.get(i).length()-2)));
            monkeys[currentMonkey] = new Monkey();

            String itemLine = inputs.get(i+1);
            itemLine = itemLine.substring(itemLine.indexOf(":") + 1);
            itemLine = itemLine.replace(",", "");
            StringTokenizer st = new StringTokenizer(itemLine);
            while (st.hasMoreTokens()) {
                monkeys[currentMonkey].items.add(Long.parseLong(st.nextToken()));
            }

            String opLine = inputs.get(i+2);
            opLine = opLine.substring(opLine.indexOf("=") + 1);
            st = new StringTokenizer(opLine);
            monkeys[currentMonkey].firstOp = st.nextToken();
            monkeys[currentMonkey].operation = st.nextToken().charAt(0);
            monkeys[currentMonkey].secondOp = st.nextToken();

            String testLine = inputs.get(i+3);
            testLine = testLine.substring(testLine.indexOf("by") + 3);
            st = new StringTokenizer(testLine);
            monkeys[currentMonkey].test = Integer.parseInt(st.nextToken());

            String trueLine = inputs.get(i+4);
            monkeys[currentMonkey].success = Integer.parseInt(String.valueOf(trueLine.charAt(trueLine.length()-1)));

            String falseLine = inputs.get(i+5);
            monkeys[currentMonkey].fail = Integer.parseInt(String.valueOf(falseLine.charAt(falseLine.length()-1)));
        }
        int lcm = 1;
        for (int i = 0; i < monkeys.length; i++) {
            lcm *= monkeys[i].test;
        }
        // Part A is 20 simulations with worry /= 3
        // Part B is 10,000 simulations with worry /= lcm
        for (int i = 1; i <= 10_000; i++) {
            simulateRound(lcm);
        }
        long[] maxValues = new long[monkeys.length];
        for (int i = 0; i < monkeys.length; i++) {
            maxValues[i] = monkeys[i].itemsInspected;
        }
        Arrays.sort(maxValues);
        System.out.println(maxValues[maxValues.length-1] * maxValues[maxValues.length-2]);
    }

    static void simulateRound(int lcm) {
        for (int i = 0; i < monkeys.length; i++) {
            Monkey current = monkeys[i];
            current.itemsInspected += current.items.size();
            while (current.items.size() > 0) {
                long firstOp = -1, secondOp = -1, old = current.items.pollFirst();
                if (current.firstOp.strip().equals("old")) {
                    firstOp = old;
                } else {
                    firstOp = Integer.parseInt(current.firstOp);
                }

                if (current.secondOp.strip().equals("old")) {
                    secondOp = old;
                } else {
                    secondOp = Integer.parseInt(current.secondOp);
                }

                if (current.operation == '*') {
                    old = firstOp * secondOp;
                } else if (current.operation == '+') {
                    old = firstOp + secondOp;
                }
                // Part A is old /= 3
                // old /= 3;

                // Part B is old %= lcm
                old %= lcm;
                if (old % current.test == 0) {
                    monkeys[current.success].items.add(old);
                } else {
                    monkeys[current.fail].items.add(old);
                }
            }
        }
    }
}