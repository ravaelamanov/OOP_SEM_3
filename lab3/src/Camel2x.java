public class Camel2x extends NonFlying {
    public Camel2x() {
        velocity = 10;
        restInterval = 30;
    }
    @Override
    public double calcRestDuration() {
        return numberOfRests < 1 ? 5 : 8;
    }
}
