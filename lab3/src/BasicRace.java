import java.util.HashSet;
import java.util.Set;

abstract public class BasicRace implements IBasicRace {
    protected Set<Vehicle> vehicles;
    protected double distance;
    protected Class<? extends Vehicle> vehicleType;

    public BasicRace(Class<? extends Vehicle> vehicleType) {
        this.vehicleType = vehicleType;
        vehicles = new HashSet<>();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicle.setDistanceToCover(distance);
        vehicles.add(vehicleType.cast(vehicle));
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
        for(Vehicle vehicle : vehicles) {
            vehicle.setDistanceToCover(distance);
        }
    }
}
