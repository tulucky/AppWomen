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
        ServiceApi mService = RetrofitO.getmRetrofit().create(ServiceApi.class);
        if (StateHolder.loai == null && StateHolder.sapxep == null && StateHolder.mausac == null) {
            call = mService.getProducts();
        } else if (StateHolder.sapxep == null && StateHolder.mausac == null && StateHolder.loai != null) {
            call = mService.colorByType(StateHolder.loai);
        } else if (StateHolder.loai == null && StateHolder.mausac == null && StateHolder.sapxep != null) {
            call = mService.sortProducts(StateHolder.sapxep);
        } else if (StateHolder.sapxep == null && StateHolder.loai == null && StateHolder.mausac != null) {
            call = mService.productByColor(StateHolder.mausac);
        } else if (StateHolder.loai != null && StateHolder.sapxep != null && StateHolder.mausac == null) {
            call = mService.sortTypesbyPrice(StateHolder.loai, StateHolder.sapxep);
        } else if (StateHolder.loai != null && StateHolder.sapxep == null && StateHolder.mausac != null) {
            call = mService.loaiTheoMau(StateHolder.loai, StateHolder.mausac);
        } else if (StateHolder.loai == null && StateHolder.sapxep != null && StateHolder.mausac != null) {
            call = mService.sortColorByPrice(StateHolder.mausac, StateHolder.sapxep);
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