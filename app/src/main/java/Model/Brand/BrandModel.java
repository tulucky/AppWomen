package Model.Brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nameb")
    @Expose
    private String nameb;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("desbrand")
    @Expose
    private String desbrand;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("creat_at")
    @Expose
    private String creatAt;
    @SerializedName("updaet_at")
    @Expose
    private String updaetAt;

    public BrandModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameb() {
        return nameb;
    }

    public void setNameb(String name) {
        this.nameb = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesbrand() {
        return desbrand;
    }

    public void setDesbrand(String desbrand) {
        this.desbrand = desbrand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

    public String getUpdaetAt() {
        return updaetAt;
    }

    public void setUpdaetAt(String updaetAt) {
        this.updaetAt = updaetAt;
    }

}