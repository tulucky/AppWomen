package Model.Brand;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lucky.dev.tu.devandroid.R;

public class BrandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<BrandModel> listBrand;
    View view;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater myLayouInflater = LayoutInflater.from(mcontext);
        view = myLayouInflater.inflate(R.layout.item_brand,viewGroup,false);
        return new MyHolderBrand(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolderBrand holder = (MyHolderBrand)viewHolder;
        Glide.with(mcontext)
                .load(listBrand.get(i).getImageBrand())
                .into(holder.imageBrand);
        Glide.with(mcontext)
                .load(listBrand.get(i).getLogo())
                .into(holder.logoBrand);
        Glide.with(mcontext)
                .load(listBrand.get(i).getItem1())
                .into(holder.item1);
        holder.price1.setText(listBrand.get(i).getPrice1());
        holder.sale1.setText(listBrand.get(i).getSale1());
        Glide.with(mcontext)
                .load(listBrand.get(i).getItem2())
                .into(holder.item2);
        holder.price1.setText(listBrand.get(i).getPrice2());
        holder.sale1.setText(listBrand.get(i).getSale2());
        Glide.with(mcontext)
                .load(listBrand.get(i).getItem3())
                .into(holder.item3);
        holder.price1.setText(listBrand.get(i).getPrice3());
        holder.sale1.setText(listBrand.get(i).getSale3());

    }

    @Override
    public int getItemCount() {
        return listBrand.size();
    }

    private class MyHolderBrand extends RecyclerView.ViewHolder {
        ImageView imageBrand;
        ImageView logoBrand;
        ImageView item1;
        TextView price1;
        TextView sale1;
        ImageView item2;
        TextView price2;
        TextView sale2;
        ImageView item3;
        TextView price3;
        TextView sale3;
        public MyHolderBrand(View view) {
            super(view);
            imageBrand = view.findViewById(R.id.image_brand);
            logoBrand = view.findViewById(R.id.logo_brand);
            item1 = view.findViewById(R.id.item1);
            price1= view.findViewById(R.id.price1);
            sale1 = view.findViewById(R.id.sale1);
            item2 = view.findViewById(R.id.item2);
            price2= view.findViewById(R.id.price2);
            sale2 = view.findViewById(R.id.sale2);
            item3 = view.findViewById(R.id.item3);
            price3= view.findViewById(R.id.price3);
            sale3 = view.findViewById(R.id.sale3);
        }
    }
}
