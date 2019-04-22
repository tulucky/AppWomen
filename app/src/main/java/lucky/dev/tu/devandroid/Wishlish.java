package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.GridAdapter;
import Model.Product;
import Model.RetrofitO;
import Model.ServiceApi;
import Model.State;
import Model.WishL.Wishada;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wishlish extends AppCompatActivity {
    RecyclerView recyWl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);
        recyWl = findViewById(R.id.recy_wl);
        Log.i("lo", " " + State.ids);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Wishlish.this);
        recyWl.setLayoutManager(linearLayoutManager);
        Wishada wishada = new Wishada(Wishlish.this, State.ids);
        recyWl.setHasFixedSize(true);
        recyWl.setAdapter(wishada);

    }

}


