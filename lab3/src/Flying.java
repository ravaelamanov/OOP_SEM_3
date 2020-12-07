abstract public class Flying extends Vehicle implements IFlying{
    protected double distanceReducer;

    @Override
    public void move() {
        distanceCovered += velocity;
    }

    @Override
    public void setDistanceToCover(double distanceToCover) {
        this.distanceToCover = distanceToCover;
        distanceReducer = calcDistanceReducer();
        this.distanceToCover -= distanceToCover * distanceReducer;
    }
}
