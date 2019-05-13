package Model;

import java.util.ArrayList;
import java.util.List;

public class State {
    public int on;
    public static List<Product> ids = new ArrayList<>();
    public static List<Product> products;


    public static void addP(Product p) {
        ids.add(p);
    }

    public static void removeP(Product p) {
        ids.remove(ids.indexOf(p));
    }

    public static void removeProduct(Product k) {
        ids.remove(ids.indexOf(k));
    }
}
