package lucky.dev.tu.devandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.AdapterProduct;
import Model.AdapterZezo;
import Model.BrandProductDetal.AdapterInfor;
import Model.BrandProductDetal.BottomAdapImage;
import Model.BrandProductDetal.BottomAdapSize;
import Model.BrandProductDetal.DetailFrag;
import Model.BrandProductDetal.ViewPagerProduct;
import Model.Itemthree;
import Model.MySingleton;
import Model.Product;
import Model.RetrofitO;
import Model.Service;
import Model.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;

import android.view.MotionEvent;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ProductDetail extends AppCompatActivity {
    TextView text;
    int pagePosition;
    ViewPager viewPager;
    RecyclerView recyclerView;
    RecyclerView recInfo;
    List<Product> list;
    List<String> inFor;
    List<Product> aProduct;
    NestedScrollView scrollDetail;
    ConstraintLayout contrains1;
    TextView detalBottom;
    ConstraintLayout bottomsheet;
    ImageView backgroun;
   ImageView sub;
    TextView bootom;
    TextView nameB;
    TextView aProductDes;
    TextView aProductName;
    TextView originPrice;
    TextView sale;
    TextView priceB;
    TextView originPriceB;
    TextView desscriptionB;
    ImageView imageBotSheet;
    RecyclerView recColor;
    RecyclerView sizeRec;
    Toolbar toolbar;
    TextView sizeDes;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private static final String urlData0 = "http://192.168.1.24/wmshop/tops.php";
    List<String> daTa;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_detail);
        viewPager =findViewById(R.id.view_pager_product);
         text=findViewById(R.id.text_page);
        recInfo =findViewById(R.id.danhsachinfor);
        scrollDetail = findViewById(R.id.scroll_dital);
        contrains1 = findViewById(R.id.contrains1);
        detalBottom = findViewById(R.id.detail_bottom);
        nameB = findViewById(R.id.name_brand);
        aProductDes = findViewById(R.id.aproduct_des);
        aProductName = findViewById(R.id.aproduct_name);
        originPrice = findViewById(R.id.origin_price);
        sale = findViewById(R.id.sale);
        toolbar = findViewById(R.id.toolbar);
        priceB = findViewById(R.id.price_b);
        originPriceB = findViewById(R.id.origin_price_b);
        desscriptionB = findViewById(R.id.description_b);
        imageBotSheet = findViewById(R.id.image_botsheet);
        final Toolbar toolbar1 = findViewById(R.id.toolbar);
        bottomsheet = findViewById(R.id.boottom_sheet);
        bootom = findViewById(R.id.bottom);
        backgroun = findViewById(R.id.backgroun);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        sub = findViewById(R.id.sub);
        recColor = findViewById(R.id.rec_color);
        sizeRec = findViewById(R.id.size_rec);
        sizeDes = findViewById(R.id.size_des);
        sizeDes.setVisibility(GONE);
        bottomsheet.setVisibility(GONE);
        aProduct = new ArrayList<>();
        daTa = new ArrayList<>();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String image = intent.getStringExtra("image");
        Log.i("id", " " + id);
        getaProduct(id);
        getImageProduct(id, image);
        getImageSize(id);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
              pagePosition=viewPager.getCurrentItem()+1;
                text.setText("" + pagePosition + "/" + daTa.size());

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
      /*  collapsingToolbarLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_OUTSIDE)
            }
        });*/
        scrollDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                Log.i("ss"," "+i1);

                if(i1>112){
                    toolbar1.setVisibility(VISIBLE);
                    //khi nesdscroll bat dau dc cuon
                  //  Current vertical scroll origin.
                    //diem bat dau de cuon vertical hien tai in pixcel
                }
                else toolbar1.setVisibility(GONE);

                Log.i("as", ""+ contrains1.getTop());

            }
        });
        inFor = new ArrayList<>();
        inFor.add("Chọn Màu Và Kích Thước");
        inFor.add("Miêu Tả");
        inFor.add("Hiểu Về Kích Thước");
        inFor.add("Thông Tin Giao Hàng");
        AdapterInfor adapterInfor = new AdapterInfor(this,inFor);
        recInfo.setNestedScrollingEnabled(false);
        recInfo.setLayoutManager(new LinearLayoutManager(this));
        recInfo.setAdapter(adapterInfor);
        detalBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(ProductDetail.this,R.anim.test);
                toolbar1.setVisibility(GONE);
                collapsingToolbarLayout.setVisibility(GONE);
                bottomsheet.startAnimation(animation);
                bottomsheet.setVisibility(VISIBLE);

            }
        });
        bottomsheet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
                //the listener has consumed the event so that parent dont have it
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetail.this,"jjjjj",Toast.LENGTH_LONG).show();
            }
        });
        backgroun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar1.setVisibility(VISIBLE);
                collapsingToolbarLayout.setVisibility(VISIBLE);
                bottomsheet.setVisibility(GONE);

            }
        });
        bootom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetail.this, "add zozoz", Toast.LENGTH_SHORT).show();
            }

        });
 getDataRecyZezo();
  setSupportActionBar(toolbar1);
  getSupportActionBar().setTitle("");
 toolbar1.setNavigationIcon(R.drawable.left_chevron);
toolbar1.setVisibility(GONE);



    }

    private void getImageSize(int id) {
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<String>> call = serviceApi.getaSize(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductDetail.this, 10);
                BottomAdapSize adapSize = new BottomAdapSize(ProductDetail.this, response.body());
                sizeRec.setLayoutManager(gridLayoutManager);
                sizeRec.setAdapter(adapSize);

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });


    }

    private void getaProduct(int id) {
        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = service.getaProduct(id);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                aProduct = response.body();
                Log.i("pd", " " + daTa);
                nameB.setText(aProduct.get(0).getNameB());
                aProductDes.setText(aProduct.get(0).getTitle());
                aProductName.setText(aProduct.get(0).getName());
                originPrice.setText(aProduct.get(0).getOriginprice());
                sale.setText(aProduct.get(0).getSale());
                priceB.setText(aProduct.get(0).getName());
                originPriceB.setText(aProduct.get(0).getOriginprice());
                desscriptionB.setText(aProduct.get(0).getDestile());

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    private void getImageProduct(int id, final String image) {
        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<String>> call = service.getColor(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                daTa = response.body();
                Log.i("pd", " " + daTa);
                if (daTa.contains(image)) {
                    daTa.remove(daTa.indexOf(image));
                }
                daTa.add(0, image);
                ViewPagerProduct adapter = new ViewPagerProduct(ProductDetail.this, daTa);
                PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);
                viewPager.setAdapter(wrappedAdapter);
                BottomAdapImage adapImage = new BottomAdapImage(ProductDetail.this, daTa);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductDetail.this, 5);
                recColor.setLayoutManager(gridLayoutManager);
                recColor.setAdapter(adapImage);
                Glide.with(ProductDetail.this)
                        .load(RetrofitO.url + image)
                        .into(imageBotSheet);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }

    private void getDataRecyZezo() {
        recyclerView = findViewById(R.id.content);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
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
                                list.add(new Product(a.getInt("id"), a.getString("image"),
                                        a.getString("name"),
                                        a.getString("originprice"),
                                        a.getString("sale"))
                                );
                            }
                            AdapterProduct madapter = new AdapterProduct(ProductDetail.this,list);
                            recyclerView.setAdapter(madapter);

                            Log.i("ko", "" + list);
                        }
                        catch (JSONException e1) {
                            Log.i("j", "khong");
                        }}}
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        MySingleton.getInstance(ProductDetail.this).addToRequestQueue(obj);

    }


}
