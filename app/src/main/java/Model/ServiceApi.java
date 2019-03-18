package Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("ka.php")
    Call<List<Product>> getProduct();
}
