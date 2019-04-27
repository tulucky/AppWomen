package Model.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import Model.GridProduct;
import Model.ItemRecy0;
import Model.ListProduct;
import Model.MySingleton;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;
import lucky.dev.tu.devandroid.Wishlish;

public class Home extends Fragment {
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
    private static final String urlData0 = "http://192.168.1.109/wmshop/tops.php";
    private static final String urlData3 = "http://192.168.1.109/wmshop/tops.php";
    int[] sampleImages = {R.drawable.image_3, R.drawable.lko, R.drawable.image_4, R.drawable.image_2, R.drawable.image_3};

    public Home() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        frameLayout = view.findViewById(R.id.toptop);
        recyMain = view.findViewById(R.id.recy_main);
        nestMain = view.findViewById(R.id.nest_main);
        relativ = view.findViewById(R.id.relativ);
        list = view.findViewById(R.id.list);
        grid = view.findViewById(R.id.grid);
        progress = view.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        grid.setVisibility(View.GONE);
        mRecycleZezo = view.findViewById(R.id.Recycle_one);
        getDataRecyZezo();
        //cu truy cap tai nguen la phai r
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
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
                    progress.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progress.setVisibility(View.GONE);
                        }
                    }, 300);
                }
            }
        });
        return view;
    }

    private void getDataRecyZezo() {
        mRecycleZezo.setHasFixedSize(true);
        mRecycleZezo.setNestedScrollingEnabled(false);
        containerZezo = new ArrayList<>();
        mRecycleZezo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        StringRequest obj = new StringRequest(Request.Method.GET, urlData0,
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
                            AdapterZezo madapter = new AdapterZezo(containerZezo, getActivity());
                            mRecycleZezo.setAdapter(madapter);

                            Log.i("ko", "" + containerZezo);
                        } catch (JSONException e1) {
                            Log.i("j", "khong");
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(obj);

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //  b_logo.setImageResource(sampleImages[position]);
        }
    };

}
