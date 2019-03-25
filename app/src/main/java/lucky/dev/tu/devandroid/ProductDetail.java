package lucky.dev.tu.devandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.AdapterProduct;
import Model.AdapterZezo;
import Model.BrandProductDetal.AdapterInfor;
import Model.BrandProductDetal.DetailFrag;
import Model.BrandProductDetal.ViewPagerProduct;
import Model.Itemthree;
import Model.MySingleton;
import Model.Product;

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
    NestedScrollView scrollDetail;
    ConstraintLayout contrains1;
    TextView detalBottom;
    ConstraintLayout bottomsheet;
    ImageView backgroun;
   ImageView sub;
    private static final String urlData0 ="http://192.168.0.100/ted/ka.php";
    int[] daTa = {R.drawable.vay1,R.drawable.android_apple_fight,R.drawable.lko  };

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
        final Toolbar toolbar1 = findViewById(R.id.toolbar);
        bottomsheet = findViewById(R.id.boottom_sheet);
        backgroun = findViewById(R.id.cancel_d);
        sub = findViewById(R.id.sub);
        bottomsheet.setVisibility(GONE);
        ViewPagerProduct adapter = new ViewPagerProduct(this,daTa);
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);
        viewPager.setAdapter(wrappedAdapter);
        bottomsheet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
              pagePosition=viewPager.getCurrentItem()+1;
               text.setText(""+pagePosition+"/"+daTa.length);

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
                bottomsheet.startAnimation(animation);
                bottomsheet.setVisibility(VISIBLE);
                scrollDetail.setVerticalScrollBarEnabled(false);
                scrollDetail.setNestedScrollingEnabled(false);

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
                bottomsheet.setVisibility(GONE);

            }
        });
 getDataRecyZezo();
  setSupportActionBar(toolbar1);
  getSupportActionBar().setTitle("");
 toolbar1.setNavigationIcon(R.drawable.left_chevron);
toolbar1.setVisibility(GONE);



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
                                list.add(new Product(a.getString("Anh"),
                                        a.getString("Price"),
                                        a.getString("GiaGoc"),
                                        a.getString("PhanTram"))
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
