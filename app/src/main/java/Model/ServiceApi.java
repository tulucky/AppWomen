package Model;

import java.util.List;

import Model.Brand.BrandModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("tops.php")
    Call<List<Product>> getProduct();
    @GET("dress.php")
    Call<List<Product>> getDress();
    @GET("tops")
    Call<List<Product>> getTops();
    @GET("bottoms")
    Call<List<Product>> getBottoms();

    @GET("brand.php")
    Call<List<BrandModel>> getListBrand();

    @GET("getabrand.php")
    Call<List<BrandModel>> getaBrand(@Query("brand") int id);

    @GET("color.php")
    Call<List<String>> getColor(@Query("product") int id);

    @GET("aproduct.php")
    Call<List<Product>> getaProduct(@Query("aproduct") int id);

    @GET("size.php")
    Call<List<String>> getaSize(@Query("asize") int id);
}
