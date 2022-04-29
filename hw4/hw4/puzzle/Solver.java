package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class Searchnode implements Comparable<Searchnode> {
        private WorldState state;
        private int moves;
        private Searchnode pre;
        public Searchnode (WorldState ws, int m, Searchnode pre){
            this.state = ws;
            this.moves = m;
            this.pre = pre;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return moves;
        }

        public Searchnode getPre() {
            return pre;
        }

        @Override
        public int compareTo(Searchnode o) {
            return this.moves + this.state.estimatedDistanceToGoal()
                    -o.moves - o.state.estimatedDistanceToGoal();
        }
    }
    private MinPQ<Searchnode> openNodes = new MinPQ<>();
    private List<WorldState> bestsolution;
    private int totmoves;
    /** Cache the answer for later use */
    private void getAnswer(Searchnode goal) {
        totmoves = goal.moves;
        bestsolution = new ArrayList<>();
        Searchnode p =goal;
        while (p != null) {
            bestsolution.add(p.state);
            p = p.pre;
        }
    }

    public Solver(WorldState initial) {
        openNodes.insert(new Searchnode(initial, 0, null));
        while (true) {
            Searchnode node = openNodes.delMin();
            if (node.state.isGoal()) {
                getAnswer(node);
                return;
            }else {
                for (WorldState neighbor : node.state.neighbors()) {
                    if (node.pre == null || !neighbor.equals(node.pre.state)) {
                        openNodes.insert(new Searchnode(neighbor, node.moves + 1, node));
                    }
                }
            }
        }
    }
    public int moves () {
        return totmoves;
    }
    public Iterable<WorldState> solution() {
        List<WorldState> ret = new ArrayList<>();
        for (int i = totmoves; i >= 0; i--) {
            ret.add(bestsolution.get(i));
        }
        return ret;
    }
}
