package your_code;

import sun.awt.image.ImageWatched;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue extends MyQueue {

    private LinkedList<Integer> maxElementStack;

    public MyPriorityQueue() {
        super();
        maxElementStack = new LinkedList<>();
    }

    public MyPriorityQueue(Collection<Integer> elements) {
        super(elements);
        maxElementStack = new LinkedList<>();
    }

    public void enqueue(int item) {
        enqueue(new Integer(item));
    }

    public void enqueue(Integer item) {
        if (item != null) {
            if (!myLinkedList.isEmpty() && item.compareTo(myLinkedList.peek()) >= 0) {
                maxElementStack.push(item);
            }
            myLinkedList.push(item);
        }
    }

    /**
     * Return and remove the largest item on the queue.
     * O(n)
     */
    public int dequeueMax() {
        if (myLinkedList.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            myLinkedList.removeFirstOccurrence(maxElementStack.peek());
            return maxElementStack.pop().intValue();
        }
    }

}