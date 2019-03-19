package Model.ProductBrand;

public class MenuList {
    String text;
    int color;

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

    public MenuList(String text, int color) {

        this.text = text;
        this.color = color;
    }
}
