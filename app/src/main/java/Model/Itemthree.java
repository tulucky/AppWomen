package Model;

public class Itemthree {
    private String Image;
    private String Price;
    private String giaGoc;
    private String phanTram;

    public Itemthree(String image, String price, String giaGoc, String phanTram) {
        Image = image;
        Price = price;
        this.giaGoc = giaGoc;
        this.phanTram = phanTram;
    }

    public String getImage() {
        return Image;
    }

    public String getPrice() {
        return Price;
    }

    public String getGiaGoc() {
        return giaGoc;
    }

    public String getPhanTram() {
        return phanTram;
    }
}
