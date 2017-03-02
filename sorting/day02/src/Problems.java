import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        RadixSort.radixSort(A, 10);
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        int max_char = getNthCharacter(A[0], n);
        int min_char = getNthCharacter(A[0], n);
        for (String s : A) {
            int char_val = getNthCharacter(s, n);
            if (char_val > max_char) {
                max_char = char_val;
            }
            if (char_val < min_char) {
                min_char = char_val;
            }
        }

        LinkedList<String>[] L = new LinkedList[max_char - min_char + 1];

        for (int i = 0; i < L.length; i++)
            L[i] = new LinkedList<>();

        for (String s : A) {
            // Extract the relevant digit from i, and add i to the corresponding Linked List.
            int char_val = getNthCharacter(s, n);
            L[char_val - min_char].add(s);
        }

        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            // Put all numbers in the linked lists into A
            for (String s : list) {
                A[j] = s;
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int i = 0; i < stringLength; i++) {
            countingSortByCharacter(S, i);
        }
    }

}
