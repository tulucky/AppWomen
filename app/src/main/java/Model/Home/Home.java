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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
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
import Model.RetrofitO;
import Model.ServiceApi;
import Model.Slide;
import lucky.dev.tu.devandroid.MainActivity;
import lucky.dev.tu.devandroid.R;
import lucky.dev.tu.devandroid.Wishlish;
import retrofit2.Call;
import retrofit2.Callback;

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
    ImageView news;
    android.support.design.widget.FloatingActionButton fab;

    public NestedScrollView nestMain;
    private static final String urlData0 = "http://192.168.1.109/wmshop/tops.php";
    int[] sampleImages = {R.drawable.image_3, R.drawable.image_4, R.drawable.image_2, R.drawable.image_3};

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
        fab = view.findViewById(R.id.fab);
        news = view.findViewById(R.id.news);
        news.setImageResource(R.drawable.slide2);
        fab.hide();
        final ProgressBar progressBar = view.findViewById(R.id.spin_kit);
        Sprite foldingCube = new FoldingCube();
        progressBar.setIndeterminateDrawable(foldingCube);
        progressBar.setVisibility(View.GONE);
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
                list.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        grid.setVisibility(View.VISIBLE);
                        list.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                ListProduct listProduct = new ListProduct();
                                transaction.replace(R.id.recy_main, listProduct);
                                transaction.commit();
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 1500);
                    }
                });
                grid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.setVisibility(View.VISIBLE);
                        grid.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                                GridProduct gridProduct = new GridProduct();
                                transaction.replace(R.id.recy_main, gridProduct);
                                transaction.commit();
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 1500);
                    }
                });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestMain.smoothScrollTo(0, 0);
            }
        });
        nestMain.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (i1 > 2500) {
                    fab.show();
                } else {
                    fab.hide();
                }
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
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "Clicked item: " + position, Toast.LENGTH_SHORT).show();
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
        public void setImageForPosition(final int position, final ImageView imageView) {
            ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
            Call<List<Slide>> call = serviceApi.getSlide();
            call.enqueue(new Callback<List<Slide>>() {
                @Override
                public void onResponse(Call<List<Slide>> call, retrofit2.Response<List<Slide>> response) {
                    Log.i("kk", "" + response.body());
                    switch (position) {
                        case 0:
                            Glide.with(getActivity()).load(RetrofitO.url + response.body().get(position).getImage()).into(imageView);
                            break;
                        case 1:
                            Glide.with(getActivity()).load(RetrofitO.url + response.body().get(position).getImage()).into(imageView);
                            break;
                        case 2:
                            Glide.with(getActivity()).load(RetrofitO.url + response.body().get(position).getImage()).into(imageView);
                            break;
                        case 3:
                            Glide.with(getActivity()).load(RetrofitO.url + response.body().get(position).getImage()).into(imageView);
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<Slide>> call, Throwable t) {
                    Log.i("kk", "" + t.getMessage());
                }
            });
        }
    };

}
