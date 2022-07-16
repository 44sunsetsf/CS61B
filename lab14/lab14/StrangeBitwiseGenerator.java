package lab14;
import lab14lib.*;
public class StrangeBitwiseGenerator implements Generator{
    private int state;
    private int period;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

    @Override
    public double next() {
        state = state + 1;
        int weirdState = state & (state >>> 3) % period;
        weirdState = state & (state >> 3) & (state >> 8) % period;
        return normalize(weirdState % period);
    }
    private double normalize (int i) {
        return (2 * i) / (0.0 + period - 1) - 1;
    }
}
