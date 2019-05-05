package lucky.dev.tu.devandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonyt.infiniteviewpager.InfinitePagerAdapter;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import Model.BrandProductDetal.BottomAdapImage;
import Model.BrandProductDetal.OrderP;
import Model.BrandProductDetal.ViewPagerProduct;
import Model.RetrofitO;
import Model.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageZoom extends AppCompatActivity {
    PhotoView zoom;
    ImageView close;
    List<String> data;
    TextView number;
    int pagePosition;
    com.antonyt.infiniteviewpager.InfiniteViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_zoom);
        zoom = findViewById(R.id.zoom);
        close = findViewById(R.id.close);
        viewPager = findViewById(R.id.view_pager_zoom);
        number = findViewById(R.id.text_number);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 2);
        String image = intent.getStringExtra("anh");
        getImageProduct(id, image);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                pagePosition = viewPager.getCurrentItem() + 1;
                number.setText("" + pagePosition + "/" + data.size());
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void getImageProduct(final int id, final String image) {
        ServiceApi service = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<String>> call = service.getColor(id);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                //tra ve mang[] number =>ok
                data = response.body();
                Log.i("pd", " " + data);
                if (data.contains(image)) {
                    data.remove(data.indexOf(image));
                }
                data.add(0, image);
                ViewPagerProduct adapter = new ViewPagerProduct(ImageZoom.this, data, id, 1);
                PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);
                viewPager.setAdapter(wrappedAdapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }

}
