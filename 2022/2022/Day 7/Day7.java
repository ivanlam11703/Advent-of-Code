import java.io.*;
import java.util.*;

public class Day7 {

    static List<Node> list = new ArrayList<>();
    
    static List<Node> preorder(Node root) {
        if (root == null) {
            return list;
        }
        list.add(root);
        for (int i = 0; i < root.subDirs.size(); i++) {
            preorder(root.subDirs.get(i));
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.replace("$ ", "");
                inputs.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        Node current = null;
        Node root = null;
        for (String s : inputs) {
            StringTokenizer st = new StringTokenizer(s);
            String token = st.nextToken();
            if (token.equals("ls")) {
                continue;
            } else if (token.equals("cd")) {
                String nextToken = st.nextToken();
                if (nextToken.equals("/")) {
                    root = new Node("/");
                    current = root;
                } else if (nextToken.equals("..")) {
                    current = current.parent;
                } else {
                    for (Node n : current.subDirs) {
                        if (n.filename.equals(nextToken)) {
                            current = n;
                            break; 
                        }
                    }
                }
            } else if (token.equals("dir")) {
                Node tempNode = new Node(st.nextToken());
                tempNode.parent = current;
                current.addSubDir(tempNode);
            } else {
                int filesize = Integer.parseInt(token);
                current.fileSizeSum += filesize;
                Node tempParent = current.parent;
                while (tempParent != null) {
                    tempParent.fileSizeSum += filesize;
                    tempParent = tempParent.parent;
                }
            }
        }
        preorder(root);

        // Part A
        // int answer = 0;
        // for (Node n : list) {
        //     if (n.fileSizeSum <= 100000) {
        //         answer += n.fileSizeSum;
        //     }
        // }
        // System.out.println(answer);

        // Part B
        int spaceNeeded = 30000000 - (70000000 - root.fileSizeSum);
        int currentMin = Integer.MAX_VALUE;
        for (Node n : list) {
            if (n.fileSizeSum > spaceNeeded && n.fileSizeSum < currentMin) {
                currentMin = n.fileSizeSum;
            }
        }
        System.out.println(currentMin);
    }
}