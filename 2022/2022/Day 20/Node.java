public class Node {
    public long val;
    public Node next;
    public Node prev;

    Node(long val) {
        this.val = val;
        next = null;
        prev = null;
    }
}