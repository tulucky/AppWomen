package Model;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitO {
    private static Retrofit mRetrofit = null;
    private static final String url = "http://192.168.0.100/wmshop/";

    public RetrofitO() {
    }
    public static Retrofit getmRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
