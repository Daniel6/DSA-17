import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static List<Integer> justifyText(String[] w, int m) {
        List<Integer> solution = new ArrayList<Integer>();
        // Spaces needed if words from i to j are in a line
        int[][] spaces = new int[w.length+1][w.length+1];
        // Cost of a line made up of words from i to j
        int[][] costs = new int[w.length+1][w.length+1];
        // Lowest possible cost for arrangement of words up to i
        int[] solns = new int[w.length+1];
        int[] splits = new int[w.length+1];

        for (int i = 1; i <= w.length; i++) {
            spaces[i][i] = m - w[i-1].length();
            for (int j = i+1; j <= w.length; j++) {
                spaces[i][j] = spaces[i][j-1] - w[j-1].length() - 1;
            }
        }

        for (int i = 1; i <= w.length; i++) {
            for (int j = i; j <= w.length; j++) {
                if (spaces[i][j] < 0) {
                    costs[i][j] = Integer.MAX_VALUE;
                } else if (j == w.length && spaces[i][j] > 0) {
                    costs[i][j] = 0;
                } else {
                    costs[i][j] = (int) Math.pow(spaces[i][j], 2);
                }
            }
        }

        solns[0] = 0;
        for (int j = 1; j <= w.length; j++) {
            solns[j] = Integer.MAX_VALUE;
            for (int i = 1; i <= j; i++) {
                if (solns[i-1] != Integer.MAX_VALUE && costs[i][j] != Integer.MAX_VALUE && (solns[i-1] + costs[i][j] < solns[j])) {
                    solns[j] = solns[i-1] + costs[i][j];
                    splits[j] = i;
                }
            }
        }

        helper(splits, splits.length-1, solution);

        return solution;
    }

    private static int helper(int[] p, int n, List<Integer> solution) {
        int k;
        if (p[n] == 1) {
            k = 1;
        } else {
            k = helper(p, p[n] - 1, solution) + 1;
        }
        solution.add(p[n] - 1);
        return k;
    }
}