public class InvalidShopIDException extends Exception {
    private int shopID;

    InvalidShopIDException(String errMsg, int shopID) {
        super(errMsg);
        this.shopID = shopID;
    }

    InvalidShopIDException(String errMsg) {
        super(errMsg);
    }

    public int getShopID() {
        return shopID;
    }
}
