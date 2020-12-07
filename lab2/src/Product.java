public class Product {
    private int ID;
    private String name;
    private static int IDCount = 0;

    Product(String name) {
        IDCount++;
        ID = IDCount;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

}
