package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean isFound = false;
    private int[] nodeto;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeto = new int[maze.N() * maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(-1, 0);
    }

    // Helper methods go here
    private void dfs (int u, int v) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                nodeto[w] = v;
                dfs(v, w);
            }else if (w != u) {
                edgeTo[w] = v;
                announce();
                for (int x = v; x != w; x = nodeto[x]) {
                    edgeTo[x] = nodeto[x];
                    announce();
                }
                isFound = true;
            }
            if (isFound) return;
        }
    }
}

