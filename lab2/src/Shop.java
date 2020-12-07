import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop {
    private int ID;
    private static int IDCount = 0;
    private String name;
    private String address;
    private HashMap<Integer, Record> database;

    Shop(String name, String address) {
        IDCount++;
        ID = IDCount;
        this.name = name;
        this.address = address;
        database = new HashMap<>();
    }

    public void importProduct(Record record) {
        if (!has(record.getProductID()) && record.getPrice() == null) {
            throw new RuntimeException("Cannot import a new product without a price!\n");
        }
        Integer newAmount = record.getAmount();
        if (has(record.getProductID())) {
            newAmount += getProductAmount(record.getProductID());
        }
        Double newPrice;
        if (record.getPrice() != null) {
            newPrice = record.getPrice();
        } else {
            newPrice = getProductPrice(record.getProductID());
        }
        database.put(record.getProductID(), new Record(record.getProductID(), newAmount, newPrice));
    }

    public double sellProduct(Record record) {
        int productID = record.getProductID();
        Integer amount = record.getAmount();
        database.get(productID).setAmount(getProductAmount(productID) - amount);
        return database.get(productID).getPrice() * amount;
    }

    public ArrayList<Record> whatToBuyFor(double price) {
        ArrayList<Record> records = new ArrayList<>();
        for (Map.Entry<Integer, Record> it : database.entrySet()) {
            int productID = it.getKey();
            int amount = (int)(price / database.get(productID).getPrice());
            Record record = new Record(productID, amount);
            records.add(record);
        }
        return records;
    }


    public boolean has(int productID) {
        return database.containsKey(productID);
    }

    public int getProductAmount(int productID) {
        return database.get(productID).getAmount();
    }

    public double getProductPrice(int productID) {
        return database.get(productID).getPrice();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void print() {
        System.out.println("Shop ID: " + ID + "\t" + "Shop Name: " + name + "\t" + "Shop Adress: " + address);
        for (Map.Entry<Integer, Record> it : database.entrySet()) {
            Record rec = it.getValue();
            System.out.println("Product ID: " + rec.getProductID() + "\tAmount: " + rec.getAmount() + "\tPrice: " + rec.getPrice());
        }
    }
}
