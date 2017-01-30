import java.util.Arrays;

public class MyArrayList<T> {
    private T[] elems;
	private int size;
	private int nextEmptyIndex = 0;

	public MyArrayList() {
		size = 10;
		elems = (T[]) new Object[10];
	}

	public MyArrayList(int capacity) {
		size = capacity;
		elems = (T[]) new Object[capacity];
	}

	public void add(T c) {
		elems[nextEmptyIndex] = c;
		nextEmptyIndex++;
		resize();
	}

	public int size() {
		// The next empty index is always equivalent to the number of cows in the array
		return nextEmptyIndex;
	}

	public T get(int index) {
		if (index >= nextEmptyIndex) {
			throw new IndexOutOfBoundsException("No cow at that index");
		} else {
			return elems[index];
		}
	}

	public T remove(int index) {
		T removedCow = elems[index];
		for (int i = index; i < nextEmptyIndex; i++) {
            elems[i] = elems[i + 1];
        }
        nextEmptyIndex--; // the number of cows in the array has decreased by 1

        resize();
		return removedCow;
	}

	public void add(int index, T c) {
	    // You can only insert cows in between indices containing cows.
        if (index >= nextEmptyIndex) {
            throw new IndexOutOfBoundsException("Cannot insert cow between non-cow indices");
        } else {
            // shift all the elements to the right of the insertion index
            for (int i = nextEmptyIndex; i > index; i--) {
                elems[i] = elems[i-1];
            }
            elems[index] = c;
            nextEmptyIndex++;
        }

        resize();
	}

	/*
	    If the arraylist is <25% full, halve the capacity.
	    If the arraylist is full, double the capacity.
	 */
	private void resize() {
        if (nextEmptyIndex >= size) {
            elems = Arrays.copyOf(elems, size * 2);
            size *= 2;
        } else if (nextEmptyIndex < size/4 && size >= 20) {
            elems = Arrays.copyOf(elems, size / 2);
            size /= 2;
        }
    }
}
