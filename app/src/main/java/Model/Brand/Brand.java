package Model.Brand;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import Model.RetrofitO;
import Model.ServiceApi;
import lucky.dev.tu.devandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Brand extends Fragment {
    TextView text;
    TabLayout tabLayout;
    int pagePosition;
    CarouselView carouselView;
    RecyclerView recycleBrand;
    int[] sampleImages = {R.drawable.image_3, R.drawable.image_2, R.drawable.image_3};

    public Brand() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand, container, false);
        recycleBrand = view.findViewById(R.id.list_brand);
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        ServiceApi serviceApi = RetrofitO.getmRetrofit().create(ServiceApi.class);
        Call<List<BrandModel>> call = serviceApi.brandList();
        call.enqueue(new Callback<List<BrandModel>>() {
            @Override
            public void onResponse(Call<List<BrandModel>> call, Response<List<BrandModel>> response) {
                recycleBrand.setNestedScrollingEnabled(false);
                recycleBrand.setHasFixedSize(true);
                BrandAdapter brandList = new BrandAdapter(getActivity(), response.body());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recycleBrand.setLayoutManager(layoutManager);
                recycleBrand.setAdapter(brandList);
            }

            @Override
            public void onFailure(Call<List<BrandModel>> call, Throwable t) {

            }
        });
        return view;

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}
