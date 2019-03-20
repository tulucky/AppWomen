package lucky.dev.tu.devandroid;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Model.AdapterProduct;
import Model.Product;
import Model.ProductBrand.AdapterMenu;

import Model.ProductBrand.MenuList;
import Model.Service;
import me.didik.component.StickyNestedScrollView;


public class ProducBrandtActivity extends AppCompatActivity {
    FrameLayout theLoai;
    FrameLayout sapxep;
    RecyclerView recyclerView;
    RecyclerView product;
    List<MenuList> catlist;
    List<MenuList>sortList;
    StickyNestedScrollView mscroll;
    ImageView iconTheLoai;
    ConstraintLayout menu;
    ImageView iconSort;
    LinearLayoutManager linearLayoutManager;

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
       int a= theLoai.getTop();
        catlist= new ArrayList<>();
        catlist.add(new MenuList("Dress", Color.BLACK,0));
        catlist.add(new MenuList("Tops", Color.BLACK,0));
        catlist.add(new MenuList("Bottoms", Color.BLACK,0));
        sortList= new ArrayList<>();
        sortList.add(new MenuList("Tang dan", Color.BLACK,0));
        sortList.add(new MenuList("Giamdan", Color.BLACK,0));
        sortList.add(new MenuList("Bottoms", Color.BLACK,0));
        recyclerView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setVisibility(View.GONE);
       Log.i("mn",""+a);
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

       /*  final PopupMenu popup = new PopupMenu(this,theLoai);
        popup.getMenuInflater().inflate(R.menu.theloai_product, popup.getMenu());
        theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();
            }
        });*/
       Service service= new Service(this,product);
       service.Request(0);

    }

    private void Sort() {
        AdapterMenu adapterMenu= new AdapterMenu(this,sortList,recyclerView,iconSort);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMenu);
        if(recyclerView.getVisibility()==View.GONE){
            int s= menu.getTop();
            mscroll.smoothScrollTo(0,menu.getTop());
            Log.i("s",""+s+" "+mscroll.getHeight()+recyclerView.getTop());
            recyclerView.setVisibility(View.VISIBLE);
            iconSort.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
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
        AdapterMenu adapterMenu= new AdapterMenu(this,catlist,recyclerView,iconTheLoai);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMenu);
        if(recyclerView.getVisibility()==View.GONE){
            int s= menu.getTop();
            mscroll.smoothScrollTo(0,menu.getTop());
            Log.i("s",""+s+" "+mscroll.getHeight()+recyclerView.getTop());
            recyclerView.setVisibility(View.VISIBLE);
            iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
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
