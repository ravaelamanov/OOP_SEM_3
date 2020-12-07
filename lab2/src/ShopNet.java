import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopNet {
    HashMap<Integer, Shop> IDShopMap;
    HashMap<Integer, Product> IDProductMap;

    ShopNet() {
        IDShopMap = new HashMap<>();
        IDProductMap = new HashMap<>();
    }

    public void createShop(String name, String address) {
        Shop shop = new Shop(name, address);
        IDShopMap.put(shop.getID(), shop);
    }

    public void createProduct(String name) {
        Product product = new Product(name);
        IDProductMap.put(product.getID(), product);
    }

    public void importProducts(Integer shopID, ArrayList<Record> records) throws InvalidShopIDException {
        if (!IDShopMap.containsKey(shopID))
            throw new InvalidShopIDException("Non existing shop ID", shopID);
        for (Record record : records) {
            IDShopMap.get(shopID).importProduct(record);
        }
    }

    public double sell(Integer shopID, ArrayList<Record> records) throws InvalidShopIDException {
        if (!IDShopMap.containsKey(shopID))
            throw new InvalidShopIDException("Non existing shop ID", shopID);
        Shop shop = IDShopMap.get(shopID);
        for (Record record : records) {
            if (!shop.has(record.getProductID()) || record.getAmount() > shop.getProductAmount(record.getProductID())) {
                throw new RuntimeException("Couldn't sell products");
            }
        }
        double res = 0;
        for (Record record : records) {
            res += shop.sellProduct(record);
        }
        return res;
    }

    public Shop getShopWithCheapestProduct(int productID) {
        Shop cheapest = null;
        double minPrice = Double.MAX_VALUE;
        for (Map.Entry<Integer, Shop> it : IDShopMap.entrySet()) {
            Shop cur = it.getValue();
            if (cur.has(productID) && cur.getProductPrice(productID) < minPrice) {
                minPrice = cur.getProductPrice(productID);
                cheapest = cur;
            }
        }
        return cheapest;
    }

    public ArrayList<Record> whatToBuyFor(int shopID, double price) throws InvalidShopIDException {
        if (!IDShopMap.containsKey(shopID))
            throw new InvalidShopIDException("Non existing shop ID", shopID);
        Shop shop = IDShopMap.get(shopID);
        return shop.whatToBuyFor(price);
    }

    public Shop getShopWithCheapestProducts(ArrayList<Record> records) {
        Shop best = null;
        double bestSum = Double.MAX_VALUE;
        for (Map.Entry<Integer, Shop> it : IDShopMap.entrySet()) {
            Shop cur = it.getValue();
            double sum = 0;
            boolean ok = true;
            for (Record record : records) {
                if (!cur.has(record.getProductID()) || cur.getProductAmount(record.getProductID()) < record.getAmount()) {
                    ok = false;
                    break;
                }
                sum += cur.getProductPrice(record.getProductID()) * record.getAmount();
            }
            if (!ok)
                continue;
            if (sum < bestSum) {
                best = cur;
                bestSum = sum;
            }
        }
        return best;
    }

    public void print() {
        for (Map.Entry<Integer, Shop> it : IDShopMap.entrySet()) {
            it.getValue().print();
            System.out.println();
        }
    }

}
