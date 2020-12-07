public class SprintFactory implements IRaceFactory {
    @Override
    public IBasicRace createRace(Class<? extends Vehicle> vehicleType) {
        return new Sprint(vehicleType);
    }
}
