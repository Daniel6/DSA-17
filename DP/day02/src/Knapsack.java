import java.util.Arrays;

public class Knapsack {

    public static int maxValue(int knapsackSize, int[] S, int[] V) {
        // Memo maps from # of items considered -> max possible capacity
        int[][] memos = new int[S.length][knapsackSize + 1];
        for (int i = 0; i < memos.length; i++) {
            for (int j = 0; j < knapsackSize+1; j++) {
                memos[i][j] = -1;
            }
        }

        System.out.println("S " + Arrays.toString(S));
        System.out.println("V " + Arrays.toString(V));
        int soln = helper(0, 0, 0, S, V, knapsackSize, memos);

        System.out.println(soln);
        return soln;
    }

    private static int helper(int i, int totalValue, int totalSpace, int[] S, int[] V, int knapsackSize, int[][] memos) {
        if (i >= S.length) {
            return totalValue;
        }

        if (totalSpace == knapsackSize) {
            memos[i][totalSpace] = totalValue;
            return totalValue;
        } else if (totalSpace > knapsackSize) {
            return -1;
        }

        if (memos[i][totalSpace] == -1) {
            if (totalSpace + S[i] <= knapsackSize) {
                memos[i][totalSpace] = Math.max(
                    memos[i][totalSpace],
                    helper(i+1, totalValue + V[i], totalSpace + S[i], S, V, knapsackSize, memos));
            }
            memos[i][totalSpace] = Math.max(
                memos[i][totalSpace],
                helper(i+1, totalValue, totalSpace, S, V, knapsackSize, memos)
            );
        }

        return memos[i][totalSpace];
    }

}
