package lab14;
import lab14lib.*;
import java.util.ArrayList;
public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    @Override
    public double next() {
        state = state + 1;
        return normalize(state % period);
    }
    private double normalize(int i) {
        return 2 * i / (0.0 + period - 1) - 1;
    }
}
