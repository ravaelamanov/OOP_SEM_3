import java.util.ArrayList;

public class RaceEngine {
    private IBasicRace race;
    private IRaceFactory factory;

    public RaceEngine(IRaceFactory factory) {
        setFactory(factory);
    }

    public void createRace(Class<? extends Vehicle> vehicleType) {
        race = factory.createRace(vehicleType);
    }

    public void setFactory(IRaceFactory factory) {
        this.factory = factory;
    }

    public void addVehicle(Vehicle vehicle) {
        if (race == null)
            throw new RuntimeException("No race yet!");
        race.addVehicle(vehicle);
    }

    public void setRaceDistance(double distance) {
        if (race == null)
            throw new RuntimeException("No race yet!");
        race.setDistance(distance);
    }

    public ArrayList<? extends Vehicle> startRace() {
        if (race == null)
            throw new RuntimeException("No race yet!");
        return race.start();
    }
}
