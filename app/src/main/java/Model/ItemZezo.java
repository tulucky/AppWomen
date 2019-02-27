package Model;

public class ItemZezo {
    private String Image;
    private String Price;
    private String phanTram;

    public ItemZezo(String image, String price, String giaGoc, String phanTram) {
        Image = image;
        Price = price;
        this.phanTram = phanTram;
    }

    public String getImage() {
        return Image;
    }

    public String getPrice() {
        return Price;
    }

    public String getPhanTram() {
        return phanTram;
    }
}
