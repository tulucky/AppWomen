package Model.BrandProductDetal;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import Model.RetrofitO;
import lucky.dev.tu.devandroid.ImageZoom;
import lucky.dev.tu.devandroid.R;

public class ViewPagerProduct extends PagerAdapter {
    Context mcontext;
    List<String> images;
   View view;
    ImageView imageView;
    PhotoView photo;
    int id;
    int k;

    public ViewPagerProduct(Context mcontext, List<String> images, int id, int k) {
        this.mcontext = mcontext;
        this.images = images;
        this.id = id;
        this.k = k;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
         return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(mcontext);
        if (k == 0) {
            view = myLayoutInflater.inflate(R.layout.itemviewpager_detail, container, false);
            imageView = view.findViewById(R.id.slide_product);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, ImageZoom.class);
                    intent.putExtra("id", id);
                    intent.putExtra("anh", images.get(position));
                    mcontext.startActivity(intent);

                }
            });
            Glide.with(mcontext).load(RetrofitO.url + images.get(position)).into(imageView);
            container.addView(view);
        } else if (k == 1) {
            view = myLayoutInflater.inflate(R.layout.item_img_zoom, container, false);
            photo = (PhotoView) view;
            Glide.with(mcontext).load(RetrofitO.url + images.get(position)).into(photo);
            container.addView(view);
        }

        return  view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
