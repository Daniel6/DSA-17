public class EditDistance {

    private static int[][] memos;

    // Top-down method
    public static int minEditDist(String a, String b) {
        memos = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < a.length()+1; i++) {
            for (int j = 0; j < b.length()+1; j++) {
                memos[i][j] = Integer.MAX_VALUE;
            }
        }

        return helper(a.length(), b.length(), a, b);
    }

    private static int helper(int i, int j, String a, String b) {
        if (i == 0) return j;
        if (j == 0) return i;

        if (a.charAt(i-1) == b.charAt(j-1)) return helper(i-1, j-1, a, b);

        int soln = 1 + Math.min(Math.min(helper(i, j-1, a, b), helper(i-1, j, a, b)), helper(i-1, j-1, a, b));
        if (soln < memos[i][j]) memos[i][j] = soln;
        return memos[i][j];
    }
}
