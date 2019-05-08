package Model;

import java.util.List;

import Model.Account.Alter;
import Model.Brand.BrandModel;
import Model.BrandProductDetal.OrderP;
import Model.Resum.OrderedP;
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
    Call<List<Product>> getProducts();
    @GET("dress.php")
    Call<List<Product>> getDress();

    @GET("sort.php")
    Call<List<Product>> getDesc();

    @GET("asc.php")
    Call<List<Product>> getAsc();
    @GET("bottoms")
    Call<List<Product>> getBottoms();

    @GET("brand3image.php")
    Call<List<Product>> brandImages(@Query("brand") int brand);
    @GET("brand.php")
    Call<List<BrandModel>> brandList();

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
    Call<List<OrderP>> setOrder(@Field("idProduct") int id, @Field("name") String name, @Field("image") String image, @Field("size") String size, @Field("number") int number, @Field("price") float price);
    //the value of a Field is option as long as match with $_post[value]
    @FormUrlEncoded
    @POST("listorders.php")
    Call<List<OrderP>> getListOrder(@Field("name") String name);

    @FormUrlEncoded
    @POST("upnumberPrice.php")
    Call<List<OrderP>> upNumberPrice(@Field("id") int id, @Field("number") int number, @Field("price") float price);

    @FormUrlEncoded
    @POST("updateorder.php")
    Call<List<OrderP>> updateOrderProduct(@Field("id") int id, @Field("image") String image, @Field("size") String size, @Field("number") int number, @Field("price") float price);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<Alter>> logIn(@Field("name") String namne, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("signup.php")
    Call<List<Alter>> signUp(@Field("name") String namne, @Field("pass") String pass);
    @FormUrlEncoded
    @POST("checkout.php")
    Call<List<String>> checkOut(@Field("name") String namne, @Field("idorder") int id, @Field("status") String status);

    @FormUrlEncoded
    @POST("orderedproducts.php")
    Call<List<OrderedP>> orderedProducts(@Field("name") String name);

    @FormUrlEncoded
    @POST("soluong.php")
    Call<List<SoLuong>> getSoLuong(@Field("name") String name);

    @FormUrlEncoded
    @POST("giacheck.php")
    Call<List<SoLuong>> getPriceCheck(@Field("name") String name);

    @GET("homeslide.php")
    Call<List<Slide>> getSlide();

    @GET("brandslide.php")
    Call<List<Slide>> getSlideBrand();
}