package lucky.dev.tu.devandroid;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Model.AdapterProduct;
import Model.Product;
import Model.ProductBrand.AdapterMenu;
import Model.RetrofitO;
import Model.ServiceApi;
import me.didik.component.StickyNestedScrollView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducBrandtActivity extends AppCompatActivity {
    FrameLayout theLoai;
    RecyclerView recyclerView;
    RecyclerView product;
    List<String> mlist;
    StickyNestedScrollView mscroll;
    ImageView iconTheLoai;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_brand);
       theLoai = findViewById(R.id.theloai);
       recyclerView= findViewById(R.id.menu_list);
       mscroll=findViewById(R.id.stickyone);
       iconTheLoai=findViewById(R.id.icon_theloai);
       mlist= new ArrayList<>();
       mlist.add("Dress");
       mlist.add("Tops");
       mlist.add("Bottoms");
       int a= theLoai.getTop();
       Log.i("mn",""+a);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        AdapterMenu adapterMenu= new AdapterMenu(this,mlist,recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterMenu);
       recyclerView.setVisibility(View.GONE);
       theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(recyclerView.getVisibility()==View.GONE){
                   int s= mscroll.getHeight();
                   mscroll.smoothScrollTo(0,mscroll.getHeight()+recyclerView.getTop());
                   Log.i("s",""+s+" "+mscroll.getHeight()+recyclerView.getTop());
                   //ta phai lay toa do cua nestedscroll
                   recyclerView.setVisibility(View.VISIBLE);
                   iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp2);
               }
               else
               {
                   iconTheLoai.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                   recyclerView.setVisibility(View.GONE);}
            }
        });
         mscroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
           @Override
           public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
               Log.i("b",""+i1);
               int heiht = mscroll.getHeight()+recyclerView.getTop();
               Log.i("c",""+heiht);
               if(i1-200<heiht&&heiht<i1+200){
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
       /* final PopupMenu popup = new PopupMenu(this,theLoai);
        popup.getMenuInflater().inflate(R.menu.theloai_product, popup.getMenu());
        theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.show();
            }
        });*/
        ServiceApi mService = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<Product>> call = mService.getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                showContent(response.body());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });


    }

    private void showContent(List<Product> body) {
        product= findViewById(R.id.product_rec);
        product.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        product.setLayoutManager(gridLayoutManager);
        AdapterProduct adapter = new AdapterProduct(this,body);
        product.setAdapter(adapter);
        Log.i("m",""+mscroll.getHeight());

    }
}
