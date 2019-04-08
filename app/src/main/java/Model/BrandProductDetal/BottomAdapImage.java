package Model.BrandProductDetal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import Model.RetrofitO;
import lucky.dev.tu.devandroid.R;

public class BottomAdapImage extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<String> listImageColor;
    ImageView imagebotsheet;
    OrderP orderP;

    public BottomAdapImage(Context mcontext, List<String> listImageColor, ImageView imagesheet, OrderP order) {
        this.mcontext = mcontext;
        this.listImageColor = listImageColor;
        this.imagebotsheet = imagesheet;
        this.orderP = order;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.bottomsheet_image, viewGroup, false);
        return new ImageHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ImageHolder holder = (ImageHolder) viewHolder;
        Glide.with(mcontext)
                .load(RetrofitO.url + listImageColor.get(i))
                .into(holder.imageColor);
        holder.imageColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mcontext).load(RetrofitO.url + listImageColor.get(i))
                        .into(imagebotsheet);
                orderP.setImagebag(listImageColor.get(i));

            }
        });

    }

    @Override
    public int getItemCount() {
        return listImageColor.size();
    }

    private class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imageColor;

        public ImageHolder(View view) {
            super(view);
            imageColor = view.findViewById(R.id.image_color);
        }
    }
}
