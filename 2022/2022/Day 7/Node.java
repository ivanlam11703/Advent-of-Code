import java.util.*;

public class Node {
    public Node parent;
    public String filename;
    public ArrayList<Node> subDirs;
    public int fileSizeSum;

    public Node(String name) {
        parent = null;
        subDirs = new ArrayList<>();
        fileSizeSum = 0;
        filename = name;
    }

    public void addSubDir(Node n) {
        subDirs.add(n);
    }
}