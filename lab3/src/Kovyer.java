public class Kovyer extends Flying{
    public Kovyer() {
        velocity = 10;
    }

    @Override
    public double calcDistanceReducer() {
        if (distanceToCover < 1000)
            return 0;
        else if (distanceToCover < 5000)
            return 0.03;
        else if (distanceToCover < 10000)
            return 0.1;
        else
            return 0.05;
    }
}
