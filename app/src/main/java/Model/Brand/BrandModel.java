package Model.Brand;

public class BrandModel {
  private   int imageBrand;
   private int logo;
   private int item1;
   private String price1;
   private String sale1;
   private int item2;
   private String price2;
   private String sale2;
   private int item3;
   private String price3;
   private String sale3;

    public BrandModel(int imageBrand, int logo, int item1, String price1, String sale1, int item2,
                      String price2, String sale2, int item3, String price3, String sale3) {
        this.imageBrand = imageBrand;
        this.logo = logo;
        this.item1 = item1;
        this.price1 = price1;
        this.sale1 = sale1;
        this.item2 = item2;
        this.price2 = price2;
        this.sale2 = sale2;
        this.item3 = item3;
        this.price3 = price3;
        this.sale3 = sale3;
    }

    public int getImageBrand() {
        return imageBrand;
    }

    public int getLogo() {
        return logo;
    }

    public int getItem1() {
        return item1;
    }

    public String getPrice1() {
        return price1;
    }

    public String getSale1() {
        return sale1;
    }

    public int getItem2() {
        return item2;
    }

    public String getPrice2() {
        return price2;
    }

    public String getSale2() {
        return sale2;
    }

    public int getItem3() {
        return item3;
    }

    public String getPrice3() {
        return price3;
    }

    public String getSale3() {
        return sale3;
    }

    public void setImageBrand(int imageBrand) {
        this.imageBrand = imageBrand;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public void setSale1(String sale1) {
        this.sale1 = sale1;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public void setSale2(String sale2) {
        this.sale2 = sale2;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public void setSale3(String sale3) {
        this.sale3 = sale3;
    }
}
