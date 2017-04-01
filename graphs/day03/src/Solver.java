import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;
    private State root;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves;
        public int cost;
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            cost = board.manhattan() + this.moves;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        /*
         * Return the cost difference between two states
         */
        @Override
        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        State s = state;
    	while (s.prev != null) {
    	    s = s.prev;
        }
        return s;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
    	this.root = new State(initial, 0, null);
    }

    /*
     * Is the input board a solvable state
     */
    public boolean isSolvable() {
    	return root.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        List<Board> solution = Lists.newArrayList();
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return o1.compareTo(o2);
            }
        });

        pq.add(root);
        while (!pq.isEmpty()) {
            State s = pq.poll();
            System.out.println(s.cost);
            s.board.printBoard();
            if (s.board.isGoal()) {
                // Add all boards to solution set
                solution.add(s.board);
                State curr = s;
                while (curr.prev != null) {
                    curr = curr.prev;
                    solution.add(0, curr.board);
                }
                break;
            } else {
                for (Board neighbor : s.board.neighbors()) {
                    State n = new State(neighbor, s.moves + 1, s);
                    pq.add(n);
                }
            }
        }

        return solution;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space: Your solution can have whatever output you find useful
     * Optional challenge: create a command line interaction for users to input game
     * states
     */
    public static void main(String[] args) {
        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
        Board initial = new Board(initState);

        // Solve the puzzle
        Solver solver = new Solver(initial);
        System.out.println("Solver Created.");
        if (!solver.isSolvable())
            System.out.println("No solution");
        else {
            for (Board board : solver.solution()) {
                board.printBoard();
            }
            System.out.println(solver.minMoves);
        }
    }


}
