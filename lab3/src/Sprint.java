import java.util.ArrayList;

public class Sprint extends BasicRace {
    public Sprint(Class<? extends Vehicle> vehicleType) {
        super(vehicleType);
    }

    @Override
    public ArrayList<Vehicle> start() {
        if (vehicles.isEmpty())
            throw new RuntimeException("No vehicles in the race!");
        ArrayList<Vehicle> leaders = new ArrayList<>();
        boolean hasWinner = false;
        while (true) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                if (vehicle.finished()) {
                    hasWinner = true;
                    leaders.add(vehicle);
                }
            }
            if (hasWinner) {
                break;
            }
        }
        return leaders;
    }

}