public class DLL {
    public Node head, tail;
    public long numElements;

    DLL() {
        head = tail = null;
        numElements = 0;
    }

    void append(Node temp) {
        if (tail == null) {
            head = tail = temp;
        }
        temp.prev = tail;
        tail.next = temp;
        tail = temp;
        numElements++;
    }

    void show() {
        System.out.print("[" + head.val + "]");
        for (Node temp = head.next; temp != null; temp = temp.next) {
            if (head == temp ) {
                break;
            }
            System.out.print("[" + temp.val + "]");
        }
        System.out.println();
    }
}
