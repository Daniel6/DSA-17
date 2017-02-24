
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j >= 1 && array[j] < array[j-1]) {
                int t = array[j];
                array[j] = array[j-1];
                array[j-1] = t;
                j--;
            }
        }

        return array;
    }
}
