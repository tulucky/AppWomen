package Model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;

public class GridProduct extends Fragment {
    RecyclerView listProduct;
    List<Product> listData;

    public GridProduct() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hg", "" + container);
        View view = inflater.inflate(R.layout.gridproduct, container, false);
        listProduct = view.findViewById(R.id.grid_product);
        listProduct.setNestedScrollingEnabled(false);
        listProduct.setHasFixedSize(true);
        getGrid();

        return view;

    }

    private void getGrid() {
        listProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = serviceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                GridAdapter madapter = new GridAdapter(getActivity(), response.body());
                listProduct.setAdapter(madapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

}