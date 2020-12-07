abstract public class NonFlying extends Vehicle implements INonFlying {
    protected double restInterval;
    protected double restDuration;
    protected int numberOfRests;
    protected int movingTime;

    protected NonFlying() {
        numberOfRests = 0;
        movingTime = 0;
        restDuration = calcRestDuration();
    }

    @Override
    public void move() {
        if (movingTime == restInterval) {
            restDuration--;
            if (restDuration <= 0) {
                numberOfRests++;
                restDuration = calcRestDuration();
                movingTime = 0;
            }
            return;
        }
        distanceCovered += velocity;
        movingTime++;
    }
}
