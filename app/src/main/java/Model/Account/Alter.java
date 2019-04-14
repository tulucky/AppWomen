package Model.Account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alter {
    @SerializedName("ketqua")
    @Expose
    String alter;

    public Alter(String alter) {
        this.alter = alter;
    }

    public String getAlter() {
        return alter;
    }
}
