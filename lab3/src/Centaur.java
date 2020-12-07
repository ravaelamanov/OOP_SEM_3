public class Centaur extends NonFlying{
    public Centaur() {
        velocity = 8;
        restInterval = 8;
    }
    @Override
    public double calcRestDuration() {
        return 2;
    }
}
