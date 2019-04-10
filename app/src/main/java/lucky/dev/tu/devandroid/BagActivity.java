package lucky.dev.tu.devandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Model.Bag.AdapterBag;
import Model.BrandProductDetal.OrderP;
import Model.RetrofitO;
import Model.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;

public class BagActivity extends AppCompatActivity {
    List<OrderP> daTa;
    RecyclerView listOrder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bag_activity);
        listOrder = findViewById(R.id.list_order);
        getListOrder();
    }

    private void getListOrder() {
        final ServiceApi getOrders = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<OrderP>> callOrder = getOrders.getListOrder();
        callOrder.enqueue(new Callback<List<OrderP>>() {
            @Override
            public void onResponse(Call<List<OrderP>> call, retrofit2.Response<List<OrderP>> response) {
                Toast.makeText(BagActivity.this, " hhh", Toast.LENGTH_LONG).show();
                Log.i("hh", " " + response.body());
                daTa = response.body();
                listOrder.setNestedScrollingEnabled(false);
                AdapterBag adapterBag = new AdapterBag(BagActivity.this, daTa);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BagActivity.this);
                listOrder.setLayoutManager(linearLayoutManager);
                listOrder.setAdapter(adapterBag);

            }

            @Override
            public void onFailure(Call<List<OrderP>> call, Throwable t) {
                t.getMessage();

            }
        });
    }
}
