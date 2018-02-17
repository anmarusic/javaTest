import java.math.BigInteger;

public class Sgtin_96 {
    private String header;
    private String filter;
    private String partition;

    private String type;

    private String companyPrefix;
    private String itemReference;
    private String serial;

    private static int invalidSgtin_96=0;

    public Sgtin_96(String sgtin_96) throws Exception {
        sgtin_96=hexToBin(sgtin_96);
        if(sgtin_96.substring(0,8) != "00110000" && sgtin_96.length() != 96) {
            invalidSgtin_96++;
            throw new Exception("Invalid string: "+ sgtin_96);
        } else {
            this.header = sgtin_96.substring(0,8);
            this.filter = sgtin_96.substring(8,11);
            switch (this.filter) {
                case "001": this.type = "Point of scale (POS) trading item";
                    break;
                case "010": this.type = "Full case for transport";
                    break;
                case "011": this.type = "Reserved";
                    break;
                case "100": this.type = "Inner pack trade item grouping for handling";
                    break;
                case "101": this.type = "Reserved";
                    break;
                case "110": this.type = "Unit load";
                    break;
                case "111": this.type = "Unit inside trade item or component inside a product not intended for individual sale";
                    break;
                default: this.type="All others";
                    break;
            }
            this.partition = sgtin_96.substring(11,14);
            switch (this.partition) {
                case "000": this.companyPrefix = sgtin_96.substring(14,54);
                    this.itemReference = sgtin_96.substring(54,58);
                    break;
                case "001": this.companyPrefix = sgtin_96.substring(14,51);
                    this.itemReference = sgtin_96.substring(51,58);
                    break;
                case "010": this.companyPrefix = sgtin_96.substring(14,48);
                    this.itemReference = sgtin_96.substring(48,58);
                    break;
                case "011": this.companyPrefix = sgtin_96.substring(14,44);
                    this.itemReference = sgtin_96.substring(44,58);
                    break;
                case "100": this.companyPrefix = sgtin_96.substring(14,41);
                    this.itemReference = sgtin_96.substring(41,58);
                    break;
                case "101": this.companyPrefix = sgtin_96.substring(14,38);
                    this.itemReference = sgtin_96.substring(38,58);
                    break;
                case "110": this.companyPrefix = sgtin_96.substring(14,34);
                    this.itemReference = sgtin_96.substring(34,58);
                    break;
                default: throw new Exception("Invalid partition");
            }
            this.serial=sgtin_96.substring(58,96);

        }
    }

    public String getHeader() {
        return asHex(header);
    }

    public String getFilter() {
        return asHex(filter);
    }

    public String getPartition() {
        return asHex(partition);
    }

    public String getType() {
        return type;
    }

    public String getCompanyPrefix() {
        return asHex(companyPrefix);
    }

    public String getItemReference() {
        return itemReference;
    }

    public String getSerial() {
        return asHex(serial);
    }

    public static int getInvalidSgtin_96() {
        return invalidSgtin_96;
    }

    public boolean isThisProduct(Product p) {
        if(Integer.parseInt(this.itemReference,2) == Integer.parseInt(p.getItemReference())){
            return true;
        }
        return false;
    }

    private String asHex(String bin) {
        int decimal = Integer.parseInt(bin,2);
        String hexStr = Integer.toString(decimal,16);

        return hexStr;
    }

    static String hexToBin(String s) {
        try {
            String preBin = new BigInteger(s, 16).toString(2);
            Integer length = preBin.length();
            if (length < 8) {
                for (int i = 0; i < 8 - length; i++) {
                    preBin = "0" + preBin;
                }
            }
            preBin = "00"+preBin;
            return preBin;
        } catch (Exception e){
            return ("Error for: " + s);
        }
    }
}
