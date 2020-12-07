public interface IRaceFactory {
    IBasicRace createRace(Class<? extends Vehicle> vehicleType);
}
