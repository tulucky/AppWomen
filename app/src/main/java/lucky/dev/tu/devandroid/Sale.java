package lucky.dev.tu.devandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.GridAdapter;
import Model.Product;
import Model.RetrofitO;
import Model.SaleAda;
import Model.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;

public class Sale extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageSale;
    List<Product> products;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale);
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recy_sale);
        imageSale = findViewById(R.id.image_sale);
        imageSale.setImageResource(R.drawable.slide2);
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = serviceApi.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                recyclerView.setHasFixedSize(true);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new GridLayoutManager(Sale.this, 2));
                products = response.body();
                Collections.reverse(products);
                SaleAda adapter = new SaleAda(Sale.this, products);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
}