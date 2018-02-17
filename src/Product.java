public class Product {
    private String companyPrefix;
    private String companyName;
    private String itemReference;
    private String itemName;

    public Product(String line) {
        String[] product = line.split(";");

        this.companyPrefix = product[0];
        this.companyName = product[1];
        this.itemReference = product[2];
        this.itemName = product[3];
    }

    public String getCompanyPrefix() {
        return companyPrefix;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getItemReference() {
        return itemReference;
    }

    public String getItemName() {
        return itemName;
    }

    private String toHex(String str) {
        return Integer.toHexString(Integer.parseInt(str));
    }
}
