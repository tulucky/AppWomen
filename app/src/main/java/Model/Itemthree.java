package Model;

public class Itemthree {
    private String image;
    private String price;
    private String giaGoc;
    private String phanTram;

    public Itemthree(String image, String price, String giaGoc, String phanTram) {
        image = image;
        price = price;
        this.giaGoc = giaGoc;
        this.phanTram = phanTram;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getGiaGoc() {
        return giaGoc;
    }

    public String getPhanTram() {
        return phanTram;
    }
}
