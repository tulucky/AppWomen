
package Model.Resum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderedP {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("nameb")
    @Expose
    private String nameb;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("time")
    @Expose
    private String time;

    public float getPrice() {
        return price;
    }

    public String getNameb() {
        return nameb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}