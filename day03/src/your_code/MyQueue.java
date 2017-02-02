package your_code;

import ADTs.QueueADT;

import java.util.Collection;
import java.util.LinkedList;

/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {

    protected LinkedList<Integer> myLinkedList;

    public MyQueue() {
        myLinkedList = new LinkedList();
    }

    public MyQueue(Collection<Integer> elements) {
        myLinkedList = new LinkedList(elements);
    }

    @Override
    public void enqueue(Integer item) {
        myLinkedList.addLast(item);
    }

    @Override
    public Integer dequeue() {
        return myLinkedList.pop();
    }

    @Override
    public boolean isEmpty() {
        return myLinkedList.isEmpty();
    }

    @Override
    public Integer front() {
        return myLinkedList.peek();
    }
}