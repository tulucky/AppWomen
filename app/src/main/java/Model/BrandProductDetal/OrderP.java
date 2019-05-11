package Model.BrandProductDetal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderP {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("idproduct")
    @Expose
    int idProductb;
    @SerializedName("image")
    @Expose
    String imagebag;
    @SerializedName("size")
    @Expose
    String sizebag;
    @SerializedName("number")
    @Expose
    int number;
    @SerializedName("price")
    @Expose
    float price;
    @SerializedName("checked")
    @Expose
    int checked;

    public int getChecked() {
        return checked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public OrderP() {
    }

    public void setIdProductb(int idProductb) {
        this.idProductb = idProductb;
    }

    public void setImagebag(String imagebag) {
        this.imagebag = imagebag;
    }

    public void setSizebag(String sizebag) {
        this.sizebag = sizebag;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdProductb() {
        return idProductb;
    }

    public String getImagebag() {
        return imagebag;
    }

    public String getSizebag() {
        return sizebag;
    }

    public int getNumber() {
        return number;
    }
}
