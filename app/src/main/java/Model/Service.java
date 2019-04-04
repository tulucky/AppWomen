package Model;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Service {
   private static Context context;
   private static RecyclerView mrecycle;
  private static Call<List<Product>> call= null;

    public Service(Context mcontext, RecyclerView mrecycleView) {
        this.context = mcontext;
        this.mrecycle = mrecycleView;
    }

    public static void Request(int k) {
        ServiceApi mService = RetrofitO.getmRetrofit().create(ServiceApi.class);
        switch (k){
            case 0:
                call = mService.getProduct();
                break;
            case 1:
                 call = mService.getDress();
                break;
            case 2:
                 call = mService.getTops();
                break;
            case 3:
                 call = mService.getBottoms();
                break;
        }
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                showContent(response.body());
                Log.i("aa", " " + response);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
    private static void showContent(List<Product> body) {
        mrecycle.setNestedScrollingEnabled(false);
        mrecycle.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
       mrecycle.setLayoutManager(gridLayoutManager);
        AdapterProduct adapter = new AdapterProduct(context,body);
        mrecycle.setAdapter(adapter);
       // Log.i("m",""+mscroll.getHeight());

    }
}
