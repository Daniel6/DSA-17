import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextJustification {

    private static int[] memos;

    public static List<Integer> justifyText(String[] w, int m) {
        memos = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            memos[i] = Integer.MAX_VALUE;
        }
        helper(w, 0, m, 0);

        System.out.println(Arrays.toString(memos));
        List<Integer> soln = new ArrayList<Integer>();

        return soln;
    }

    private static int helper(String[] words, int wordsProcessed, int lineLength, int oldCost) {
        if (wordsProcessed == words.length) {
            memos[wordsProcessed - 1] = oldCost;
            return oldCost;
        }
        if (memos[wordsProcessed] < oldCost) {
            return memos[wordsProcessed];
        }


        for (int i = wordsProcessed; i < words.length; i++) {
            int cost = cost(words, wordsProcessed, i, lineLength);
            if (cost >= 0) {
                int soln = helper(words, i+1, lineLength, cost + oldCost);
                if (soln < memos[wordsProcessed]) memos[wordsProcessed] = soln;
            } else {
                break;
            }
        }

        return memos[wordsProcessed];
    }

    private static int cost(String[] words, int i, int j, int lineLength) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += words[k].length();
        }
        sum += j-i;
        return (int) Math.pow(lineLength - sum, 3);
    }

    private static class Solution {
        public static int cost;
        public static List<Integer> breaks;
        public Solution(int cost, List<Integer> breaks) {
            this.cost = cost;
            this.breaks = breaks;
        }
    }
}