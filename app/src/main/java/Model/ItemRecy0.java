package Model;

public class ItemRecy0 {
    private int id;
    private String image;
    private String price;
    private String giaGoc;
    private String phanTram;


    public ItemRecy0(int id, String image, String price, String phanTram) {
        this.id = id;
        this.image = image;
        this.price = price;
        this.phanTram = phanTram;
    }

    public int getId() {
        return id;
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
