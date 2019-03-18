package lucky.dev.tu.devandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.antonyt.infiniteviewpager.InfinitePagerAdapter;

import Model.BrandProductDetal.ViewPagerProduct;

public class ProductDetail extends AppCompatActivity {
    TextView text;
    int pagePosition;
    ViewPager viewPager;
    int[] daTa = {R.drawable.image_1,R.drawable.image_5,R.drawable.image_3  };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_detail);
        viewPager =findViewById(R.id.view_pager_product);
        text=findViewById(R.id.text_page);
        ViewPagerProduct adapter = new ViewPagerProduct(this,daTa);
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);
        viewPager.setAdapter(wrappedAdapter);

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








    }
}
