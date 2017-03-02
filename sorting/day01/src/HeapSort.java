import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Recursively corrects the position of element indexed i: check children, and swap with larger child if necessary.
    public void heapify(int index) {
        if (index < size && index >= 0) {
            int left_index = leftChild(index);
            int right_index = rightChild(index);
            int max_index = index;

            if (left_index < size && heap[left_index] > heap[max_index]) {
                max_index = left_index;
            }

            if (right_index < size && heap[right_index] > heap[max_index]) {
                max_index = right_index;
            }

            if (max_index != index) {
                int temp = heap[index];
                heap[index] = heap[max_index];
                heap[max_index] = temp;
                heapify(max_index);
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position.
    public void buildHeapFrom(int[] array) {
        this.heap = array;
        this.size = array.length;

        // Iterate backwards through array, this looks at the "bottom" of the heap first
        for (int i = (size - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Best-case runtime: O(n)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        buildHeapFrom(array);

        for (int i=size-1; i>0; i--) {
            int t = heap[0];
            heap[0] = heap[i];
            heap[i] = t;

            size = i;
            heapify(0);
        }

        return heap;
    }
}
