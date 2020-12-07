public class Record {
    private int productID;
    private Integer amount;
    private Double price;

    Record(int productID, int amount) {
        this.productID = productID;
        this.amount = amount;
        if (amount < 0)
            throw new RuntimeException("Negative amount!\n");
    }

    Record(int productID, int amount, double price) {
        this.productID = productID;
        this.amount = amount;
        this.price = price;
        if (amount < 0)
            throw new RuntimeException("Negative amount!\n");
        if (price < 0)
            throw new RuntimeException("Negative price\n");
    }

    public int getProductID() {
        return productID;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
