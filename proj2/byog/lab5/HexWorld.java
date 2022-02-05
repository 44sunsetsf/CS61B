package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return
     */
    public static int hexRowWidth(int s,int i) {
        int effectiveI = i;
        if (i >= s){
            effectiveI = 2*s - 1 - i;
        }
        return s + 2*effectiveI;
    }
    public static int hexRowOffset(int s,int i) {
        int effectiveI = i;
        if (i >= s){
            effectiveI = 2*s - 1 - i;
        }
        return -effectiveI;
    }
    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
}
