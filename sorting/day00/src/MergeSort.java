import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     *
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        } else {
            int[] first_half = Arrays.copyOfRange(array, 0, array.length/2);
            int[] second_half = Arrays.copyOfRange(array, array.length/2, array.length);
            int[] res = merge(sort(first_half), sort(second_half));
//            System.out.println("Merge Result = " + ArrayUtils.toString(res));
            return res;
        }
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {
//        System.out.println(ArrayUtils.toString(a) + " merging with " + ArrayUtils.toString(b));
        int[] res;
        if (a.length + b.length <= INSERTION_THRESHOLD) {
            InsertionSort is = new InsertionSort();
            res = is.sort(ArrayUtils.addAll(a, b));
        } else {
            int len = a.length + b.length;
            res = new int[len];
            int j = 0;
            int k = 0;
            for (int i = 0; i < len; i++) {
                if (j >= a.length) {
                    res[i] = b[k];
                    k++;
                } else if (k >= b.length) {
                    res[i] = a[j];
                    j++;
                } else {
                    if (a[j] < b[k]) {
                        res[i] = a[j];
                        j++;
                    } else {
                        res[i] = b[k];
                        k++;
                    }
                }
            }
        }
//        System.out.println("res = " + ArrayUtils.toString(res));
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 7, 2, 8, 4, 5, 5, 0, 2, 3, -1};
        System.out.println(a.length + " " + ArrayUtils.toString(a));
        MergeSort s = new MergeSort();
        a = s.sort(a);
        System.out.println(a.length + " " + ArrayUtils.toString(a));
    }

}
