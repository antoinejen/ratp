import java.util.ArrayList;

/**
 * Created by antoine on 06/06/17.
 */
public class Stack {

    private ArrayList<Integer> stack = new ArrayList<>();

    public void push(int i) {
        stack.add(i);
    }

    public int pop() {
        int i = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return i;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
