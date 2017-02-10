package your_code;

import sun.awt.image.ImageWatched;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue extends MyQueue {

    public void enqueue(int item) {
        this.enqueue(new Integer(item));
    }

    public void enqueue(Integer item) {
        if (item != null) {
            super.enqueue(item);
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
            Integer max = myLinkedList.peek();
            Iterator<Integer> iterator = myLinkedList.iterator();
            while(iterator.hasNext()) { // O(n)
                Integer el = iterator.next();
                if (el.compareTo(max) > 0) {
                    max = el;
                }
            }
            myLinkedList.removeFirstOccurrence(max); // O(n)
            return max.intValue();
        }
    }

}