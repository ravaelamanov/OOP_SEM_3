abstract public class Vehicle implements IMovable{
    protected int velocity;
    protected double distanceCovered;
    protected double distanceToCover;

    public void setDistanceToCover(double distanceToCover) {
        this.distanceToCover = distanceToCover;
    }

    public boolean finished() {
        return distanceCovered >= distanceToCover;
    }
}
