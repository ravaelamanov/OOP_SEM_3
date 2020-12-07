public class BootsOfSpeed extends NonFlying {
    public BootsOfSpeed() {
        velocity = 6;
        restInterval = 60;
    }
    @Override
    public double calcRestDuration() {
        return numberOfRests < 1 ? 10 : 5;
    }
}
