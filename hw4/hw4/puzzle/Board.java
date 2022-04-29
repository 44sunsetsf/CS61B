package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState{
    private final int[][] tiles;
    private static final int BLANK = 0;
    private int N;
    private int estimatedDist;

    public Board(int[][] tiles) {
        if (tiles == null || tiles[0] == null|| tiles.length !=
        tiles[0].length) {
            throw new IllegalArgumentException();
        }
        estimatedDist = -1;
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public int size(){
        return N ;
    }
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0||j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if (tiles[i][j] == 0) {
                    continue;
                }else if (tiles[i][j] != i * N + j + 1) {
                    dist++;
                }
            }
        }
        return dist;
    }
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    continue;
                }else {
                    int targeti = (tiles[i][j] - 1) / N;
                    int targetj = (tiles[i][j] -1) % N;
                    dist += Math.abs(targeti - i);
                    dist +=Math.abs(targetj - j);
                }
            }
        }
        return dist;
    }
    @Override
    public int estimatedDistanceToGoal() {
        if (estimatedDist == -1) {
            estimatedDist = manhattan();
        }
        return estimatedDist;
    }
    @Override
    public boolean equals (Object y){
        if (y == this) return true;
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board other = (Board) y;
        if (this.N != ((Board) y).N) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tiles[i][j] != other.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode () {
        return super.hashCode();
    }

    /** Returns the string representation of the board.*/
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
