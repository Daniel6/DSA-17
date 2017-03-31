import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        List<char[][]> solns = Lists.newArrayList();

        for (int y = 0; y < n; y++) {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(board[i], '.');
            }
            board[0][y] = 'Q';
            solns.addAll(helper(n - 1, board, Sets.newHashSet(y)));
        }

        return solns;
    }

    public static List<char[][]> helper(int n, char[][] board, Set<Integer> badY) {
        List<char[][]> solns = Lists.newArrayList();

        if (n <= 0) {
            solns.add(board);
            return solns;
        }

        int x = board.length - n;

        for (int y = 0; y < board.length; y++) {
            if (!badY.contains(y) && !checkDiagonal(board, x, y)) {
                Set<Integer> myBadY = Sets.newHashSet(badY);
                myBadY.add(y);
                char[][] myBoard = copyOf(board);
                myBoard[x][y] = 'Q';
                solns.addAll(helper(n-1, myBoard, myBadY));
            }
        }

        return solns;
    }

    public static boolean containsBoard(List<char[][]> l, char[][] g, int n) {
        for (char[][] s : l) {
            if (isSameGrid(s, g, n)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSameGrid(char[][] s, char[][] s2, int n) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (s[x][y] != s2[x][y]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }

//        y = r + 1;
//        x = c + 1;
//        while (y < board.length && x < board[0].length) {
//            if (board[y][x] == 'Q') return true;
//            x++;
//            y++;
//        }

        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }

//        y = r + 1;
//        x = c - 1;
//        while (y < board.length && x >= 0) {
//            if (board[y][x] == 'Q') return true;
//            x--;
//            y++;
//        }

        return false;
    }
}
