import java.io.*;
import java.util.*;

public class Day20 {
    public static void main(String[] args) {
        List<Long> inputs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null){
                inputs.add(Long.parseLong(line));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        DLL original = new DLL();
        DLL edit = new DLL();
        Map<Node,Node> map = new HashMap<>();

        // Part B
        for (Long x : inputs) {
            Long tempVal = x * 811589153;
            Node temp = new Node(tempVal);
            Node temp1 = new Node(tempVal);
            edit.append(temp);
            original.append(temp1);
            map.put(temp1,temp);
        }
        edit.head.prev = edit.tail;
        edit.tail.next = edit.head;
        int size = inputs.size()-1;
        for (int x = 0; x < 10; x++) {
            for (Node temp = original.head; temp != null; temp = temp.next) {
                Node n = map.get(temp);
                Node current = n;
                if (current.val > 0) {
                    for (long i = 0; i < n.val % size; i++) {
                        current = current.next;
                    }
                    mix(n,current);
                } else if (current.val < 0) {
                    for (long i = 0; i <= Math.abs(n.val) % size; i++) {
                        current = current.prev;
                    }
                    mix(n,current);
                }
            }
        }
        System.out.println(findAnswerPart2(edit.head));

        // Part A
        // for (Long x : inputs) {
        //     Node temp = new Node(x);
        //     Node temp1 = new Node(x);
        //     edit.append(temp);
        //     original.append(temp1);
        //     map.put(temp1,temp);
        // }
        // edit.head.prev = edit.tail;
        // edit.tail.next = edit.head;
        // for (Node temp = original.head; temp != null; temp = temp.next) {
        //     Node n = map.get(temp);
        //     Node current = n;
        //     if (current.val > 0) {
        //         for (int i = 0; i < n.val; i++) {
        //             current = current.next;
        //         }
        //         mix(n,current);
        //     } else if (current.val < 0) {
        //         for (int i = 0; i <= Math.abs(n.val); i++) {
        //             current = current.prev;
        //         }
        //         mix(n,current);
        //     }
        // }
        // System.out.println(findAnswerPart1(edit.head));
    }

    static void mix(Node n, Node current) {
        if (n == current) {
            return;
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
        current.next.prev = n;
        Node temp = current.next;
        current.next = n;
        n.next = temp;
        n.prev = current;
    }

    static long findAnswerPart1(Node n) {
        Node temp = n;
        long answer = 0;
        while (temp.val != 0) {
            temp = temp.next;
        }
        for (int i = 0; i < 3000; i++) {
            if (i % 1000 == 0) {
                answer += temp.val;
            }
            temp = temp.next;
        }
        return answer + temp.next.val;
    }

    static long findAnswerPart2(Node n) {
        Node temp = n;
        long answer = 0;
        while (temp.val != 0) {
            temp = temp.next;
        }
        for (int i = 0; i <= 3000; i++) {
            if (i % 1000 == 0) {
                answer += temp.val;
            }
            temp = temp.next;
        }
        return answer;
    }
}