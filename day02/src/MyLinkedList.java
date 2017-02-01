public class MyLinkedList<T> {

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		T val;
		Node prev;
		Node next;

		private Node(T d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(T c) {
		addLast(c);
	}

	public T pop() {
		return removeLast();
	}

	public void addLast(T c) {
	    Node newNode = new Node(c, null,null);
		if (size == 0) {
		    head = newNode;
		    tail = newNode;
		    size = 1;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }
	}

	public void addFirst(T c) {
        Node newNode = new Node(c, null,null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size = 1;
        } else {
		    head.prev = newNode;
		    newNode.next = head;
		    head = newNode;
		    size++;
        }
	}

	public T get(int index) {
		Node currNode = null;
		if (index <= size/2 && index >= 0) {
		    currNode = head;
		    for (int i = 0; i < index; i++) {
		        currNode = currNode.next;
            }
            return currNode.val;
        } else if (index < size) {
		    currNode = tail;
		    for (int i = size-1; i > index; i--) {
		        currNode = currNode.prev;
            }
            return currNode.val;
        } else {
		    throw new IndexOutOfBoundsException("Index out of bounds.");
        }
	}

	public T remove(int index) {
		Node currNode = head;
		for (int i = 0; i < index; i++) {
		    currNode = currNode.next;
        }

        if (currNode.prev != null) {
			currNode.prev.next = currNode.next;
		}

		if (currNode.next != null) {
			currNode.next.prev = currNode.prev;
		}

		size--;
		return currNode.val;
	}

	public T removeFirst() {
		if (head != null) {
			T oldHead = head.val;
			head = head.next;
			if (head != null) {
				head.prev = null;
			}
			size--;
			return oldHead;
		}
		return null;
	}

	public T removeLast() {
		if (tail != null) {
			T oldTail = tail.val;
			tail = tail.prev;
			if (tail != null) {
				tail.next = null;
			}
			size--;
			return oldTail;
		}
		return null;
	}
}