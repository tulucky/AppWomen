package Model.ProductBrand;

public class MenuList {
    String text;
    int color;
    int imageicon;

    public void setImageicon(int imageicon) {
        this.imageicon = imageicon;
    }

    public int getImageicon() {

        return imageicon;
    }

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public MenuList(String text, int color, int imageicon) {
        this.text = text;
        this.color = color;
        this.imageicon = imageicon;
    }
}
