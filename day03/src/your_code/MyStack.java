package your_code;

import java.util.Collection;
import java.util.LinkedList;
import ADTs.StackADT;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> myLinkedList;
    private MyStack maxElementStack;

    public MyStack() {
        myLinkedList = new LinkedList();
        maxElementStack = new MyStack();
    }

    public MyStack(Collection<Integer> elements) {
        myLinkedList = new LinkedList(elements);
        maxElementStack = new MyStack();
    }

    @Override
    public void push(Integer e) {
        if (e.compareTo(maxElementStack.peek()) >= 0) {
            maxElementStack.push(e);
        }
        myLinkedList.push(e);
    }

    @Override
    public Integer pop() {
        if (myLinkedList.peek().compareTo(maxElementStack.peek()) == 0) {
            maxElementStack.pop();
        }
        return myLinkedList.pop();
    }

    @Override
    public boolean isEmpty() {
        return myLinkedList.isEmpty();
    }

    @Override
    public Integer peek() {
        return myLinkedList.peek();
    }

    public Integer maxElement() {
        return maxElementStack.peek();
    }
}
