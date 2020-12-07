public class Stupa extends Flying {
    public Stupa() {
        velocity = 8;
    }

    @Override
    public double calcDistanceReducer() {
        return 0.06;
    }
}
