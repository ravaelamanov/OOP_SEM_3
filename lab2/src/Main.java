import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ShopNet net = new ShopNet();
            net.createShop("shop1", "address1");
            //net.init(); // создание продуктов, магазинов и их наполнение
            //net.print();
            Record record = new Record(1, 10, 100);
            ArrayList<Record> records = new ArrayList<>();
            records.add(record);
            net.importProducts(1, records);
            net.print();
            Record record1 = new Record(1, 5);
            records.clear();
            records.add(record1);
            net.importProducts(1, records);
            net.print();
            Record record2 = new Record(1, 10, 200);
            records.clear();
            records.add(record2);
            net.importProducts(1, records);
            net.print();

            /*Scanner sc = new Scanner(System.in);
            System.out.println(net.getShopWithCheapestProduct(sc.nextInt()).getName()); // магазин с самым дешевым продуктом
            net.whatToBuyFor(sc.nextInt(), sc.nextDouble()); // что купить на сумму
            System.out.println(net.sell(sc.nextInt(), createRecords())); // продать
            net.print();
            System.out.println(net.f(createRecords()).getName());*/
        } catch (RuntimeException | InvalidShopIDException exception) {
            System.err.println(exception.toString());
        }
    }
}
