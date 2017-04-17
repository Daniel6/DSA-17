import java.util.Arrays;

public class DungeonGame {

    private static int[][] memos;

    // Bottom-up
    public static int minInitialHealth(int[][] map) {
        memos = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                memos[i][j] = Integer.MAX_VALUE;
            }
        }

        int i = map.length-1;
        int j = map[0].length-1;
        if (i < 0 || j < 0) {
            return -1;
        }
        memos[i][j] = -1*map[i][j]+1; // 0 coffee needed if starting at sidd's location

        helper(i, j, map);

        return memos[0][0];
    }

    private static void helper(int i, int j, int[][] map) {
        if (i > 0) {
            int left = Math.max((-1*map[i-1][j]) + memos[i][j], 0);
            if (left < memos[i-1][j]) {
                memos[i-1][j] = left;
                helper(i-1, j, map);
            }
        }

        if (j > 0) {
            int up = Math.max((-1*map[i][j-1]) + memos[i][j], 0);
            if (up < memos[i][j-1]) {
                memos[i][j-1] = up;
                helper(i, j-1, map);
            }
        }
    }
}
