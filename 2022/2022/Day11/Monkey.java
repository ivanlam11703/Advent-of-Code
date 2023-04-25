import java.util.*;

public class Monkey {
    public LinkedList<Long> items;
    public char operation;
    public String firstOp, secondOp;
    public int test, success, fail, itemsInspected;

    Monkey() {
        items = new LinkedList<>();
        operation = '\0';
        firstOp = secondOp = "";
        itemsInspected = 0;
    }

    void showItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.print(items.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}