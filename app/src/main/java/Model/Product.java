
package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("idbrand")
    @Expose
    private String idbrand;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("originprice")
    @Expose
    private String originprice;
    @SerializedName("sale")
    @Expose
    private String sale;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("destile")
    @Expose
    private String destile;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("satus")
    @Expose
    private String satus;
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("nameb")
    private String nameB;
    @SerializedName("price")
    @Expose
    private float price;

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {

        return price;
    }

    public Product() {
    }

    public Product(int id, String image, String name, String originprice, String sale) {
        super();
        this.id = id;
        this.name = name;
        this.originprice = originprice;
        this.sale = sale;
        this.image = image;
    }

    public Product(int id, String title, String image, String name, String originprice, String sale) {
        super();
        this.id = id;
        this.name = name;
        this.originprice = originprice;
        this.sale = sale;
        this.image = image;
        this.title = title;
    }

    public String getNameB() {
        return nameB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdbrand() {
        return idbrand;
    }

    public void setIdbrand(String idbrand) {
        this.idbrand = idbrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginprice() {
        return originprice;
    }

    public void setOriginprice(String originprice) {
        this.originprice = originprice;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestile() {
        return destile;
    }

    public void setDestile(String destile) {
        this.destile = destile;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

}