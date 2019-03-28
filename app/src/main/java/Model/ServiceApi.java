package Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("tops.php")
    Call<List<Product>> getProduct();
    @GET("dress.php")
    Call<List<Product>> getDress();
    @GET("tops")
    Call<List<Product>> getTops();
    @GET("bottoms")
    Call<List<Product>> getBottoms();
}
