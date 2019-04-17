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
    @SerializedName("image1")
    @Expose
    private String image1;
    @SerializedName("originprice1")
    @Expose
    private String originprice1;
    @SerializedName("price1")
    @Expose
    private String price1;
    @SerializedName("sale1")
    @Expose
    private String sale1;
    @SerializedName("image2")
    @Expose
    private String image2;
    @SerializedName("originprice2")
    @Expose
    private String originprice2;
    @SerializedName("price2")
    @Expose
    private String price2;
    @SerializedName("sale2")
    @Expose
    private String sale2;
    @SerializedName("image3")
    @Expose
    private String image3;
    @SerializedName("originprice3")
    @Expose
    private String originprice3;
    @SerializedName("price3")
    @Expose
    private String price3;
    @SerializedName("sale3")
    @Expose
    private String sale3;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getOriginprice1() {
        return originprice1;
    }

    public void setOriginprice1(String originprice1) {
        this.originprice1 = originprice1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getSale1() {
        return sale1;
    }

    public void setSale1(String sale1) {
        this.sale1 = sale1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImang2(String imang2) {
        this.image2 = imang2;
    }

    public String getOriginprice2() {
        return originprice2;
    }

    public void setOriginprice2(String originprice2) {
        this.originprice2 = originprice2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getSale2() {
        return sale2;
    }

    public void setSale2(String sale2) {
        this.sale2 = sale2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getOriginprice3() {
        return originprice3;
    }

    public void setOriginprice3(String originprice3) {
        this.originprice3 = originprice3;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public String getSale3() {
        return sale3;
    }

    public void setSale3(String sale3) {
        this.sale3 = sale3;
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