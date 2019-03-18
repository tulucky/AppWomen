package lucky.dev.tu.devandroid;

import android.graphics.Point;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
import Model.AdapterProduct;
import Model.Itemthree;
import Model.MySingleton;
import Model.Product;

public class MainActivity extends AppCompatActivity {
 List<Product> containerThree;
 List<Itemthree> containerZezo;
    CarouselView carouselView;
    RecyclerView mRecycleThree;
    RecyclerView mRecycleZezo;
    FrameLayout frameLayout;
    private static final String urlData0 ="http://192.168.0.101/ted/ka.php";
    private static final String urlData3 ="http://192.168.0.101/ted/ka.php";

   int[] sampleImages = {R.drawable.image_3, R.drawable.lko, R.drawable.image_4, R.drawable.image_2, R.drawable.image_3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.toptop);
        getDataRecyZezo();
        getDataRecyThree();
        //cu truy cap tai nguen la phai r
        carouselView = (CarouselView) findViewById(R.id.carouselView);
      carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

    }

    private void getDataRecyZezo() {
        mRecycleZezo = findViewById(R.id.Recycle_Zezo);
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
                                containerZezo.add(new Itemthree(a.getString("Anh"),
                                        a.getString("Price"),
                                        a.getString("GiaGoc"),
                                        a.getString("PhanTram"))
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


    private void getDataRecyThree() {
        mRecycleThree = findViewById(R.id.Recycle_Three);
        mRecycleThree.setHasFixedSize(true);
        mRecycleThree.setNestedScrollingEnabled(false);
        containerThree = new ArrayList<>();
        mRecycleThree.setLayoutManager(new GridLayoutManager(this,2));
        StringRequest obj = new StringRequest(Request.Method.GET,urlData3,
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
                                containerThree.add(new Product(a.getString("Anh"),
                                        a.getString("Price"),
                                        a.getString("GiaGoc"),
                                        a.getString("PhanTram"))
                                );
                            }
                            AdapterProduct madapter = new AdapterProduct(MainActivity.this,containerThree);
                            mRecycleThree.setAdapter(madapter);
                            Display display = getWindowManager().getDefaultDisplay();
                            Point size = new Point();
                            display.getSize(size);
                            int width = size.x;
                            int height = size.y;
                            Log.e("Width", "" + width);
                            Log.e("height", "" + height);

                        Log.i("ko", "" + containerThree);
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
         //   imageView.setImageResource(sampleImages[position]);
        }
    };

}
