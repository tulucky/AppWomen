package lucky.dev.tu.devandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import Model.Brand.BrandAdapter;
import Model.Brand.BrandModel;
import Model.RetrofitO;
import Model.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class bnew extends AppCompatActivity {
    TextView text;
    TabLayout tabLayout;
    int pagePosition;
    CarouselView carouselView;
    RecyclerView recycleBrand;
    int[] sampleImages = {R.drawable.image_3, R.drawable.image_2, R.drawable.image_3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        recycleBrand = findViewById(R.id.list_brand);
        carouselView =findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<BrandModel>> call = serviceApi.brandList();
        call.enqueue(new Callback<List<BrandModel>>() {
            @Override
            public void onResponse(Call<List<BrandModel>> call, Response<List<BrandModel>> response) {
                recycleBrand.setNestedScrollingEnabled(false);
                recycleBrand.setHasFixedSize(true);
                BrandAdapter brandList = new BrandAdapter(bnew.this, response.body());
                LinearLayoutManager layoutManager = new LinearLayoutManager(bnew.this);
                recycleBrand.setLayoutManager(layoutManager);
                recycleBrand.setAdapter(brandList);
            }

            @Override
            public void onFailure(Call<List<BrandModel>> call, Throwable t) {

            }
        });




    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
              imageView.setImageResource(sampleImages[position]);
        }
    };
}