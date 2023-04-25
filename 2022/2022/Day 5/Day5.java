import java.io.*;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        List<String> instructions = new ArrayList<>();

        LinkedList<Character> stack1 = new LinkedList<>();
        LinkedList<Character> stack2 = new LinkedList<>();
        LinkedList<Character> stack3 = new LinkedList<>();
        LinkedList<Character> stack4 = new LinkedList<>();
        LinkedList<Character> stack5 = new LinkedList<>();
        LinkedList<Character> stack6 = new LinkedList<>();
        LinkedList<Character> stack7 = new LinkedList<>();
        LinkedList<Character> stack8 = new LinkedList<>();
        LinkedList<Character> stack9 = new LinkedList<>();
        List<LinkedList<Character>> stacks = new ArrayList<>();
        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
        stacks.add(stack5);
        stacks.add(stack6);
        stacks.add(stack7);
        stacks.add(stack8);
        stacks.add(stack9);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                line = line.replace("["," ");
                line = line.replace("]"," ");
                int stack = 0;
                for (int i = 1; i < line.length(); i+=4) {
                    if (line.charAt(i) != ' ') {
                        stacks.get(stack).addLast(line.charAt(i));
                    }
                    stack++;
                }
            }
            while ((line = br.readLine()) != null) {
                line = line.replace("move", "");
                line = line.replace("from", "");
                line = line.replace("to", "");
                instructions.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Part A
        // for (String s : instructions) {
        //     StringTokenizer st = new StringTokenizer(s);
        //     int numToMove = Integer.parseInt(st.nextToken());
        //     int from = Integer.parseInt(st.nextToken())-1;
        //     int to = Integer.parseInt(st.nextToken())-1;
        //     for (int i = 0; i < numToMove; i++) {
        //         if(stacks.get(from).size() > 0){
        //             stacks.get(to).addFirst(stacks.get(from).pollFirst());
        //         }
        //     }
        // }

        // Part B
        for (String s : instructions) {
            StringTokenizer st = new StringTokenizer(s);
            int numToMove = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            numToMove = Math.min(numToMove, stacks.get(from).size());
            for (int i = numToMove; i > 0; i--) {
                if(stacks.get(from).size() > 0){
                    stacks.get(to).addFirst(stacks.get(from).get(i-1));
                    stacks.get(from).remove(i-1);
                }
            }
        }
        
        String answer = "";
        for (int i = 0; i < stacks.size(); i++) {
            if (stacks.get(i).size() > 0) {
                answer += stacks.get(i).peek();
            }
        }
        System.out.println(answer);
    }
}