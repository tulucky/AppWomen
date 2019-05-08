package Model;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitO {
    private static Retrofit mRetrofit = null;
    /*public static final String url = "https://shoplady668.000webhostapp.com/";*/
    public static final String url = "http://192.168.1.108/wmshop/";

    private RetrofitO() {
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
