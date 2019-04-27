package lucky.dev.tu.devandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import Model.Resum.OrderedAda;
import Model.Resum.OrderedP;
import Model.RetrofitO;
import Model.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Resum extends AppCompatActivity {
    RecyclerView productLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resum);
        productLists = findViewById(R.id.ordered_rec);
        SharedPreferences sharedPref = this.getSharedPreferences("Accout"
                , this.MODE_PRIVATE);
        String name = sharedPref.getString("idName", "khong");
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<OrderedP>> call = serviceApi.orderedProducts(name);
        call.enqueue(new Callback<List<OrderedP>>() {
            @Override
            public void onResponse(Call<List<OrderedP>> call, Response<List<OrderedP>> response) {
                productLists.setHasFixedSize(true);
                productLists.setLayoutManager(new LinearLayoutManager(Resum.this));
                OrderedAda orderedAda = new OrderedAda(Resum.this, response.body());
                productLists.setAdapter(orderedAda);

            }

            @Override
            public void onFailure(Call<List<OrderedP>> call, Throwable t) {
                Log.i("ss", t.getMessage());

            }
        });
    }
}
