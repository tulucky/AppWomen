package Model.ProductBrand;

public class StateHolder {
    public static String loai;
    public static String sapxep;
    public static String mua;
    public static String mausac;

    public static void setLoai(String loai) {
        StateHolder.loai = loai;
    }

    public static void setSapxep(String sapxep) {
        StateHolder.sapxep = sapxep;
    }

    public static void setMua(String mua) {
        StateHolder.mua = mua;
    }

    public static void setMausac(String mausac) {
        StateHolder.mausac = mausac;
    }

    public static void reset() {
        StateHolder.loai = null;
        StateHolder.sapxep = null;
    }
}
