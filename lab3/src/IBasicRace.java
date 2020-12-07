import java.util.ArrayList;

public interface IBasicRace {
    ArrayList<? extends Vehicle> start();
    void addVehicle(Vehicle vehicle);
    void setDistance(double distance);
}
