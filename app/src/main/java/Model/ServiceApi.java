package Model;

import java.util.List;

import Model.Brand.BrandModel;
import Model.BrandProductDetal.OrderP;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("orders.php")
    Call<List<OrderP>> setOrder(@Field("idProduct") int id, @Field("image") String image, @Field("size") String size, @Field("number") int number);
    //the value of a Field is option as long as match with $_post[value]
    @GET("listorders.php")
    Call<List<OrderP>> getListOrder();

    @FormUrlEncoded
    @POST("upnumber.php")
    Call<List<OrderP>> upNumber(@Field("id") int id, @Field("number") int number);

    @FormUrlEncoded
    @POST("updateorder.php")
    Call<List<OrderP>> updateOrderProduct(@Field("id") int id, @Field("image") String image, @Field("size") String size, @Field("number") int number);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<String>> logIn(@Field("name") String namne, @Field("pass") String pass);
}
