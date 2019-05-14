package lucky.dev.tu.devandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.GridAdapter;
import Model.BrandProductDetal.AdapterInfor;
import Model.BrandProductDetal.BottomAdapImage;
import Model.BrandProductDetal.BottomAdapSize;
import Model.BrandProductDetal.OrderP;
import Model.BrandProductDetal.ViewPagerProduct;
import Model.MySingleton;
import Model.Product;
import Model.RetrofitO;
import Model.ServiceApi;
import Model.SoLuong;
import retrofit2.Call;
import retrofit2.Callback;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ProductDetail extends AppCompatActivity {
    TextView text;
    int pagePosition;
    ViewPager viewPager;
    RecyclerView content;
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
    ImageView plus;
    TextView amount;
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
    ImageView homed;
    ImageView loved;
    ImageView bagd;
    String sizeBag;
    ImageView cancel;
    ImageView toBag;
    int addNumber;
    int quantity = 1;
    int test = 0;
    TextView count;
    List<OrderP> temp;
    FloatingActionButton fab;
    ConstraintLayout backtopb;
    android.support.design.widget.AppBarLayout appbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private static final String urlData0 = "https://shoplady668.000webhostapp.com//allproducts.php";
    /*  private static final String urlData0 = "http://192.168.1.108/wmshop/allproducts.php";*/
    List<String> daTa;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share1:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, " http://www.google.com");
                intent.setType("image/jpeg");
                startActivity(Intent.createChooser(intent, "share"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_detail);
        viewPager =findViewById(R.id.view_pager_product);
        text = findViewById(R.id.text_page);
        recInfo =findViewById(R.id.danhsachinfor);
        scrollDetail = findViewById(R.id.scroll_dital);
        contrains1 = findViewById(R.id.contrains1);
        detalBottom = findViewById(R.id.detail_bottom);
        nameB = findViewById(R.id.name_brand);
        aProductDes = findViewById(R.id.aproduct_des);
        aProductName = findViewById(R.id.aproduct_name);
        originPrice = findViewById(R.id.origin_price);
        sale = findViewById(R.id.sale);
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
        plus = findViewById(R.id.plus);
        amount = findViewById(R.id.amount);
        recColor = findViewById(R.id.rec_color);
        sizeRec = findViewById(R.id.size_rec);
        sizeDes = findViewById(R.id.size_des);
        content = findViewById(R.id.content);
        homed = findViewById(R.id.homed);
        loved = findViewById(R.id.loved);
        bagd = findViewById(R.id.bagd);
        cancel = findViewById(R.id.cancel_d);
        toBag = findViewById(R.id.to_bag);
        count = findViewById(R.id.count);
        count.setVisibility(GONE);
        fab = findViewById(R.id.fab);
        appbar = findViewById(R.id.app_bar_layout);
        fab.hide();
        backtopb = findViewById(R.id.backtopb);
        SharedPreferences sharedPref = ProductDetail.this.getSharedPreferences("Accout"
                , Context.MODE_PRIVATE);
        final String name = sharedPref.getString("idName", "khong");
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<SoLuong>> call = serviceApi.getSoLuong(name);
        call.enqueue(new Callback<List<SoLuong>>() {
            @Override
            public void onResponse(Call<List<SoLuong>> call, retrofit2.Response<List<SoLuong>> response) {
                if (response.body().get(0).getSoLuong() == 0) {
                    count.setVisibility(GONE);
                } else {
                    count.setVisibility(VISIBLE);
                    count.setText("" + response.body().get(0).getSoLuong());
                }
            }

            @Override
            public void onFailure(Call<List<SoLuong>> call, Throwable t) {

            }
        });
        backtopb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toBag.setVisibility(GONE);
        sizeDes.setVisibility(GONE);
        bottomsheet.setVisibility(GONE);
        final SoLuong soLuong = new SoLuong();
        aProduct = new ArrayList<>();
        daTa = new ArrayList<>();
       /* findViewById(R.id.share1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "share"));
            }
        });*/
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String image = intent.getStringExtra("image");
        final OrderP orderP = new OrderP();
        //luu lai su lua chon cua user
        orderP.setIdProductb(id);
        orderP.setNumber(quantity);
        getaProduct(id, soLuong);
        getImageProduct(id, image, orderP);
        getImageSize(id, orderP);
        Log.i("k", " " + sizeBag);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                pagePosition = viewPager.getCurrentItem() + 1;
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
/*
                if (!scrollDetail.canScrollVertically(1)) {
                    Log.i("ss", "llol ");
                    content.setPadding(0, 0, 0, 112);
                }*/
                if (i1 > 1500) {
                    fab.show();
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            scrollDetail.fullScroll(View.FOCUS_UP);
                            scrollDetail.scrollTo(0, 0);
                            appbar.setExpanded(true);
                        }
                    });
                } else {
                    fab.hide();
                }


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
        inFor.add("Về Kích Thước");
        inFor.add("Thông Tin Giao Hàng");
        AdapterInfor adapterInfor = new AdapterInfor(this,inFor);
        recInfo.setNestedScrollingEnabled(false);
        recInfo.setLayoutManager(new LinearLayoutManager(this));
        recInfo.setAdapter(adapterInfor);
        detalBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = ProductDetail.this.getSharedPreferences("Accout"
                        , Context.MODE_PRIVATE);
                String name = sharedPref.getString("idName", "khong");
                if (name.equals("khong")) {
                    Intent intent = new Intent(ProductDetail.this, Login.class);
                    intent.putExtra("ide", 1);
                    startActivity(intent);
                   /* SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("idName");
                    editor.apply();
                    //doan xoa shared*/
                } else {
                    Animation animation = AnimationUtils.loadAnimation(ProductDetail.this, R.anim.test);
                    toolbar1.setVisibility(GONE);
                    collapsingToolbarLayout.setVisibility(GONE);
                    //de no ko bat su kien
                    bottomsheet.startAnimation(animation);
                    bottomsheet.setVisibility(VISIBLE);
                    ServiceApi getOrders = RetrofitO.getmRetrofit().create(ServiceApi.class);
                    Call<List<OrderP>> callOrder = getOrders.getListOrder(name);
                    callOrder.enqueue(new Callback<List<OrderP>>() {
                        @Override
                        public void onResponse(Call<List<OrderP>> call, retrofit2.Response<List<OrderP>> response) {
                            //chua co gi response is empty
                            Log.i("hh", " " + response.body());
                            temp = response.body();
                        }

                        @Override
                        public void onFailure(Call<List<OrderP>> call, Throwable t) {
                            Log.i("hh", " " + t.getMessage());

                        }
                    });
                }
            }
        });
        bottomsheet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
                //the listener has consumed the event so that parent dont have it
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar1.setVisibility(VISIBLE);
                collapsingToolbarLayout.setVisibility(VISIBLE);
                bottomsheet.setVisibility(GONE);
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
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 1) {
                    amount.setText(" " + quantity);
                    orderP.setNumber(quantity);
                } else {
                    quantity--;
                    amount.setText(" " + quantity);
                    orderP.setNumber(quantity);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                amount.setText(" " + quantity);
                orderP.setNumber(quantity);
            }
        });
        bootom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = ProductDetail.this.getSharedPreferences("Accout", Context.MODE_PRIVATE);
                final String name = sharedPreferences.getString("idName", "khong");
                //kiem tra dau vao truoc khi add nhu id,image,size de update number
                switch (test) {
                    case 0:
                        int m = 0;
                        if (temp == null) {

                        } else {
                            for (int i = 0; i < temp.size(); i++) {
                                /* Toast.makeText(ProductDetail.this, "nna nanh", Toast.LENGTH_LONG).show();*/
                                if (temp.get(i).getIdProductb() == orderP.getIdProductb() && temp.get(i).getImagebag()
                                        .equals(orderP.getImagebag()) && temp.get(i).getSizebag().equals(orderP.getSizebag())) {
                                    Log.i("pl", " davao day" + temp.get(i).getId());
                                    /*  Toast.makeText(ProductDetail.this, "tao ra", Toast.LENGTH_LONG).show();*/
                                    test = 2;
                                    m = i;
                                    addNumber = temp.get(i).getNumber() + orderP.getNumber();
                                    ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                                    Call<Void> call = serviceApi.upNumberPrice(temp.get(i).getId(), addNumber);
                                    call.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                                            Log.i("mm", "onResponse: " + response);
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                            Log.i("mm", "onFailure: " + t.getMessage());

                                        }
                                    });
                                }
                            }
                            if (test == 2) {
                                final KProgressHUD kProgressHUD = KProgressHUD.create(ProductDetail.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setCancellable(true)
                                        .setBackgroundColor(Color.GRAY)
                                        .setAnimationSpeed(2)
                                        .setSize(100, 100)
                                        .setDimAmount(0.5f)
                                        .show();
                                Glide.with(ProductDetail.this).load(RetrofitO.url + orderP.getImagebag())
                                        .into(toBag);
                                final int finalM = m;
                                ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                                Call<List<SoLuong>> call = serviceApi.getSoLuong(name);
                                //trean da up to db
                                call.enqueue(new Callback<List<SoLuong>>() {
                                    @Override
                                    public void onResponse(Call<List<SoLuong>> call, final retrofit2.Response<List<SoLuong>> response) {
                                        kProgressHUD.dismiss();
                                        if (temp.get(finalM).getNumber() == response.body().get(0).getSoLuong()) {
                                            Toast.makeText(ProductDetail.this, "Có một lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                        } else {
                                            toBag.setVisibility(VISIBLE);
                                            Animation animation = AnimationUtils.loadAnimation(ProductDetail.this, R.anim.to_bag);
                                            toBag.startAnimation(animation);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    toBag.setVisibility(GONE);
                                                    count.setText("" + response.body().get(0).getSoLuong());
                                                    Animation animation = AnimationUtils.loadAnimation(ProductDetail.this, R.anim.count);
                                                    count.startAnimation(animation);
                                                }
                                            }, 1200);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<List<SoLuong>> call, Throwable t) {

                                    }
                                });

                                test = 0;
                                toolbar1.setVisibility(VISIBLE);
                                collapsingToolbarLayout.setVisibility(VISIBLE);
                                bottomsheet.setVisibility(GONE);
                                break;
                            }
                        }
                    case 1:
                        Log.i("an", "haha");
                        final ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                        //Toast.makeText(ProductDetail.this, "vao tao roi", Toast.LENGTH_LONG).show();
                        Toast.makeText(ProductDetail.this, " " + orderP.getIdProductb() + " " + orderP.getImagebag() + " " + orderP.getSizebag() + orderP.getNumber(), Toast.LENGTH_SHORT).show();
                        if (orderP.getImagebag() == null | orderP.getSizebag() == null) {
                            Toast.makeText(ProductDetail.this, "Vui lòng chọn màu sắc và kích thước !", Toast.LENGTH_SHORT).show();
                        } else {
                            final KProgressHUD kProgressHUD = KProgressHUD.create(ProductDetail.this)
                                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setCancellable(true)
                                    .setBackgroundColor(Color.GRAY)
                                    .setAnimationSpeed(2)
                                    .setSize(100, 100)
                                    .setDimAmount(0.5f)
                                    .show();
                            Log.i("sa", "onClick: " + soLuong.getGia());
                            Call<Void> callInsert = serviceApi.setOrder(orderP.getIdProductb(), name, orderP.getImagebag(), orderP.getSizebag(), orderP.getNumber(), soLuong.getGia());
                            callInsert.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                                    ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
                                    Call<List<SoLuong>> call2 = serviceApi.getSoLuong(name);
                                    call2.enqueue(new Callback<List<SoLuong>>() {
                                        @Override
                                        public void onResponse(Call<List<SoLuong>> call, final retrofit2.Response<List<SoLuong>> response) {
                                            count.setVisibility(VISIBLE);
                                            //tren da up to db
                                            toBag.setVisibility(VISIBLE);
                                            kProgressHUD.dismiss();
                                            Glide.with(ProductDetail.this).load(RetrofitO.url + orderP.getImagebag())
                                                    .into(toBag);
                                            Animation animation = AnimationUtils.loadAnimation(ProductDetail.this, R.anim.to_bag);
                                            toBag.startAnimation(animation);
                                            toolbar1.setVisibility(VISIBLE);
                                            collapsingToolbarLayout.setVisibility(VISIBLE);
                                            bottomsheet.setVisibility(GONE);
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    toBag.setVisibility(GONE);
                                                    count.setText("" + response.body().get(0).getSoLuong());
                                                    Animation animation = AnimationUtils.loadAnimation(ProductDetail.this, R.anim.count);
                                                    count.startAnimation(animation);
                                                }
                                            }, 1200);
                                                }

                                                @Override
                                                public void onFailure(Call<List<SoLuong>> call, Throwable t) {

                                                }
                                            });
                                        }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.i("lop", "onResponse: kdkdkd " + t.getMessage());

                                }
                            });

                        }
                        test = 0;
                }

            }
        });

        getDataRecyZezo();
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");
        toolbar1.setNavigationIcon(R.drawable.left_chevron);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetail.this.finish();
            }
        });
        toolbar1.setVisibility(GONE);
        loved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductDetail.this, Wishlish.class);
                ProductDetail.this.startActivity(intent1);
            }
        });
        bagd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductDetail.this, MainActivity.class);
                intent1.putExtra("ide", 2);
                ProductDetail.this.startActivity(intent1);
            }
        });
        homed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductDetail.this, MainActivity.class);
                ProductDetail.this.startActivity(intent1);
            }
        });

    }

    private void getImageSize(int id, final OrderP orderP) {
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<String>> call = serviceApi.getaSize(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductDetail.this, 5);
                BottomAdapSize adapSize = new BottomAdapSize(ProductDetail.this, response.body(), orderP, sizeDes);
                sizeRec.setLayoutManager(gridLayoutManager);
                sizeRec.setAdapter(adapSize);

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });


    }

    private void getaProduct(int id, final SoLuong soLuong) {
        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = service.getaProduct(id);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                aProduct = response.body();
                Log.i("pd", " " + daTa);
                soLuong.setGia(aProduct.get(0).getPrice());
                nameB.setText(aProduct.get(0).getNameB());
                aProductDes.setText(aProduct.get(0).getTitle());
                aProductName.setText(aProduct.get(0).getName());
                originPrice.setText(aProduct.get(0).getOriginprice());
                originPrice.setPaintFlags(originPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                sale.setText(aProduct.get(0).getSale());
                priceB.setText(aProduct.get(0).getName());
                originPriceB.setText(aProduct.get(0).getOriginprice());
                originPriceB.setPaintFlags(originPriceB.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                desscriptionB.setText(aProduct.get(0).getDestile());

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    private void getImageProduct(final int id, final String image, final OrderP orderP) {
        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<String>> call = service.getColor(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
                //tra ve mang[] number =>ok
                daTa = response.body();
                Log.i("pd", " " + daTa);
                if (daTa.contains(image)) {
                    daTa.remove(daTa.indexOf(image));
                }
                daTa.add(0, image);
                Glide.with(ProductDetail.this)
                        .load(RetrofitO.url + image)
                        .into(imageBotSheet);
                ViewPagerProduct adapter = new ViewPagerProduct(ProductDetail.this, daTa, id, 0);
                PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);
                viewPager.setAdapter(wrappedAdapter);
                BottomAdapImage adapImage = new BottomAdapImage(ProductDetail.this, daTa, imageBotSheet, orderP);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductDetail.this, 5);
                recColor.setLayoutManager(gridLayoutManager);
                recColor.setAdapter(adapImage);

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }

    private void getDataRecyZezo() {
        content.setHasFixedSize(true);
        content.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        content.setLayoutManager(new GridLayoutManager(this, 2));
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
                            GridAdapter madapter = new GridAdapter(ProductDetail.this, list);
                            content.setAdapter(madapter);

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