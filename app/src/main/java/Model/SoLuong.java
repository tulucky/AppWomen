package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoLuong {
    @SerializedName("soluong")
    @Expose
    int soLuong;
    @SerializedName("price")
    @Expose
    float gia;

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {

        return soLuong;
    }

    public float getGia() {
        return gia;
    }
}
