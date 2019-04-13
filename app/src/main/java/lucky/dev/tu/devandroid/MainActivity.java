package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.AdapterZezo;
import Model.GridAdapter;
import Model.GridProduct;
import Model.ItemRecy0;
import Model.ListProduct;
import Model.MySingleton;
import Model.Product;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<ItemRecy0> containerZezo;
    CarouselView carouselView;
    RecyclerView mRecycleZezo;
    RecyclerView listProduct;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    ImageView list;
    ImageView grid;
    RelativeLayout recyMain;
    RelativeLayout relativ;
    ConstraintLayout progress;
    public NestedScrollView nestMain;
    private static final String urlData0 = "http://192.168.1.24/wmshop/tops.php";
    private static final String urlData3 = "http://192.168.1.24/wmshop/tops.php";

   int[] sampleImages = {R.drawable.image_3, R.drawable.lko, R.drawable.image_4, R.drawable.image_2, R.drawable.image_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.toptop);
        recyMain = findViewById(R.id.recy_main);
        bottomNavigationView = findViewById(R.id.navigation);
        nestMain = findViewById(R.id.nest_main);
        relativ = findViewById(R.id.relativ);
        list = findViewById(R.id.list);
        grid = findViewById(R.id.grid);
        progress = findViewById(R.id.progress);
        //progress.setVisibility(View.GONE);
        grid.setVisibility(View.GONE);
        getDataRecyZezo();
        //cu truy cap tai nguen la phai r
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        findViewById(R.id.home).setOnClickListener(this);
        findViewById(R.id.brand).setOnClickListener(this);
        findViewById(R.id.cart).setOnClickListener(this);
        findViewById(R.id.account).setOnClickListener(this);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        GridProduct gridProduct = new GridProduct();
        transaction.add(R.id.recy_main, gridProduct);
        transaction.commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grid.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        ListProduct listProduct = new ListProduct();
                        transaction.replace(R.id.recy_main, listProduct);
                        transaction.commit();

                    }

                });
            }
        }, 100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                grid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.setVisibility(View.VISIBLE);
                        grid.setVisibility(View.GONE);
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        GridProduct gridProduct = new GridProduct();
                        transaction.replace(R.id.recy_main, gridProduct);
                        transaction.commit();
                    }
                });
            }
        }, 100);
        nestMain.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (!nestMain.canScrollVertically(1)) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Class clas = null;
        switch (v.getId()) {
            case R.id.brand:
                clas = BrandActivity.class;
                break;
            case R.id.cart:
                clas = BagActivity.class;
                break;
            case R.id.account:
                clas = Account.class;
                break;
            case R.id.home:
                clas = MainActivity.class;
                break;
        }
        startActivity(new Intent(MainActivity.this, clas));

    }




    private void getDataRecyZezo() {
        mRecycleZezo = findViewById(R.id.Recycle_one);
        mRecycleZezo.setHasFixedSize(true);
        mRecycleZezo.setNestedScrollingEnabled(false);
        containerZezo = new ArrayList<>();
        mRecycleZezo.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        StringRequest obj = new StringRequest(Request.Method.GET,urlData0,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("p", "" + response);
                        JSONArray aray = null;
                        try {
                            aray = new JSONArray(response);

                            for (int i = 0; i < aray.length(); i++) {
                                // chu y du lieu tra ve tu url len de la acsoc thi ta moi getdc jsonobject
                                JSONObject a = aray.getJSONObject(i);
                                containerZezo.add(new ItemRecy0(a.getInt("id"), a.getString("image"),
                                        a.getString("name"),
                                        a.getString("sale"))
                                );
                            }
                            AdapterZezo madapter = new AdapterZezo(containerZezo,MainActivity.this);
                            mRecycleZezo.setAdapter(madapter);

                            Log.i("ko", "" + containerZezo);
                        }
                        catch (JSONException e1) {
                            Log.i("j", "khong");
                        }}}
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(obj);

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //  imageView.setImageResource(sampleImages[position]);
        }
    };

}
