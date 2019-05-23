package Model;

public class ItemRecy0 {
    private int id;
    private String image;
    private float price;
    private String nameb;
    private String giaGoc;
    private String phanTram;


    public ItemRecy0(int id, String image, String nameb, double price, String phanTram) {
        this.id = id;
        this.image = image;
        this.nameb = nameb;
        this.price = (float) price;
        this.phanTram = phanTram;
    }

    public String getNameb() {
        return nameb;
    }
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public String getGiaGoc() {
        return giaGoc;
    }

    public String getPhanTram() {
        return phanTram;
    }
}
