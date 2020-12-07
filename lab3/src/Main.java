public class Main {
    public static void main(String[] args) {
        //пример программы  
        try {
            IRaceFactory factory = new SprintFactory();
            RaceEngine engine = new RaceEngine(factory);

            engine.createRace(NonFlying.class);
            engine.setRaceDistance(10000);

            engine.addVehicle(new Camel2x());
            engine.addVehicle(new CamelFast());
            engine.addVehicle(new Centaur());
            engine.addVehicle(new BootsOfSpeed());
            engine.addVehicle(new BootsOfSpeed());

            System.out.println(engine.startRace().toString());

            engine.createRace(Flying.class);
            engine.setRaceDistance(10000);

            engine.addVehicle(new Metla());
            engine.addVehicle(new Stupa());
            engine.addVehicle(new Kovyer());

            System.out.println(engine.startRace().toString());

            engine.createRace(Vehicle.class);
            engine.setRaceDistance(10000);

            engine.addVehicle(new Metla());
            engine.addVehicle(new Stupa());
            engine.addVehicle(new Kovyer());
            engine.addVehicle(new Camel2x());
            engine.addVehicle(new CamelFast());
            engine.addVehicle(new Centaur());
            engine.addVehicle(new BootsOfSpeed());

            System.out.println(engine.startRace().toString());
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
