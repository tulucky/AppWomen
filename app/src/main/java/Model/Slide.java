package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slide {
    @SerializedName("image")
    @Expose
    String image;

    public String getImage() {
        return image;
    }
}
