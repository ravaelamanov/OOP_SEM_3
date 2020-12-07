public class CamelFast extends NonFlying {
    public CamelFast() {
        velocity = 40;
        restInterval = 10;
    }
    @Override
    public double calcRestDuration() {
        return numberOfRests < 1 ? 5 : numberOfRests < 2 ? 6.5 : 8;
    }
}
