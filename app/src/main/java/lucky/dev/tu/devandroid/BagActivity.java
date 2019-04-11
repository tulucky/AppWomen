package lucky.dev.tu.devandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    ConstraintLayout updateProduct;
    ImageView exitBackground;
    ImageView cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bag_activity);
        listOrder = findViewById(R.id.list_order);
        updateProduct = findViewById(R.id.update_product);
        updateProduct.setVisibility(View.GONE);
        exitBackground = findViewById(R.id.exit_backgroud);
        cancel = findViewById(R.id.cancel_d);
        getListOrder();
        exitBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct.setVisibility(View.GONE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct.setVisibility(View.GONE);
            }
        });
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
                AdapterBag adapterBag = new AdapterBag(BagActivity.this, daTa, updateProduct);
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
