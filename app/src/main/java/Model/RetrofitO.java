package Model;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitO {
    private static Retrofit mRetrofit = null;
    public static final String url = "http://192.168.1.24/wmshop/";

    public RetrofitO() {
    }
    public static Retrofit getmRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //new Retrofit.Builder() Create the Retrofit.Builder instance
        }
        return mRetrofit;
    }
}
