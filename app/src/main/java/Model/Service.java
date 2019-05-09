package Model;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import Model.ProductBrand.Adapterpb;
import Model.ProductBrand.StateHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Service {
    private static Context context;
    private static RecyclerView mrecycle;
    private static Call<List<Product>> call = null;

    public Service(Context mcontext, RecyclerView mrecycleView) {
        this.context = mcontext;
        this.mrecycle = mrecycleView;
    }

    public static void Request() {
        Log.i("opo", "Request: " + StateHolder.loai + " " + StateHolder.mausac);
        ServiceApi mService = RetrofitO.getmRetrofit().create(ServiceApi.class);
        if (StateHolder.loai == null && StateHolder.sapxep == null && StateHolder.mausac == null) {
            call = mService.getProducts();
            //get loai
        } else if (StateHolder.sapxep == null && StateHolder.mausac == null && StateHolder.loai != null) {
            call = mService.getType(StateHolder.loai);
            //sap xep sp
        } else if (StateHolder.loai == null && StateHolder.mausac == null && StateHolder.sapxep != null) {
            call = mService.sortProducts(StateHolder.sapxep);
            //getcolors
        } else if (StateHolder.sapxep == null && StateHolder.loai == null && StateHolder.mausac != null) {
            call = mService.productByColor(StateHolder.mausac);
            //get loai va sort
        } else if (StateHolder.loai != null && StateHolder.sapxep != null && StateHolder.mausac == null) {
            call = mService.sortTypesbyPrice(StateHolder.loai, StateHolder.sapxep);
            //get typebycolor
        } else if (StateHolder.loai != null && StateHolder.sapxep == null && StateHolder.mausac != null) {
            Log.i("opo", "jo");
            call = mService.typeByColor(StateHolder.loai, StateHolder.mausac);
            //getcolors va sort
        } else if (StateHolder.loai == null && StateHolder.sapxep != null && StateHolder.mausac != null) {
            call = mService.sortColorByPrice(StateHolder.mausac, StateHolder.sapxep);
            // get colors and a type and sort by price
        } else if (StateHolder.loai != null && StateHolder.sapxep != null && StateHolder.mausac != null) {
            call = mService.typeColorByPrice(StateHolder.loai, StateHolder.sapxep, StateHolder.mausac);
        }

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                showContent(response.body());
                Log.i("aa", " " + response);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i("aa", " " + t.getMessage());

            }
        });
    }
    private static void showContent(List<Product> body) {
        mrecycle.setNestedScrollingEnabled(false);
        mrecycle.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        mrecycle.setLayoutManager(gridLayoutManager);
        Adapterpb adapterpb = new Adapterpb(context, body);
        mrecycle.setAdapter(adapterpb);
        // Log.i("m",""+mscroll.getHeight());

    }
}