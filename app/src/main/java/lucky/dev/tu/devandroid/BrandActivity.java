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

import Model.Brand.BrandAdapter;


public class BrandActivity extends AppCompatActivity {
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleBrand.setLayoutManager(layoutManager);


    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
              imageView.setImageResource(sampleImages[position]);
        }
    };
}