import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class RadixSort {

    /**
     * @param n the digit number, 0 is least significant
     * @return
     */
    private static int getNthDigit(int number, int base, int n) {
        return number / ((int) Math.pow(base, n)) % base;
    }


    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByDigit(int[] A, int b, int n) {
        LinkedList<Integer>[] L = new LinkedList[2 * b];

        for (int i = 0; i < L.length; i++)
            L[i] = new LinkedList<>();

        for (int i : A) {
            // Extract the relevant digit from i, and add i to the corresponding Linked List.
            int digit = getNthDigit(i, b, n);
            L[digit + b].add(i);
        }

        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            // Put all numbers in the linked lists into A
            for (Integer i : list) {
                A[j] = i;
                j++;
            }
        }
    }

    /**
     * Runtime in terms of n, b, and w: O(wb + wn)
     *
     * n: length of array
     * w: word length of integers A in base b (equal to log base b of k (log_b k) )
     *
     * @param b The base to use for radix sort
     */
    static void radixSort(int[] A, int b) {
        // Calculate the upper-bound for numbers in A
        int k = Math.abs(A[0]) + 1;
        for (int i = 1; i < A.length; i++)
            k = (Math.abs(A[i]) + 1 > k) ? Math.abs(A[i]) + 1 : k;
        int w = (int) Math.ceil(Math.log(k) / Math.log(b)); // w = log base b of k, word length of numbers

        // Perform radix sort
        for (int digit = 0; digit < w; digit++) {
            countingSortByDigit(A, b, digit);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {10,-53,-96,-25,-90,-7,-57,41,-15,50,-51,28,-89,94,-53,-42,-66,50,15,36,35,-94,11,84,-33,-63,-91,67,34,-93,27,-10,-42,-47,-64,-16,42,39,61,-41,61,-14,-11,65,3,-17,20,-100,42,-100,53,-17,25,4,-12,79,-60,-17,-47,86,72,12,46,-75,59,-50,-1,10,-6,1,-31,-25,75,56,17,43,48,-8,-18,-49,-61,-73,96,18,-30,-13,12,-20,91,-68,-89,4,34,85,-46,-58,97,40,50,-61};
        System.out.println("Unsorted: " + ArrayUtils.toString(arr));
        radixSort(arr, 10);
        System.out.println("Sorted:   " + ArrayUtils.toString(arr));
    }
}
