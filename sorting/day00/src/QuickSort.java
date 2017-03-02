import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            if (low < p - 1) {
                quickSort(a, low, p - 1);
            }
            if (p < high) {
                quickSort(a, p, high);
            }
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int low, int high) {
        int pivot = array[low];

        array[low] = array[(low + high) / 2];
        array[(low + high) / 2] = pivot;

        int i = low;
        int j = high;
        int t;

        while (i <= j) {
            while (array[i] < pivot)
                i++;
            while (array[j] > pivot)
                j--;
            if (i <= j) {
                t = array[i];
                array[i] = array[j];
                array[j] = t;
                i++;
                j--;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        QuickSort s = new QuickSort();

        int[] c = new int[] {5,6,8,3,1,10,2,5};
        System.out.println("Original:    " + ArrayUtils.toString(c));

        int[] partitioned = new int[] {2,3,1,5,8,10,6,5};
        int finalIndex = s.partition(c, 0, 7);
        System.out.println("Partitioned: " + ArrayUtils.toString(c));
        System.out.println("   Expected: " + ArrayUtils.toString(partitioned));

        c = new int[] {5,6,8,3,1,10,2,5};
        System.out.println("Sorted:      " + ArrayUtils.toString(s.sort(c)));

        System.out.println();
        c = new int[] {1,5,2,6,8,3,7,3,6,78,2,1,1,7,7,0,-1,1,-1,-2,-7,-5,0,5,2};
        System.out.println("Sorted:      " + ArrayUtils.toString(s.sort(c)));
    }
}
