public class Metla extends Flying {
    int countThousands;
    public Metla() {
        countThousands = 0;
        velocity = 20;
        distanceReducer = calcDistanceReducer();
    }

    @Override
    public double calcDistanceReducer() {
        return 0.01;
    }

    @Override
    public void move() {
        super.move();
        if ((int)(distanceCovered / 1000.0) > countThousands) {
            countThousands++;
            setDistanceToCover(distanceToCover);
        }
    }
}
