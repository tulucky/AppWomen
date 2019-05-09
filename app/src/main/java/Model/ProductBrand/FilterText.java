package Model.ProductBrand;

public class FilterText {
    String content;
    int bacground;
    int color;

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {

        return color;
    }

    public int getBacground() {
        return bacground;
    }

    public void setBacground(int bacground) {

        this.bacground = bacground;
    }

    public FilterText(String content, int bacground, int color) {
        this.content = content;
        this.bacground = bacground;
        this.color = color;
    }

    public String getContent() {
        return content;
    }
}
