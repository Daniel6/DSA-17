import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private HashMap<Integer, int[]> goalPositions;

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        tiles = b;

        // populate goalPositions
        // TODO: verify
        goal = new int[b.length][b[0].length];
        int n = 1;
        goalPositions = new HashMap<>();
        for (int y = 0; y < b.length; y++) {
            for (int x = 0; x < b.length; x++) {
                goal[y][x] = n;
                goalPositions.put(n, new int[]{x, y});
                n++;
                if (n == b.length * b[0].length) n = 0;
            }
        }
    }

    /*
     * Size of the board (equal to 3 for 8 puzzle, but in theory the Board
     * class should  work for any puzzle size)
     */
    private int size() {
        return tiles.length;
    }

    public int applyHeuristic(Heuristic h) {
        if (h == null) {
            return manhattan();
        } else {
            return h.getCost(tiles);
        }
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     * Estimated cost from the current node to the goal for A* (h(n))
     */
    public int manhattan() {
        int[][] t = tiles;
        HashMap<Integer, int[]> g = goalPositions;

        int m = 0; // manhattan distance

        int i, j; // target position;
        int[] targetPosition;

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                if (tiles[y][x] != 0) {
                    targetPosition = goalPositions.get(tiles[y][x]);
                    m += abs(x - targetPosition[0]) + abs(y - targetPosition[1]);
                }
            }
        }
		// TODO: verify
        return m;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        if (tiles == null) {
            return false;
        }
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                if (tiles[y][x] != goal[y][x]) return false;
            }
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     */
    public boolean solvable() {
        if (tiles != null) {
            int inversions = 0;

            List<Integer> listified = Lists.newArrayList();
            for (int[] row : tiles) {
                for (int i : row) {
                    if (i != 0) {
                        listified.add(i);
                    }
                }
            }

            for (int i = 0; i < listified.size(); i++) {
                for (int j = i + 1; j < listified.size(); j++) {
                    if (listified.get(j) > listified.get(i)) {
                        inversions++;
                    }
                }
            }

            return !(inversions % 2 == 1);
        }
        return false;
    }

    /*
     * Return the neighboring boards in the state tree
     * One possible method: Create a duplicate board state, try moving the
     * blank space up, down, left, and right, and if the resulting board state
     * is valid, add it to an accumulator.
     */
    public Iterable<Board> neighbors() {
        HashSet<Board> neighbors = Sets.newHashSet();
        if (tiles != null) {

            // Find empty space
            int r = 0;
            int c = 0;
            for (int row = 0; row < tiles.length; row++) {
                for (int column = 0; column < tiles[row].length; column++) {
                    if (tiles[row][column] == 0) {
                        r = row;
                        c = column;
                    }
                }
            }

            if (r < tiles.length - 1) {
                // Try shifting down
                Board b = shiftAndCopy(this, r, c, 1, 0);
                if (b != null) {
                    neighbors.add(b);
                }
            }

            if (r > 0) {
                // Try shifting up
                Board b = shiftAndCopy(this, r, c, -1, 0);
                if (b != null) {
                    neighbors.add(b);
                }
            }

            if (c < tiles[r].length - 1) {
                // Try shifting right
                Board b = shiftAndCopy(this, r, c, 0, 1);
                if (b != null) {
                    neighbors.add(b);
                }
            }

            if (c > 0) {
                // Try shifting left
                Board b = shiftAndCopy(this, r, c, 0, -1);
                if (b != null) {
                    neighbors.add(b);
                }
            }

        }
        return neighbors;
    }

    /*
        either dr or dc must be 0
     */
    public Board shiftAndCopy(Board b, int r, int c, int dr, int dc) {
        if (dr != 0 && dc != 0) {
            return null;
        }
        int[][] altTiles = copyOf(b.tiles);

        int t = altTiles[r+dr][c+dc];
        altTiles[r+dr][c+dc] = altTiles[r][c];
        altTiles[r][c] = t;
        Board altBoard = new Board(altTiles);
        if (altBoard.solvable()) {
            return altBoard;
        }
        return null;
    }

    /*
     * Prints out the board state nicely for debugging purposes
     */
    public void printBoard() {
        if (tiles != null) {
            for (int[] tile : tiles) {
                for (int aTile : tile) System.out.print(aTile + " ");
                System.out.println();
            }
            System.out.println();
        }
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static int[][] copyOf(int[][] A) {
        int[][] B = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{5, 6, 4}, {7, 1, 8}, {2, 0, 3}};
        Board board = new Board(initState);

        board.printBoard();
        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for (Board b : it)
            b.printBoard();
    }
}
