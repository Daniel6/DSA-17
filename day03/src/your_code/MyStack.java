package your_code;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import ADTs.StackADT;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> myLinkedList;
    private LinkedList<Integer> maxElementStack;

    public MyStack() {
        myLinkedList = new LinkedList();
        maxElementStack = new LinkedList();
    }

    public MyStack(Collection<Integer> elements) {
        this();
        for (Integer e : elements) {
            push(e);
        }
    }

    @Override
    public void push(Integer e) {
        if (isEmpty() || e.compareTo(maxElementStack.peek()) >= 0) {
            maxElementStack.push(e);
        }
        myLinkedList.push(e);
    }

    @Override
    public Integer pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            if (myLinkedList.peek().compareTo(maxElementStack.peek()) == 0) {
                maxElementStack.pop();
            }
            return myLinkedList.pop();
        }
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
