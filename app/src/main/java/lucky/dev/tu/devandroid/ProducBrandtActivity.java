package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Model.Product;
import Model.ProductBrand.AdapterCate;

import Model.ProductBrand.AdapterSort;
import Model.ProductBrand.Colorada;
import Model.ProductBrand.FilterText;
import Model.ProductBrand.MenuList;
import Model.ProductBrand.StateHolder;
import Model.RetrofitO;
import Model.Service;
import Model.ServiceApi;
import me.didik.component.StickyNestedScrollView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProducBrandtActivity extends AppCompatActivity {
    FrameLayout theLoai;
    FrameLayout sapxep;
    FrameLayout loc;
    RecyclerView recyclerView;
    RecyclerView product;
    List<MenuList> catlist;
    List<MenuList>sortList;
    StickyNestedScrollView mscroll;
    ImageView iconTheLoai;
    ConstraintLayout menu;
    ImageView iconSort;
    ImageView iconFilter;
    LinearLayoutManager linearLayoutManager;
    ImageView imageBrandp;
    ImageView logoBrandp;
    TextView nameBrandp;
    TextView desBrandp;
    ImageView image1p;
    TextView gia1p;
    TextView giagoc1p;
    TextView sale1p;
    ImageView image2p;
    TextView gia2p;
    TextView giagoc2p;
    TextView sale2p;
    ImageView image3p;
    TextView gia3p;
    TextView giagoc3p;
    TextView sale3p;
    ImageView backTo;
    ImageView share;
    ConstraintLayout filter;
    RecyclerView recyMua;
    RecyclerView recyColor;
    List<FilterText> season;
    List<FilterText> colors;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_brand);
        theLoai = findViewById(R.id.theloai);
        sapxep = findViewById(R.id.sort);
        recyclerView= findViewById(R.id.menu_list);
        mscroll=findViewById(R.id.stickyone);
        iconTheLoai=findViewById(R.id.icon_theloai);
        product= findViewById(R.id.product_rec);
        menu = findViewById(R.id.constraintLayout2);
        iconSort= findViewById(R.id.image_sort);
        imageBrandp = findViewById(R.id.image_brand_p);
        logoBrandp = findViewById(R.id.logo_brand_p);
        nameBrandp = findViewById(R.id.name_brand_p);
        desBrandp = findViewById(R.id.des_brand_p);
        image1p = findViewById(R.id.image1_p);
        gia1p = findViewById(R.id.gia1_p);
        giagoc1p = findViewById(R.id.giagoc1_p);
        sale1p = findViewById(R.id.sale1_p);
        image2p = findViewById(R.id.image2_p);
        gia2p = findViewById(R.id.gia2_p);
        giagoc2p = findViewById(R.id.giagoc2_p);
        sale2p = findViewById(R.id.sale2_p);
        image3p = findViewById(R.id.image3_p);
        gia3p = findViewById(R.id.gia3_p);
        giagoc3p = findViewById(R.id.giagoc3_p);
        sale3p = findViewById(R.id.sale3_p);
        backTo = findViewById(R.id.backto);
        share = findViewById(R.id.share);
        filter = findViewById(R.id.filter);
        filter.setVisibility(View.GONE);
        recyColor = findViewById(R.id.recy_color);
        loc = findViewById(R.id.filterby);
        iconFilter = findViewById(R.id.icon_filter);

        int a= theLoai.getTop();
        catlist= new ArrayList<>();
        catlist.add(new MenuList("Dress", Color.BLACK,0));
        catlist.add(new MenuList("Tops", Color.BLACK,0));
        catlist.add(new MenuList("Bottoms", Color.BLACK,0));
        sortList= new ArrayList<>();
        sortList.add(new MenuList("Giảm dần", Color.BLACK, 0));
        sortList.add(new MenuList("Tăng dần", Color.BLACK, 0));
       /* loc= new ArrayList<>();
        sortList.add(new MenuList("mau", Color.BLACK, 0));
        sortList.add(new MenuList("Giảm dần", Color.BLACK, 0));*/
        recyclerView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setVisibility(View.GONE);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String brandImage = intent.getStringExtra("brandimage");
        String logo = intent.getStringExtra("logo");
        String nameb = intent.getStringExtra("nameb");
        String desbrand = intent.getStringExtra("description");
        getaBrand(id, brandImage, logo, nameb, desbrand);
        theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconSort.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                theLoai();
            }
        });
        sapxep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                Sort();
                  /*  new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        }
                    },100);*/
            }
        });
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconSort.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                loc();
            }
        });
        backTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateHolder.reset();
                ProducBrandtActivity.this.finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "share"));
            }
        });

       /*  final PopupMenu popup = new PopupMenu(this,theLoai);
        popup.getMenuInflater().inflate(R.menu.theloai_product, popup.getMenu());
        theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();
            }
        });*/
        Service service= new Service(this,product);
        service.Request();
       /* season = new ArrayList<>();
        season.add(new FilterText("Xuân"));
        season.add(new FilterText("Hạ"));
        season.add(new FilterText("Thu"));
        season.add(new FilterText("Đông"));*/
        colors = new ArrayList<>();
        colors.add(new FilterText("White"));
        colors.add(new FilterText("Blue"));
        colors.add(new FilterText("Green"));
        colors.add(new FilterText("Black"));
        colors.add(new FilterText("Brown"));
        colors.add(new FilterText("Yallow"));
        colors.add(new FilterText("Gray"));
        colors.add(new FilterText("Pink"));

    }

    private void loc() {
       /* recyMua.setHasFixedSize(true);
        recyMua.setLayoutManager(new GridLayoutManager(this, 4));
        Muaada muaada = new Muaada(this, season);
        recyMua.setAdapter(muaada);*/
        recyColor.setLayoutManager(new GridLayoutManager(this, 4));
        Colorada colorada = new Colorada(this, colors, iconFilter, filter);
        recyColor.setAdapter(colorada);
        if (filter.getVisibility() == View.GONE) {
            int s = menu.getTop();
            mscroll.smoothScrollTo(0, menu.getTop());
            filter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            iconSort.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            iconFilter.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
        } else {
            filter.setVisibility(View.GONE);
            iconFilter.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
    }

    private void getaBrand(int id, final String brandImage, final String logo, final String nameb, final String desbrand) {
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = serviceApi.brandImages(id);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, final Response<List<Product>> response) {
                Log.i("ko", " " + response.body());
                Glide.with(ProducBrandtActivity.this)
                        .load(RetrofitO.url + brandImage)
                        .into(imageBrandp);
                Glide.with(ProducBrandtActivity.this)
                        .load(RetrofitO.url + logo)
                        .into(logoBrandp);
                nameBrandp.setText(nameb);
                desBrandp.setText(desbrand);
                Glide.with(ProducBrandtActivity.this)
                        .load(RetrofitO.url + response.body().get(0).getImage())
                        .into(image1p);
                gia1p.setText(response.body().get(0).getName());
                sale1p.setText(response.body().get(0).getSale());
                giagoc1p.setText(response.body().get(0).getOriginprice());
                giagoc1p.setPaintFlags(giagoc1p.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                //null do ko chon cot originprice trong file php
                Glide.with(ProducBrandtActivity.this)
                        .load(RetrofitO.url + response.body().get(1).getImage())
                        .into(image2p);
                gia2p.setText(response.body().get(1).getName());
                sale2p.setText(response.body().get(1).getSale());
                giagoc2p.setText(response.body().get(1).getOriginprice());
                giagoc2p.setPaintFlags(giagoc2p.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                Glide.with(ProducBrandtActivity.this)
                        .load(RetrofitO.url + response.body().get(2).getImage())
                        .into(image3p);
                gia3p.setText(response.body().get(2).getName());
                sale3p.setText(response.body().get(2).getSale());
                giagoc3p.setText(response.body().get(2).getOriginprice());
                giagoc3p.setPaintFlags(giagoc3p.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                image1p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProducBrandtActivity.this, ProductDetail.class);
                        intent.putExtra("id", response.body().get(0).getId());
                        intent.putExtra("image", response.body().get(0).getImage());
                        ProducBrandtActivity.this.startActivity(intent);
                    }
                });
                image2p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProducBrandtActivity.this, ProductDetail.class);
                        intent.putExtra("id", response.body().get(1).getId());
                        intent.putExtra("image", response.body().get(1).getImage());
                        ProducBrandtActivity.this.startActivity(intent);
                    }
                });
                image3p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProducBrandtActivity.this, ProductDetail.class);
                        intent.putExtra("id", response.body().get(2).getId());
                        intent.putExtra("image", response.body().get(2).getImage());
                        ProducBrandtActivity.this.startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void Sort() {
        AdapterSort adapterSort = new AdapterSort(this, sortList, recyclerView, iconSort);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterSort);
        if(recyclerView.getVisibility()==View.GONE){
            int s= menu.getTop();
            mscroll.smoothScrollTo(0,menu.getTop());
            Log.i("s",""+s+" "+mscroll.getHeight()+recyclerView.getTop());
            recyclerView.setVisibility(View.VISIBLE);
            iconSort.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
            filter.setVisibility(View.GONE);
            iconFilter.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
        else
        {
            iconSort.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            recyclerView.setVisibility(View.GONE);}

        mscroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (menu.getTop()-100<i1&&i1<menu.getTop()+100){

                    iconSort.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else
                {

                   iconSort.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void theLoai() {
        AdapterCate adapterCate = new AdapterCate(this, catlist, recyclerView, iconTheLoai);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCate);
        if(recyclerView.getVisibility()==View.GONE){
            int s= menu.getTop();
            mscroll.smoothScrollTo(0,menu.getTop());
            //scroll xuong 1 doan dung bang khoang cach voi vi tri con cua no
            Log.i("s",""+s+" "+mscroll.getHeight()+recyclerView.getTop());
            recyclerView.setVisibility(View.VISIBLE);
            iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
            filter.setVisibility(View.GONE);
            iconFilter.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
        else
        {
            iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            recyclerView.setVisibility(View.GONE);}

        mscroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                if (menu.getTop()-100<i1&&i1<menu.getTop()+100){

                    iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                else
                {

                    iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }


}
