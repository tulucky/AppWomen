package Model.BrandProductDetal;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import lucky.dev.tu.devandroid.R;

public class ViewPagerProduct extends PagerAdapter {
    Context mcontext;
   int[]  list;
   View view;
    ImageView imageView;

    public ViewPagerProduct(Context mcontext, int[] list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
         return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(mcontext);
        view = myLayoutInflater.inflate(R.layout.itemviewpager_detail,container,false);
      imageView =view.findViewById(R.id.slide_product);
      imageView.setImageResource(list[position]);
        container.addView(view);
        return  view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
