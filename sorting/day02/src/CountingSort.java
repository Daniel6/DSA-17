import org.apache.commons.lang3.ArrayUtils;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + d) where n is number of elements in A and d is the largest element in A
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max_el = A[0];
        int min_el = A[0];

        // Get max and min elements of A
        for (int i : A) {
            if (i > max_el) {
                max_el = i;
            }
            if (i < min_el) {
                min_el = i;
            }
        }

        // Initialized to all zeroes
        int[] counter = new int[(max_el - min_el) + 1];

        for (int i : A) {
            counter[i - min_el] += 1;
        }

        int i = 0;
        for (int j = 0; j < counter.length; j++) {
            for (int count = counter[j]; count > 0; count--) {
                A[i] = j + min_el;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-5, 6, 5, 8, 2, 5, 9, 7, 8, 1, -6, -2, -1, 2, 0, 5, 4, 6, 4, 2, 5, -2, 0, -1, -5, -4};
        System.out.println("Unsorted: " + ArrayUtils.toString(arr));
        countingSort(arr);
        System.out.println("Sorted:   " + ArrayUtils.toString(arr));
    }

}
